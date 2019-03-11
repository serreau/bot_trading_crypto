package com.sero.bot.impl;

import com.sero.bot.config.Constants;
import com.sero.bot.interfaces.Core;
import com.sero.bot.interfaces.Parameter;
import com.sero.bot.model.Target;
import com.sero.bot.model.Target.Location;
import com.sero.bot.model.Target.State;
import com.sero.bot.model.Trade;
import com.sero.bot.model.Wallet;

public class CoreImpl implements Core {
	Wallet wallet;
	Target bearish;
	Target bullish;
	Rules rules;
	Actions actions;
	
	@Override
	public void init(Trade trade) {
//		
		rules = new Rules();
		actions = new Actions();
		
		initBearishTargets(trade.getP());
	}

	private void initBearishTargets(Double price) {
		bearish = new Target(price, Target.Type.BEARISH);
		System.out.println(price+"\n BUY BEARISH : "+bearish.toString()+" / "+bearish.getNext().toString());
	}

	@Override
	public void run(Parameter parameter) {
		Trade trade = parameter.getInstance();
		
		if(rules.toBuy(trade.getP(), bearish))
			actions.buyBearish(trade.getP());
		
	}

	private class Rules{
		public Boolean toBuy(Double price, Target target) {
			boolean isnextequal = target.getNext().equals(price);
			boolean isnextskipped = target.getNext().isSkipped(price);
			boolean isnextnotbought = !target.getNext().isBought();
			
			boolean isnexttobuy = (isnextequal || isnextskipped) && isnextnotbought;
			
			if (isnexttobuy)
				return true;
			return false;
		}

//		public boolean toRevert(Double price, Target target) {
//			boolean torevert = target.equals(price-target.getInterval());
//			boolean torevertskipped = target.lessThan(price-target.getInterval());
//			
//			if(torevert || torevertskipped) 
//				return true;
//			
//			return false;
//		}
	}
	
	private class Actions{
//		public void buyBullish(Double price) {
//			System.out.println(price+"\n BUY BULLISH : "+bullish.toString());
//			System.out.println();
//		}

		public void buyBearish(Double price) {
			System.out.println(price+"\n BUY BEARISH : "+bearish.toString());
			System.out.println();
		}

	}
}
