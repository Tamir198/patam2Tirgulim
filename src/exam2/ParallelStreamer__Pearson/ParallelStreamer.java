package exam2.ParallelStreamer__Pearson;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;

public class ParallelStreamer<E> {

	ArrayList<BlockingQueue<E>> queues;
	int round;
	int size;
	volatile boolean stop;
	//Array or threads
	Thread[] threads;
	public ParallelStreamer(int size,int capacity,Consumer<E> consumer) {
		round=0;
		stop=false;
		this.size=size;
		queues=new ArrayList<>();
		threads=new Thread[size];

		for(int i=0;i<size;i++){
			BlockingQueue<E> q=new ArrayBlockingQueue<>(capacity);
			queues.add(q);
			threads[i]=new Thread(()->{
				while(!stop){
					try {
						consumer.accept(q.take());
					} catch (InterruptedException e) {}
				}
			});
			threads[i].start();
		}
	}


	public void add(E e) throws InterruptedException{
		if(!stop){
			queues.get(round).put(e);
			round=(round+1)%size;
		}
	}

	public void endOfInput(){
		stop=true;
		for(int i=0;i<threads.length;i++)
			threads[i].interrupt();
	}


}
