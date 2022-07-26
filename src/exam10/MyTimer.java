package exam10;

public class MyTimer {

    volatile boolean stop;
    Thread thread;
    public MyTimer() {
        thread =null;
    }

    public void start(Runnable r, int timesPerSecond) throws Exception{
        if(thread ==null) {
            thread =new Thread(()->{
                while(!stop) {
                    r.run();
                    try {
                        Thread.sleep(1000/timesPerSecond);
                    } catch (InterruptedException e) {}
                }
            });
            thread.start();
        }else {
            throw new Exception("this timer already runs a task");
        }
    }

    public void stop() {
        stop=true;
        thread.interrupt();
        thread =null;
    }

}
