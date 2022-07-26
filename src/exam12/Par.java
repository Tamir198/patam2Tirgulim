package exam12;

import java.util.concurrent.RecursiveTask;

public class Par extends RecursiveTask<Integer> {
    BinTree tree;

    public Par(BinTree tree) {
        this.tree = tree;
    }


    @Override
    protected Integer compute() {
        if(tree.left == null && tree.right == null)
            return tree.get();

        Par left = new Par(tree.getLeft());
        left.fork();
        Par right = new Par(tree.getRight());

        return tree.get() + left.join() + right.compute();


    }
}