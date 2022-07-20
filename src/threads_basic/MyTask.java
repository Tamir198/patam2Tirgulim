package threads_basic;

public class MyTask{
    int x;
    volatile boolean stop;

    public MyTask(int x) {
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

}
