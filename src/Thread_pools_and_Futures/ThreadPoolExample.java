package Thread_pools_and_Futures;

import org.omg.Messaging.SyncScopeHelper;

import java.util.concurrent.*;

public class ThreadPoolExample {

    public static class ExampleTask implements Runnable {
        int id;

        public ExampleTask(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println("Task started" + id);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {}

            System.out.println("The task is finished" + id);
        }
    }

    //Callable - A task that returns a result and may throw an exception.
    // Implementors define a single method with no arguments called call.
    public static class ExampleTask2 implements Callable<Integer>{

        //Callable method - need to return Integer (ar any <V> type)
        @Override
        public Integer call(){
            //mimic "calculation" for 2 seconds
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {}
            //After calculator ended return
            return 42;
        }
    }

    public static void main(String[] args) {
        //To create thread pool we need some executor service
        ExecutorService es = Executors.newFixedThreadPool(3 );

        /***Use execute for void values and no calculations method
        Use submit for Callables - a task with calculations and resault **/
        es.execute(new ExampleTask(1));
        es.execute(new ExampleTask(2));
        es.execute(new ExampleTask(3));
        es.execute(new ExampleTask(4));
        es.execute(new ExampleTask(5));

        //Just like js async submit returns promise
        Future<Integer> myPromise =  es.submit(new ExampleTask2());
        try {
            //This is like await in js
            Integer i = myPromise.get();
            System.out.println(Thread.currentThread().getName() + "return Result from promise = " + i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {}


        //Wait for all tasks to be finished and only then c;close es
        es.shutdown();
        System.out.println("Main is death now");
    }

}
