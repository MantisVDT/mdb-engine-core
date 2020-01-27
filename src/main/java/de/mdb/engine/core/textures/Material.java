package de.mdb.engine.core.textures;

import org.joml.Vector3f;
import org.joml.Vector4f;

import de.mdb.engine.core.shader.ShaderProgram;

public class Material {
	
	public static final Color DEFAULT_COLOR = Color.WHITE;
	
	private Vector4f ambientColor;
	private Vector4f diffuseColor;
	private Vector4f specularColor;
	
	private float reflectance;
	
	private Texture texture;
	private Texture normalMap;
	
	public Material()
	{
		this(null);
	}
	
	public Material(Texture texture)
	{
		this.ambientColor = DEFAULT_COLOR.toVector4f();
		this.diffuseColor = DEFAULT_COLOR.toVector4f();
		this.specularColor = DEFAULT_COLOR.toVector4f();
		this.reflectance = 0;
		this.texture = null;
	}
	
	public Material(Vector4f ambientColor, Vector4f diffuseColor, Vector4f specularColor, float reflectance)
	{
		this(null, ambientColor, diffuseColor, specularColor, reflectance);
	}
	
	public Material(Texture texture, Vector4f ambientColor, Vector4f diffuseColor, Vector4f specularColor, float reflectance)
	{
		this.ambientColor = ambientColor;
		this.diffuseColor = diffuseColor;
		this.specularColor = specularColor;
		this.reflectance = reflectance;
		this.texture = texture;
	}
	
	public Material(Color color, float relfectance)
	{
		this(null, color.toVector4f(), color.toVector4f(), color.toVector4f(), relfectance);
	}
	
	/**
	 * Loads this material into the given Program
	 * Requires one shader to have
	 * <pre><code>
	struct Material {
    		vec3 ambient;
    		vec3 diffuse;
    		vec3 specular;
    		float reflectance;
	};

	uniform Material material
	</code></pre>
	 * 
	 * @param shader
	 */
	public void load(ShaderProgram shader)
	{
		shader.use();
		shader.setVec3("material.ambient", new Vector3f(ambientColor.x, ambientColor.y, ambientColor.z));
		shader.setVec3("material.diffuse", new Vector3f(diffuseColor.x, diffuseColor.y, diffuseColor.z));
		shader.setVec3("material.specular", new Vector3f(specularColor.x, specularColor.y, specularColor.z));
		shader.setFloat("material.reflectance", reflectance);
	}
	
	public Vector4f getAmbientColor() {
		return ambientColor;
	}

	public void setAmbientColor(Vector4f ambientColor) {
		this.ambientColor = ambientColor;
	}

	public Vector4f getDiffuseColor() {
		return diffuseColor;
	}

	public void setDiffuseColor(Vector4f diffuseColor) {
		this.diffuseColor = diffuseColor;
	}

	public Vector4f getSpecularColor() {
		return specularColor;
	}

	public void setSpecularColor(Vector4f specularColor) {
		this.specularColor = specularColor;
	}

	public float getReflectance() {
		return reflectance;
	}

	public void setReflectance(float reflectance) {
		this.reflectance = reflectance;
	}

	public Texture getTexture() {
		if(texture != null)
		{
			return texture;
		}else {
			return Texture.DEFAULT_TEXTURE;
		}
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Texture getNormalMap() {
		return normalMap;
	}

	public void setNormalMap(Texture normalMap) {
		this.normalMap = normalMap;
	}
	
	public boolean hasNormalMap()
	{
		return this.normalMap != null;
	}
	
}
