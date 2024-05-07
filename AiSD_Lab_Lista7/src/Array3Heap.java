import java.util.Comparator;
import java.util.NoSuchElementException;

public class Array3Heap<T> {
    private int capacity;
    private T[] heapArray;
    private int size;
    private final Comparator<T> comparator;

    @SuppressWarnings("unchecked")
    public Array3Heap(int startCapacity, Comparator<T> comparator) {
        this.capacity = startCapacity;
        this.heapArray = (T[]) new Object[capacity];
        this.comparator = comparator;
        this.size = 0;
    }
    @SuppressWarnings("unchecked")
    public Array3Heap(Array3Heap<T> heapToCopy) {
        this.capacity = heapToCopy.capacity;
        this.comparator = heapToCopy.comparator;
        this.size = heapToCopy.size();
        this.heapArray = (T[]) new Object[capacity];

        for (int i = 0; i < heapToCopy.size(); i++) {
            this.heapArray[i] = heapToCopy.heapArray[i];
        }
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
        if (size == heapArray.length) { //gdy w kopcu nie ma miejsca, należy rozmiar podwoić
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

    //wynoszenie elementu w górę, wersja iteracyjna
    private void swim(int index) {
        int parentIndex;
        while (index != 0) {
            parentIndex = (index - 1) / 3;
            if (comparator.compare(heapArray[index], heapArray[parentIndex]) > 0) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    // opuszczanie elementu w dół stogu, wersja iteracyjna
    private void sink(int index) {
        int child;
        while ((child = (3 * index) + 1) < size) {
            int maxChildIndex = findMaxChildIndex(child, child + 1, child + 2);
            if (comparator.compare(heapArray[index], heapArray[maxChildIndex]) < 0) {
                swap(index, maxChildIndex);
                index = maxChildIndex;
            } else {
                break;
            }
        }
    }
    private void swap(int index1, int index2) {
        T temp = heapArray[index1];
        heapArray[index1] = heapArray[index2];
        heapArray[index2] = temp;
    }

    private int findMaxChildIndex(int index1, int index2, int index3) {
        int maxIndex = index1;
        if (index2 < size && comparator.compare(heapArray[index2], heapArray[maxIndex]) > 0) {
            maxIndex = index2;
        }
        if (index3 < size && comparator.compare(heapArray[index3], heapArray[maxIndex]) > 0) {
            maxIndex = index3;
        }
        return maxIndex;
    }

    @SuppressWarnings("unchecked")
    private void increaseCapacity() {
        int newCapacity = heapArray.length * 2;
        T[] newHeapArray = (T[]) new Object[newCapacity];

        for (int i = 0; i < size; i++) {
            newHeapArray[i] = heapArray[i];
        }
        heapArray = newHeapArray;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public T nthElement(int n) {
        if (n < 1 || n > size) {
            throw new IllegalArgumentException("Invalid value of n");
        }

        Array3Heap<T> copyHeap = new Array3Heap<>(this); // zlozonosc O(n)

        for (int i = 0; i < n - 1; i++) { // zlozonosc O(k*logn), k wprowadzony numer maksymalnego elementu, O(logn) ma operacja maks
            copyHeap.maximum();
        }

        return copyHeap.maximum();
    }





}
