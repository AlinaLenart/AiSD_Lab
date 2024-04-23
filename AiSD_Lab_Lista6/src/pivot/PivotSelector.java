package pivot;

import java.util.List;

public interface PivotSelector<T> {
    T selectPivot(List<T> list, int low, int high);

}