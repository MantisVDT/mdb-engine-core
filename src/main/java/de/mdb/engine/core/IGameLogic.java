package de.mdb.engine.core;

public interface IGameLogic {
	
	void init() throws Exception;
	
	void input();
	
	void update();
	
	void render();
	
	void cleanup();
	
}
