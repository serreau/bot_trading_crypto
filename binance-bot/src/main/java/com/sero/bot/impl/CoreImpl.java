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
		wallet = new Wallet(Constants.CAPITAL);
//		
		rules = new Rules();
		actions = new Actions();
		
		initBearishTargets(trade.getP());
		initBullishTargets(trade.getP());
	}

	private void initBearishTargets(Double startprice) {
		bearish = new Target(startprice);
		bearish.setMarge(startprice*Constants.MARGIN);
		bearish.setInterval(startprice/Constants.BEARISH_DIVISOR);
		bearish.setDirection(Location.BELOW);
		bearish.setState(State.WAITING);
		
		Target next = bearish.createNext();
		next.setMarge(bearish.getMarge());
		next.setInterval(bearish.getInterval());
		next.setDirection(bearish.getDirection());
		
	}
	
	private void initBullishTargets(Double startprice) {
		bullish = new Target(startprice);
		bullish.setMarge(startprice*Constants.MARGIN);
		bullish.setInterval(startprice/Constants.BULLISH_DIVISOR);
		bullish.setDirection(Location.ABOVE);
		bullish.setState(State.WAITING);
		
		Target next = bullish.createNext();
		next.setMarge(bullish.getMarge());
		next.setInterval(bullish.getInterval());
		next.setDirection(bullish.getDirection());
	}

	@Override
	public void run(Parameter parameter) {
		Trade trade = parameter.getInstance();
		bearish.refresh(trade.getP());
		bullish.refresh(trade.getP());
		if(rules.toBuy(trade.getP(), bullish))
			actions.buyBullish(trade.getP());
		else
		if(rules.toBuy(trade.getP(), bearish))
			actions.buyBearish(trade.getP());
//		else 
//		if(rules.toSell(trade.getP()), bullish)
//			actions.sell(trade.getP());
	}

	private class Rules{
		public Boolean toBuy(Double price, Target target) {
			boolean iscurrentequal = target.equals(price);
			boolean iscurrentskipped = target.isSkipped();
			boolean iscurrentnotbought = !target.isBought();
			boolean isnextequal = target.getNext().equals(price);
			boolean isnextskipped = target.getNext().isSkipped();
			boolean isnextnotbought = !target.getNext().isBought();
			
			boolean iscurrenttobuy = (iscurrentequal || iscurrentskipped) && iscurrentnotbought;
			boolean isnexttobuy = (isnextequal || isnextskipped) && isnextnotbought;
			
			if (iscurrenttobuy || isnexttobuy)
				return true;
			return false;
		}

	}
	
	private class Actions{
		public void buyBullish(Double price) {
			bullish.setState(State.BOUGHT);
			System.out.println(price+"\n BUY BULLISH : "+bullish.toString()+" / "+bullish.getNext().toString());
			nextBullish();
		}
		
		public void buyBearish(Double price) {
			bearish.setState(State.BOUGHT);
			System.out.println(price+"\n BUY BEARISH : "+bearish.toString()+" / "+bearish.getNext().toString());
			nextBearish();
			if(price == price-1)
				System.out.println();
		}
		
		public void nextBullish() {
			bullish = bullish.getNext();
			Target next = bullish.createNext();
			next.setMarge(bullish.getMarge());
			next.setInterval(bullish.getInterval());
			next.setDirection(bullish.getDirection());
		}
		
		public void nextBearish() {
			bearish = bearish.getNext();
			Target next = bearish.createNext();
			next.setMarge(bearish.getMarge());
			next.setInterval(bearish.getInterval());
			next.setDirection(bearish.getDirection());
		}
		
//		public void sell(Double price) {
////	System.out.println(price +"\n SELL : "+bearish.toString());
//	initBearishTargets(price);
//}
	}
}
