package com.sero.bot.impl;

import com.sero.bot.config.Constants;
import com.sero.bot.interfaces.Core;
import com.sero.bot.interfaces.Parameter;
import com.sero.bot.model.Target;
import com.sero.bot.model.Target.Position;
import com.sero.bot.model.Target.State;
import com.sero.bot.model.TargetMap;
import com.sero.bot.model.Trade;
import com.sero.bot.model.Wallet;

public class CoreImpl implements Core {
	Wallet wallet;
	TargetMap bearish;
	Rules rules;
	Actions actions;
	
	@Override
	public void init(Trade trade) {
		wallet = new Wallet(Constants.CAPITAL);
		bearish = new TargetMap();
		rules = new Rules();
		actions = new Actions();
		initBearishTargets(trade.getP());
		System.out.println(bearish.toString());
	}

	private void initBearishTargets(Double startprice) {
		Double interval = startprice/-Constants.BEARISH_TARGETS_SUM;
		bearish.put(0, new Target(0, startprice));
		bearish.get(0).setState(Target.State.WAITING);
		for (int i = 0; i > -Constants.BEARISH_TARGETS_SUM; i--) {
			Double p = startprice-interval*i;
			Target target = new Target(i, p);
			target.setState(State.WAITING);
			bearish.put(i, target);
		}
	}

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
		public Boolean deepToBuy(Double price) {
			Target current = bearish.getCurrent();
			if(current.isBought())
				return false;
			Boolean isequal = current.isEqual();
			Boolean isoverstep = current.isSkipped();
			if ((isequal || isoverstep) && current.isWaiting())
				return true;
			return false;
		}
		public Boolean topToBuy(Double price) {
			//TODO
			return false;
		}
		public Boolean topToSell(Double price) {
			//TODO
			return false;
		}
	}
	
	private class Actions{
		public void buy(Double price) {
			
//			wallet.buy(amount);
		}
		public void sell(Double price) {
		}
	}
}
