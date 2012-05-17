package com.juntest.concurrent;

public class PingPong implements Runnable {
	
	static volatile boolean flag = true;
	
	private String id;
	private boolean myFlag;

	public PingPong(String id, boolean flag){
		
		this.id = id;
		this.myFlag = flag;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while (true){
			
			synchronized (PingPong.class){
				
				//while ( odd && count%2==0 || !odd && count%2==1){
				while ( flag!=myFlag){
					try {
						PingPong.class.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("My turn: " + id);
			
			synchronized (PingPong.class){				
				flag = !flag;				
				PingPong.class.notifyAll();
			}
		}
	}	
	
	
	public static void main(String[] args){
		PingPong ping = new PingPong("Ping", true);
		PingPong pong = new PingPong("Pong", false);
		
		new Thread(ping).start();
		new Thread(pong).start();
	}
}
