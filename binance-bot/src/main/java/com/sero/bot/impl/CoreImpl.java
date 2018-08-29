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
	
	public enum Choice {
		BUY,
		SELL,
		WAIT
	}
	
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
		for (int i = -1; i > -Constants.BEARISH_TARGETS_SUM; i--) {
			Double p = startprice-interval*i;
			bearish.put(i, new Target(i, p));
		}
	}

	@Override
	public void run(Parameter parameter) {
		Trade trade = parameter.getInstance();
//		bearish.refresh(trade.getP());
		if(rules.toBuy(trade.getP(), bearish))
			actions.buy(trade.getP());
		else if(rules.toSell(trade.getP(), bearish))
			actions.sell(trade.getP());
	}

	private class Rules{
		
		public Boolean toBuy(Double price, TargetMap bearish) {
			Target top = bearish.getTop();
			Target deep = bearish.getDeep();
			Boolean istopequal = top.getPosition().equals(Position.EQUAL);
			Boolean isdeepequal = deep.getPosition().equals(Position.EQUAL);
			
			if (istopequal && top.isWaiting())
				return true;
			else if (isdeepequal && deep.isWaiting())
				return true;
			
			return false;
		}
		public Boolean toSell(Double price, TargetMap bearish) {
			return true;
		}
	}
	
	private class Actions{
		public void buy(Double price) {
		}
		public void sell(Double price) {
		}
	}
}
