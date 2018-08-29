package com.sero.bot.interfaces;

import com.sero.bot.model.Trade;

public interface Core {
	public void run(Parameter p);
	public void init(Trade trade);
}
