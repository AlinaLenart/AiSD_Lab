package copy;
import java.util.*;

public class BTree<T> {
    private Node<T> root;
    private final Comparator<T> comp;
    private final int t;

    public BTree(Comparator<T> comp, int t) {
        if (t < 2) {
            throw new IllegalArgumentException("t < 2");
        }
        this.comp = comp;
        this.t = t;
        this.root = null;
    }

    public Node<T> search(T key) {
        if (root == null) {
            return null;
        }
        return root.search(key);
    }

    public boolean add(T key) {
        if (key == null) {
            throw new IllegalArgumentException("Nulls are not allowed");
        }
        if (search(key) != null) {
            throw new IllegalArgumentException("Duplicated key!");
        }
        if (root == null) {
            root = new Node<>(true, t, comp);
            root.addToLeaf(0, key);
        } else {

            Stack<Node<T>> stack = new Stack<>();
            root.add(stack, key);

            Node<T> currentNode = stack.pop(); // wyciagam najmlodszy

            while (!stack.isEmpty() && currentNode.isFull()) { //po kolei kazdy z nich split
                Node<T> parentNode = stack.pop();
                parentNode.split(parentNode, currentNode);
                currentNode = parentNode;
            }

            if (currentNode.isFull()) { //jezeli doszlam do roota, ktory tez jest PEŁNY
                Node<T> newRoot = new Node<>(false, t, comp);
                newRoot.children.add(root); //tworze nowy root ktory dostanie elementy ze splita
                currentNode.split(newRoot, currentNode);
                this.root = newRoot;
            }
        }
        return true;
    }

    public boolean remove(T elem) {
        if (root == null) {
            return false;
        }

        boolean removed;
        removed = root.remove(elem);

        // if root is empty and there is a new root merged
        if (root.keys.isEmpty() && !root.isLeaf) {
            root = root.children.get(0);
        }

        return removed;
    }

    public void printTree() {
        printTreeHelper(root, 0);
    }

    private void printTreeHelper(Node<T> node, int level) {
        if (node == null) {
            return;
        }
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
        System.out.println(node.keys);
        if (!node.isLeaf) {
            for (Node<T> child : node.children) {
                printTreeHelper(child, level + 1);
            }
        }
    }

}




/*public class Node {
        int size = 0;
        List<T> keys;
        List<Node> children;
        boolean isLeaf;

        private Node (boolean isLeaf) {
            this.isLeaf = isLeaf;
            this.keys = new ArrayList<>(2 * t - 1);
            this.children = new ArrayList<>(2 * t);
        }

        //modul SEARCH
        private Node search(T val) {
            int i = searchIndex(val);
            if (i < size && comp.compare(val, keys.get(i)) == 0) {
                return this;
            }
            return isLeaf ? null : children.get(i).search(val); //rekurencja gdy nie jest lisciem
        }

        private int searchIndex(T val) {
            if (size > 0) {
                int i = binarySearch(val, 0, keys.size() - 1);
                if(i < size && comp.compare(val, keys.get(i)) > 0) {
                    i++;
                }
                return i;
            }
            return 0;
        }
        private int binarySearch(T val, int low, int high) {
            if(low == high) {
                return low;
            }
            int med = low + (high - low) / 2;
            int cmp = comp.compare(val, keys.get(med));
            if(cmp > 0) {
                return binarySearch(val, med + 1, high);
            }
            else if (cmp < 0){
                return binarySearch(val, low, med);
            }
            else {
                return med;
            }
        }

        //modul ADD
        private void split(Node parent, Node fullChild) {
            Node newNode = new Node(fullChild.isLeaf);
            int midIndex = t - 1;
            T median = fullChild.keys.get(midIndex);

            // Populate newNode with keys and children from fullChild
            newNode.keys.addAll(fullChild.keys.subList(midIndex + 1, fullChild.keys.size()));
            fullChild.keys.subList(midIndex, fullChild.keys.size()).clear();

            if (!fullChild.isLeaf) {
                newNode.children.addAll(fullChild.children.subList(t, fullChild.children.size()));
                fullChild.children.subList(t, fullChild.children.size()).clear();
            }

            newNode.size = newNode.keys.size();
            fullChild.size = fullChild.keys.size();

            // Insert the median into the parent node
            int insertIndex = parent.children.indexOf(fullChild) + 1;
            parent.children.add(insertIndex, newNode);
//            parent.keys.add(parent.findInsertIndex(median), median);
            parent.keys.add(parent.searchIndex(median), median);
            parent.size++;
        }

        private void add(Stack<Node> stack, T val) {
            int index = searchIndex(val);

            if (isLeaf) {
                insertInisLeaf(index, val);
                stack.push(this);
            } else {
                stack.push(this);
                children.get(index).add(stack, val);
            }
        }

        private void insertInisLeaf(int index, T val) {
            keys.add(index, val);
            size++;
        }

        private boolean remove(T value) {
            boolean retValue = false;
            int i = searchIndex(value);

            // value is within that node
            if(i < size && comp.compare(value, keys.get(i)) == 0) {
                retValue = true;

                // if the node is the isLeaf we remove directly from the node
                if(isLeaf) {
                    deleteConfident(i);

                    //we now close the recursion to execute fixing lines
                    return retValue;
                }

                // it is an internal node, we need to find the predecessor and fix its removal.
                else {
                    // we swap intended value with its predecessor
                    keys.set(i, children.get(i).removePredecessor(null));
                    // to allow appropriate flow of fixing we need to ensure that current node's child
                    // meets the requirements ONLY AFTER SWAPPING VALUES
                    // fixes from the isLeaf up to the direct child of current node are handled in removePredecessor method

//                    if(children.get(i).size < t - 1) {
//                        this.fixNodesChild(i);
//                    }
                }
            }

            // there's no such value as we are already in the isLeaf and the index i exceeded last key index.
            else if(isLeaf) {
                return retValue;
            }

            // value is in one of the node's children
            else {
                retValue = children.get(i).remove(value);
            }

            // we fix the node
            // when the recursion closes in cascade, we fix every node recursively (if there's a need)
            // we check if last accessed child meets requirements
            if(children.get(i).size < t - 1) {
                // if not, we call fixNodesChild on the parent of the child and pass child's index
                this.fixNodesChild(i);
            }

            return retValue;
        }


        // we call it on parent and point to a specific child which needs fixing
        private void fixNodesChild(int index) {
            Node child = children.get(index);
            Node leftSibling = index > 0 ? children.get(index - 1) : null;
            Node rightSibling = index < size ? children.get(index + 1) : null;

            // right rotation (borrow from left sibling)
            if(leftSibling != null && leftSibling.size >= t) {
                // add to the child the left separator key from parent
                child.keys.add(0, keys.remove(index - 1));
                child.size++;
                // add to the parent the last of the left siblings key, at the same index we removed line before
                keys.add(index - 1, leftSibling.deleteConfident(leftSibling.size - 1));
                // transfer last left sibling child to the child
                if( ! leftSibling.isLeaf) {
                    child.children.add(0, leftSibling.children.removeLast());
                }
            }
            // left rotation (borrow from right sibling)
            else if(rightSibling != null && rightSibling.size >= t) {
                child.keys.add(keys.remove(index));
                child.size++;
                keys.add(index,rightSibling.deleteConfident(0));
                if( !rightSibling.isLeaf) {
                    child.children.add(rightSibling.children.removeFirst());
                }
            }
            // merging
            else {
                // merge with left sibling
                if(leftSibling != null) {
                    // we delete left separator from parent node (this) and add it to the final node (left sibling)
                    leftSibling.keys.add(this.deleteConfident(index - 1));
                    // we move all keys from child to left node
                    leftSibling.keys.addAll(child.keys);
                    // we move all children from child to left node
                    leftSibling.children.addAll(child.children);
                    // we update number of keys
                    leftSibling.size = leftSibling.keys.size();
                    // we remove the child pointer from the parent
                    this.children.remove(index);
                }

                // merge with right sibling (same operation as above,
                // the only difference is, the final Node is the child, and right is being deleted)
                else if(rightSibling != null) {
                    child.keys.add(0, this.deleteConfident(index));
                    child.keys.addAll(rightSibling.keys);
                    child.children.addAll(rightSibling.children);
                    child.size = child.keys.size();
                    this.children.remove(index + 1);
                }
            }
        }

        // we call it on a child we want to start traversing tree with
        // the parent parameter helps with fixing the tree after recursive deletion
        private T removePredecessor(Node parent) {
            // buffer value
            T retValue;

            // we need to traverse until we find the isLeaf, and mark its last value as a buffer
            if(isLeaf) {
                retValue = deleteConfident(size - 1);
            }

            // else we search in last child of currently operated node
            // the parameter parent is this because we will be operating on this.child
            else {
                retValue = children.getLast().removePredecessor(this);
            }

            // closing recursion we fix the nodes recursively up until there's no need, or the parent is null
            // the null parent indicates that we accessed the direct child of a node we operated in main remove method
            // we fix it only to this moment to ensure proper fixing flow, because the swap operation
            // (which is the return of this method) is done BEFORE fixing predecessor trail nodes
            if(parent != null && size < t - 1) {
                parent.fixNodesChild(parent.children.size() - 1);
            }

            // we return the predecessor value to swap objective node
            return retValue;
        }

        private T deleteConfident(int index) {
            T retValue = keys.remove(index);
            size--;
            return retValue;
        }

    }*/