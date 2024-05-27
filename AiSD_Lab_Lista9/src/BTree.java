import java.util.Comparator;

public class BTree<T> {
    private Node<T> root;
    private int t;
    private Comparator<T> comparator;
    public BTree(int t, Comparator<T> comparator) {
        if (t < 2) {
            throw new IllegalArgumentException("Stopien wezla t > 1");
        }
        this.root = null;
        this.t = t;
        this.comparator = comparator;
    }
    public Node<T> search(Node<T> node, T key) {
        int i = 0;
        while (i < node.getSize() && comparator.compare(key, node.getKeys()[i]) > 0) {
            i++;
        }
        if (i < node.getSize() && comparator.compare(key, node.getKeys()[i]) == 0) {
            return node;
        }
        if (node.isLeaf()) {
            return null;
        }
        else {
            return search(node.getChildren()[i], key);
        }
    }
    public void insert(T key) {
        if (root == null) {
            root = new Node<>(t, true);
            root.getKeys()[0] = key;
            root.increaseSize();
        }
        else {
            if (root.getSize() == 2 * t - 1) {
                Node<T> newRoot = new Node<>(t, false);
                newRoot.getChildren()[0] = root;
                splitChild(newRoot, 0, root);
                insertNonFull(newRoot, key);
                root = newRoot;
            }
            else {
                insertNonFull(root, key);
            }
        }
    }
    private void insertNonFull(Node<T> node, T k) {
        int i = node.getSize() - 1;
        if (node.isLeaf()) { //TODO insertKey in node class
            while (i >= 0 && comparator.compare(k, node.getKeys()[i]) < 0) {
                node.getKeys()[i + 1] = node.getKeys()[i];
                i--;
            }
            node.getKeys()[i + 1] = k;
            node.increaseSize();
        }
        else {
            while (i >= 0 && comparator.compare(k, node.getKeys()[i]) < 0) {
                i--;
            }
            i++;
            if (node.getChildren()[i].getSize() == 2 * t - 1) {
                splitChild(node, i, node.getChildren()[i]);
                if (comparator.compare(k, node.getKeys()[i]) > 0) {
                    i++;
                }
            }
            insertNonFull(node.getChildren()[i], k);
        }
    }
    private void splitChild(Node<T> x, int pos, Node<T> y) {
        Node<T> z = new Node<>(t, y.isLeaf());
        z.setSize(t - 1);

        for (int j = 0; j < t - 1; j++) {
            z.getKeys()[j] = y.getKeys()[j + t];
        }
        if (!y.isLeaf()) {
            for (int j = 0; j < t; j++) {
                z.getChildren()[j] = y.getChildren()[j + t];
            }
        }
        y.setSize(t - 1);

        for (int j = x.getSize(); j >= pos + 1; j--) {
            x.getChildren()[j + 1] = x.getChildren()[j];
        }
        x.getChildren()[pos + 1] = z;

        for (int j = x.getSize() - 1; j >= pos; j--) {
            x.getKeys()[j + 1] = x.getKeys()[j];
        }
        x.getKeys()[pos] = y.getKeys()[t - 1];
        x.increaseSize();
    }






}
