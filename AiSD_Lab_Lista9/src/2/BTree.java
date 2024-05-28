//import java.util.Comparator;
//public class BTree<T> {
//    private Node<T> root;
//    private int t;
//    private Comparator<T> comparator;
//    public BTree(int t, Comparator<T> comparator) {
//        if (t < 2) {
//            throw new IllegalArgumentException("Stopien wezla t > 1");
//        }
//        this.root = null;
//        this.t = t;
//        this.comparator = comparator;
//    }
//    public Object search(T key) {
//        return search(root, key);
//    }
//
//    private Object search(Node<T> node, T key) {
//        if (node == null) {
//            return null;
//        }
//
//        int idx = node.binarySearch(key, comparator);
//
//        if (idx != -1) {
//            return node.getKeys()[idx];
//        }
//
//        if (node.isLeaf()) {
//            return null;
//        }
//
//        int i = 0;
//        while (i < node.getSize() && comparator.compare(key, node.getKeys()[i]) > 0) {
//            i++;
//        }
//
//        return search(node.getChildren()[i], key);
//    }
//    private void splitChild(Node<T> x, int pos, Node<T> y) {
//        Node<T> z = new Node<>(t, y.isLeaf());
//        z.setSize(t - 1);
//
//        for (int j = 0; j < t - 1; j++) {
//            z.getKeys()[j] = y.getKeys()[j + t];
//        }
//        if (!y.isLeaf()) {
//            for (int j = 0; j < t; j++) {
//                z.getChildren()[j] = y.getChildren()[j + t];
//            }
//        }
//        y.setSize(t - 1);
//
//        for (int j = x.getSize(); j >= pos + 1; j--) {
//            x.getChildren()[j + 1] = x.getChildren()[j];
//        }
//        x.getChildren()[pos + 1] = z;
//
//        for (int j = x.getSize() - 1; j >= pos; j--) {
//            x.getKeys()[j + 1] = x.getKeys()[j];
//        }
//        x.getKeys()[pos] = y.getKeys()[t - 1];
//        x.increaseSize();
//    }
//
//    // Inserting a value
//    public void insert(T key) {
//        if (root == null) {
//            root = new Node<>(t, true);
//            root.getKeys()[0] = key;
//            root.increaseSize();
//        } else {
//            if (root.getSize() == 2 * t - 1) {
//                Node<T> newRoot = new Node<>(t, false);
//                newRoot.getChildren()[0] = root;
//                splitChild(newRoot, 0, root);
//                insertNonFull(newRoot, key);
//                root = newRoot;
//            } else {
//                insertNonFull(root, key);
//            }
//        }
//    }
//
//    private void insertNonFull(Node<T> node, T k) {
//        if (node.isLeaf()) {
//            node.insertKey(k, comparator);
//        } else {
//            int i = node.getSize() - 1;
//            while (i >= 0 && comparator.compare(k, node.getKeys()[i]) < 0) {
//                i--;
//            }
//            i++;
//            if (node.getChildren()[i].getSize() == 2 * t - 1) {
//                splitChild(node, i, node.getChildren()[i]);
//                if (comparator.compare(k, node.getKeys()[i]) > 0) {
//                    i++;
//                }
//            }
//            insertNonFull(node.getChildren()[i], k);
//        }
//    }
//    private Node<T> delete(Node<T> node, T key) {
//        int idx = node.binarySearch(key, comparator);
//        if (idx != -1) {
//            if (node.isLeaf()) {
//                //node.deleteKey(idx);
//                return node;
//            }
//            T predecessor = node.getKeys()[idx];
//            Node<T> child = node.getChildren()[idx];
//            if (child.getSize() >= t) {
//                T predecessorKey = getPredecessor(child);
//                node.getKeys()[idx] = predecessorKey;
//                delete(child, predecessorKey);
//                return node;
//            }
//            Node<T> sibling = getSibling(node, idx);
//            if (sibling.getSize() >= t) {
//                T successorKey = getSuccessor(child);
//                node.getKeys()[idx] = successorKey;
//                delete(sibling, successorKey);
//                return node;
//            }
//            merge(node, idx);
//            delete(child, predecessor);
//            return node;
//        }
//        int i = 0;
//        while (i < node.getSize() && comparator.compare(key, node.getKeys()[i]) > 0) {
//            i++;
//        }
//        Node<T> child = node.getChildren()[i];
//        if (child.getSize() == t - 1) {
//            Node<T> leftSibling = (i > 0) ? node.getChildren()[i - 1] : null;
//            Node<T> rightSibling = (i < node.getSize()) ? node.getChildren()[i + 1]: null;
//            if (leftSibling != null && leftSibling.getSize() >= t) {
//                borrowFromLeftSibling(node, i);
//            } else if (rightSibling != null && rightSibling.getSize() >= t) {
//                borrowFromRightSibling(node, i);
//            } else if (leftSibling != null) {
//                merge(node, i - 1);
//                child = leftSibling;
//            } else if (rightSibling != null) {
//                merge(node, i);
//            }
//        }
//        delete(child, key);
//        return node;
//    }
//
//    private T getPredecessor(Node<T> node) {
//        while (!node.isLeaf()) {
//            node = node.getChildren()[node.getSize()];
//        }
//        return node.getKeys()[node.getSize() - 1];
//    }
//
//    private T getSuccessor(Node<T> node) {
//        while (!node.isLeaf()) {
//            node = node.getChildren()[0];
//        }
//        return node.getKeys()[0];
//    }
//
//    private Node<T> getSibling(Node<T> node, int idx) {
//        if (idx == 0) {
//            return node.getChildren()[idx + 1];
//        } else if (idx == node.getSize()) {
//            return node.getChildren()[idx - 1];
//        } else {
//            return null;
//        }
//    }
//
//    private void borrowFromLeftSibling(Node<T> node, int idx) {
//        Node<T> child = node.getChildren()[idx];
//        Node<T> leftSibling = node.getChildren()[idx - 1];
//        T parentKey = node.getKeys()[idx - 1];
//
//        // Shift keys and child pointers in child node
//        for (int i = child.getSize() - 1; i >= 0; i--) {
//            child.getKeys()[i + 1] = child.getKeys()[i];
//        }
//        if (!child.isLeaf()) {
//            for (int i = child.getSize(); i >= 0; i--) {
//                child.getChildren()[i + 1] = child.getChildren()[i];
//            }
//        }
//
//        // Move key from parent to child
//        child.getKeys()[0] = parentKey;
//
//        // Move key from left sibling to parent
//        node.getKeys()[idx - 1] = leftSibling.getKeys()[leftSibling.getSize() - 1];
//
//        if (!leftSibling.isLeaf()) {
//            child.getChildren()[0] = leftSibling.getChildren()[leftSibling.getSize()];
//        }
//
//        child.increaseSize();
//        leftSibling.decreaseSize();
//    }
//
//    private void borrowFromRightSibling(Node<T> node, int idx) {
//        Node<T> child = node.getChildren()[idx];
//        Node<T> rightSibling = node.getChildren()[idx + 1];
//        T parentKey = node.getKeys()[idx];
//
//        // Move key from parent to child
//        child.getKeys()[child.getSize()] = parentKey;
//
//        // Move key from right sibling to parent
//        node.getKeys()[idx] = rightSibling.getKeys()[0];
//
//        if (!rightSibling.isLeaf()) {
//            child.getChildren()[child.getSize() + 1] = rightSibling.getChildren()[0];
//        }
//
//        // Shift keys and child pointers in right sibling node
//        for (int i = 1; i < rightSibling.getSize(); i++) {
//            rightSibling.getKeys()[i - 1] = rightSibling.getKeys()[i];
//        }
//        if (!rightSibling.isLeaf()) {
//            for (int i = 1; i <= rightSibling.getSize(); i++) {
//                rightSibling.getChildren()[i - 1] = rightSibling.getChildren()[i];
//            }
//        }
//
//        child.increaseSize();
//        rightSibling.decreaseSize();
//    }
//
//    private void merge(Node<T> node, int idx) {
//        Node<T> child = node.getChildren()[idx];
//        Node<T> sibling = node.getChildren()[idx + 1];
//
//        // Move key from node to child
//        child.getKeys()[t - 1] = node.getKeys()[idx];
//
//        // Move keys from sibling to child
//        for (int i = 0; i < sibling.getSize(); i++) {
//            child.getKeys()[t + i] = sibling.getKeys()[i];
//        }
//
//        // Move children from sibling to child
//        if (!sibling.isLeaf()) {
//            for (int i = 0; i <= sibling.getSize(); i++) {
//                child.getChildren()[t + i] = sibling.getChildren()[i];
//            }
//        }
//
//        // Shift keys and children in node to fill the gap
//        for (int i = idx + 1; i < node.getSize(); i++) {
//            node.getKeys()[i - 1] = node.getKeys()[i];
//        }
//        for (int i = idx + 2; i <= node.getSize(); i++) {
//            node.getChildren()[i - 1] = node.getChildren()[i];
//        }
//
//        child.setSize(2 * t - 1);
//        for (int i = idx; i < node.getSize() - 1; i++) {
//            node.getKeys()[i] = node.getKeys()[i + 1];
//        }
//        for (int i = idx + 1; i < node.getSize(); i++) {
//            node.getChildren()[i] = node.getChildren()[i + 1];
//        }
//        node.decreaseSize();
//    }
//
//    public void print() {
//        print(root, "", true);
//    }
//
//    private void print(Node<T> node, String prefix, boolean isTail) {
//        if (node != null) {
//            System.out.println(prefix + (isTail ? "└── " : "├── ") + nodeKeys(node));
//            for (int i = 0; i < node.getSize(); i++) {
//                if (node.getChildren()[i] != null) {
//                    print(node.getChildren()[i], prefix + (isTail ? "    " : "│   "), false);
//                }
//            }
//            if (node.getChildren()[node.getSize()] != null) {
//                print(node.getChildren()[node.getSize()], prefix + (isTail ? "    " : "│   "), true);
//            }
//        }
//    }
//
//    private String nodeKeys(Node<T> node) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("[");
//        for (int i = 0; i < node.getSize(); i++) {
//            sb.append(node.getKeys()[i]);
//            if (i < node.getSize() - 1) {
//                sb.append(", ");
//            }
//        }
//        sb.append("]");
//        return sb.toString();
//    }
//
//
//
//
//}
