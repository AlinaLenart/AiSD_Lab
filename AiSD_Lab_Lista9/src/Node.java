import java.util.Comparator;

public class Node<T> {
    private T[] keys;
    private Node<T>[] children;
    private boolean leaf;
    private final int t;
    private int size;
    @SuppressWarnings("unchecked")
    public Node(int t, boolean leaf) {
        this.t = t;
        this.leaf = leaf;
        this.keys = (T[]) new Object[2 * t - 1];
        this.children = new Node[2 * t];
        this.size = 0;
    }
    public int findKey(T key, Comparator<T> comp) {
        for (int i = 0; i < size; i++) {
            if (comp.compare(keys[i], key) == 0) {
                return i;
            }
        }
        return -1;
    }
    public void insertKey(T key){

    }

    public T[] getKeys() {
        return keys;
    }

    public Node[] getChildren() {
        return children;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public int getT() {
        return t;
    }

    public int getSize() {
        return size;
    }

    public void setKeys(T[] keys) {
        this.keys = keys;
    }

    public void setChildren(Node<T>[] children) {
        this.children = children;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public void increaseSize() {
        size++;
    }
    public void decreaseSize() {
        size--;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
