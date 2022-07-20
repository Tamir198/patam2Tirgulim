package threads_basic;

public class MyTaskExtendsThread extends Thread{
    int x;
    volatile boolean stop;

    public MyTaskExtendsThread(int x) {
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
