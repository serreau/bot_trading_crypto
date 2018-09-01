package com.sero.bot.model;

import java.util.Observable;

public class Target extends Observable{
	private Double gap;
	private Double price;
	private Target next;
	private Target previous;
	private State state;
	private Position position;
	
	
	public enum State{
		WAINTING,
		SKIPPED,
		BOUGHT,
		REBOUGHT,
		SOLD
	}
	
	public enum Position{
		TOP,
		EQUAL,
		DEEP
	}

	public Target(Double price) {
		this.setPrice(price);
	}

	public Double getGap() {
		return gap;
	}

	public void setGap(Double gap) {
		this.gap = gap;
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

	public Target getPrevious() {
		return previous;
	}

	public void setPrevious(Target previous) {
		this.previous = previous;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
}
