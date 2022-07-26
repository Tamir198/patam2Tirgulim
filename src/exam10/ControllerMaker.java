package exam10;
public class ControllerMaker {

    //This is my answer that is working, in the exam answers they created helper function
//    private static class Helper{
//        public static Controller c=new Controller();
//    }
//
//    public static Controller get() {
//        return Helper.c;
//    }//

    private static final Controller controller = new Controller();
    public static Controller get() {
        return controller;
    }
}
