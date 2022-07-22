package Fork__Join__pool;

import java.util.concurrent.RecursiveTask;

public class ParMaxSearcher extends RecursiveTask<Integer> {
    //Recursive task contains 2 cool methods:
    //Fork - splitting the object of the compute method and insert it into
    // thread pool(fork join pool) while calculating it parallel way
    //Join  waiting for the result from the Form method (saves a lot of Future handling)
    BinTree tree;

    public ParMaxSearcher(BinTree tree) {
        this.tree = tree;
    }

    //Calculation method,coming from Recursive task
    @Override
    protected Integer compute() {
        if(tree.getLeft() ==null && tree.getRight() == null)
            return tree.get();

        //LEft and right subtree
        ParMaxSearcher left = new ParMaxSearcher(tree.getLeft());
        ParMaxSearcher right = new ParMaxSearcher(tree.getRight());


        //tale left compute method and trow it inside a thread pool (now we can calculate left
        //and right in different threads to save time)
        left.fork();
        //Do the work on current thread
        right.compute();
        //left.join() Gets left integer(result from running compute of left) if ready and if not we need to wait
        return Math.max(tree.get(), Math.max(right.compute(),left.join()));
    }
}
