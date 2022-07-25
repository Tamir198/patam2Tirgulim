package test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class Par {
	ExecutorService es;
	
	public Par(int nThreads) {
		es=Executors.newFixedThreadPool(nThreads);
	}

	public <V> Future<V> fold(V[] buff, BinaryOperator<V> op){
		return es.submit(()->{
			V v=buff[0];
			for(int i=1;i<buff.length;i++)
				v=op.apply(v, buff[i]);
			return v;
		});
	}
	
	public <V,R> Future<List<R>> map(V[] buff,Function<V,R> mapper){
		return es.submit(()->{
			List<R> r=new ArrayList<R>();
			for(V v : buff)
				r.add(mapper.apply(v));
			return r;
		});
	}
	
	public void close(){
		es.shutdown();
	}
}
