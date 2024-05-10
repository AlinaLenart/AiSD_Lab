import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.function.Consumer;
public class BST<T> {
    private Node<T> root;
    private Comparator<T> comparator;

    public BST(Comparator<T> comparator) {
        this.root = null;
        this.comparator = comparator;
    }

    public void insert(T key) {
        root = insertRecursive(root, key);
    }

    private Node<T> insertRecursive(Node<T> root, T key) {
        if (root == null) {
            return new Node<>(key);
        }

        if (comparator.compare(key, root.getKey()) < 0) {
            Node<T> left = insertRecursive(root.getLeft(), key);
            root.setLeft(left);
        } else if (comparator.compare(key, root.getKey())  > 0) {
            Node<T> right = insertRecursive(root.getRight(), key);
            root.setRight(right);
        }

        return root;
    }

    public boolean search(T data) {
        return searchRecursive(root, data);
    }

    private boolean searchRecursive(Node<T> root, T key) {
        if (root == null) {
            return false;
        }

        if (key.equals(root.getKey())) {
            return true;
        } else if (comparator.compare(key, root.getKey()) < 0) {
            return searchRecursive(root.getLeft(), key);
        } else {
            return searchRecursive(root.getRight(), key);
        }
    }

    public T findMin() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }

        Node<T> minNode = findMinRecursive(root);
        return minNode.getKey();
    }

    private Node<T> findMinRecursive(Node<T> root) {
        if (root.getLeft() == null) {
            return root;
        }
        return findMinRecursive(root.getLeft());
    }

    public T findMax() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }

        Node<T> maxNode = findMaxRecursive(root);
        return maxNode.getKey();
    }

    private Node<T> findMaxRecursive(Node<T> root) {
        if (root.getRight() == null) {
            return root;
        }
        return findMaxRecursive(root.getRight());
    }

    public void preOrderTraversal(Consumer<T> visitor) {
        preOrderRecursive(root, visitor);
    }

    private void preOrderRecursive(Node<T> root, Consumer<T> visitor) {
        if (root != null) {
            visitor.accept(root.getKey());
            preOrderRecursive(root.getLeft(), visitor);
            preOrderRecursive(root.getRight(), visitor);
        }
    }

    public void inOrderTraversal(Consumer<T> visitor) {
        inOrderRecursive(root, visitor);
    }

    private void inOrderRecursive(Node<T> root, Consumer<T> visitor) {
        if (root != null) {
            inOrderRecursive(root.getLeft(), visitor);
            visitor.accept(root.getKey());
            inOrderRecursive(root.getRight(), visitor);
        }
    }

    public void postOrderTraversal(Consumer<T> visitor) {
        postOrderRecursive(root, visitor);
    }

    private void postOrderRecursive(Node<T> root, Consumer<T> visitor) {
        if (root != null) {
            postOrderRecursive(root.getLeft(), visitor);
            postOrderRecursive(root.getRight(), visitor);
            visitor.accept(root.getKey());
        }
    }

    public void levelOrderTraversal(Consumer<T> visitor) {
        if (root == null) {
            return;
        }

        Deque<Node<T>> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<T> current = queue.poll();
            visitor.accept(current.getKey());

            if (current.getLeft() != null) {
                queue.offer(current.getLeft());
            }

            if (current.getRight() != null) {
                queue.offer(current.getRight());
            }
        }
    }

    public boolean delete(T key) {
        if (root == null) {
            return false;
        }

        Node<T> parent = null;
        Node<T> current = root;

        while (current != null && !current.getKey().equals(key)) {
            parent = current;
            if (comparator.compare(key, current.getKey()) < 0) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }

        if (current == null) {
            return false;
        }

        if (current.getLeft() == null && current.getRight() == null) {
            if (current != root) {
                if (parent.getLeft() == current) {
                    parent.setLeft(null);
                } else {
                    parent.setRight(null);
                }
            } else {
                root = null;
            }
        } else if (current.getLeft() != null && current.getRight() != null) {
            Node<T> successor = findMinRecursive(current.getRight());
            T successorData = successor.getKey();
            delete(successorData);
            current.setKey(successorData);
        } else {
            Node<T> child = (current.getLeft() != null) ? current.getLeft() : current.getRight();
            if (current != root) {
                if (current == parent.getLeft()) {
                    parent.setLeft(child);
                } else {
                    parent.setRight(child);
                }
            } else {
                root = child;
            }
        }

        return true;
    }





}
