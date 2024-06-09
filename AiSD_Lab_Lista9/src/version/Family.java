package version;

public class Family<T> {
    Node<T> parent;
    Node<T> child;
    int childIndex;

    public Family(Node<T> parent, Node<T> child, int childIndex) {
        this.parent = parent;
        this.child = child;
        this.childIndex = childIndex;
    }
}
