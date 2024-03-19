import java.util.Iterator;
import java.util.NoSuchElementException;

public class DivisorIterator implements Iterator<Integer> {
    private int n;
    private int divider;
    public DivisorIterator(int n) {

        if (n < 1)
            System.out.println("Podana liczba nie jest naturalna dodatnia");

        this.n = n;
        this.divider = 1;
    }

    @Override
    public boolean hasNext() {
        return divider <= n;
    }

    @Override
    public Integer next() throws NoSuchElementException {

        if (!hasNext())
            throw new NoSuchElementException();

        while (n % divider != 0) {
            divider++;
        }

        return divider++;
    }
}

