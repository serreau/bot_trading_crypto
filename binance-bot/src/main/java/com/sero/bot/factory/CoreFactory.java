package com.sero.bot.factory;

import com.sero.bot.impl.CoreImpl;
import com.sero.bot.interfaces.Core;

public final class CoreFactory {
	
	private static Core CORE;
	
	private CoreFactory() {}
	
	public final static Core Core() {
		if (CORE == null)
			CORE = new CoreImpl();
		return CORE;
	}
	
}
