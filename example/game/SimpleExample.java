package game;

import de.mdb.engine.core.GameEngine;
import de.mdb.engine.core.IGameLogic;

public class SimpleExample implements IGameLogic{

	@Override
	public void init() throws Exception {
		
	}

	@Override
	public void input() {
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render() {
		
	}

	@Override
	public void cleanup() {
		
	}
	
	public static void main(String[] args)
	{
		GameEngine engine = new GameEngine("SimpleExample", 1280, 860, true, new SimpleExample());
		engine.start();
	}

}
