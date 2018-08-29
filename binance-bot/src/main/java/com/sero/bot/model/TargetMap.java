package com.sero.bot.model;

import java.util.HashMap;
import com.sero.bot.model.Target;
import com.sero.bot.model.Target.Position;
import com.sero.bot.model.Target.State;

@SuppressWarnings({ "serial" })
public class TargetMap extends HashMap<Integer, Target>{
	private Target current;
	private Target reference;

	
	@Override
	public Target put(Integer key, Target target) {
		if(key.equals(0))
			reference = current = target;
		target.setMarge(reference);
		return super.put(key, target);
	}

	public void refresh(Double price) {
		forEach((k, v) -> {
			if (v.equals(price)) {
				v.setPosition(Position.EQUAL);
				current = v;
			}
			else if (v.moreThan(price)) {
				v.setPosition(Position.ABOVE);
				if (current.moreThan(v))
					current = v;
					current.setState(State.SKIPPED);
			}
			else if (v.lessThan(price)) {
				v.setPosition(Position.BELOW);
				if (current.lessThan(v)) {
					current = v;
					current.setState(State.SKIPPED);
				}
			}
		});
	}

	public Target getReference() {
		return reference;
	}

	public void setReference(Target reference) {
		this.reference = reference;
	}

	public Target getCurrent() {
		return current;
	}

	public void setCurrent(Target current) {
		this.current = current;
	}

}
