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
