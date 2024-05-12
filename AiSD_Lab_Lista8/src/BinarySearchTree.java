import java.util.Comparator;
import java.util.NoSuchElementException;

public class BinarySearchTree<T> {
    private Node<T> root;
    private final Comparator<T> comparator;

    public BinarySearchTree(Comparator<T> comparator) {
        this.root = null;
        this.comparator = comparator;
    }

    public void insert(T key) {
        Node<T> newNode = new Node<>(key);
        Node<T> current = root;
        Node<T> parent = null;

        while (current != null) {
            parent = current;
            if (comparator.compare(key, current.getKey()) < 0)
                current = current.getLeft();
            else
                current = current.getRight();
        }

        if (parent == null)
            this.root = newNode;

        else if (comparator.compare(key, parent.getKey()) < 0)
            parent.setLeft(newNode);

        else
            parent.setRight(newNode);
    }


    public boolean search(T data) {
        return searchRecursive(root, data);
    }

    private boolean searchRecursive(Node<T> node, T key) { //O(h), h to wysokość drzewa,
        // worst case: kiedy drzewo BST jest niesbalansowane, ma formę listy, wysokość h będzie równa liczbie węzłów w drzewie, co oznacza złożoność O(n)

        if (node == null) {
            return false;
        }

        if (key == null) {
            return node.getKey() == null || searchRecursive(node.getLeft(), key) || searchRecursive(node.getRight(), key);
        }

        if (key.equals(node.getKey())) {
            return true;
        } else if (comparator.compare(key, node.getKey()) < 0) {
            return searchRecursive(node.getLeft(), key);
        } else {
            return searchRecursive(node.getRight(), key);
        }
    }


    public T findMin() {
        if (root == null) {
            throw new NoSuchElementException("Root is null");
        }
        Node<T> minNode = findMinRecursive(root);
        return minNode.getKey();
    }

    private Node<T> findMinRecursive(Node<T> parent) {
        if (parent.getLeft() == null) {
            return parent;
        }
        return findMinRecursive(parent.getLeft());
    }

    public T findMax() {
        if (root == null) {
            throw new IllegalStateException("Root is null");
        }
        Node<T> maxNode = findMaxRecursive(root);
        return maxNode.getKey();
    }

    private Node<T> findMaxRecursive(Node<T> parent) {
        if (parent.getRight() == null) {
            return parent;
        }
        return findMaxRecursive(parent.getRight());
    }

    public <R> void preOrderWalk(IExecutor<T,R> exec) {
        preOrderWalk(root, exec);
    }

    private <R> void preOrderWalk(Node<T> node, IExecutor<T,R> exec) {
        if (node != null) {
            exec.execute(node.getKey());
            preOrderWalk(node.getLeft(), exec);
            preOrderWalk(node.getRight(), exec);
        }
    }

    public T successor(T elem) {
        Node<T> succNode = successorNode(root, elem);
        return succNode == null ? null : succNode.getKey();
    }

    private Node<T> successorNode(Node<T> node, T elem) {
        if (node == null) {
            throw new NoSuchElementException("Element not found or no successor exists");
        }

        Node<T> successor = null;
        while (node != null) {
            int cmp = comparator.compare(elem, node.getKey());
            if (cmp == 0) {
                if (node.getRight() != null) {
                    return findMinRecursive(node.getRight());
                } else {
                    return successor;
                }
            } else if (cmp < 0) {
                successor = node;
                node = node.getLeft();
            } else { // cmp > 0
                node = node.getRight();
            }
        }

        throw new NoSuchElementException("Element not found or no successor exists");
    }


    public void delete(T elem) {
        if (root == null) {
            throw new NoSuchElementException("Tree is empty");
        }
        if (!search(elem)) {
            throw new NoSuchElementException("Element not found");
        }
        root = deleteIterative(elem, root);
    }

    private Node<T> deleteIterative(T elem, Node<T> node) {
        Node<T> parent = null;
        Node<T> current = node;

        while (current != null) {
            int cmp = comparator.compare(elem, current.getKey());
            if (cmp < 0) {
                parent = current;
                current = current.getLeft();
            } else if (cmp > 0) {
                parent = current;
                current = current.getRight();
            } else {
                return deleteNode(parent, current);
            }
        }
        return node;
    }

    private Node<T> deleteNode(Node<T> parent, Node<T> nodeToDelete) {
        if (nodeToDelete.getLeft() == null || nodeToDelete.getRight() == null) {
            return handleNodeWithLessThanTwoChildren(parent, nodeToDelete);
        } else {
            return handleNodeWithTwoChildren(nodeToDelete);
        }
    }

    private Node<T> handleNodeWithLessThanTwoChildren(Node<T> parent, Node<T> nodeToDelete) {
        Node<T> child = (nodeToDelete.getLeft() != null) ? nodeToDelete.getLeft() : nodeToDelete.getRight();
        if (parent == null) {
            return child;
        } else if (parent.getLeft() == nodeToDelete) {
            parent.setLeft(child);
        } else {
            parent.setRight(child);
        }
        return nodeToDelete;
    }

    private Node<T> handleNodeWithTwoChildren(Node<T> nodeToDelete) {
        Node<T> successor = findSuccessor(nodeToDelete.getRight());
        nodeToDelete.setKey(successor.getKey());
        nodeToDelete.setRight(deleteMin(nodeToDelete.getRight()));
        return nodeToDelete;
    }

    private Node<T> deleteMin(Node<T> node) {
        if (node.getLeft() == null) {
            return node.getRight();
        }
        node.setLeft(deleteMin(node.getLeft()));
        return node;
    }

    private Node<T> findSuccessor(Node<T> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }





}
