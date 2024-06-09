package copy;
import java.util.ArrayList;
import java.util.Comparator;

public class BTree<T> {
    class Node<T> {
        ArrayList<T> values;
        ArrayList<Node<T>> children;
        boolean isLeaf;
        int size;
        final int t;

        private Node(int t, boolean isLeaf) {
            this.t = t;
            this.isLeaf = isLeaf;
            children = new ArrayList<>(2 * t);
            values = new ArrayList<>(2 * t - 1);
        }

        boolean isFull() {
            return size >= values.size();
        }

        boolean isTooSmall() {
            if (this == root || this.size == 0) {
                return false;
            }
            return size < t - 1;
        }

        boolean isMinimal() {
            if (!this.equals(root)) {
                return size <= t - 1;
            }
            return size == 1;
        }



        protected int add(T value) {
            if (isFull()) {
                throw new IndexOutOfBoundsException();
            }
            if (size == 0) {
                values.add(value);
                size++;
                return 0;
            }
            int biggerIndex = findClosestBigger(value);
            values.add(biggerIndex, value);
            if (!isLeaf) {
                children.add(biggerIndex + 1, null);
            }
            size++;
            return biggerIndex;
        }

        private int findClosestBigger(T value) {
            if (comparator.compare(values.get(size - 1), value) < 0) {
                return size;
            }
            if (comparator.compare(values.get(0), value) > 0) {
                return 0;
            }
            return findClosestBiggerRec(value, 0, size);
        }

        private int findClosestBiggerRec(T value, int low, int high) {
            if (low >= high) {
                return -1;
            }
            int mid = low + (high - low) / 2;
            if (comparator.compare(values.get(mid - 1), value) <= 0 && comparator.compare(values.get(mid), value) > 0) {
                return mid;
            }
            if (comparator.compare(values.get(mid), value) > 0) {
                return findClosestBiggerRec(value, low, mid);
            }
            return findClosestBiggerRec(value, mid, high);
        }

        private boolean split(Node parent) {
            int temp = Math.floorDiv(size, 2);
            Node rightNode = new Node();
            for (int i = temp + 1; i < size; i++) {
                rightNode.values.add(values.get(i));
            }
            for (int i = temp + 1; i <= size; i++) {
                rightNode.children.add(children.get(i));
            }
            T val = values.get(temp);
            values.subList(temp, size).clear();
            int pos = parent.add(val);
            parent.children.set(pos, this);
            parent.children.add(pos + 1, rightNode);
            rightNode.size = size - temp - 1;
            size = temp;
            if (this.isLeaf) {
                rightNode.isLeaf = true;
            }
            return true;
        }

        private void printArr(ArrayList<?> arr) {
            for (Object o : arr) {
                System.out.print(o);
            }
            System.out.println();
        }

        Node goDown(T value) {
            if (isLeaf) {
                return null;
            }
            if (values.contains(value)) {
                return null;
            }
            int temp = findClosestBigger(value);
            return children.get(temp);
        }

        T getPredecessor(int idx) {
            Node<T> current = children.get(idx);
            while (!current.isLeaf) {
                current = current.children.get(current.size);
            }
            return current.values.get(current.size - 1);
        }

        T getSuccessor(int idx) {
            Node<T> current = children.get(idx + 1);
            while (!current.isLeaf) {
                current = current.children.get(0);
            }
            return current.values.get(0);
        }

        void merge(int idx) {
            Node<T> child = children.get(idx);
            Node<T> sibling = children.get(idx + 1);
            child.values.add(values.get(idx));
            child.values.addAll(sibling.values);
            if (!child.isLeaf) {
                child.children.addAll(sibling.children);
            }
            values.remove(idx);
            children.remove(idx + 1);
            child.size += sibling.size + 1;
            size--;
        }

        void borrowFromPrev(int idx) {
            Node<T> child = children.get(idx);
            Node<T> sibling = children.get(idx - 1);
            child.values.add(0, values.get(idx - 1));
            if (!child.isLeaf) {
                child.children.add(0, sibling.children.remove(sibling.size));
            }
            values.set(idx - 1, sibling.values.remove(sibling.size - 1));
            child.size++;
            sibling.size--;
        }

        void borrowFromNext(int idx) {
            Node<T> child = children.get(idx);
            Node<T> sibling = children.get(idx + 1);
            child.values.add(values.get(idx));
            if (!child.isLeaf) {
                child.children.add(sibling.children.remove(0));
            }
            values.set(idx, sibling.values.remove(0));
            child.size++;
            sibling.size--;
        }

        void fill(int idx) {
            if (idx != 0 && children.get(idx - 1).size >= t) {
                borrowFromPrev(idx);
            } else if (idx != size && children.get(idx + 1).size >= t) {
                borrowFromNext(idx);
            } else {
                if (idx != size) {
                    merge(idx);
                } else {
                    merge(idx - 1);
                }
            }
        }

        void removeFromLeaf(int idx) {
            values.remove(idx);
            size--;
        }

        void removeFromNonLeaf(int idx) {
            T value = values.get(idx);
            if (children.get(idx).size >= t) {
                T pred = getPredecessor(idx);
                values.set(idx, pred);
                children.get(idx).remove(pred);
            } else if (children.get(idx + 1).size >= t) {
                T succ = getSuccessor(idx);
                values.set(idx, succ);
                children.get(idx + 1).remove(succ);
            } else {
                merge(idx);
                children.get(idx).remove(value);
            }
        }

        void remove(T value) {
            int idx = findClosestBigger(value);
            if (idx < size && comparator.compare(values.get(idx), value) == 0) {
                if (isLeaf) {
                    removeFromLeaf(idx);
                } else {
                    removeFromNonLeaf(idx);
                }
            } else {
                if (isLeaf) {
                    return;
                }
                boolean flag = (idx == size);
                if (children.get(idx).size < t) {
                    fill(idx);
                }
                if (flag && idx > size) {
                    children.get(idx - 1).remove(value);
                } else {
                    children.get(idx).remove(value);
                }
            }
        }

    }

    private Node root;
    protected int t;
    Comparator<T> comparator;

    public BTree(int t, Comparator<T> comparator) {
        this.t = t;
        this.comparator = comparator;
    }

    private Node search(T value) {
        return searchRec(value, root);
    }

    private Node searchRec(T value, Node root) {
        if (root == null) {
            return null;
        }
        if (root.values.contains(value)) {
            return root;
        }
        return searchRec(value, root.goDown(value));
    }

    public boolean add(T value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }
        if (search(value) != null) {
            return false;
        }
        if (root == null) {
            root = new Node(t, true);
            root.add(value);
            return true;
        }
        addRec(value, root, null);
        return true;
    }

    private Node addRec(T value, Node root, Node parent) {
        if (root == null) {
            return null;
        }
        addRec(value, root.goDown(value), root);
        if (root.isFull()) {
            if (parent == null) {
                Node newNode = new Node();
                parent = newNode;
            }
            root.split(parent);
            this.root = parent;
            return addRec(value, parent, null);
        }
        if (!root.isFull() && root.isLeaf) {
            root.add(value);
            return null;
        }
        return null;
    }

    public boolean remove(T value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }
        Node<T> nodeToDelete = search(value);
        if (nodeToDelete == null) {
            return false;
        }
        removeFromLeaf(nodeToDelete, value);
        return true;
    }

    private void removeFromLeaf(Node<T> node, T value) {
        int index = node.values.indexOf(value);
        if (index == -1) {
            return;
        }
        node.values.remove(index);
        node.size--;

        // Handle cases for underflow after removal
        if (node == root && node.size == 0) {
            root = null;
            return;
        }

        if (node.isTooSmall()) {
            handleUnderflow(node);
        }
    }

    private void handleUnderflow(Node<T> underflowNode) {
        Node<T> sibling = getSibling(underflowNode);

        // Case 1: Borrow from sibling with more than minimum keys
        if (sibling.size > underflowNode.t - 1) {
            transferFromSibling(underflowNode, sibling);
        } else {
            // Case 2: Merge with sibling
            merge(underflowNode, sibling);
        }
    }

    private Node<T> getSibling(Node<T> node) {
        Node<T> parent = node.parent;
        if (parent == null) {
            return null;
        }
        int index = parent.children.indexOf(node);
        if (index > 0) {
            return parent.children.get(index - 1);
        } else {
            return parent.children.get(index + 1);
        }
    }

    private void transferFromSibling(Node<T> underflowNode, Node<T> sibling) {
        int transferIndex = sibling.isLeaf ? 0 : 1;

        // Move key and child (if not leaf) from sibling to underflowNode
        underflowNode.values.add(sibling.values.get(transferIndex));
        underflowNode.size++;
        sibling.values.remove(transferIndex);
        sibling.size--;

        // Update parent's key if necessary
        if (!underflowNode.isLeaf) {
            underflowNode.children.add(sibling.children.get(transferIndex));
            sibling.children.remove(transferIndex);
        }

        // Update parent's median key if changed
        if (underflowNode.parent != null) {
            underflowNode.parent.values.set(underflowNode.parent.children.indexOf(underflowNode) - 1, sibling.values.get(0));
        }
    }

    private void merge(Node<T> underflowNode, Node<T> sibling) {
        Node<T> parent = underflowNode.parent;

        // Move all keys and children from underflowNode to sibling
        sibling.values.addAll(underflowNode.values);
        if (!sibling.isLeaf) {
            sibling.children.addAll(underflowNode.children);
        }
        sibling.size += underflowNode.size;

        // Remove underflowNode from parent's children
        parent.children.remove(underflowNode);

        // Update parent's key if necessary
        if (parent.size > 1) {
            parent.values.remove(parent.children.indexOf(sibling));
        } else {
            root = sibling; // Root becomes the merged sibling if parent becomes empty
        }

        // Check and handle underflow for parent after merge
        if (parent != null && parent.isTooSmall()) {
            handleUnderflow(parent);
        }
    }

}
/*public boolean remove(T value) {
        if (root == null) {
            return false;
        }
        root.remove(value);
        if (root.size == 0) {
            if (!root.isLeaf) {
                root = root.children.get(0);
            } else {
                root = null;
            }
        }
        return true;
    }/*
