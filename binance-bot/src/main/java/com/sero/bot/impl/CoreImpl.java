package com.sero.bot.impl;

import com.sero.bot.config.Constants;
import com.sero.bot.interfaces.Core;
import com.sero.bot.interfaces.Parameter;
import com.sero.bot.model.Target;
import com.sero.bot.model.Target.State;
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
		bearish = new TargetMap();
		bullish = new TargetMap();
		rules = new Rules();
		actions = new Actions();
		initBearishTargets(trade.getP());
//		initBullishTargets(trade.getP());
	}

	private void initBearishTargets(Double startprice) {
		Double interval = startprice/Constants.BEARISH_TARGETS_SUM;
		bearish.setIntervale(interval);
		for (int i = 0; i > -Constants.BEARISH_TARGETS_SUM; i--) {
			Double p = startprice+interval*i;
			Target target = new Target(i, p);
			bearish.put(i, target);
		}
		bearish.setState(State.WAITING);
		bearish.setMarge(bearish.get(0));
		
		System.out.println(bearish);
	}
	
//	private void initBullishTargets(Double startprice) {
//		Double interval = startprice/bearish.getIntervale()/Constants.BULLISH_TARGETS_SUM;
//		bullish.setIntervale(interval);
//		for (int i = 1; i < Constants.BEARISH_TARGETS_SUM; i++) {
//			Double p = startprice-interval*i;
//			Target target = new Target(i, p);
//			target.setState(State.WAITING);
//			bearish.put(i, target);
//		}
//	}

	@Override
	public void run(Parameter parameter) {
		Trade trade = parameter.getInstance();
		bearish.refresh(trade.getP());
		if(rules.topToBuy(trade.getP()))
			actions.buy(trade.getP());
		else
		if(rules.deepToBuy(trade.getP()))
			actions.buy(trade.getP());
		else 
		if(rules.topToSell(trade.getP()))
			actions.sell(trade.getP());
	}

	private class Rules{
		public Boolean topToBuy(Double price) {
			Target current = bearish.getCurrent();
			Boolean isequals = current.isEqual();
			Boolean isskipped = current.isBelow() && current.isSkipped();
			Boolean isnotrebought = !current.isRebought();
			Boolean ispreviousbought = bearish.isPreviousBought() || bearish.isPreviousRebought();
			if ((isequals || isskipped) && ispreviousbought && isnotrebought) {
				current.setState(State.REBOUGHT);
				System.out.println("price : "+price+"\n"+bearish.toString());
				return true;
			}
			return false;
		}
		public Boolean deepToBuy(Double price) {
			Target current = bearish.getCurrent();
			if(current.isBought())
				return false;
			boolean isequals = (current.isEqual() && current.isWaiting());
			boolean isskipped = current.isAbove() && current.isSkipped();
			if (isequals|| isskipped) {
				current.setState(State.BOUGHT);
				System.out.println("price : "+price+"\n"+bearish.toString());
				return true;
			}
			return false;
		}
		public Boolean topToSell(Double price) {
			Target current = bearish.getCurrent();
			if(current.isRebought() && current.isAbove()) {
				current.setState(State.SOLD);
				System.out.println("price : "+price+"\n"+bearish.toString());
				return true;
			}
			return false;
		}
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
