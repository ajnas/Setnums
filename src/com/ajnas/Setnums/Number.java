package com.ajnas.Setnums;

public class Number {
	
	
	private int num;
	private int exactx,exacty,x,y;
	
	
	
	
	public int getExactx() {
		return exactx;
	}
	public void setExactx(int exactx) {
		this.exactx = exactx;
	}
	public int getExacty() {
		return exacty;
	}
	public void setExacty(int exacty) {
		this.exacty = exacty;
	}
	public Number(int num, int x, int y,int exactx,int exacty) {
		super();
		this.num = num;
		this.exactx = exactx;
		this.exacty = exacty;
		this.x= x;
		this.y= y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void update(){
		
	}
	
	
	

}
