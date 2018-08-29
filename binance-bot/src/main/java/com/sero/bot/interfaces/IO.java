package com.sero.bot.interfaces;

import com.sero.bot.model.Trade;

public interface IO {
	
	void connect();
	
	void getAllOnFile(String pairname, String filename);
	void gsonReader(String filename, BotEvent event);
	
	void buy(double price, double amount);
	void sell(double price, double amount);

	Trade getLastTrade();

}
