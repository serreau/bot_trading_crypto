package com.sero.bot.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.AggTrade;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.sero.bot.config.Config;
import com.sero.bot.interfaces.BotEvent;
import com.sero.bot.interfaces.IO;
import com.sero.bot.model.Trade;

public class IOImpl implements IO {
	
	private BinanceApiRestClient client;

	public void connect() {
		BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(Config.KEY, Config.SECRET);
		client = factory.newRestClient();
	}
	
	public void getAllOnFile(String pairname, String filename) {
		List<AggTrade> aggTrades = null;
		int id = 0;
		
		PrintWriter writer = null;
		
			try {
				writer = new PrintWriter(new File(filename), "UTF-8");
			} catch (FileNotFoundException e) {
				System.out.println(e);
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				System.out.println(e);
				e.printStackTrace();
			}
		
		do  {
			aggTrades = client.getAggTrades(pairname, ""+id, 1000, null, null);
			writer.write(aggTrades.toString());
			System.out.println(id);
			id += 1000;
		} while (!aggTrades.isEmpty());
		writer.close();
	}
	
	@Override
	public void gsonReader(String filename, BotEvent event) {
		try {
			FileInputStream fis = new FileInputStream(filename);
			InputStreamReader isr = new InputStreamReader(fis);
			JsonReader jr = new JsonReader(isr);
				
		    Gson gson = new GsonBuilder().create();
		    jr.setLenient(true);
		    do {
	    		jr.beginArray();
		        while (jr.hasNext()) {
		        	event.event(gson.fromJson(jr, Trade.class));
		        }
		        jr.endArray();
			}while(!jr.peek().equals(JsonToken.END_DOCUMENT));
		    jr.close();
		}
		catch (UnsupportedEncodingException e) {
		    e.printStackTrace();
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}

	public void buy(double price, double amount) {
		
		
	}
	
	public void sell(double price, double amount) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Trade getLastTrade() {
		Trade trade = new Trade();
//		{"a":56960000,"p":"6489.75000000","q":"0.01248300","f":64232155,"l":64232155,"T":1534529026923,"m":false}
		trade.setA(0);
		trade.setP(4261.48);
		trade.setQ(0.1);
		trade.setT(1502942428322L);
		trade.setM(true);
		
		return trade;
		
	}

}
