import core.AbstractSwappingSortingAlgorithm;

import java.util.*;
public class ModifiedMergeSort<T> extends AbstractSwappingSortingAlgorithm<T> {
    public ModifiedMergeSort(Comparator<? super T> comparator) {
        super(comparator);
    }

    public List<T> sort(List<T> list) {

        if (list == null || list.size() <= 1) {
            return list;
        }

        LinkedList<List<T>> queue = new LinkedList<>();

        for (T element : list) {
            List<T> singleElementList = new ArrayList<>();
            singleElementList.add(element);
            queue.addLast(singleElementList);
        }

        List<T> merged = null;

        while (queue.size() > 1) {
            List<T> first = queue.removeFirst();
            List<T> second = queue.removeFirst();
            merged = merge(first, second);
            queue.addLast(merged);

        }

        list = merged;
        return list;
    }

    private List<T> merge(List<T> list1, List<T> list2) {
        List<T> merged = new ArrayList<>();
        int i = 0, j = 0;

        while (i < list1.size() && j < list2.size()) {
            if (compare(list1.get(i), list2.get(j)) <= 0) {
                merged.add(list1.get(i));
                i++;
            } else {
                merged.add(list2.get(j));
                j++;
            }
        }

        while (i < list1.size()) {
            merged.add(list1.get(i));
            i++;
        }

        while (j < list2.size()) {
            merged.add(list2.get(j));
            j++;
        }

        return merged;
    }

}
