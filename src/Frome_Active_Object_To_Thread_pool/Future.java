package Frome_Active_Object_To_Thread_pool;

import java.util.function.Consumer;
import java.util.function.Function;

public class Future<V> {
    V v;
    //WE are using this to return an object from then apply
    //future.set(f.apply(v) that we inserted before
    Runnable task;

    public synchronized void set(V v) {
        this.v = v;
        //Run the
        task.run();
        //Wake up every one who went so sleep under await inside get method
        notifyAll();
    }

    //Wait must be together with synchronized
    public V get() {
        //Double check locking
        if (v == null) {
            synchronized (this) {
                try {
                    //This extra check is needed as part of double lock check
                    if (v == null) {
                        // causes current thread to wait until another thread invokes
                        // the notify() method or the notifyAll() method for this object.
                        wait();
                    }
                } catch (InterruptedException e) {
                }
            }

        }
        return v;
    }

    //This function will return Future<R> immediately
    //only after V will arrive this function will execute V and put the resault in
    //The future that we are returning
    public <R> Future<R> thenApply(Function<V,R> f){
        Future<R> future = new Future<>();
        //future.set(f.apply(v)) is the definition, we want to execute it
        //Inside set method, so we are doing the task = ...
        //When we will call task.run we will actually run it
        task = ()-> future.set(f.apply(v));
        return future;
    }

    public void thenAccept(Consumer<V> consumer) {
        task = ()->consumer.accept(v);
    }
}
