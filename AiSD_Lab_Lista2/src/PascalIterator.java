import java.util.Iterator;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class PascalIterator implements Iterator<Integer> {
    private final int N;
    private int row;
    private ArrayList<Integer> line;
    private int currentIndex;

    public PascalIterator(int N) {
        this.N = N;
        this.currentIndex = 0;
        this.line = new ArrayList<>();
        line.add(1);
        this.row = line.size();
        while (row < N) {
            generateLine();
        }

    }

    @Override
    public boolean hasNext() {
        return currentIndex < N;
    }

    @Override
    public Integer next() throws NoSuchElementException {

        if (!hasNext())
            throw new NoSuchElementException();

        return line.get(currentIndex++);

    }

    private void generateLine() {

        ArrayList<Integer> nextLine = new ArrayList<>();
        row++;

        for (int i = 0; i < row; i++) {

            if (i == 0 || i == row - 1)
                nextLine.add(1);
            else
                nextLine.add(line.get(i - 1) + line.get(i));

        }

        line.clear();
        line.addAll(nextLine);
        nextLine.clear();

    }

}
