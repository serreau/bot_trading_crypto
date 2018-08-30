package com.sero.bot.model;

import java.util.HashMap;
import java.util.Map;

import com.sero.bot.config.Constants;
import com.sero.bot.model.Target.Position;
import com.sero.bot.model.Target.State;

@SuppressWarnings({ "serial" })
public class TargetMap extends HashMap<Integer, Target> {
	private Target current;
	private Target reference;
	private Double intervale;

	
	@Override
	public Target put(Integer key, Target target) {
		return super.put(key, target);
	}

	public void refresh(Double price) {
		for (Map.Entry<Integer, Target> entry : this.entrySet())
		{
			Target v = entry.getValue();
			
			if (v.equals(price)) {
				v.setPosition(Position.EQUAL);
				current = v;
			}
			else if (v.moreThan(price)) {
				v.setPosition(Position.ABOVE);
				if (current.moreThan(v)) {
					current = v;
					current.setState(State.SKIPPED);
				}
			}
			else if (v.lessThan(price)) {
				v.setPosition(Position.BELOW);
				if (current.lessThan(v)) {
					current = v;
					current.setState(State.SKIPPED);
				}
			}
		}
	}
	
	public void setState(State state) {
		for (Map.Entry<Integer, Target> entry : this.entrySet())
		{
			Target v = entry.getValue();
			v.setState(state);
		}
	}
	
	public void setMarge(Target reference) {
		for (Map.Entry<Integer, Target> entry : this.entrySet())
		{
			Target v = entry.getValue();
			v.setMarge(reference);
		}
	}
	
	public void setReference(Target reference) {
		this.reference = reference;
		for (Map.Entry<Integer, Target> entry : this.entrySet())
		{
			Target v = entry.getValue();
			v.setReference(reference);
		}
	}

	public Target getReference() {
		return reference;
	}

	public Target getCurrent() {
		return current;
	}

	public void setCurrent(Target current) {
		this.current = current;
	}

	public boolean isPreviousBought() {
		if(get(current.getIndex()-1) == null)
			return false;
		return get(current.getIndex()-1).isBought();
	}
	
	public boolean isPreviousRebought() {
		if(get(current.getIndex()-1) == null)
			return false;
		return get(current.getIndex()-1).isRebought();
	}

	public Double getIntervale() {
		return intervale;
	}

	public void setIntervale(Double intervale) {
		this.intervale = intervale;
	}
	
	@Override
	public String toString() {
		String s = "";
		for (int i=0 ; i>-Constants.BEARISH_TARGETS_SUM ; i--)
			s += get(i).toString()+"\n";
		s += "\n";
		return s;
	}

}
