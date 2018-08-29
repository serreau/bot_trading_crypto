package com.sero.bot.factory;

import com.sero.bot.impl.IOImpl;
import com.sero.bot.interfaces.IO;

public final class IOFactory {
	
	private static IO IO;
	
	private IOFactory() {}
	
	public final static IO IO () {
		if (IO == null)
			IO = new IOImpl();
		return IO;
	}

}
