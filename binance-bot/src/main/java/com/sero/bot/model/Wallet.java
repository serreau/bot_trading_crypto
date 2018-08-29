package com.sero.bot.model;

import com.sero.bot.config.Constants;

public class Wallet {
	private Double capitalout;
	private Double capitalin;
	private Double globalinvestment;
	private Double globalfees;
	private State state;
	
	public enum State{
		PROFIT,
		LOSS
	}
	
	public Wallet(Double c) {
		capitalout = c;
		capitalin = 0.0;
		globalinvestment = 0.0;
		globalfees = 0.0;
		state = State.LOSS;
		buy(0.0);
	}
	
	public Double buy(Double amount) {
		if(amount > capitalout)
			amount = capitalout;
		capitalout -= amount;
		globalinvestment += amount;
		globalfees +=  amount*Constants.FEES;
		return globalinvestment;
	}
	
	public Double sell(Double amount) {
		if(amount > capitalin)
			amount = capitalin;
		capitalin -= amount;
		globalinvestment -= amount;
		globalfees += amount*Constants.FEES;
		return globalinvestment;
	}
	
	public Double allIn() {
		return buy(capitalout-globalinvestment);
	}
	
	public Double allOut() {
		return sell(capitalin);
	}
	
	public void refresh(Double amount) {
		capitalin = amount;
		state = ( getDelta() <= 0.0) ? State.LOSS : State.PROFIT;
	}
	
	public Double getCapital() {
		return capitalout;
	}
	public Double push(Double amount) {
		this.capitalout += amount;
		return this.capitalout;
	}
	public Double pull(Double amount) {
		this.capitalout -= amount;
		return this.capitalout;
	}
	
	public Double getGlobalinvestment() {
		return globalinvestment;
	}
	public Double getGlobalfees() {
		return globalfees;
	}
	public State getState() {
		return state;
	}
	public Double getDelta() {
		return capitalin-globalinvestment;
	}


}
