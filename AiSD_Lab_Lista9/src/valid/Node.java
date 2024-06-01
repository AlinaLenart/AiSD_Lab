package valid;

import java.util.Comparator;

public class Node<T> {
    private T[] keys;
    private Node<T>[] children;
    private boolean leaf;
    private int size;

    @SuppressWarnings("unchecked")
    Node(int t, boolean leaf) {
        this.keys = (T[]) new Object[2 * t - 1];
        this.children = new Node[2 * t];
        this.leaf = leaf;
        this.size = 0;
    }

    public int findKey(T key, Comparator<T> comp) {
        int left = 0;
        int right = size - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = comp.compare(key, keys[mid]);

            if (cmp == 0) {
                return mid; //key found
            } else if (cmp < 0) {
                right = mid - 1; //left half
            } else {
                left = mid + 1; //right half
            }
        }
        return -(left + 1); //key not found
    }

    public void increaseSize(){
        size++;
    }
    public void decreaseSize(){
        size--;
    }
    public T[] getKeys() {
        return keys;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Node<T>[] getChildren() {
        return children;
    }
}
