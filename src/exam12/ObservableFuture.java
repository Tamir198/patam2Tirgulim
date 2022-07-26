package exam12;

import java.util.Observable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ObservableFuture<V> extends Observable {

    V submit;

    public ObservableFuture(Future<V> submit) {
        new Thread(() -> {
            try {
                this.submit = submit.get();
                setChanged();
                notifyObservers();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public V get() {
        return submit;
    }

}
