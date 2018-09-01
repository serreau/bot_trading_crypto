package com.sero.bot.model;

import java.util.LinkedList;

@SuppressWarnings({ "serial" })
public class TargetMap extends LinkedList<Target> {
	private Target reference;
	private Double interval;
	
	public TargetMap(Double price) {
		reference = new Target(price);
		add(0, reference);
		add(0, reference);
	}
	
//	public void refresh(Double price) {
//		for (Target t : this) {
//			if (t.getPosition) {
//				
//			}
//		}
//		
//		
//		for (Map.Entry<Integer, Target> entry : this.entrySet())
//		{
//			Target v = entry.getValue();
//			
//			if (v.equals(price)) {
//				v.setPosition(Position.EQUAL);
//				current = v;
//			}
//			else if (v.moreThan(price)) {
//				v.setPosition(Position.ABOVE);
//				if (current.moreThan(v)) {
//					current = v;
//					current.setState(State.SKIPPED);
//				}
//			}
//			else if (v.lessThan(price)) {
//				v.setPosition(Position.BELOW);
//				if (current.lessThan(v)) {
//					current = v;
//					current.setState(State.SKIPPED);
//				}
//			}
//		}
//	}

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
