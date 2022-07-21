package Frome_Active_Object_To_Thread_pool;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
//Mimic Thread pool
public class MyThreadPool {
    //Generate thread safe data structure, this is Active object type
    //Because every active object is having its tasks related thread, and if the thread is empty
    //and no tasks available he goes to sleep
    BlockingQueue<ActiveObject> activeObjects;

    //This is out main thread - saves us a lot of heavy calculations in execute method
    Thread thread;

    BlockingQueue<Runnable> mainQueue;
    volatile boolean stop;
    Runnable stopAllTasks = () -> stop = true;

    public MyThreadPool(int capacity, int maxThreads) {
        activeObjects = new ArrayBlockingQueue<>(maxThreads);
        mainQueue = new ArrayBlockingQueue<>(capacity);

        thread = new Thread(() -> {
            while (!stop){
                try {
                    //Take is thread friendly
                    Runnable task = mainQueue.take();

                    if(task == stopAllTasks){
                        task.run();//This is the same as calling stop=true
                    }else{
                        //If we can open more thread (not at max capacity) add another thread
                        // (he will be inside the active object)
                        if(mainQueue.size() < maxThreads){
                            ActiveObject activeObject = new ActiveObject(capacity);
                            activeObject.execute(task);

                            activeObjects.add(activeObject);
                        }else{
                            //Insert the new task to the smallest active object
                            ActiveObject smallestExistingActiveObject = chooseSmallestActiveObject();
                            smallestExistingActiveObject.execute(task);
                        }

                    }

                } catch (InterruptedException e) {}
            }
        });

        thread.start();
    }

    private ActiveObject chooseSmallestActiveObject() {
        ActiveObject res = null;
        int min = Integer.MAX_VALUE;
        for (ActiveObject item: activeObjects) {
            if(item.size() < min){
                res = item;
                min = item.size();
            }

        }
        return res;
    }

    public void execute(Runnable r){
        //We want this method to be fast so the logic of "take the task and put it in x thread"
        //will be done in separate thread - in mainQueue thread
        if(stop) return;
        //We use put because add returns boolean value and this is void method
        try {
            mainQueue.put(r);
        } catch (InterruptedException e) {}


    }

    public void shutdownNow(){
        stop = true;
        activeObjects.forEach(item -> item.shutDownNow());
        thread.interrupt();
    }

    public void shutdown(){
        activeObjects.forEach(item -> item.shutDownNow());
        //Make sure that this is executed inside current/main(for the class) thread
        //And not in one of the active objects
        execute(stopAllTasks);

    }

    public <V>  Future<V> submit (Callable<V> c){
        Future<V> res = new Future<>();
        //() -> execute runnable, return the future on the spot
        //execute accept runnable that run call() abd put the return value from call inside
        //The future
        execute(() -> res.set(c.call()));

        return res;
    }

}
