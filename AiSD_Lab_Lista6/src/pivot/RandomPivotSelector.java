package pivot;

import java.util.List;
import java.util.Random;

public class RandomPivotSelector<T> implements PivotSelector<T> {
    @Override
    public T selectPivot(List<T> list, int low, int high) {
        Random rand = new Random();
        return list.get(rand.nextInt(high - low + 1) + low);
    }
}
