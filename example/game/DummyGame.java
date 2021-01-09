package game;

import static org.lwjgl.glfw.GLFW.*;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.assimp.Assimp;
import org.lwjgl.nuklear.NkColorf;

import de.mdb.engine.core.GameEngine;
import de.mdb.engine.core.IGameLogic;
import de.mdb.engine.core.camera.FirstPersonCamera;
import de.mdb.engine.core.display.Display;
import de.mdb.engine.core.event.Event;
import de.mdb.engine.core.event.EventListener;
import de.mdb.engine.core.event.EventManager;
import de.mdb.engine.core.gui.GUIManager;
import de.mdb.engine.core.gui.elements.GUIDebugElement;
import de.mdb.engine.core.gui.style.GUIRedStyle;
import de.mdb.engine.core.input.Input;
import de.mdb.engine.core.input.events.KeyReleasedEvent;
import de.mdb.engine.core.light.DirectionalLight;
import de.mdb.engine.core.light.PointLight;
import de.mdb.engine.core.light.PointLightManager;
import de.mdb.engine.core.model.Model;
import de.mdb.engine.core.model.OBJLoader;
import de.mdb.engine.core.particle.ParticleSystemImpl;
import de.mdb.engine.core.render.*;
import de.mdb.engine.core.render.shapes.Rectangle;
import de.mdb.engine.core.shader.Shader;
import de.mdb.engine.core.shader.ShaderProgram;
import de.mdb.engine.core.textures.Texture;
import de.mdb.engine.core.textures.TextureCache;
import de.mdb.engine.core.util.Clock;
import de.mdb.engine.core.util.Data;

public class DummyGame implements IGameLogic, EventListener {
	
	//VARIABLES
	private GUIDebugElement debugElement;
	
	private ShaderProgram simpleShader;
	private ShaderProgram particleShader;

	private FirstPersonCamera camera;
	private boolean freeMove = true;
	
	private Model monkey;

	private DirectionalLight dirLight;
	
	private ParticleSystemImpl particleSystem;
	
	private Rectangle rect;
	private Matrix4f projection;

	Vector3f pointLightPositions[] = { 
				new Vector3f(0.7f, 0.2f, 2.0f), 
				new Vector3f(2.3f, -3.3f, -4.0f),
				new Vector3f(-4.0f, 2.0f, -12.0f),
				new Vector3f(0.0f, 0.0f, -3.0f) };

	public void init() throws Exception {
		EventManager.registerListener(this);
		
		Display engineDisplay = GameEngine.getDisplay();
		engineDisplay.setWindowIcon("textures/MdB.png");
		
		particleSystem = new ParticleSystemImpl(1000, 25, 1f, 4, 1);
		particleSystem.randomizeRotation();
		particleSystem.setDirection(new Vector3f(1, 0, 0), 0.1f);
		particleSystem.setLifeError(0.4f);
		particleSystem.setSpeedError(0.4f);
		particleSystem.setScaleError(0.4f);
		
		//GUI Stuff
		GUIRenderer guiRenderer = new GUIRenderer();
		GUIManager.registerGUIRenderer(guiRenderer);
		
		//Create the DebugElement and set its style	
		debugElement = new GUIDebugElement("Debug", 20, 20);
		debugElement.setGUIStyle(new GUIRedStyle());
		guiRenderer.addGUIElement(debugElement);

		//Main shader
		simpleShader = new ShaderProgram();
		simpleShader.attachShader(new Shader(Data.RES_PATH + "shaders/vertexShaderSimple.vs", Shader.VERTEX_SHADER));
		simpleShader.attachShader(new Shader(Data.RES_PATH + "shaders/fragmentShaderSimple.fs", Shader.FRAGMENT_SHADER));
		simpleShader.linkShader();
		
		projection = new Matrix4f().perspective((float)Math.toRadians(90.0f),
				(float)GameEngine.getDisplay().getWidth() / GameEngine.getDisplay().getHeight(), 0.1f, 1000.0f);
		
		simpleShader.use();
		simpleShader.setMat4("projection", projection);
		
		particleShader = new ShaderProgram();
		particleShader.attachShader(new Shader(Data.RES_PATH + "shaders/particleVertexShader.vs", Shader.VERTEX_SHADER));
		particleShader.attachShader(new Shader(Data.RES_PATH + "shaders/particleFragmentShader.fs", Shader.FRAGMENT_SHADER));
		particleShader.linkShader();
		
		//Camera
		camera = new FirstPersonCamera(new Vector3f(0.0f, 0.0f, 5.0f), new Vector3f());
		camera.setupSettings(GameEngine.getDisplay());
		
		//FirstPersonRenderer
		FirstPersonRenderer firstPersonRenderer = new FirstPersonRenderer(simpleShader, camera, engineDisplay.getWidth(), engineDisplay.getHeight());
		GameEngine.registerRenderer(firstPersonRenderer);

		//Lights
		dirLight = new DirectionalLight(new Vector3f(-0.2f, -1.0f, 0.3f), new Vector3f(0.05f), new Vector3f(0.4f),
				new Vector3f(0.5f));

		Vector3f ambient = new Vector3f(0.05f);
		Vector3f diffuse = new Vector3f(0.05f);
		Vector3f specular = new Vector3f(0.05f);
		float constant = 1.0f;
		float linear = 0.09f;
		float quadratic = 0.032f;
		
		//Point lights automatically register to the PointLightManager
		new PointLight(pointLightPositions[0], ambient, diffuse, specular, constant, linear, quadratic);
		new PointLight(pointLightPositions[1], ambient, diffuse, specular, constant, linear, quadratic);
		new PointLight(pointLightPositions[2], ambient, diffuse, specular, constant, linear, quadratic);
		new PointLight(pointLightPositions[3], ambient, diffuse, specular, constant, linear, quadratic);
		
		//Models
		Model nanoSuit = OBJLoader.loadModel(Data.RES_PATH + "models/nanosuit/nanosuit.obj", "models/nanosuit/", Assimp.aiProcess_Triangulate);
		
		Model cube = OBJLoader.loadModel(Data.RES_PATH + "models/cube/cube.obj", "", Assimp.aiProcess_Triangulate);
		cube.translate(5.0f, 0, 0);
		
		monkey = OBJLoader.loadModel(Data.RES_PATH + "models/monkey/monkey.obj", "", Assimp.aiProcess_Triangulate);
		monkey.translate(-8.0f, 0, 0);
		
		Model tower = OBJLoader.loadModel(Data.RES_PATH + "models/tower/tower_High.obj", "models/tower/textures", Assimp.aiProcess_Triangulate);
		tower.translate(0, 0, -10.0f);
		
		//Model renderer
		ModelRenderer modelRenderer = new ModelRenderer(simpleShader);
		modelRenderer.addModel(nanoSuit);
		modelRenderer.addModel(cube);
		modelRenderer.addModel(monkey);
		modelRenderer.addModel(tower);
		
		GameEngine.registerRenderer(modelRenderer);
		
//		GameEngine.registerRenderer(new ParticleRenderer(particleShader, projection, camera));
		
		rect = new Rectangle(100, 100, 100, 100);
		Texture texture = TextureCache.getInstance().getTexture("textures/something.png");
		rect.setTexture(texture);
	}
	
	@Event
	public void onKeyReleased(KeyReleasedEvent e)
	{
		if(e.getKeyCode() == GLFW_KEY_TAB) switchInputState();
	}
	
	public void input() {
		if (Input.isKeyDown(GLFW_KEY_SPACE)) {
			camera.movePosition(0, 5.0f * Clock.getDeltaTime(), 0);
		} else if (Input.isKeyDown(GLFW_KEY_LEFT_SHIFT)) {
			camera.movePosition(0, -5.0f * Clock.getDeltaTime(), 0);
		}

		if (Input.isKeyDown(GLFW_KEY_W)) {
			camera.movePosition(0, 0, -5.0f * Clock.getDeltaTime());
		} else if (Input.isKeyDown(GLFW_KEY_S)) {
			camera.movePosition(0, 0, 5.0f * Clock.getDeltaTime());
		}

		if (Input.isKeyDown(GLFW_KEY_A)) {
			camera.movePosition(-5.0f * Clock.getDeltaTime(), 0, 0);
		} else if (Input.isKeyDown(GLFW_KEY_D)) {
			camera.movePosition(5.0f * Clock.getDeltaTime(), 0, 0);
		}
		
		if(Input.isKeyDown(GLFW_KEY_R)) {
			GameEngine.setRenderMode(RenderMode.FILL);
		}else if(Input.isKeyDown(GLFW_KEY_T)) {
			GameEngine.setRenderMode(RenderMode.WIREFRAME);
		}else if(Input.isKeyDown(GLFW_KEY_Y)) {
			GameEngine.setRenderMode(RenderMode.POINT);
		}
	}
	
	private void switchInputState()
	{
		if(freeMove) {
			GameEngine.getDisplay().setInputMode(GLFW_CURSOR, GLFW_CURSOR_NORMAL);
			freeMove = false;
			camera.setMovePosition(freeMove);
			camera.setMoveRotation(freeMove);
		}else {
			GameEngine.getDisplay().setInputMode(GLFW_CURSOR, GLFW_CURSOR_DISABLED);
			freeMove = true;
			camera.setMovePosition(freeMove);
			camera.setMoveRotation(freeMove);
		}
	}

	public void update() {
		camera.setFlySpeed(debugElement.getFlySpeed());
		
		simpleShader.use();
		simpleShader.setVec3("viewPos", camera.getPosition());
		
		NkColorf bg = debugElement.getBackground();
		GameEngine.setClearColor(new Vector4f(bg.r(), bg.g(), bg.b(), bg.a()));	
	}

	public void render() {
		dirLight.load(simpleShader);
		PointLightManager.load(simpleShader);
		particleSystem.generateParticles(new Vector3f(0.0f));
		rect.render();
	}

	public void cleanup() {
		simpleShader.delete();
	}

	public static void main(String[] args) {
		GameEngine engine = new GameEngine("Dummy", 1280, 860, true, new DummyGame());
		engine.start();
	}

}
