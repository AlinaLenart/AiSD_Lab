import java.util.Comparator;
import java.util.List;

import core.AbstractSwappingSortingAlgorithm;

public class ModifiedInsertSort<T> extends AbstractSwappingSortingAlgorithm<T> {

    public ModifiedInsertSort(Comparator<? super T> comparator) {

        super(comparator);
    }

    @Override
    public List<T> sort(List<T> list) {

        for (int i = 1; i < list.size(); i++) {
            T key = list.get(i);
            int insertIndex = binarySearch(list, key, 0, i - 1);
            if (insertIndex < i && compare(key, list.get(insertIndex)) == 0) {
                // Jeśli klucz jest równy innemu kluczowi, szukaj miejsca za równym kluczem
                while (insertIndex < i && compare(key, list.get(insertIndex)) == 0) {
                    insertIndex++;
                }
            }
            for (int j = i; j > insertIndex; j--) {
                swap(list, j, j - 1); // Przesunięcie elementów w prawo
            }
        }
        return list;
    }


    public int binarySearch(List<T> list, T key, int low, int high) {

        while (low <= high) {

            int mid = (low + high) / 2;

            if (compare(key, list.get(mid)) < 0) {

                high = mid - 1;
            }

            else if (compare(key, list.get(mid)) > 0) {

                low = mid + 1;
            }

            else {

                return mid;
            }
        }

        return low;
    }

}
