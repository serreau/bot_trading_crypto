package com.sero.bot.model;

import java.util.Observable;

import com.sero.bot.config.Constants;

public class Target extends Observable{
	private Integer index;
	private Double price;
	private Double relativemargemin;
	private Double relativemargemax;
	private Double amount;
	private State state;
	private Position position;
	

	public static enum State {
		WAITING,
		SKIPPED,
		BOUGHT,
		REBOUGHT,
		SOLD
	}
	
	public static enum Position {
		ABOVE,
		EQUAL,
		BELOW
	}
	
	public Target(Integer index, Double price) {
		this.price = price;
		this.index = index;
		setState(State.WAITING);
		setPosition(position);
	}
	
	public void setMarge(Target reference) {
		relativemargemin = price-(reference.getPrice()*Constants.MARGIN);
		relativemargemax = price+((reference.getPrice()*Constants.MARGIN));
	}
	
	public Boolean equals(Double price) {
		return price > relativemargemin && price < relativemargemax;
	}
	
	public Boolean moreThan(Double price) {
		return price < relativemargemin;
	}
	
	public Boolean lessThan(Double price) {
		return price > relativemargemax;
	}
	
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getIndex() {
		return index;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Boolean isWaiting() {
		return getState().equals(State.WAITING);
	}
	
	public Boolean isBought() {
		return getState().equals(State.BOUGHT);
	}
	
	public Boolean isRebought() {
		return getState().equals(State.REBOUGHT);
	}
	
	public Boolean isSold() {
		return getState().equals(State.SOLD);
	}

	public Boolean isAbove() {
		return getPosition().equals(Position.ABOVE);
	}
	
	public Boolean isEqual() {
		return getPosition().equals(Position.EQUAL);
	}
	
	public Boolean isBelow() {
		return getPosition().equals(Position.BELOW);
	}

	public Boolean moreThan(Target v) {
		return getPrice() > v.getPrice();
	}
	
	public Boolean lessThan(Target v) {
		return getPrice() < v.getPrice();
	}
	
	public Boolean equals(Target v) {
		return getPrice() == v.getPrice();
	}
	
	public Boolean isSkipped() {
		return getState().equals(State.SKIPPED);
	}

	@Override
	public String toString() {
		return "[index : "+index+", price : "+price+", state : "+state+", position : "+position+"]";
	}

}
