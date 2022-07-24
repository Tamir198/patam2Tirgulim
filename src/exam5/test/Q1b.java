package exam5.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Q1b {
	BlockingQueue<Runnable> q;
	volatile boolean stop;
	public Q1b() {
		q=new ArrayBlockingQueue<>(100);
		stop=false;

		new Thread(
				()->{
					while(!stop){
						try {
							q.take().run();
						} catch (InterruptedException e) {}
					}
				}
		).start();
	}

	public void push(Runnable r){
		if(!stop)
			q.add(r);
	}

	public void close(){
		q.add(()->stop=true);
	}
}