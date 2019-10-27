package de.mdb.engine.core.util;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.List;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

public final class Util {
	
	public static <T> T[] concat(T[] first, T[] second) {
		  T[] result = Arrays.copyOf(first, first.length + second.length);
		  System.arraycopy(second, 0, result, first.length, second.length);
		  return result;
	}
	
	public static IntBuffer[] fillIntBufferArray(int size)
	{
		IntBuffer[] buffer = new IntBuffer[size];
		for(int i=0; i<size; i++)
		{
			buffer[i] = IntBuffer.allocate(1);
		}
		return buffer;
	}
	
	public static ByteBuffer[] fillByteBufferArray(int size)
	{
		ByteBuffer[] buffer = new ByteBuffer[size];
		for(int i=0; i<size; i++)
		{
			buffer[i] = ByteBuffer.allocate(1);
		}
		return buffer;
	}
	
	public static int[] concat(int[] first, int[] second)
	{
		int[] both = new int[first.length + second.length];
		System.arraycopy(first, 0, both, 0, first.length);
		System.arraycopy(second, 0, both, first.length, second.length);
		return both;
	}
	
	/**
	 * Converts an List of Vector3f to a FloatBuffer for the use with OpenGL
	 * 
	 * @param list The list to convert
	 * @return The created FloatBuffer
	 */
	public static FloatBuffer Vector3ListToFloatBuffer(List<Vector3f> list)
	{
		FloatBuffer res = BufferUtils.createFloatBuffer(list.size() * 3);
		for(int i=0; i<list.size(); i++)
		{
			res.put(i * 3 + 0, list.get(i).x);
			res.put(i * 3 + 1, list.get(i).y);
			res.put(i * 3 + 2, list.get(i).z);
		}
		res.flip();
		return res;
	}
	
	public static float[] convertListToFloatArray(List<Float> list)
	{
		float[] ret = new float[list.size()];
		for(int i=0; i<list.size(); i++)
		{
			ret[i] = list.get(i);
		}
		return ret;
	}
	
	public static int[] convertListToIntArray(List<Integer> list)
	{
		int[] ret = new int[list.size()];
		for(int i=0; i<list.size(); i++)
		{
			ret[i] = list.get(i);
		}
		return ret;
	}

	/**
	 * Converts an List of Vector2f to a FloatBuffer for the use with OpenGL
	 * 
	 * @param list The list to convert
	 * @return The created FloatBuffer
	 */
	public static FloatBuffer Vector2ListToFloatBuffer(List<Vector2f> list)
	{
		FloatBuffer res = BufferUtils.createFloatBuffer(list.size() * 2);
		for(int i=0; i<list.size(); i++)
		{
			res.put(i * 2 + 0, list.get(i).x);
			res.put(i * 2 + 1, list.get(i).y);
		}
		res.flip();
		return res;
	}
	
	public static FloatBuffer createIndicesBuffer(List<Integer> indices)
	{
		FloatBuffer res = BufferUtils.createFloatBuffer(indices.size());
		for(int i : indices)
		{
			res.put(i);
		}
		res.flip();
		return res;
	}
	
}
