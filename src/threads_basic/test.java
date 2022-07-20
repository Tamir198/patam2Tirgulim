package threads_basic;


public class test {

    public static void main(String[] args) {

        /************************************************************************************/
        //Same solution as before but without the RunnableTask classbnnb

        //How many active  threads are
        System.out.println(Thread.activeCount());
        //Name of current thread
        System.out.println(Thread.currentThread().getName());

        //The best solution is to create an object adapter,check RunnableTask

        MyTask myTask = new MyTask(0);
        //We can create anonymous class and not create RunnableTask class
        RunnableTask runnableTask = new RunnableTask(myTask);

        //If we need to do one thing we can crete thread like this
        //doAction will run in background thread
        //Thread thread = new Thread(() -> myTask.doAction());

        Thread thread = new Thread(runnableTask);
        //Calls runnableTask.run() in background
        thread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        myTask.stopMe();

        System.out.println(myTask.getX());


        /************************************************************************************/


//
//
//        MyTaskImplementingRunnable taskWithRunnable = new MyTaskImplementingRunnable(0);
//        //Create thread that accepts runnable
//        Thread thread = new Thread(taskWithRunnable);
//
//        thread.start();
//
//        try {
//            //pause the execution of current thread
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(taskWithRunnable.getX());

/************************************************************************************/
        //This is bad solution - when class extending thread
        //Better solution is to implement runnable
//
//        MyTaskExtendsThread task = new MyTaskExtendsThread(0);
//        /**
//         This is synchronized method
//         task.run();
//         * */
//
//        //Start is async function, executes run method in background
//        task.start();
//
//        try {
//            //pause the execution of current thread
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        //This will stop task thread from running after the 2 seconds delay
//        task.stopMe();
//        //Prints value after the thread is done,
//        //After some thread wakes up he goes to the ready queue and only after runnig
//        //Meaning - this next line can be different from run to run
//        System.out.println(task.getX());
//    }
    }
}
