public class TwoWayLinkedList<E> implements IList<E> {
    //dwukierunkowa, glowa i ogon, bez straznika, prosta(niecykliczna, ostatni wskazuje null)
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

        else if (index == size()) {     //dodanie na koniec niepustej listy
            tail.insertAfter(newElem);
            tail = newElem;
        }

        else {
            Element<E> current = getElementAtIndex(index);  //throws kiedy index > size, index = size obsluzony wyzej
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
    public E get(int index) {

        Element<E> current = getElementAtIndex(index);
        return current.getValue();

    }

    @Override
    public E set(int index, E e) {

        Element<E> current = getElementAtIndex(index);
        current.setValue(e);
        return current.getValue();

    }

    @Override
    public int indexOf(E e) {

        Element<E> current = head;
        int index = 0;

        while (current != null) {

            if (current.getValue().equals(e)) {
                return index;
            }

            current = current.getNext();
            index++;

        }
        return -1;
    }

    @Override
    public boolean contains(E e) {
        return indexOf(e) > -1;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public E remove(int index) {

        Element<E> current = getElementAtIndex(index);

        if (index == 0) {
            head = current.getNext();
        }
        if (index == size() - 1){
            tail = current.getPrevious();
        }

        current.remove();
        size--;
        return current.getValue();

    }

    @Override
    public boolean remove(E e) {

        Element<E> current = head;

        while (current != null) {

            if (current.getValue().equals(e)) {

                if (current == head) {
                    head = current.getNext();
                }
                if (current == tail) {
                    tail = current.getPrevious();
                }

                current.remove();
                size--;
                return true;
            }

            current = current.getNext();
        }

        return false;
    }

    public void reverse(){

        Element<E> current = head;
        head = tail;
        tail = current;

        while (current != null){

            current.reverseHelper();
            current = current.getPrevious();

        }

    }
    @Override
    public int size() {
        return size;
    }

    public Element getHead() {
        return head;
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
