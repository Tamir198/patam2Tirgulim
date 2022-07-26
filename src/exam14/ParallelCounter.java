package exam14;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class ParallelCounter {


    public static <V> int parallelCountIf(List<V> list, Predicate<V> p, int threadsNum){
        AtomicInteger atomicInteger = new AtomicInteger();
        Thread t = new Thread(() -> {
            for (V item: list) {
                int res = p.test(item)? 1 : -1;
                res += atomicInteger.get();
                atomicInteger.set(res);

            }
        });

        t.start();

        try {
            //Use join to wait for current thread to die
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return atomicInteger.get();

    }

}
