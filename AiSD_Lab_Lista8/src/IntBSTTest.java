import java.util.Comparator;

public class IntBSTTest extends BinarySearchTree<Integer> {

    private static class MaxSumSubtreeResult {
        int sum;
        IntBSTTest subtree;

        MaxSumSubtreeResult(int sum, IntBSTTest subtree) {
            this.sum = sum;
            this.subtree = subtree;
        }
    }

    public IntBSTTest(Comparator<Integer> comp) {
        super(comp);
    }


    public IntBSTTest maxSumSubtree(int n) {
        MaxSumSubtreeResult result = maxSumSubtree(root, n);
        return result.subtree;
    }


    private MaxSumSubtreeResult maxSumSubtree(Node<Integer> node, int n) {
        if (node == null) {
            return new MaxSumSubtreeResult(0, new IntBSTTest(getComparator()));
        }

        MaxSumSubtreeResult leftResult = maxSumSubtree(node.getLeft(), n);
        MaxSumSubtreeResult rightResult = maxSumSubtree(node.getRight(), n);

        int currentSum = node.getKey() + leftResult.sum + rightResult.sum;


        if (currentSum < n) {

            IntBSTTest subtree = new IntBSTTest(getComparator());
            subtree.insert(node.getKey());
            copySubtree(subtree, node.getLeft());
            copySubtree(subtree, node.getRight());
            return new MaxSumSubtreeResult(currentSum, subtree);
        }


        if (leftResult.sum > rightResult.sum && leftResult.sum < n) {
            return leftResult;
        }
        else if (rightResult.sum < n) {
            return rightResult;
        }
        else {
            return new MaxSumSubtreeResult(0, new IntBSTTest(getComparator()));
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