package com.sero.bot.model;

import java.util.Observable;

import com.sero.bot.config.Constants;

public class Target extends Observable{
	private Double price;
	private Double marge;
	private Double interval;
	
	private Type type;
	private State state;
	private Location position;
	private Target next;
	
	public enum State{
		WAITING,
		BOUGHT,
		SOLD
	}
	
	public enum Location{
		ABOVE,
		EQUAL,
		BELOW
	}
	
	public enum Type{
		BEARISH,
		BULLISH
	}

	public Target(Double price, Type type) {
		setPrice(price);
		setType(type);
		setMarge();
		setInterval();
		
		setState(State.WAITING);
		
		if(isBearish()) {
			setPosition(Location.ABOVE);
			next = new Target(price+interval, Type.BEARISH);
		}
		else {
			setPosition(Location.BELOW);
			next = new Target(price-interval, Type.BULLISH);
		}
	}
	
	public Boolean isBearish() {
		return type.equals(Type.BEARISH);
	}
	
	public Boolean isBullish() {
		return type.equals(Type.BULLISH);
	}

	public Double getMarge() {
		return marge;
	}

	public void setMarge() {
			marge = price * Constants.MARGIN;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Target getNext() {
		return next;
	}

	public void setNext(Target next) {
		this.next = next;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	public Location getPosition() {
		return position;
	}

	public void setPosition(Location position) {
		this.position = position;
	}
	
	public Double getMargeMin() {
		return price-marge;
	}
	
	public Double getMargeMax() {
		return price+marge;
	}
	
	public Boolean equals(Double price) {
		return price > getMargeMin() && price < getMargeMax();
	}

	public Boolean moreThan(Double price) {
		return price < getMargeMin();
	}

	public Boolean lessThan(Double price) {
		return price > getMargeMax();
	}

	public boolean equals(Target t) {
		return equals(t.getPrice());
	}
	
	public boolean moreThan(Target t) {
		return moreThan(t.getPrice());
	}
	
	public boolean lessThan(Target t) {
		return lessThan(t.getPrice());
	}
	
	public boolean isWaiting() {
		return state.equals(State.WAITING);
	}
	public boolean isBought() {
		return state.equals(State.BOUGHT);
	}
	public boolean isSold() {
		return state.equals(State.SOLD);
	}
	public boolean isEqual() {
		return position.equals(Location.EQUAL);
	}
	public boolean isAbove() {
		return position.equals(Location.ABOVE);
	}
	public boolean isBelow() {
		return position.equals(Location.BELOW);
	}
	public Double getInterval() {
		return interval;
	}
	public void setInterval() {
		if(type.equals(Type.BEARISH))
			this.interval = price/Constants.BEARISH_DIVISOR;
		else
			this.interval = price/Constants.BULLISH_DIVISOR;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
		setInterval();
	}
	
	public void refresh(Double price) {
		if(equals(price))
			setPosition(Location.EQUAL);
		else
		if(moreThan(price))
			setPosition(Location.ABOVE);
		else
		if(lessThan(price))
			setPosition(Location.BELOW);
		
		if(getNext().equals(price))
			getNext().setPosition(Location.EQUAL);
		else
		if(getNext().moreThan(price))
			getNext().setPosition(Location.ABOVE);
		else
		if(getNext().lessThan(price))
			getNext().setPosition(Location.BELOW);
	}

	public boolean isSkipped(Double price) {
		if(type.equals(Type.BEARISH) && moreThan(price))
			return true;
		else if(type.equals(Type.BULLISH) && lessThan(price))
			return true;
			
		return false;
	}
	
	@Override
	public String toString() {
		return "price : "+price+", position : "+position+", state : "+state;
	}

}


