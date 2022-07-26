package exam14;
import java.util.concurrent.RecursiveTask;

public class LucasNumbers extends RecursiveTask<Integer> {
    int index;

    public LucasNumbers(int index) {
        this.index = index;
    }

    public Integer compute() {
        if(index == 0) return 2;
        if(index == 1) return 1;

        LucasNumbers lucasNumbers1 = new LucasNumbers(index-1);
        LucasNumbers lucasNumbers2 = new LucasNumbers(index-2);

        lucasNumbers1.fork();

        return (lucasNumbers2.compute() + lucasNumbers1.join());
	}

}
