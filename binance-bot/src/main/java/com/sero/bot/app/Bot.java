package com.sero.bot.app;

import com.sero.bot.factory.CoreFactory;
import com.sero.bot.factory.IOFactory;
import com.sero.bot.interfaces.BotEvent;
import com.sero.bot.interfaces.Core;
import com.sero.bot.interfaces.IO;
import com.sero.bot.interfaces.Parameter;

public class Bot implements BotEvent{
	private IO io;
	private Core core;
	
	public Bot() {
		io = IOFactory.IO();
		core = CoreFactory.Core();
		this.start();
	}

	private void start() {
		System.out.println("Bot.run()");
//		io.connect();
//		io.getAllOnFile("BTCUSDT", "historiqueBTCUSDT.txt");
		core.init(io.getLastTrade());
		io.gsonReader("historiqueBTCUSDT.txt", this);
	}

	@Override
	public void event(Parameter p) {
		core.run(p);
	}
	
}
