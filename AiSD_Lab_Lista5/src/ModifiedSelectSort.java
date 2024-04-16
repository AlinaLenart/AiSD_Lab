import java.util.Comparator;
import java.util.List;

import core.AbstractSwappingSortingAlgorithm;

public class ModifiedSelectSort<T> extends AbstractSwappingSortingAlgorithm<T> {

    public ModifiedSelectSort(Comparator<? super T> comparator) {

        super(comparator);
    }

    @Override
    public List<T> sort(List<T> list) {

        for (int i = 0; i < list.size() / 2; i++) {

            int minIndex = i;
            int maxIndex = i;

            for (int j = i + 1; j < list.size() - i; j++) {

                if (compare(list.get(j), list.get(minIndex)) < 0) {

                    minIndex = j;
                }
                if (compare(list.get(j), list.get(maxIndex)) > 0) {

                    maxIndex = j;
                }
            }

            if (minIndex != i) {
                swap(list, minIndex, i);

                if (maxIndex == i) {

                    maxIndex = minIndex;
                }
            }

            if (maxIndex != list.size() - i - 1) {

                swap(list, list.size() - i - 1, maxIndex);
            }

        }

        return list;
    }
}
