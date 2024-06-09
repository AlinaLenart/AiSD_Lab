public class Node<T> {
    private T key;
    private Node<T> left, right;
    public Node (T key) {
        this.key = key;
        this.left = right = null;
    }

    public Node<T> findMinIterative() {
        Node<T> current = this;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }
    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
