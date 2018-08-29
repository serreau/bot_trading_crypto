package com.sero.bot.model;

import java.util.HashMap;
import com.sero.bot.model.Target;

@SuppressWarnings({ "serial" })
public class TargetMap extends HashMap<Integer, Target>{
	private Target top;
	private Target deep;
	
	public static enum Move {
		UP,
		DOWN
	}
	
	@Override
	public Target put(Integer key, Target target) {
		return super.put(key, target);
	}
	
//	public void move(Move move) {
//		if(move.equals(Move.UP)) {
//			deep = top;
//			top = get(top.getIndex()+1);
//		}
//		if(move.equals(Move.DOWN)) {
//			top = deep;
//			deep = get(deep.getIndex()-1);
//		}
//	}

	public Target getDeep() {
		return deep;
	}

	public void setDeep(Target deep) {
		this.deep = deep;
	}

	public Target getTop() {
		return top;
	}

	public void setTop(Target top) {
		this.top = top;
	}

	public void refresh(Double price) {
		forEach((k, v) -> {
			if (v.equals(price)) {
				v.setPosition(Target.Position.EQUAL);
			}
			else if (v.moreThan(price)) {
				v.setPosition(Target.Position.TOP);
				if (v.lessThan(top.getPrice()))
					top = v;
			}
			else if (v.lessThan(price)){
				v.setPosition(Target.Position.DEEP);
				if (v.moreThan(deep.getPrice()))
					deep = v;
			}
		});
	}

}
