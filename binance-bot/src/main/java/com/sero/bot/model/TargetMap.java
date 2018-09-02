package com.sero.bot.model;

import java.util.ArrayList;

@SuppressWarnings({ "serial" })
public class TargetMap extends ArrayList<Target> {
	private Target reference;
	private Double interval;
	private Target current;
	
	public TargetMap(Double price) {
		reference = new Target(price);
		add(reference);
		current = reference;
	}
	
	public void refresh(Double price) {
		if(current.equals(price)) {
			
		}

//		for (Target t : this) {
//			if (t.equals(price)) {
//				t.setPosition(Position.EQUAL);
//				current = t;
//			}
//			else if (t.moreThan(price)) {
//				t.setPosition(Position.ABOVE);
//				if (current.moreThan(t)) {
//					current = t;
//					current.setState(State.SKIPPED);
//				}
//			}
//			else if (t.lessThan(price)) {
//				t.setPosition(Position.BELOW);
//				if (current.lessThan(t)) {
//					current = t;
//					current.setState(State.SKIPPED);
//				}
//			}
//		}
	}

//	public void setGap(Double d) {
//		for(Target t : this)
//			t.setGap(d);
//	}

	public Double getInterval() {
		return interval;
	}

	public void setInterval(Double interval) {
		this.interval = interval;
	}

	public Target getCurrent() {
		return current;
	}


}
