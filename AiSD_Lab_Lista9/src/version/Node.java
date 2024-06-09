package version;
import java.util.*;


public class Node<T> {
    ArrayList<T> keys;
    ArrayList<Node<T>> children;
    boolean isLeaf;
    int size; //chyba nie trzeba
    final int t;

    public Node(int t, boolean isLeaf) {
        this.children = new ArrayList<>();
        this.keys = new ArrayList<>();
        this.isLeaf = isLeaf;
        this.size = 0;
        this.t = t;
    }

    public Node<T> search(T k, Comparator<T> comp) {
        int i = binarySearch(k, comp);
        if (i != -1) return this;
        if (isLeaf) return null;
        i = 0;
        while (i < keys.size() && comp.compare(k, keys.get(i)) > 0) {
            i++;
        }
        return children.get(i).search(k, comp);
    }

    private int binarySearch(T k, Comparator<T> comp) {
        int low = 0, high = keys.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = comp.compare(k, keys.get(mid));
            if (cmp == 0) return mid;
            else if (cmp < 0) high = mid - 1;
            else low = mid + 1;
        }
        return -1;
    }

    public void insertNonFull(T k, Comparator<T> comp) {
        int i = keys.size() - 1;
        if (isLeaf) {
            while (i >= 0 && comp.compare(k, keys.get(i)) < 0) {
                i--;
            }
            keys.add(i + 1, k);
        } else {
            while (i >= 0 && comp.compare(k, keys.get(i)) < 0) {
                i--;
            }
            i++;
            if (children.get(i).keys.size() == 2 * t - 1) {
                splitChild(i, children.get(i));
                if (comp.compare(k, keys.get(i)) > 0) {
                    i++;
                }
            }
            children.get(i).insertNonFull(k, comp);
        }
    }

    public void splitChild(int i, Node<T> y) {
        Node<T> z = new Node<>(y.t, y.isLeaf);
        for (int j = 0; j < t - 1; j++) {
            z.keys.add(y.keys.remove(t));
        }
        if (!y.isLeaf) {
            for (int j = 0; j < t; j++) {
                z.children.add(y.children.remove(t));
            }
        }
        this.children.add(i + 1, z);
        this.keys.add(i, y.keys.remove(t - 1));
    }
    public void delete(T k, Comparator<T> comp) {
        int idx = binarySearch(k, comp);
        if (idx != -1) {
            if (isLeaf) {
                keys.remove(idx);
            } else {
                deleteInternalNode(k, idx, comp);
            }
        } else {
            if (isLeaf) {
                return; // Key not found
            }
            boolean flag = (idx == keys.size());
            if (children.get(idx).keys.size() < t) {
                fill(idx);
            }
            if (flag && idx > keys.size()) {
                children.get(idx - 1).delete(k, comp);
            } else {
                children.get(idx).delete(k, comp);
            }
        }
    }

    private void deleteInternalNode(T k, int idx, Comparator<T> comp) {
        Node<T> child = children.get(idx);
        Node<T> sibling = null;
        if (child.keys.size() >= t) {
            T predecessor = getPredecessor(idx);
            keys.set(idx, predecessor);
            child.delete(predecessor, comp);
        } else if (children.get(idx + 1).keys.size() >= t) {
            T successor = getSuccessor(idx);
            keys.set(idx, successor);
            children.get(idx + 1).delete(successor, comp);
        } else {
            merge(idx);
            child.delete(k, comp);
        }
    }

    private T getPredecessor(int idx) {
        Node<T> current = children.get(idx);
        while (!current.isLeaf) {
            current = current.children.get(current.keys.size());
        }
        return current.keys.get(current.keys.size() - 1);
    }

    private T getSuccessor(int idx) {
        Node<T> current = children.get(idx + 1);
        while (!current.isLeaf) {
            current = current.children.get(0);
        }
        return current.keys.get(0);
    }

    private void fill(int idx) {
        if (idx != 0 && children.get(idx - 1).keys.size() >= t) {
            borrowFromPrev(idx);
        } else if (idx != keys.size() && children.get(idx + 1).keys.size() >= t) {
            borrowFromNext(idx);
        } else {
            if (idx != keys.size()) {
                merge(idx);
            } else {
                merge(idx - 1);
            }
        }
    }

    private void borrowFromPrev(int idx) {
        Node<T> child = children.get(idx);
        Node<T> sibling = children.get(idx - 1);
        child.keys.add(0, keys.get(idx - 1));
        if (!child.isLeaf) {
            child.children.add(0, sibling.children.remove(sibling.children.size() - 1));
        }
        keys.set(idx - 1, sibling.keys.remove(sibling.keys.size() - 1));
    }

    private void borrowFromNext(int idx) {
        Node<T> child = children.get(idx);
        Node<T> sibling = children.get(idx + 1);
        child.keys.add(keys.get(idx));
        if (!child.isLeaf) {
            child.children.add(sibling.children.remove(0));
        }
        keys.set(idx, sibling.keys.remove(0));
    }

    private void merge(int idx) {
        Node<T> child = children.get(idx);
        Node<T> sibling = children.get(idx + 1);
        child.keys.add(keys.remove(idx));
        for (int i = 0; i < sibling.keys.size(); i++) {
            child.keys.add(sibling.keys.get(i));
        }
        if (!child.isLeaf) {
            for (int i = 0; i < sibling.children.size(); i++) {
                child.children.add(sibling.children.get(i));
            }
        }
        children.remove(sibling);
    }

}






    /*public int addToNode(T value, Comparator<T> comp){
        if(size == 2*t - 1){
            throw new IndexOutOfBoundsException("Pelny");
        }
        if(size == 0){
            keys.add(value);
            size++;
            return 0;
        }
        int index = insertOnCorrectPosition(value, comp);
        return index;
    }
    public int insertOnCorrectPosition(T element, Comparator<T> comparator) {
        for (int i = 0; i < keys.size(); i++) {
            if (comparator.compare(element, keys.get(i)) < 0) {
                keys.add(i, element);
                size++;
                return i;
            }
        }
        keys.add(element);
        size++;
        return size;
    }
    public boolean split(Node<T> parent, Comparator<T> comp) {
        int middleIndex = Math.floorDiv(size, 2);

        // Create a new right node with an ArrayList for values and children
        Node<T> rightNode = new Node<>(t, true);
        rightNode.keys = new ArrayList<>(size - middleIndex);
        rightNode.children = new ArrayList<>(size + 1 - middleIndex);

        // Move elements from the current node to the right node using subList
        rightNode.keys.addAll(keys.subList(middleIndex + 1, size));
        rightNode.children.addAll(children.subList(middleIndex + 1, size + 1));

        // Extract the middle value and update sizes
        T middleValue = keys.get(middleIndex);
        keys.remove(middleIndex);
        size = middleIndex;
        rightNode.size = size - middleIndex;

        // Insert the middle value into the parent node, handling potential resizing
        int insertionPosition = parent.addToNode(middleValue, comp);
        if (parent.children.size() == insertionPosition) {
            parent.children.add(rightNode);
        } else {
            parent.children.add(insertionPosition + 1, rightNode);
        }

        // Update leaf state if applicable
        if (this.isLeaf) {
            rightNode.isLeaf = true;
        }

        return true;
    }

    public Node<T> goDown(T value, Comparator<T> comp){
        if(isLeaf){
            return null;
        }
        if(keys.contains(value)){
            return null;
        }
        int temp = findClosestBigger(value, comp);
        return children.get(temp);
    }

    public int findClosestBigger(T value, Comparator<T> comparator) {
        if (keys.isEmpty()) {
            return 0; // Handle empty list case (insert at the beginning)
        }
        int low = 0;
        int high = keys.size() - 1;
        return findClosestBiggerRec(value, comparator, low, high);
    }

    private int findClosestBiggerRec(T value, Comparator<T> comparator, int low, int high) {
        if (low >= high) {
            return -1; // No element is bigger
        }
        int mid = low + (high - low) / 2;
        if (comparator.compare(keys.get(mid - 1), value) <= 0 && comparator.compare(keys.get(mid), value) > 0) {
            return mid; // Found the closest bigger element
        }
        if (comparator.compare(keys.get(mid), value) > 0) {
            return findClosestBiggerRec(value, comparator, low, mid);
        }
        return findClosestBiggerRec(value, comparator, mid + 1, high);
    }




    public boolean isTooLarge() {
        return size > 2 * t - 1;
    }














/*public void addOnCorrectPosition(T k, Comparator<T> comp) {
        int i = Collections.binarySearch(values, k, comp);
        if (i >= 0) {
            // Key already exists, do nothing (or throw an exception if duplicates are not allowed)
            return;
        }
        i = Math.abs(i + 1); // Get the insertion index (use negation for non-existent key)
        values.add(i, k);
        size++;
    }
*/


//    public void insertNonFull(T k, Comparator<T> comp) {
//        int i = size - 1;
//        if (isLeaf) {
//            while (i >= 0 && comp.compare(values.get(i), k) > 0) {
//                values.set(i + 1, values.get(i));
//                i--;
//            }
//            values.set(i + 1, k);
//            size++;
//        } else {
//            while (i >= 0 && comp.compare(values.get(i), k) > 0) {
//                i--;
//            }
//            if (children.get(i + 1).size == 2 * t - 1) {
//                splitChild(i + 1, children.get(i + 1));
//                if (comp.compare(values.get(i + 1), k) < 0) {
//                    i++;
//                }
//            }
//            children.get(i + 1).insertNonFull(k);
//        }
//    }
//
//    public void splitChild(int index, Node<T> y) {
//        Node<T> z = new Node<>(y.t, y.isLeaf);
//        z.size = t - 1;
//
//        for (int j = 0; j < t - 1; j++) {
//            z.values.add(y.values.get(j + t));
//        }
//
//        if (!y.isLeaf) {
//            for (int j = 0; j < t; j++) {
//                z.children.add(y.children.get(j + t));
//            }
//        }
//
//        y.size = t - 1;
//
//        children.add(index + 1, z);
//        values.add(index, y.values.get(t - 1));
//        size++;
//    }









   /* public int findKey(T key, Comparator<T> comp) {
        int left = 0;
        int right = size - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = comp.compare(key, values.get(mid));

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

    public void addValue(T value, Comparator<T> comp) {
        int i = size - 1;
        while (i >= 0 && comp.compare(value, values.get(i)) < 0) {
            i--;
        }
        values.add(i + 1, value);
        size++;
    }

    public boolean isFull() {
        return size == 2 * t - 1;
    }

    public Node<T> travelDown(T value, Comparator<T> comp) {
        int i = 0;
        while (i < size && comp.compare(value, values.get(i)) > 0) {
            i++;
        }
        return children.get(i);
    }
    public Node<T> goDown(T value, Comparator<T> comp) {
        int i = 0;
        while (i < size && comp.compare(value, values.get(i)) > 0) {
            i++;
        }
        return children.get(i);
    }
    public void splitChild(int i, Node<T> parent) {
        Node<T> y = parent.children.get(i);
        Node<T> z = new Node<>(y.t, y.isLeaf);
        z.size = t - 1;

        for (int j = 0; j < t - 1; j++) {
            z.values.add(y.values.remove(t));
        }

        if (!y.isLeaf) {
            for (int j = 0; j < t; j++) {
                z.children.add(y.children.remove(t));
            }
        }

        y.size = t - 1;

        parent.children.add(i + 1, z);
        parent.values.add(i, y.values.remove(t - 1));
        parent.size++;
    }

*/




