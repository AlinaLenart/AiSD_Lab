
public class OneWayArrayList<E> implements IList<E> {
    private final int M;
    private Node<E> head;

    public OneWayArrayList(int M){
        this.M = M;
        this.head = null;
    }
    public boolean add(E element) {

        if (head == null) {
            head = new Node<>(M);
            head.addToNode(element);
            return true;
        }

        Node<E> tail = head;
        while (tail.getCounter() == M) {

            if (tail.getNextNode() == null) {
                Node<E> newNode = new Node<>(M);
                newNode.addToNode(element);
                tail.setNextNode(newNode);
                return true;
            }
            tail = tail.getNextNode();
        }
        tail.addToNode(element);

        return true;
    }
    public boolean add(int index, E element) {

        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index + " size of list: "+ size());
        }

        else if (index % M == 0 && index == size()){
            add(element);
            return true;
        }

        for (int i = size(); i > index ; i--) {
            set(i, get(i - 1));
        }

        set(index, element);
        return true;
    }
    public void clear() {
        head = null;
    }
    public boolean contains(E element) {
        Node<E> current = head;
        while (current != null) {
            for (int i = 0; i < current.getCounter(); i++) {
                if (element.equals(current.getElements()[i])) {
                    return true;
                }
            }
            current = current.getNextNode();
        }
        return false;
    }
    public E get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index + " size of list: "+ size());
        }

        Node<E> current = head;
        int currentIndex = index;
        while (currentIndex >= current.getCounter()) {
            currentIndex -= current.getCounter();
            current = current.getNextNode();
        }
        return current.getElements()[currentIndex];
    }
    public E set(int index, E element) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index + " size of list: "+ size());
        }
        Node<E> current = head;
        int currentIndex = index;

        while (currentIndex >= current.getCounter()) {
            currentIndex -= current.getCounter();
            current = current.getNextNode();
        }
        current.changeElement(element, currentIndex);
        return element;
    }
    public int indexOf(E element) {
        int index = 0;
        Node<E> current = head;
        while (current != null) {
            for (int i = 0; i < current.getCounter(); i++) {
                if (element.equals(current.getElements()[i])) {
                    return index + i;
                }
            }
            index += current.getCounter();
            current = current.getNextNode();
        }
        return -1;

    }
    public boolean isEmpty() {
        return size() == 0;
    }
    public E remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index + " size of list: "+ size());
        }

        E removedElement = get(index);

        for (int i = index; i < size() - 1 ; i++) {
            set(i, get(i + 1));
        }

        set(size() - 1, null);

        if (head.getCounter() == 0){
            head = null;
            return removedElement;
        }

        Node<E> current = head.getNextNode();

        while (current.getNextNode() != null) {
            if (current.getNextNode().getCounter() == 0){
                current.setNextNode(null);
                break;
            }
            current = current.getNextNode();
        }
        return removedElement;
    }

    public boolean remove(E element) {
        for (int i = 0; i < size(); i++){
            if (get(i) == element){
                remove(i);
                return true;
            }
        }
        return false;
    }
    public int size() {
        int pos = 0;
        Node<E> current = head;
        while(current != null)
        {
            pos += current.getCounter();
            current = current.getNextNode();
        }
        return pos;
    }

    public int getM() {
        return M;
    }

    public Node<E> getHead() {
        return head;
    }
}


