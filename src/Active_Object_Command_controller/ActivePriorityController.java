package Active_Object_Command_controller;

import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class ActivePriorityController {
    //WE use this class to achieve priority on the commands
    //Without changing the interface (keep is solid)
    public class PriorityCommand{
        public Command c;
        public int priority;

        public PriorityCommand(Command c, int priority) {
            this.c = c;
            this.priority = priority;
        }
    }

    //This queue will manage the execution of commands according to the priority + its thread saf
    PriorityBlockingQueue<PriorityCommand> queue;
    Thread thread;
    volatile boolean stop;

    public ActivePriorityController() {
        //100 - initial capacity
        // second argument is the comparing function
        queue = new PriorityBlockingQueue<PriorityCommand>(100, (a,b) -> a.priority - b.priority);
        thread = new Thread(() -> {
            while (!stop){
                //Take is telling the thread go to sleep if i am empty
                //poll will return null if is empty - a lot of thing to handle
                try {
                    queue.take().c.execute();
                } catch (InterruptedException e) {}
            }
        });
        thread.start();
    }

    public void addCommand(Command c, int priority){
        queue.add(new PriorityCommand(c,priority));
    }

    public void close(){
        //Adding new item to the queue, with execute that stops the thread from running
        //Give it max value so it execute last(low priority) and all the other tasks will execute
        queue.add(new PriorityCommand(() -> stop=true,Integer.MAX_VALUE));
    }
}
