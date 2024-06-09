//package Old;
//
//import java.util.Comparator;
//
//public class Node<T> {
//     T[] keys;
//     Node<T>[] children;
//     boolean leaf;
//     int size;
//
//    @SuppressWarnings("unchecked")
//    Node(int t, boolean leaf) {
//        this.keys = (T[]) new Object[2 * t - 1];
//        this.children = new Node[2 * t];
//        this.leaf = leaf;
//        this.size = 0;
//    }
//
//    public int findKey(T key, Comparator<T> comp) {
//        int left = 0;
//        int right = size - 1;
//
//        while (left <= right) {
//            int mid = left + (right - left) / 2;
//            int cmp = comp.compare(key, keys[mid]);
//
//            if (cmp == 0) {
//                return mid; //key found
//            } else if (cmp < 0) {
//                right = mid - 1; //left half
//            } else {
//                left = mid + 1; //right half
//            }
//        }
//        return -(left + 1); //key not found
//    }
//
//    public void insertNonFull(int k) {
//        int i = n - 1;
//        if (leaf) {
//            while (i >= 0 && keys[i] > k) {
//                keys[i + 1] = keys[i];
//                i--;
//            }
//            keys[i + 1] = k;
//            n++;
//        } else {
//            while (i >= 0 && keys[i] > k) {
//                i--;
//            }
//            i++;
//            if (children[i].n == 2 * t - 1) {
//                splitChild(i, children[i]);
//                if (keys[i] < k) {
//                    i++;
//                }
//            }
//            children[i].insertNonFull(k);
//        }
//    }
//
//    // Podział dziecka tego węzła. y jest pełnym dzieckiem.
//    public void splitChild(int i, BTreeNode y) {
//        BTreeNode z = new BTreeNode(y.t, y.leaf);
//        z.n = t - 1;
//
//        // Przenoszenie drugiej połowy kluczy y do z
//        for (int j = 0; j < t - 1; j++) {
//            z.keys[j] = y.keys[j + t];
//        }
//
//        // Przenoszenie drugiej połowy dzieci y do z
//        if (!y.leaf) {
//            for (int j = 0; j < t; j++) {
//                z.children[j] = y.children[j + t];
//            }
//        }
//
//        y.n = t - 1;
//
//        // Przesuwanie dzieci węzła rodzica
//        for (int j = n; j >= i + 1; j--) {
//            children[j + 1] = children[j];
//        }
//        children[i + 1] = z;
//
//        // Przesuwanie kluczy węzła rodzica
//        for (int j = n - 1; j >= i; j--) {
//            keys[j + 1] = keys[j];
//        }
//
//        // Przesuwanie środkowego klucza y do rodzica
//        keys[i] = y.keys[t - 1];
//        n++;
//    }
//
//
//    public void increaseSize(){
//        size++;
//    }
//    public void decreaseSize(){
//        size--;
//    }
//    public T[] getKeys() {
//        return keys;
//    }
//
//    public boolean isLeaf() {
//        return leaf;
//    }
//
//    public int getSize() {
//        return size;
//    }
//
//    public void setSize(int size) {
//        this.size = size;
//    }
//
//    public Node<T>[] getChildren() {
//        return children;
//    }
//}
