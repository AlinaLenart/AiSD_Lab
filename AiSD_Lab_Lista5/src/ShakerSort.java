import java.util.Comparator;
import java.util.List;

import core.AbstractSwappingSortingAlgorithm;

public class ShakerSort<T> extends AbstractSwappingSortingAlgorithm<T> {

    public ShakerSort(Comparator<? super T> comparator) {

        super(comparator);
    }

    @Override
    public List<T> sort(List<T> list) {

        int start = 0;
        int end = list.size() - 1;

        while (start < end) {
            int newEnd = start;
            int newStart = end;


            for (int i = start; i < end; i++) {
                if (compare(list.get(i), list.get(i + 1)) > 0) {
                    swap(list, i, i + 1);
                    newEnd = i;
                }
            }
            end = newEnd;

            for (int i = end; i > start; i--) {
                if (compare(list.get(i), list.get(i - 1)) < 0) {
                    swap(list, i, i - 1);
                    newStart = i;
                }
            }
            start = newStart;
        }

        return list;
    }



}
