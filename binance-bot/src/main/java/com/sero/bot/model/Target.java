package com.sero.bot.model;

import java.util.Observable;

public class Target extends Observable{
	private Double marge;
	private Double interval;
	private Double price;
	private Target next;
	private Target previous;
	private State state;
	private Location position;
	private Location direction;
	private Double relativemargemin;
	private Double relativemargemax;
	
	public enum State{
		WAITING,
		SKIPPED,
		BOUGHT,
		SOLD
	}
	
	public enum Location{
		ABOVE,
		EQUAL,
		BELOW
	}

	public Target(Double price) {
		this.setPrice(price);
	}

	public Double getMarge() {
		return marge;
	}

	public void setMarge(Double marge) {
		this.marge = marge;
		relativemargemax = price + marge;
		relativemargemin = price - marge;
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
	
	public Location getPosition() {
		return position;
	}

	public void setPosition(Location position) {
		this.position = position;
	}
	
	public Location getDirection() {
		return direction;
	}

	public void setDirection(Location direction) {
		this.direction = direction;
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
	public boolean isSkipped() {
		return state.equals(State.SKIPPED);
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

	public void setInterval(Double interval) {
		this.interval = interval;
	}

	public void goToNext() {
		
	}
	public Target createNext() {
		if (direction.equals(Location.BELOW))
			next = new Target(getPrice()-getInterval());
		else 
		if (direction.equals(Location.ABOVE))
			next = new Target(getPrice()+getInterval());
		next.setPrevious(this);
		
		next.setState(State.WAITING);
		
		return next;
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
		
		if(getNext().moreThan(this) && getNext().isBelow() 
				|| getNext().lessThan(this) && getNext().isAbove())
			getNext().setState(State.SKIPPED);
			
		
		
//		if (t.equals(price)) {
//			t.setPosition(Position.EQUAL);
//			current = t;
//		}
//		else if (t.moreThan(price)) {
//			t.setPosition(Position.ABOVE);
//			if (current.moreThan(t)) {
//				current = t;
//				current.setState(State.SKIPPED);
//			}
//		}
//		else if (t.lessThan(price)) {
//			t.setPosition(Position.BELOW);
//			if (current.lessThan(t)) {
//				current = t;
//				current.setState(State.SKIPPED);
//			}
//		}
	}
	
	@Override
	public String toString() {
		return "price : "+price+", position : "+position+", state : "+state;
	}

}


