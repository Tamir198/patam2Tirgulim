//package test;
package exam7;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class Par {
    ExecutorService es;

    public Par(int maxThreads) {
        es = Executors.newFixedThreadPool(maxThreads);
    }

    public <V> Future<V> fold(V[] buff, BinaryOperator<V> op) {
        return es.submit(() -> {
            V result = buff[0];

            for (int i = 1; i < buff.length; i++)
                result = op.apply(result, buff[i]);

            return result;
        });
    }

    <V, R> Future<List<R>> map(V[] buff, Function<V, R> function) {
        return es.submit(() -> {
            List<R> res = new LinkedList<>();
            for (V element : buff) {
                res.add(function.apply(element));
            }
            return res;
        });
    }

    void close() {
        es.shutdown();
    }
}
