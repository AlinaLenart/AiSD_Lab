import java.util.Arrays;

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

    public Node<E> getNextNode() {return nextNode;}

    public int getCounter() {

        int count = 0;

        for (int i = 0; i < elements.length; i++) {

            if (elements[i] != null)
                count++;
        }

        return count;
    }

    public E[] getElements() {return elements;}

    public E getFromArray(int index) {return elements[index];}

    public void changeElement (E element, int index){
        elements[index] = element;
    }

    public void setNextNode(Node<E> nextNode) {this.nextNode = nextNode;}

    @Override
    public String toString() {
        return "Node{" +
                "elements=" + Arrays.toString(elements) +
                '}';
    }
}
