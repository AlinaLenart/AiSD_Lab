package version;

import java.util.*;

public class BTree<T> {
    Node<T> root;
    final int t;
    final Comparator<T> comp;

    BTree(int t, Comparator<T> comp) {
        this.t = t;
        this.comp = comp;
    }

    public Node<T> search(T k) {
        if (root == null) {
            return null;
        }
        return root.search(k, comp);
    }

    public void add(T k) {
        if (root == null) {
            root = new Node<>(t, true);
            root.keys.add(k);
        } else {
            if (root.keys.size() == 2 * t - 1) {
                Node<T> s = new Node<>(t, false);
                s.children.add(root);
                s.splitChild(0, root);
                int i = 0;
                if (comp.compare(s.keys.get(0), k) < 0) {
                    i++;
                }
                s.children.get(i).insertNonFull(k, comp);
                root = s;
            } else {
                root.insertNonFull(k, comp);
            }
        }
    }

    public void delete(T k) {
        if (root == null) {
            return;
        }
        root.delete(k, comp);
        if (root.keys.isEmpty()) {
            if (root.isLeaf) {
                root = null;
            } else {
                root = root.children.get(0);
            }
        }
    }



    /*public boolean remove(T value) {
        if (root == null) {
            return false;
        }

        NextNodeHolder<T> rootHolder = new NextNodeHolder<>(root, -1);
        boolean removed = removeRec(rootHolder, value);

        if (root.size == 0) {
            root = null; // Handle empty tree after removal
        }

        return removed;
    }

    private boolean removeRec(NextNodeHolder<T> rootHolder, T value) {
        if (rootHolder == null) {
            return false;
        }

        NextNodeHolder<T> nextNode = rootHolder.node.goDownSaveIndex(value);
        removeRec(nextNode, value);

        if (rootHolder.node.contains(value)) {
            if (rootHolder.node.isLeaf) {
                rootHolder.node.simpleRemove(value);
                return true;
            } else {
                int index = rootHolder.node.indexOf(value);
                getPredecessorAndFix(rootHolder.node, index);
            }
        }

        if (nextNode != null) {
            checkAndFix(nextNode.node, rootHolder.node, nextNode.index);
        }

        return true; // Assuming successful search even if not removed (key not found)
    }

    private T getPredecessorAndFix(Node<T> current, int childIndex) {
        Node<T> leftChild = current.children.get(childIndex);
        T predecessor = getPredecessorAndFixRec(leftChild);
        current.keys.set(childIndex, predecessor);
        checkAndFix(leftChild, current, childIndex);
        return predecessor;
    }

    private T getPredecessorAndFixRec(Node<T> current) {
        if (current == null) {
            return null;
        }

        Node<T> nextNode = current.children.get(current.size);
        T predecessor = getPredecessorAndFixRec(nextNode);

        if (current.isLeaf) {
            return current.keys.remove(current.size - 1);
        }

        checkAndFix(nextNode, current, current.size);
        return predecessor;
    }

    private boolean checkAndFix(Node<T> child, Node<T> parent, int childIndex) {
        if (child.size > t - 1) {
            return true;
        }

        // Implement getFromSibling and mergeChildrenAndMoveDownParent using ArrayLists
        if (!child.getFromSibling(parent, childIndex)) {
            parent.mergeChildrenAndMoveDownParent(childIndex);
        }
        return true;
    }

    // Helper classes (same as before)
    public class NextNodeHolder<T> {
        Node<T> node;
        int index;

        public NextNodeHolder(Node<T> node, int index) {
            this.node = node;
            this.index = index;
        }
    }

    public class ValueHolder<T> {
        T value;

        public ValueHolder() {
            this.value = null;
        }
    }
*/




//    public void insert(T k) {
//        if (root == null) {
//            root = new Node<>(t, true);
//            root.values.add(k);
//            root.size++;
//            return;
//        }
//
//        Node<T> current = root;
//        Node<T> parent = null;
//        int i = 0;
//
//        while (!current.isLeaf) {
//            i = 0;
//            while (i < current.values.size() && comp.compare(k, current.values.get(i)) > 0) {
//                i++;
//            }
//            parent = current;
//            current = current.children.get(i);
//        }
//
//        current.addOnCorrectPosition(k, comp); // increase size, find where to put key and add it
//
//        while (current != null && current.isTooLarge()) { // Loop until no more splits needed
//            Family<T> family = new Family<>(parent, current, i);
//            splitChild(family);
//            parent = current;
//            i = family.childIndex + 1; // update index for parent's child list after split
//            current = parent; // move to parent for further checks
//        }
//    }


}
    /*private void splitChild(Node<T> node) {
        int mid = t - 1; // Index of the middle element
        Node<T> right = new Node<>(t, node.isLeaf); // New right node
        right.size = t - 1; // Set size of the new right node

        // Move elements and children to the new right node
        for (int i = mid + 1; i < node.size; i++) {
            right.values.add(node.values.get(i));
            if (!node.isLeaf) {
                right.children.add(node.children.get(i));
            }
        }

        // Update size of the split node
        node.size = mid;

        // Find a suitable node for insertion (recursively)
        insertMiddleElement(node, node.values.get(mid));
    }

    private void insertMiddleElement(Node<T> current, T value) {
        // If current node is a leaf, insert the value directly
        if (current.isLeaf) {
            int i = current.values.size() - 1;
            while (i >= 0 && comp.compare(value, current.values.get(i)) > 0) {
                current.values.set(i + 1, current.values.get(i));
                i--;
            }
            current.values.set(i + 1, value);
            current.size++;
        } else {
            // Find the appropriate child for insertion
            int i = 0;
            while (i < current.values.size() && comp.compare(value, current.values.get(i)) > 0) {
                i++;
            }

            // If the child has enough space, insert directly
            if (current.children.get(i).size < 2 * t - 1) {
                insertMiddleElement(current.children.get(i), value);
            } else {
                // Recursively split the full child and adjust current node
                splitChild(current.children.get(i));

                // Check if middle element is less than all parent values
                if (comp.compare(value, current.values.get(0)) < 0) {
                    i = 0;
                } else {
                    i = Math.min(i, current.values.size() - 1);
                }
                insertMiddleElement(current.children.get(i), value);
            }
        }
    }
*/








