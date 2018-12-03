package com.dandan;

public class Movie {
	boolean flag=false;
	private String name;
	Movie(){
		
	}
	synchronized void play(String name)throws Exception{

		if (flag) {
			try {

				this.wait();
			} catch (Exception e) {
			}
		}

		Thread.sleep(500);

		this.name=name;
		System.out.println("冯提莫"+name);
		//唤醒另一个线程
		notify();
		flag=true;
	}
	synchronized void look() throws InterruptedException{
		if (!flag) {
			//线程等待
			wait();
		}
		//锁
		Thread.sleep(300);
		System.out.println("高圆圆"+name);
//		唤醒另一线程
		notify();
		flag=false;
	}
	public static void main(String[] args) {
		Movie movie=new Movie();
		Consumer consumer=new Consumer(movie);
		Producer producer=new Producer(movie);
		new Thread(producer).start();
		new Thread(consumer).start();
	}
}

class Producer implements Runnable{
	private Movie movie;
	public Producer(Movie movie) {
		this.movie=movie;
	}
	@Override
	public  void run() {
		for(int i=0;i<20;i++){
			if (i%2==0) {
				try {
					movie.play("...");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				try {
					movie.play("...");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
			}
		}
	}
}
//
class Consumer implements Runnable{
	private Movie movie;
	public Consumer(Movie movie) {
		this.movie=movie;
	}
	@Override
	public void run(){
		for(int i=0;i<20;i++){
			try {
				movie.look();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}