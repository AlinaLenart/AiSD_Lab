package valid;
import java.util.Comparator;
public class BTree<T> {
    private Node<T> root;
    private final int t;
    private final Comparator<T> comp;

    BTree(int t, Comparator<T> comp) {
        this.root = new Node<>(t, true);
        this.t = t;
        this.comp = comp;
    }
    public boolean search(T key, Node<T> node) {
        if (node == null) {
            node = root;
        }

        int idx = node.findKey(key, comp);

        if (idx >= 0) {
            return true;
        } else {
            idx = -(idx + 1); //liczenie do ktorego dziecka przechodzimy
            if (node.isLeaf()) {
                return false; //nie znaleziono - bo to lisc
            }
            else {
                return search(key, node.getChildren()[idx]);
            }
        }
    }

    public void splitChild(Node<T> x, int i) {
        int t = this.t;

        Node<T> y = x.getChildren()[i];
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

        for (int j = x.getSize(); j >= i + 1; j--) {
            x.getChildren()[j + 1] = x.getChildren()[j];
        }

        x.getChildren()[i + 1] = z;

        for (int j = x.getSize() - 1; j >= i; j--) {
            x.getKeys()[j + 1] = x.getKeys()[j];
        }

        x.getKeys()[i] = y.getKeys()[t - 1];
        x.increaseSize();
    }

    public void insert(T k) {
        if(search(k, root)){
            throw new IllegalArgumentException("Key " + k + " already exists");
        }
        if(k == null){
            throw new IllegalArgumentException("Null is not a value");
        }
        Node<T> root = this.root;
        if (root.getSize() == (2 * t) - 1) {
            Node<T> newRoot = new Node<>(t, false);
            this.root = newRoot;
            newRoot.getChildren()[0] = root;
            splitChild(newRoot, 0);
            insertNonFull(newRoot, k);
        } else {
            insertNonFull(root, k);
        }
    }

    public void insertNonFull(Node<T> x, T k) {
        int i = x.getSize() - 1;

        if (x.isLeaf()) {
            while (i >= 0 && comp.compare(k, x.getKeys()[i]) < 0) {
                x.getKeys()[i + 1] = x.getKeys()[i];
                i--;
            }
            x.getKeys()[i + 1] = k;
            x.increaseSize();
        }
        else {
            while (i >= 0 && comp.compare(k, x.getKeys()[i]) < 0) {
                i--;
            }
            i++;
            if (x.getChildren()[i].getSize() == (2 * t) - 1) {
                splitChild(x, i);
                if (comp.compare(k, x.getKeys()[i]) > 0) {
                    i++;
                }
            }
            insertNonFull(x.getChildren()[i], k);
        }
    }
    public void delete(T k){
        if(!search(k, root)){
            return;
        }
        deleteHelper(root, k);
    }
    public void deleteHelper(Node<T> x, T k) {
        int i = 0;
        while (i < x.getSize() && comp.compare(k, x.getKeys()[i]) > 0) {
            i++;
        }
        if (x.isLeaf()) {
            if (i < x.getSize() && comp.compare(k, x.getKeys()[i]) == 0) {
                for (int j = i; j < x.getSize() - 1; j++) {
                    x.getKeys()[j] = x.getKeys()[j + 1];
                }
                x.decreaseSize();
            }
            return;
        }

        if (i < x.getSize() && comp.compare(k, x.getKeys()[i]) == 0) {
            deleteInternalNode(x, k, i);
        } else if (x.getChildren()[i].getSize() >= t) {
            deleteHelper(x.getChildren()[i], k);
        } else {
            if (i != 0 && i + 1 < x.getChildren().length) {
                if (x.getChildren()[i - 1].getSize() >= t) {
                    deleteSibling(x, i, i - 1);
                } else if (x.getChildren()[i + 1].getSize() >= t) {
                    deleteSibling(x, i, i + 1);
                } else {
                    deleteMerge(x, i, i + 1);
                }
            } else if (i == 0) {
                if (x.getChildren()[i + 1].getSize() >= t) {
                    deleteSibling(x, i, i + 1);
                } else {
                    deleteMerge(x, i, i + 1);
                }
            } else if (i + 1 == x.getChildren().length) {
                if (x.getChildren()[i - 1].getSize() >= t) {
                    deleteSibling(x, i, i - 1);
                } else {
                    deleteMerge(x, i, i - 1);
                }
            }
            deleteHelper(x.getChildren()[i], k);
        }
    }

    public void deleteInternalNode(Node<T> x, T k, int i) {
        int t = this.t;
        if (x.isLeaf()) {
            if (x.getKeys()[i] == k) {
                for (int j = i; j < x.getSize() - 1; j++) {
                    x.getKeys()[j] = x.getKeys()[j + 1];
                }
                x.decreaseSize();
            }
            return;
        }

        if (x.getChildren()[i].getSize() >= t) {
            x.getKeys()[i] = deletePredecessor(x.getChildren()[i]);
        } else if (x.getChildren()[i + 1].getSize() >= t) {
            x.getKeys()[i] = deleteSuccessor(x.getChildren()[i + 1]);
        } else {
            deleteMerge(x, i, i + 1);
            deleteInternalNode(x.getChildren()[i], k, t - 1);
        }
    }

    public T deletePredecessor(Node<T> x) {
        if (x.isLeaf()) {
            T temp = x.getKeys()[x.getSize() - 1];
            x.decreaseSize();
            return temp;
        }

        int n = x.getSize() - 1;
        if (x.getChildren()[n].getSize() >= t) {
            deleteSibling(x, n + 1, n);
        } else {
            deleteMerge(x, n, n + 1);
        }
        return deletePredecessor(x.getChildren()[n]);
    }

    public T deleteSuccessor(Node<T> x) {
        if (x.isLeaf()) {
            T temp = x.getKeys()[0];
            for (int i = 0; i < x.getSize() - 1; i++) {
                x.getKeys()[i] = x.getKeys()[i + 1];
            }
            x.decreaseSize();
            return temp;
        }

        if (x.getChildren()[1].getSize() >= t) {
            deleteSibling(x, 0, 1);
        } else {
            deleteMerge(x, 0, 1);
        }
        return deleteSuccessor(x.getChildren()[0]);
    }

    public void deleteMerge(Node<T> x, int i, int j) {
        Node<T> cnode = x.getChildren()[i];

        if (j > i) {
            Node<T> rsnode = x.getChildren()[j];
            cnode.getKeys()[cnode.getSize()] = x.getKeys()[i];
            for (int k = 0; k < rsnode.getSize(); k++) {
                cnode.getKeys()[cnode.getSize() + 1 + k] = rsnode.getKeys()[k];
            }
            if (!rsnode.isLeaf()) {
                for (int k = 0; k <= rsnode.getSize(); k++) {
                    cnode.getChildren()[cnode.getSize() + 1 + k] = rsnode.getChildren()[k];
                }
            }
            for (int k = i; k < x.getSize() - 1; k++) {
                x.getKeys()[k] = x.getKeys()[k + 1];
            }
            for (int k = j; k < x.getSize(); k++) {
                x.getChildren()[k] = x.getChildren()[k + 1];
            }
            x.decreaseSize();
            int newSize = cnode.getSize() + rsnode.getSize() + 1;
            cnode.setSize(newSize);
        } else {
            Node<T> lsnode = x.getChildren()[j];
            lsnode.getKeys()[lsnode.getSize()] = x.getKeys()[j];
            for (int k = 0; k < cnode.getSize(); k++) {
                lsnode.getKeys()[lsnode.getSize() + 1 + k] = cnode.getKeys()[k];
            }
            if (!cnode.isLeaf()) {
                for (int k = 0; k <= cnode.getSize(); k++) {
                    lsnode.getChildren()[lsnode.getSize() + 1 + k] = cnode.getChildren()[k];
                }
            }
            for (int k = j; k < x.getSize() - 1; k++) {
                x.getKeys()[k] = x.getKeys()[k + 1];
            }
            for (int k = i; k < x.getSize(); k++) {
                x.getChildren()[k] = x.getChildren()[k + 1];
            }
            x.decreaseSize();
            int newSize = lsnode.getSize() + cnode.getSize() + 1;
            lsnode.setSize(newSize);
        }

        if (x == this.root && x.getSize() == 0) {
            this.root = cnode;
        }
    }

    public void deleteSibling(Node<T> x, int i, int j) {
        Node<T> cnode = x.getChildren()[i];

        if (i < j) {
            Node<T> rsnode = x.getChildren()[j];
            cnode.getKeys()[cnode.getSize()] = x.getKeys()[i];
            cnode.increaseSize();
            x.getKeys()[i] = rsnode.getKeys()[0];
            for (int k = 1; k < rsnode.getSize(); k++) {
                rsnode.getKeys()[k - 1] = rsnode.getKeys()[k];
            }
            if (!rsnode.isLeaf()) {
                cnode.getChildren()[cnode.getSize()] = rsnode.getChildren()[0];
                for (int k = 1; k <= rsnode.getSize(); k++) {
                    rsnode.getChildren()[k - 1] = rsnode.getChildren()[k];
                }
            }
            rsnode.decreaseSize();
        } else {
            Node<T> lsnode = x.getChildren()[j];
            for (int k = cnode.getSize(); k > 0; k--) {
                cnode.getKeys()[k] = cnode.getKeys()[k - 1];
            }
            cnode.getKeys()[0] = x.getKeys()[i - 1];
            cnode.increaseSize();
            x.getKeys()[i - 1] = lsnode.getKeys()[lsnode.getSize() - 1];
            if (!lsnode.isLeaf()) {
                for (int k = cnode.getSize(); k > 0; k--) {
                    cnode.getChildren()[k] = cnode.getChildren()[k - 1];
                }
                cnode.getChildren()[0] = lsnode.getChildren()[lsnode.getSize()];
            }
            lsnode.decreaseSize();
        }
    }

    public void printTree(Node<T> x, int level) {
        System.out.print("Level " + level + ": ");
        for (int i = 0; i < x.getSize(); i++) {
            System.out.print(x.getKeys()[i] + " ");
        }
        System.out.println();
        level++;
        if (!x.isLeaf()) {
            for (int i = 0; i <= x.getSize(); i++) {
                printTree(x.getChildren()[i], level);
            }
        }
    }

    public Node<T> getRoot() {
        return root;
    }
}
