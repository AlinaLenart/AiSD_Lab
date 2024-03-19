import java.util.Iterator;
import java.util.NoSuchElementException;

public class TableIterator<E> implements Iterator<E> {
    private int pos;
    private final E[] table;
    public TableIterator(E[] table){
        this.table = table;
        this.pos = 0;
    }

    @Override
    public boolean hasNext() {
        return pos < table.length;
    }

    @Override
    public E next() throws NoSuchElementException {

        if (!hasNext())
            throw new NoSuchElementException();

        return table[pos++];
    }


}
