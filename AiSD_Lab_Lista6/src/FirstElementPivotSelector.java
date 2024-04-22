import java.util.List;

public class FirstElementPivotSelector<T> implements PivotSelector<T> {
    @Override
    public T selectPivot(List<T> list, int low, int high) {
        return list.get(low);
    }
}
