package de.mdb.engine.core.model;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.joml.Vector4f;
import org.lwjgl.PointerBuffer;
import org.lwjgl.assimp.AIColor4D;
import org.lwjgl.assimp.AIFace;
import org.lwjgl.assimp.AIMaterial;
import org.lwjgl.assimp.AIMesh;
import org.lwjgl.assimp.AIScene;
import org.lwjgl.assimp.AIString;
import org.lwjgl.assimp.AIVector3D;
import org.lwjgl.assimp.Assimp;

import de.mdb.engine.core.logger.Debug;
import de.mdb.engine.core.textures.Material;
import de.mdb.engine.core.textures.Texture;
import de.mdb.engine.core.textures.TextureCache;
import de.mdb.engine.core.util.Util;

public class OBJLoader {
	
	private OBJLoader() {}
	
	public static Model loadModel(String filePath, String texturesDir, int flags) throws Exception
	{	
		AIScene aiScene = Assimp.aiImportFile(filePath, flags);
	
		if(aiScene == null)
		{
			throw new Exception("Could not load model!");
		}
		
		List<Mesh> meshes = new ArrayList<>();
		
		List<Material> materials = new ArrayList<>();
		
		for(int i=0; i<aiScene.mNumMaterials(); i++)
		{
			AIMaterial material = AIMaterial.create(aiScene.mMaterials().get(i));
			materials.add(processMaterial(material, texturesDir));
		}
		
		int numMeshes = aiScene.mNumMeshes();
		PointerBuffer aiMeshes = aiScene.mMeshes();
		for(int i=0; i<numMeshes; i++)
		{
			AIMesh mesh = AIMesh.create(aiMeshes.get(i));
			meshes.add(processMesh(mesh, materials));
		}
		
		return new Model(meshes, materials);
	}
	
	private static Material processMaterial(AIMaterial material, String dir)
	{
		AIColor4D color = AIColor4D.create();
		
		AIString path = AIString.calloc();
		Assimp.aiGetMaterialTexture(material, Assimp.aiTextureType_DIFFUSE, 0, path, (IntBuffer)null, null, null, null, null, null);
		String textureP = path.dataString();
		Texture texture = null;
		
		if(textureP != null && textureP.length() > 0)
		{
			try {
				TextureCache textCache = TextureCache.getInstance();
				String textureFile = dir + "/" + textureP;
				textureFile = textureFile.replace("//", "/").replace("\\", "/");
				texture = textCache.getTexture(textureFile);
			}catch(Exception e){
				Debug.severe("Could not process Material: " + e.getMessage());
				e.printStackTrace();
			}
		}
		
		Vector4f ambient = new Vector4f();
		int result = Assimp.aiGetMaterialColor(material, Assimp.AI_MATKEY_COLOR_AMBIENT, Assimp.aiTextureType_NONE, 0, color);
		
		if(result == 0)
		{
			ambient = Material.DEFAULT_COLOR.toVector4f();
		}
		
		Vector4f diffuse = new Vector4f();
		result = Assimp.aiGetMaterialColor(material, Assimp.AI_MATKEY_COLOR_DIFFUSE, Assimp.aiTextureType_NONE, 0, color);
		
		if(result == 0)
		{
			diffuse = Material.DEFAULT_COLOR.toVector4f();
		}
		
		Vector4f specular = new Vector4f();
		result = Assimp.aiGetMaterialColor(material, Assimp.AI_MATKEY_COLOR_SPECULAR, Assimp.aiTextureType_NONE, 0, color);
		
		if(result == 0)
		{
			specular = Material.DEFAULT_COLOR.toVector4f();
		}
		
		return new Material(texture, ambient, diffuse, specular, 1.0f);
	}
	
	private static Mesh processMesh(AIMesh aiMesh, List<Material> materials)
	{
		float[] vertices = processVertices(aiMesh);
		float[] normals = processNormals(aiMesh);
		float[] textureCoords = processTextureCoords(aiMesh);
		int[] indices = processIndices(aiMesh);
		
		Mesh m = new Mesh(vertices, normals, textureCoords, indices);
		
		Material material;
		int materialIdx = aiMesh.mMaterialIndex();
		if(materialIdx >= 0 && materialIdx < materials.size())
		{
			material = materials.get(materialIdx);
		}else {
			material = new Material();
		}
		m.setMaterial(material);
		
		return m;
	}
	
	private static float[] processVertices(AIMesh aiMesh)
	{
		AIVector3D.Buffer aiVertices = aiMesh.mVertices();
		float[] vertices = new float[aiVertices.capacity()*3];
		
		for(int i=0; i<aiVertices.capacity(); i++)
		{
			vertices[i * 3 + 0] = aiVertices.get(i).x();
			vertices[i * 3 + 1] = aiVertices.get(i).y();
			vertices[i * 3 + 2] = aiVertices.get(i).z();
		}
		return vertices;
	}
	
	private static float[] processNormals(AIMesh aiMesh)
	{
		AIVector3D.Buffer aiNormals = aiMesh.mNormals();
		float[] normals = new float[aiNormals.capacity()*3];
		
		for(int i=0; i<aiNormals.capacity() && aiNormals != null; i++)
		{
			normals[i * 3 + 0] = aiNormals.get(i).x();
			normals[i * 3 + 1] = aiNormals.get(i).y();
			normals[i * 3 + 2] = aiNormals.get(i).z();
		}
		
		return normals;
	}
	
	private static float[] processTextureCoords(AIMesh aiMesh)
	{
		AIVector3D.Buffer aiTextures = aiMesh.mTextureCoords(0);
		int numTexCoords = aiTextures != null ? aiTextures.remaining() : 0;
		float[] textures = new float[numTexCoords*2];
		
		for(int i=0; i<numTexCoords; i++)
		{
			AIVector3D texCoord = aiTextures.get();
			textures[i * 2 + 0] = texCoord.x();
			textures[i * 2 + 1] = texCoord.y();
		}
		return textures;
	}
	
	private static int[] processIndices(AIMesh aiMesh)
	{
		int numFaces = aiMesh.mNumFaces();
		AIFace.Buffer aiFaces = aiMesh.mFaces();
		
		List<Integer> indices = new ArrayList<>();
		
		for(int i=0; i<numFaces; i++)
		{
			AIFace aiFace = aiFaces.get();
			IntBuffer buffer = aiFace.mIndices();
			while(buffer.remaining() > 0) {
				indices.add(buffer.get());
			}
		}
		return Util.convertListToIntArray(indices);
	}
	
	
}
