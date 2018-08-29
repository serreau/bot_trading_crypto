package com.sero.bot.model;

import com.sero.bot.interfaces.Parameter;

public class Trade implements Parameter{
private long a;
private double p;
private double q;
private long f;
private long l;
private long T;
private boolean m;

public long getA() {
	return a;
}
public void setA(long a) {
	this.a = a;
}
public double getP() {
	return p;
}
public void setP(double p) {
	this.p = p;
}
public double getQ() {
	return q;
}
public void setQ(double q) {
	this.q = q;
}
public long getF() {
	return f;
}
public void setF(long f) {
	this.f = f;
}
public long getL() {
	return l;
}
public void setL(long l) {
	this.l = l;
}
public long getT() {
	return T;
}
public void setT(long t) {
	T = t;
}
public boolean isM() {
	return m;
}
public void setM(boolean m) {
	this.m = m;
}

@Override
public String toString() {
	return "{a="+a+", p="+p+", q="+q+", T="+T+", m="+m+"},";
}
public Trade getInstance() {
	return this;
}




}
