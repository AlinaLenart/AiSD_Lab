import java.util.Comparator;
import java.util.NoSuchElementException;

public class BinarySearchTree<T> {
    protected Node<T> root;
    private final Comparator<T> comparator;

    public BinarySearchTree(Comparator<T> comparator) {
        this.root = null;
        this.comparator = comparator;
    }

    public void insert(T key) { //O(logn) lub O(n)
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
        // worst case: kiedy drzewo BST jest niesbalansowane, ma forme listy, wysokosc h będzie rowna liczbie wezlow w drzewie, czyli zlozonosc O(n)

        if (node == null) {
            return false;
        }

        if (key == null) { //unikniecie null pointer exception
            return node.getKey() == null || searchRecursive(node.getLeft(), key) || searchRecursive(node.getRight(), key);
        }

        if (key.equals(node.getKey()))
            return true;

        else if (comparator.compare(key, node.getKey()) < 0)
            return searchRecursive(node.getLeft(), key);

        else
            return searchRecursive(node.getRight(), key);

    }

    public T findMin() { //O(h)
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
        Node<T> current = root;
        Node<T> successor = null;

        while (current != null) {
            int cmp = comparator.compare(elem, current.getKey());
            if (cmp == 0) { //znalezlismy node'a
                if (current.getRight() != null) {
                    return current.getRight().findMinIterative().getKey();
                }
                else {
                    return (successor != null) ? successor.getKey() : null;
                }
            }
            else if (cmp < 0) { //lewe podrzewo
                successor = current;
                current = current.getLeft();
            }
            else { //prawe podrzewo
                current = current.getRight();
            }
        }

        throw new NoSuchElementException("Element not found or no successor exists");
    }


    public void delete(T elem) {
        if (root == null) {
            throw new NoSuchElementException("Tree is empty");
        }
        root = deleteIterative(elem, root);
    }

   private Node<T> deleteIterative(T elem, Node<T> node) {
        Node<T> current = node; //wierzcholek
        Node<T> previous = null;

        while (current != null) {

            T currentKey = current.getKey();
            if ((currentKey == null && elem == null) || (currentKey != null && currentKey.equals(elem))) { //znalezlismy element do usuwania
                break;
            }

            previous = current;
            int cmp;
            if (currentKey == null || elem == null) {
                cmp = (currentKey == null) ? 1 : -1; //null jako wartosc wieksza
            }
            else {
                cmp = comparator.compare(elem, currentKey);
            }

            if (cmp < 0) //mniejsza idziemy w lewo
                current = current.getLeft();
            else
                current = current.getRight();
        }

        if (current == null) {
            throw new NoSuchElementException("Element not found");
        }
        //current jest naszym elementem do usuniecia
        if (current.getLeft() == null || current.getRight() == null) { //ma max 1 dziecko
            Node<T> newCurrent = (current.getLeft() == null) ? current.getRight() : current.getLeft();

            if (previous == null)
                return newCurrent;

            if (previous.getLeft() == current)
                previous.setLeft(newCurrent);

            else
                previous.setRight(newCurrent);
        }
        else { //ma dwojke dzieci
            Node<T> p = null;
            Node<T> temp = current.getRight();

            while (temp.getLeft() != null) {
                p = temp;
                temp = temp.getLeft();
            }

            if (p != null)
                p.setLeft(temp.getRight());

            else
                current.setRight(temp.getRight());

            current.setKey(temp.getKey());
        }
        return node;
    }

    public Node<T> getRoot() {
        return root;
    }public void setRoot(Node<T> newRoot){
        this.root = newRoot;
    }

    public Comparator<T> getComparator() {
        return comparator;
    }
}
