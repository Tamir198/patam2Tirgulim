package threads_basic;

public class MyTaskImplementingRunnable implements Runnable{
    int x;
    volatile boolean stop;

    public MyTaskImplementingRunnable(int x) {
        this.x = x;
    }

    public void doAction(){
        System.out.println(Thread.currentThread().getName());
        while (!stop){
            x++;
        }
    }

    public void stopMe(){
        stop=true;
    }

    public int getX() {
        return x;
    }

    //This runs in background - override method from Thread
    @Override
    public void run() {
        doAction();
    }
}
