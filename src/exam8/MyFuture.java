package exam8;


import Frome_Active_Object_To_Thread_pool.Future;

import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;

public class MyFuture<V> {
    V value;
    Runnable r;

    public MyFuture() {
        r = new Thread(() ->{});
    }

    public void set(V value) {
        this.value=value;
        r.run();
    }

    public <R> MyFuture<R> thenDo(Function<V, R> func) {
        MyFuture<R> myFuture=new MyFuture<>();
        r=()->myFuture.set(func.apply(value));
        return myFuture;
    }
    public void finallyDo(Consumer<V> c) {
        r=()->c.accept(value);
    }
}


