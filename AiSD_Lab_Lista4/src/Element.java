public class Element<E> {
    private Element next;
    private Element previous;
    private E value;

    public Element(E value) {

        this.value = value;
        this.previous = null;
        this.next = null;

    }

    public void insertAfter(Element elem) {

        if (next != null) {

            elem.setNext(next);
            next.setPrevious(elem);
        }

        this.setNext(elem);
        elem.setPrevious(this);
    }

    public void insertBefore(Element elem) {

        if (previous != null) {

            previous.setNext(elem);
            elem.setPrevious(previous);
        }

        elem.setNext(this);
        this.setPrevious(elem);
    }

    public void remove(){

        this.getNext().setPrevious(this.getPrevious());
        this.getPrevious().setNext(this.getNext());
    }

    public Element getNext() {
        return next;
    }

    public void setNext(Element next) {
        this.next = next;
    }

    public Element getPrevious() {
        return previous;
    }

    public void setPrevious(Element previous) {
        this.previous = previous;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Element{" +
                "value=" + value +
                '}';
    }
}

