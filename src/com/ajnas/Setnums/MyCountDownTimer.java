package com.ajnas.Setnums;
import android.os.CountDownTimer;

public class MyCountDownTimer extends Thread  {
	
	
	private long remaining;
	private CountDownTimer counter;

	public MyCountDownTimer(long startingTime){
		this.remaining = startingTime/1000;
		
		
	}
	
	
	
	@Override
	public synchronized void start() {
		
		super.start();
	}
	
	@Override
	public void run() {
		
	}
	public long getRemaining() {
		return remaining;
	}



	public void setRemaining(long remaining) {
		this.remaining = remaining;
	}



	public void onResume(){
		counter= new CountDownTimer(remaining*1000,1000) {
			
			@Override
			public void onTick(long millisUntilFinished) {
				remaining=millisUntilFinished/1000;
				
				
			}
			
			@Override
			public void onFinish() {
				
				remaining=0;
				
			}
		}.start();
	
		
	}
	
	public void onPause(){
		counter.cancel();
	}
	
	
	



}
