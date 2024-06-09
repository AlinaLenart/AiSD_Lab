import java.util.Comparator;

public class IntBSTTest extends BinarySearchTree<Integer> {

    private static class Result {
        int sum;
        Node<Integer> subtreeRoot;

        Result(int sum, Node<Integer> subtreeRoot) {
            this.sum = sum;
            this.subtreeRoot = subtreeRoot;
        }
    }

    public IntBSTTest(Comparator<Integer> comp) {
        super(comp);
    }

    public IntBSTTest maxSumSubtree(int n) {
        Result result = maxSumSubtree(root, n);
        IntBSTTest subtree = new IntBSTTest(getComparator());
        if (result.subtreeRoot != null) {
            copySubtree(subtree, result.subtreeRoot);
        }
        return subtree;
    }

    private Result maxSumSubtree(Node<Integer> node, int n) {
        if (node == null) {
            return new Result(0, null);
        }

        Result leftResult = maxSumSubtree(node.getLeft(), n);
        Result rightResult = maxSumSubtree(node.getRight(), n);

        int currentSum = node.getKey() + leftResult.sum + rightResult.sum;

        if (currentSum < n) {
            return new Result(currentSum, node);
        }

        if (leftResult.sum > rightResult.sum && leftResult.sum < n) {
            return leftResult;
        } else if (rightResult.sum < n) {
            return rightResult;
        } else {
            return new Result(0, null);
        }
    }

    private void copySubtree(IntBSTTest target, Node<Integer> node) {
        if (node != null) {
            target.insert(node.getKey());
            copySubtree(target, node.getLeft());
            copySubtree(target, node.getRight());
        }
    }

}
