package de.mdb.engine.core.input.events;

import de.mdb.engine.core.event.IEvent;

public class MouseEvent implements IEvent{
	
	private int keyCode;
	private int mods;
	private boolean pressed;
	private float x;
	private float y;
	
	public MouseEvent(float x, float y, int keyCode, int mods, boolean pressed) {
		super();
		this.keyCode = keyCode;
		this.mods = mods;
		this.pressed = pressed;
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public int getKeyCode() {
		return keyCode;
	}

	public int getMods() {
		return mods;
	}

	public boolean isPressed() {
		return pressed;
	}

}
