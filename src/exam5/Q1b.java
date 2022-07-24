package exam5;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Q1b {

	BlockingQueue<Runnable> queue;
	volatile  boolean stop;
	Thread thread;

	public Q1b() {
		queue = new ArrayBlockingQueue<>(1);

		thread = new Thread(() -> {
			while (!stop){
				try {
					//Take is thread friendly
					queue.take().run();
				} catch (InterruptedException e) {}
			}
		});

		thread.start();
	}


	public void push(Runnable r){
		//We use put because add returns boolean value and this is void method
		try {
			queue.put(r);
		} catch (InterruptedException e) {}
	}
	
	public void close(){
		stop = true;
		thread.interrupt();
	}
}