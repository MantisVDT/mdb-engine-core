package de.mdb.engine.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import de.mdb.engine.core.logger.Debug;

public class TestRunner {
	
	public static void main(String[] args)
	{
		Result result = JUnitCore.runClasses(CameraTest.class, FirstPersonCameraTest.class);
		
		if(result.wasSuccessful()) Debug.info("Successfully ran " + result.getRunCount() + " Tests in " + result.getRunTime() + "ms");
		
		for(Failure failure : result.getFailures())
		{
			Debug.severe(failure);
		}
	}
	
}
