import java.util.Comparator;

public class IntBST extends BinarySearchTree<Integer> {

    private static class Result {
        int sum;
        Node<Integer> subtreeRoot;

        Result(int sum, Node<Integer> subtreeRoot) {
            this.sum = sum;
            this.subtreeRoot = subtreeRoot;
        }
    }

    public IntBST(Comparator<Integer> comp) {
        super(comp);
    }

    public IntBST maxSumSubtree(int n) {  //O(n), space: O(log n) wysokosc drzewa zbalansowanego
        Result result = maxSumSubtree(root, n);
        IntBST subtree = new IntBST(getComparator());
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

    private void copySubtree(IntBST tree, Node<Integer> node) {
        if (node != null) {
            tree.insert(node.getKey());
            copySubtree(tree, node.getLeft());
            copySubtree(tree, node.getRight());
        }
    }

}
