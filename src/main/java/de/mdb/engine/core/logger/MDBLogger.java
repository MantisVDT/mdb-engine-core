package de.mdb.engine.core.logger;

import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

public class MDBLogger extends Logger {

	public MDBLogger(String name, String resourceBundleName) {
		super(name, resourceBundleName);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new LogFormatter());
		this.addHandler(handler);
	}

}
