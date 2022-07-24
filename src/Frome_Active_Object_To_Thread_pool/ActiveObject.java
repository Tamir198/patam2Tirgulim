package Frome_Active_Object_To_Thread_pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ActiveObject {

    BlockingQueue<Runnable> queue;
    volatile  boolean stop;
    Thread thread;

    public ActiveObject(int capacity) {
        queue = new ArrayBlockingQueue<>(capacity);
        //The arrow function if another way to override the run method
        thread = new Thread(() -> {
            while (!stop){
                try {
                    //Take is thread friendly
                    queue.take().run();
                } catch (InterruptedException e) {}
            }
        });

        thread.start();
    }

    public void execute(Runnable r){
        if(stop) return;
        //We use put because add returns boolean value and this is void method
        try {
            queue.put(r);
        } catch (InterruptedException e) {}

    }

    public void shutDownNow(){
        stop=true;
        thread.interrupt();

    }

    public void shutDown(){
        execute(() -> stop=true);
    }

    public int size(){
        return queue.size();
    }
}
