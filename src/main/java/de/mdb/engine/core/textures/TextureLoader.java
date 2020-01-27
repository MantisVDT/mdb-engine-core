package de.mdb.engine.core.textures;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBImage;

import de.mdb.engine.core.logger.Debug;
import de.mdb.engine.core.util.Data;
import de.mdb.engine.core.util.Util;

public class TextureLoader {

	private TextureLoader() {
	}

	public static Texture loadTexture(String filename) {

		Texture t;

		IntBuffer w = BufferUtils.createIntBuffer(1);
		IntBuffer h = BufferUtils.createIntBuffer(1);
		IntBuffer comp = BufferUtils.createIntBuffer(1);
		ByteBuffer pixels = loadImageData(filename, w, h, comp);

		int imgWidth = w.get(0);
		int imgHeight = h.get(0);

		// Loads as RGBA and forces 4 channels
		if(pixels != null) 
			t = new Texture(pixels, imgWidth, imgHeight);
		else
			t = Texture.DEFAULT_TEXTURE;
		
		STBImage.stbi_image_free(pixels);

		return t;
	}
	
	public static CubeMap loadCubeMap(String... fileNames)
	{
		ByteBuffer[] data = Util.fillByteBufferArray(fileNames.length);
		
		IntBuffer[] w = Util.fillIntBufferArray(fileNames.length);
		IntBuffer[] h = Util.fillIntBufferArray(fileNames.length);
		IntBuffer[] comp = Util.fillIntBufferArray(fileNames.length);
		
		for(int i=0; i<fileNames.length; i++)
		{
			data[i] = loadImageData(fileNames[i], w[i], h[i], comp[i]);
			if(data[i] == null) {
				Debug.severe("Data not found");
			}
		}
		
		return new CubeMap(data, w, h);
	}

	public static ByteBuffer loadImageData(String filename, IntBuffer w, IntBuffer h, IntBuffer comp) {
		File f = new File(Data.RES_PATH + filename);

		try {
			if (!f.exists())
				throw new IOException("File not found!");
			
			if(f.getPath() == null) return null;
			ByteBuffer pixels = STBImage.stbi_load(f.getPath(), w, h, comp, 4);

			if (pixels == null)
				throw new IOException(STBImage.stbi_failure_reason() + " Could not load pixels");

			return pixels;

		} catch (IOException e) {
			Debug.severe("Could not load image " + f.getName());
			Debug.severe(e.getMessage());
		}
		return null;
	}
}
