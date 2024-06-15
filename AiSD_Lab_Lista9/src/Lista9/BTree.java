package Lista9;
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


