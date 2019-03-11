package com.sero.bot.factory;

import com.sero.bot.impl.UIImpl;
import com.sero.bot.interfaces.UI;

public final class UIFactory {
	
	private static UI UI;
	
	private UIFactory() {}
	
	public final static UI UI () {
		if (UI == null)
			UI = new UIImpl();
		return UI;
	}

}
