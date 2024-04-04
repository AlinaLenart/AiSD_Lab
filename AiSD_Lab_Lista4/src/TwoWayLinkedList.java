public class TwoWayLinkedList<E> implements IList<E> {
    //dwukierunkowa, glowa i ogon, bez straznika, prosta(niecykliczna, ostatni wskazuje null)
    //bez straznika musze sprawdzac null
    private Element head;
    private Element tail;
    private int size;
    public TwoWayLinkedList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }


    @Override
    public boolean add(E e) {
        add(size, e);
        return true;
    }

    @Override
    public void add(int index, E e) {

        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index + " Size of the list: "+ size);
        }

        Element newElem = new Element<>(e);

        if (index == 0){

            if (isEmpty()) { //lista jest pusta i na poczatek

                tail = newElem;
            }

            else {
                head.insertBefore(newElem);

            }

            head = newElem;
        }

        else if (index == size()) { //dodanie na koniec niepustej
            tail.insertAfter(newElem);
            tail = newElem;
        }

        else {
            Element<E> current = getElementAtIndex(index);
            current.insertBefore(newElem);
        }

        size++;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean contains(E element) {
        return false;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E set(int index, E e) {
        return null;
    }

    @Override
    public int indexOf(E e) {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public boolean remove(E e) {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    public void displayLinkedList(){

        Element<E> current = head;
        for (int i = 0; i < size; i++) {
            if (current != null) {

                System.out.println(current);
                current = current.getNext();
            }
        }
    }

    private Element<E> getElementAtIndex(int index) {

        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index + " Size of the list: "+ size);
        }

        Element<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current;
    }
}
