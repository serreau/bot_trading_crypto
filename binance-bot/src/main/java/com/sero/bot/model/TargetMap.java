package com.sero.bot.model;

import java.util.LinkedList;

import com.sero.bot.model.Target.Position;
import com.sero.bot.model.Target.State;

@SuppressWarnings({ "serial" })
public class TargetMap extends LinkedList<Target> {
	private Target reference;
	private Double interval;
	private Target current;
	
	public TargetMap(Double price) {
		reference = new Target(price);
		add(0, reference);
		current = reference;
	}
	
	public void refresh(Double price) {
		for (Target t : this) {
			if (t.equals(price)) {
				t.setPosition(Position.EQUAL);
				current = t;
			}
			else if (t.moreThan(price)) {
				t.setPosition(Position.ABOVE);
				if (current.moreThan(t)) {
					current = t;
					current.setState(State.SKIPPED);
				}
			}
			else if (t.lessThan(price)) {
				v.setPosition(Position.BELOW);
				if (current.lessThan(t)) {
					current = t;
					current.setState(State.SKIPPED);
				}
			}
		}
	}

	public void setGap(Double d) {
		for(Target t : this)
			t.setGap(d);
	}

	public Double getInterval() {
		return interval;
	}

	public void setInterval(Double interval) {
		this.interval = interval;
	}

	public void refresh(double p) {
		// TODO Auto-generated method stub
		
	}


}
