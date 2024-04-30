import java.util.Comparator;
import java.util.NoSuchElementException;

public class Test<T> {
    private int capacity;
    private T[] heapArray;
    private int size;
    private final Comparator<T> comparator;
    private final int d;

    @SuppressWarnings("unchecked")
    public Test(int startCapacity, Comparator<T> comparator, int d) {
        if (d <= 0) {
            throw new IllegalArgumentException("d must be greater than 0");
        }
        this.capacity = startCapacity;
        this.heapArray = (T[]) new Object[capacity];
        this.comparator = comparator;
        this.size = 0;
        this.d = d;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            heapArray[i] = null;
        }
        size = 0;
    }

    public void add(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }
        if (size == heapArray.length) {
            increaseCapacity();
        }
        heapArray[size++] = element;
        swim(size - 1);
    }

    public T maximum() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        T max = heapArray[0];
        swap(0, --size);
        sink(0);
        heapArray[size] = null;
        return max;
    }

    private void swim(int index) {
        int parentIndex;
        while (index > 0) {
            parentIndex = (index - 1) / d;
            if (comparator.compare(heapArray[index], heapArray[parentIndex]) > 0) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    private void sink(int index) {
        int largestChildIndex;
        while (index * d + 1 < size) {
            largestChildIndex = findLargestChildIndex(index);
            if (comparator.compare(heapArray[index], heapArray[largestChildIndex]) < 0) {
                swap(index, largestChildIndex);
                index = largestChildIndex;
            } else {
                break;
            }
        }
    }

    private int findLargestChildIndex(int index) {
        int largestChild = index * d + 1;
        int end = Math.min(size, largestChild + d);
        for (int i = largestChild + 1; i < end; i++) {
            if (comparator.compare(heapArray[i], heapArray[largestChild]) > 0) {
                largestChild = i;
            }
        }
        return largestChild;
    }

    private void swap(int index1, int index2) {
        T temp = heapArray[index1];
        heapArray[index1] = heapArray[index2];
        heapArray[index2] = temp;
    }

    @SuppressWarnings("unchecked")
    private void increaseCapacity() {
        int newCapacity = heapArray.length * 2;
        T[] newArray = (T[]) new Object[newCapacity];

        for (int i = 0; i < size; i++) {
            newArray[i] = heapArray[i];
        }
        heapArray = newArray;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
