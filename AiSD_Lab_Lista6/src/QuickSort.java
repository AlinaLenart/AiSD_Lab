import core.AbstractSwappingSortingAlgorithm;
import testing.MarkedValue;

import java.util.*;
public class QuickSort<T> extends AbstractSwappingSortingAlgorithm<T> {

    private final PivotSelector<T> pivotSelector;

    public QuickSort(Comparator<? super T> comparator, PivotSelector<T> pivotSelector) {
        super(comparator);
        this.pivotSelector = pivotSelector;
    }

    public List<T> sort(List<T> list) {
        if (list.isEmpty() || list.size() < 2) {
            return list;
        }
        quickSort(list, 0, list.size() - 1);
        return list;
    }

    private void quickSort(List<T> list, int low, int high) {
        if (low < high) {
            T pivot = pivotSelector.selectPivot(list, low, high);
            int i = low - 1;
            int j = high + 1;
            while (true) {
                do {
                    i++;
                } while (compare(list.get(i), pivot) < 0);

                do {
                    j--;
                } while (compare(list.get(j), pivot) > 0);

                if (i >= j) {
                    break;
                }

                swap(list, i, j);
            }

            quickSort(list, low, j);
            quickSort(list, j + 1, high);
        }
    }
}
