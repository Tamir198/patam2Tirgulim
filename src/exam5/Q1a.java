package exam5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Q1a {

	ExecutorService es;

	public Q1a() {
		es=Executors.newSingleThreadExecutor();
	}


	public void close(){
		es.shutdown();
	}



	//How to run something in background and return future
	public <V> Future<V> threadIt(Callable<V> callable){
		// submit also puts the object in the queue, and returns Future<Integer>
		return es.submit(callable);
	}

}
