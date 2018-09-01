package com.sero.bot.impl;

import com.sero.bot.config.Constants;
import com.sero.bot.interfaces.Core;
import com.sero.bot.interfaces.Parameter;
import com.sero.bot.model.TargetMap;
import com.sero.bot.model.Trade;
import com.sero.bot.model.Wallet;

public class CoreImpl implements Core {
	Wallet wallet;
	TargetMap bearish;
	TargetMap bullish;
	Rules rules;
	Actions actions;
	
	@Override
	public void init(Trade trade) {
		wallet = new Wallet(Constants.CAPITAL);
		
		rules = new Rules();
		actions = new Actions();
		initBearishTargets(trade.getP());
		initBullishTargets(trade.getP());
	}

	private void initBearishTargets(Double startprice) {
		bearish = new TargetMap(startprice);
		bearish.setGap(startprice/Constants.BEARISH_DIVISOR);
	}
	
	private void initBullishTargets(Double startprice) {
		bullish = new TargetMap(startprice);
		bearish.setGap(startprice/Constants.BEARISH_DIVISOR);
	}

	@Override
	public void run(Parameter parameter) {
		Trade trade = parameter.getInstance();
		bearish.refresh(trade.getP());
	}

	private class Rules{
		
	}
	
	private class Actions{
		public void buy(Double price) {
//			System.out.println(price +"\n BUY : "+bearish.toString());
			
		}
		public void sell(Double price) {
//			System.out.println(price +"\n SELL : "+bearish.toString());
			initBearishTargets(price);
		}
	}
}
