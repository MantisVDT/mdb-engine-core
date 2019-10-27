package de.mdb.engine.core.render;

import java.util.ArrayList;
import java.util.List;

import de.mdb.engine.core.model.Model;
import de.mdb.engine.core.shader.ShaderProgram;

public class ModelRenderer extends Renderer {
	
	private List<Model> models;
	
	public ModelRenderer(ShaderProgram shader)
	{
		super(shader);
		models = new ArrayList<>();
	}
	
	public void render()
	{
		for(Model m : models)
		{
			m.render(shader);
		}
	}
	
	public void addModel(Model m)
	{
		models.add(m);
	}
	
	public void removeModel(Model m)
	{
		models.remove(m);
	}
	
	public void removeModel(int index)
	{
		models.remove(index);
	}
}
