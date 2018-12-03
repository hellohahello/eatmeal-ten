package com.dandan;

public class Box {
	public static void main(String[] args) {
		Box box=new Box();
		Consume consume=new Consume(box);
		Produce produce=new Produce(box);
		new Thread(produce).start();
		new Thread(consume).start();
	}
	boolean flag=true;
	private Object[] elementData;
	private String name;
	int index=0;
	Box(int initialCapacity){
		elementData=new Object[initialCapacity];
	}
	public Box() {
		this(1000);
	}
	synchronized void play(String name) throws InterruptedException{
		
		if (!flag) {
			//����ֹͣ
			this.wait();
		}//��ʼ����
		Thread.sleep(500);
		this.name=name;
		System.out.println("�����˵�"+(++index)+"��"+name);
		notify();
		flag=false;
	}
	synchronized void eat() throws InterruptedException{
		if (flag) {
			this.wait();
		}
		//��ʼ����
		Thread.sleep(200);
		System.out.println("�����˵�"+index+"��"+name);
		notify();
		flag=true;
	}

}
class Produce implements Runnable{
private Box box;
public Produce(Box box) {
	this.box=box;
}
public Produce() {
}
	@Override
	public void run() {
		for(int i=0;i<20;i++){
			if (i%2==0) {
				try {
					box.play("��ͷ");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}else{
				try {
					box.play("����");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
class Consume implements Runnable{
private Box box;
public Consume(Box box) {
	this.box=box;
}
public Consume() {
}
	@Override
	public void run() {
for(int i=0;i<20;i++){
				try {
					box.eat();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
