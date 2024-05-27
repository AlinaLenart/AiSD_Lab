//import java.util.Comparator;
//
//public class IntBST<Integer> extends BinarySearchTree<Integer> {
//    private int max;
//
//    public IntBST(Comparator<Integer> comparator) {
//        super(comparator);
//        this.max = 0;
//    }
//
//    public IntBST(Comparator<Integer> comparator, Node<Integer> newRoot) {
//        super(comparator);
//        setRoot(newRoot);
//        this.max = 0;
//    }
//
//    public IntBST<Integer> maxSumSubtree(int n) {
//        if (getRoot() == null || n < 1) {
//            throw new IllegalStateException("Root is null or invalid n");
//        }
//
//        IntBST<Integer> maxSubtree = new IntBST<>(super.getComparator());
//        max = 0;
//        maxSumSubtree(getRoot(), n, maxSubtree);
//        return maxSubtree;
//    }
//
//    private void maxSumSubtree(Node<Integer> node, int n, IntBST<Integer> maxSubtree) {
//        if (node == null) {
//            return;
//        }
//
//        int leftSum = sumSubtree(node.getLeft());
//        int rightSum = sumSubtree(node.getRight());
//
//        int subtreeSum = leftSum + rightSum + (int) node.getKey();
//
//        if (subtreeSum <= n && subtreeSum > max) {
//            max = subtreeSum;
//            maxSubtree.setRoot(new Node<>(node.getKey()));
//            maxSubtree.getRoot().setLeft(node.getLeft());
//            maxSubtree.getRoot().setRight(node.getRight());
//        }
//
//        maxSumSubtree(node.getLeft(), n, maxSubtree);
//        maxSumSubtree(node.getRight(), n, maxSubtree);
//    }
//
//    private int sumSubtree(Node<Integer> node) {
//        if (node == null) {
//            return 0;
//        }
//        int left = sumSubtree(node.getLeft());
//        int right = sumSubtree(node.getRight());
//        int root = (int) node.getKey();
//        return left + right + root;
//    }
//}
