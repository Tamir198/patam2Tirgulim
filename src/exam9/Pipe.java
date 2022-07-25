package exam9;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Pipe<E> implements Stoppable {
    ArrayBlockingQueue<E> queue;
    Consumer<E> c;
    Thread t;
    volatile boolean stop;
    Stoppable next;

    public Pipe() {
        queue=new ArrayBlockingQueue<>(100);
        t=new Thread(()->{
            while(!stop) {
                try {
                    if(c!=null)
                        c.accept(queue.take());
                } catch (InterruptedException e) {}
            }
        });
        t.start();
    }

    public void add(E e) {
        try {
            queue.put(e);
        } catch (InterruptedException e1) {}
    }

    public Pipe<E> filter(Predicate<E> p){
        Pipe<E> next=new Pipe<>();
        c=e->{if(p.test(e)) next.add(e);};
        this.next=next;
        return next;
    }

    public <R> Pipe<R> map(Function<E, R> f){
        Pipe<R> next=new Pipe<>();
        c=e->next.add(f.apply(e));
        this.next=next;
        return next;
    }

    public void forEach(Consumer<E> c) {
        this.c=c;
    }


    @Override
    public void stop() {
        stop=true;
        t.interrupt();
        if(next!=null)
            next.stop();
    }
}

