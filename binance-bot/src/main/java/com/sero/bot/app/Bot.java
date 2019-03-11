package com.sero.bot.app;

import com.sero.bot.factory.CoreFactory;
import com.sero.bot.factory.IOFactory;
import com.sero.bot.factory.UIFactory;
import com.sero.bot.interfaces.BotEvent;
import com.sero.bot.interfaces.Core;
import com.sero.bot.interfaces.IO;
import com.sero.bot.interfaces.Parameter;
import com.sero.bot.interfaces.UI;

public class Bot implements BotEvent{
	private IO io;
	private Core core;
	private UI ui;
	
	public Bot() {
		io = IOFactory.IO();
		core = CoreFactory.Core();
		ui = UIFactory.UI();
		this.start();
	}

	private void start() {
		System.out.println("Bot.run()");
		//GET ALL TRADES ON FILE
		//io.connect();
		//io.getAllOnFile("BTCUSDT", "historiqueBTCUSDT.txt");
		
		//READ FILE AND TEST THE CORE
		core.init(io.getLastTrade());
		ui.init();
		io.gsonReader("last.txt", this);
		
//		io.gsonReader("last.txt", this);
	}

	@Override
	public void event(Parameter p) {
		core.run(p);
		ui.run(p);
	}
	
}
