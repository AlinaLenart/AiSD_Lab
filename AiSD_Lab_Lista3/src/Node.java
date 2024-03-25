

public class Node<E> {
    private Node<E> nextNode;
    private E[] elements;
    private int counter;

    @SuppressWarnings("unchecked")
    public Node(int M){
        this.nextNode = null;
        this.counter = 0;
        this.elements = (E[]) new Object[M];
    }

    public boolean addToNode(E element) {
        if (counter == elements.length) {
            return false;
        }
        elements[counter++] = element;
        return true;
    }


    public Node<E> getNextNode() {
        return nextNode;
    }

    public int getCounter() {
        int cnt = 0;
        while (elements[cnt] != null){
            cnt++;
        }
        return cnt;
    }

    public E[] getElements() {
        return elements;
    }

    public E getFromArray(int index) {
        return elements[index];
    }

    public void changeElement(E element, int index){
        elements[index] = element;
    }

    public void setNextNode(Node<E> nextNode) {
        this.nextNode = nextNode;
    }

    public void setElements(E[] elements) {
        this.elements = elements;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}