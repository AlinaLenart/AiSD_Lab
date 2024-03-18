import java.util.Iterator;
import java.util.NoSuchElementException;

public class TableIterator<E> implements Iterator<E> {
    private int pos;
    private final E[] table;
    public TableIterator(E[] table){
        this.table = table;
        pos = 0;
    }

    @Override
    public boolean hasNext() {
        return pos < table.length;
    }

    @Override
    public E next() throws NoSuchElementException {

        if (hasNext())
            return table[pos++];
        else
            throw new NoSuchElementException();

    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

}
