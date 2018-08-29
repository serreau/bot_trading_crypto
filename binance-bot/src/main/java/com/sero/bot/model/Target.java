package com.sero.bot.model;

import java.util.Observable;

import com.sero.bot.config.Constants;

public class Target extends Observable{
	private Integer index;
	private Double price;
	private Double margemin;
	private Double margemax;
	private Double amount;
	private State state;
	private Position position;
	
	public static enum State {
		WAITING,
		BOUGHT,
		REBOUGHT,
		SOLD
	}
	
	public static enum Position {
		TOP,
		EQUAL,
		DEEP
	}
	
	public Target(Integer index, Double price) {
		this.price = price;
		this.index = index;
		margemin = price-(price*Constants.MARGIN);
		margemax = price+((price*Constants.MARGIN));
		setState(State.WAITING);
		setPosition(position);
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
	
	public Boolean equals(Double price) {
		return price > margemin && price < margemax;
	}
	
	public Boolean moreThan(Double price) {
		return price > margemax;
	}
	
	public Boolean lessThan(Double price) {
		return price < margemin;
	}

	@Override
	public String toString() {
		return "price : "+price;
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

	public boolean isWaiting() {
		return getState().equals(State.WAITING);
	}
	
	public boolean isBought() {
		return getState().equals(State.BOUGHT);
	}
	
	public boolean isRebought() {
		return getState().equals(State.REBOUGHT);
	}
	
	public boolean isSold() {
		return getState().equals(State.SOLD);
	}
}
