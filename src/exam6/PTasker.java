package exam6;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.BinaryOperator;

public class PTasker {
    ExecutorService es;

    public PTasker() {
        es = Executors.newSingleThreadExecutor();
    }

    public <V> Future<V> apply(List<V> buff, V identity, BinaryOperator<V> bo) {
        //return es.submit(()->buff.stream().reduce(identity,bo));
       return es.submit(() ->{
            V result = identity;
            for (V element : buff) {
                result = bo.apply(result, element);
            }
            return result;
        });
    }

    public void close() {
        es.shutdown();
    }
}
