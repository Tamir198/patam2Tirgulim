package exam11;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

public class ParMaxSearcher extends RecursiveTask<Integer> {

	private static final long serialVersionUID = 1L;
	BinTree tree;

	public ParMaxSearcher(BinTree tree) {
		this.tree = tree;
	}

	@Override
	protected Integer compute() {
		if(tree.left == null && tree.right == null){
			return tree.get();
		}else{
			ParMaxSearcher left = new ParMaxSearcher(tree.left);
			left.fork();
			ParMaxSearcher right = new ParMaxSearcher(tree.right);
			right.compute();
			left.join();

			return Math.max(left.compute(),right.compute());
		}
	}

}
