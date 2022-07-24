package test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Q1a {
	
	ExecutorService es;
	
	public Q1a() {
		es=Executors.newSingleThreadExecutor();
	}

	public <V> Future<V> threadIt(Callable<V> f){		
		return es.submit(f);
	}
	
	public void close(){
		es.shutdown();
	}
}
