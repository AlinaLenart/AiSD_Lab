import java.util.Iterator;

public class Table<E> implements Iterable<E>{
    private E[] table;

    public Table(E[] table){
        this.table = table;
    }

    @Override
    public Iterator<E> iterator() {
        return new TableIterator<>(table);
    }
}
