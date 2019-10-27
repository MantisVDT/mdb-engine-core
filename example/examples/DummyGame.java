package examples;

import static org.lwjgl.glfw.GLFW.GLFW_CURSOR;
import static org.lwjgl.glfw.GLFW.GLFW_CURSOR_DISABLED;
import static org.lwjgl.glfw.GLFW.GLFW_CURSOR_NORMAL;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT_SHIFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_TAB;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;

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
import de.mdb.engine.core.gui.elements.DebugElement;
import de.mdb.engine.core.gui.style.DarkStyle;
import de.mdb.engine.core.input.Input;
import de.mdb.engine.core.input.events.KeyReleasedEvent;
import de.mdb.engine.core.light.DirectionalLight;
import de.mdb.engine.core.light.PointLight;
import de.mdb.engine.core.light.PointLightManager;
import de.mdb.engine.core.model.Model;
import de.mdb.engine.core.model.OBJLoader;
import de.mdb.engine.core.render.FirstPersonRenderer;
import de.mdb.engine.core.render.GUIRenderer;
import de.mdb.engine.core.render.ModelRenderer;
import de.mdb.engine.core.shader.Shader;
import de.mdb.engine.core.shader.ShaderProgram;
import de.mdb.engine.core.util.Clock;
import de.mdb.engine.core.util.Data;

public class DummyGame implements IGameLogic, EventListener {

	private ModelRenderer modelRenderer;
	private GUIRenderer guiRenderer;
	
	private ShaderProgram simpleShader;

	private FirstPersonCamera camera;
	private boolean freeMove = true;

	private DirectionalLight dirLight;

	private Model monkey;
	
	private DebugElement debug;

	Vector3f pointLightPositions[] = { new Vector3f(0.7f, 0.2f, 2.0f), new Vector3f(2.3f, -3.3f, -4.0f),
			new Vector3f(-4.0f, 2.0f, -12.0f), new Vector3f(0.0f, 0.0f, -3.0f) };

	public void init() throws Exception {
		EventManager.registerListener(this);
		
		Display engineDisplay = GameEngine.getDisplay();
		
		//GUI Stuff
		guiRenderer = new GUIRenderer(engineDisplay.getWindow());
		GUIManager.addGUIRenderer(guiRenderer);
		
		//Create the DebugElement and set its style
		debug = new DebugElement();
		debug.setGUIStyle(new DarkStyle());
		
		guiRenderer.addGUIElement(debug);

		//Main shader
		simpleShader = new ShaderProgram();
		simpleShader.attachShader(new Shader(Data.RES_PATH + "shaders/vertexShaderSimple.vs", Shader.VERTEX_SHADER));
		simpleShader.attachShader(new Shader(Data.RES_PATH + "shaders/fragmentShaderSimple.fs", Shader.FRAGMENT_SHADER));
		simpleShader.linkShader();
		
		//Camera
		camera = new FirstPersonCamera(new Vector3f(0.0f, 8.0f, 10.0f), new Vector3f());
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
		
		//Model renderer
		modelRenderer = new ModelRenderer(simpleShader);
		modelRenderer.addModel(nanoSuit);
		modelRenderer.addModel(cube);
		modelRenderer.addModel(monkey);
		
		GameEngine.registerRenderer(modelRenderer);
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
		camera.setFlySpeed(debug.getFlySpeed());
		
		simpleShader.use();
		simpleShader.setVec3("viewPos", camera.getPosition());
		
		NkColorf bg = debug.getBackground();
		GameEngine.setClearColor(new Vector4f(bg.r(), bg.g(), bg.b(), bg.a()));
	}

	public void render() {

		dirLight.load(simpleShader);
		PointLightManager.load(simpleShader);
	}

	public void cleanup() {

	}

	public static void main(String[] args) {
		GameEngine engine = new GameEngine("Dummy", 1280, 860, true, new DummyGame());
		engine.start();
	}

}
