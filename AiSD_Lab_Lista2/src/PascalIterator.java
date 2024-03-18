import java.util.Iterator;
import java.util.ArrayList;
public class PascalIterator implements Iterator<Integer>{
    private final int N;
    private int row;
    private ArrayList<Integer> line;

    public PascalIterator(int N){
        this.N = N;
        this.line = new ArrayList<>();
        line.add(1);
        this.row = line.size();
        while (row < N){
            generateLine();
        }
        for(int i : line){
            System.out.println(i);
        }
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Integer next() {
        return null;
    }


    private void generateLine() {

        ArrayList<Integer> nextLine = new ArrayList<>();
        row++;
        for (int i = 0; i < row; i++) {

            if (i == 0 || i == row - 1){
                nextLine.add(1);
            }
            else {
                nextLine.add(line.get(i - 1) + line.get(i));
            }

        }
        line.clear();
        line.addAll(nextLine);
        nextLine.clear();

    }


}
