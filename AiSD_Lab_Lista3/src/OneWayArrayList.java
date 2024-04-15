import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OneWayArrayList<E> implements IList<E> {
    private final int M;
    private Node<E> head;
    private int size;

    public OneWayArrayList(int M){

        this.M = M;
        this.head = null;
        this.size = 0;
    }


    public boolean add(E element) {

        if (head == null) {
            head = new Node<>(M);
            head.addToNode(element);
            size++;
            return true;
        }

        Node<E> current = head;
        while (current.getCounter() == M) {

            if (current.getNextNode() == null) {
                Node<E> newNode = new Node<>(M);
                newNode.addToNode(element);
                current.setNextNode(newNode);
                size++;
                return true;
            }
            current = current.getNextNode();
        }

        current.addToNode(element);
        size++;
        return true;
    }
    public boolean add(int index, E element) {

        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index + " because list size: "+ size());
        }

        else if (index % M == 0 && index == size()){
            add(element);
            size++;
            return true;
        }

        for (int i = size(); i > index - 1; i--) {
            set(i, get(i - 1));
        }

        set(index, element);
        size++;
        return true;
    }
    public void clear() {
        head = null;
        size = 0;
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
            throw new IndexOutOfBoundsException("Invalid index: " + index + " because list size: "+ size());
        }

        Node<E> current = head;
        int currentIndex = index;

        while (currentIndex >= current.getCounter()) {

            currentIndex -= current.getCounter();
            current = current.getNextNode();
        }

        return current.getFromArray(currentIndex);
    }
    public E set(int index, E element) {

        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index + " because list size: "+ size());
        }

        Node<E> current = head;
        int currentIndex = index;

        while (current.getNextNode() != null && currentIndex >= current.getCounter()) {

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

                if (element.equals(current.getFromArray(i))) {

                    return index + i;
                }
            }

            index += current.getCounter();
            current = current.getNextNode();
        }

        return -1;
    }
    public boolean isEmpty() {return size() == 0;}

    public E remove(int index) {

        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index + " because list size: "+ size());
        }

        E removedElement = get(index);

        for (int i = index; i < size() - 1 ; i++) {

            set(i, get(i + 1));
        }

        set(size() - 1, null);

        if (head.getCounter() == 0){

            head = null;
            size--;
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
        size--;
        return removedElement;
    }

    public boolean remove(E element) {

        for (int i = 0; i < size(); i++){

            if (get(i) == element){

                remove(i);
                return true;
            }
        }
        return false; //nie ma takiego elementu
    }
    public int size() {
        return size;
    }

    public Node<E> getHead() {
        return head;
    }

    @SuppressWarnings("unchecked")
    public void reverse() {

        E[] array = (E[]) new Object[size];
        Node<E> current = head;
        int index = 0;

        while (current != null && index < size) {

            array[index] = current.getFromArray(index % M);
            index++;

            if (index % M == 0) {
                current = current.getNextNode();
            }
        }

        index = 0;
        current = head;

        while (current != null && index < size) {

            current.changeElement(array[size - index - 1], index % M);
            index++;

            if (index % M == 0) {
                current = current.getNextNode();
            }
        }
    }

}


