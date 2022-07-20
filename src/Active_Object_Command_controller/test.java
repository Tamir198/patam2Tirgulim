package Active_Object_Command_controller;

public class test {
    //This is some class to mock background tasks
    public static class LongTask implements Command{
        String s;
        public LongTask(String s) {
            this.s = s;
        }
        @Override
        public void execute() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {}
            System.out.println("This is Long task value " + s);
        }
    }

    public static void main(String[] args) {
        ActivePriorityController apc = new ActivePriorityController();
        apc.addCommand(new LongTask("A"),2);
        apc.addCommand(new LongTask("B"),1);
        apc.addCommand(new LongTask("C"),0);
        apc.close();

        System.out.println("Main is done");

    }
}
