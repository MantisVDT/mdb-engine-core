package examples;

import de.mdb.engine.core.event.IEvent;

public class CustomEventExample implements IEvent {
	
	private String someString;
	private int someInt;
	
	public CustomEventExample(String someString, int someInt)
	{
		this.someString = someString;
		this.someInt = someInt;
	}
	
	public String getSomeString()
	{
		return someString;
	}
	
	public int getSomeInt()
	{
		return someInt;
	}
	
}
