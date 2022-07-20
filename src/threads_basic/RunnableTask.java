package threads_basic;

/**
 * This is an object adapter between MyTask classs to runnable
 * This way we dont need to extend Thread or implement Runnable
 * Like we did when we want to run something in the background
 *
 *
 * */
public class RunnableTask implements Runnable{

    MyTask t;

    public RunnableTask(MyTask t) {
        this.t = t;
    }

    @Override
    public void run() {
        t.doAction();
    }
}
