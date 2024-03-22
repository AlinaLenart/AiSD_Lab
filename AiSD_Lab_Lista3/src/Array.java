

public class Array<E> {
    private Array<E> nextArray;
    private E[] values;
    private int counter;
    private final int M;

    public Array (E element, int M){
        this.counter = 0;
        this.M = M;
        this.values = new E[M];
    }

    public boolean addToArray(E element){

    }

    public Array getNextArray() {
        return nextArray;
    }

    public int getCounter() {
        return counter;
    }

    public void setNextArray(Array nextArray) {
        this.nextArray = nextArray;
    }


}
