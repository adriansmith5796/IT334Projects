package structures;

import java.util.Random;

public class FourWayHeap<T extends Comparable<T>> implements IPriorityQueue<T> {
    // CONSTANTS
    private static final int INIT_CAPACITY = 10;
    private static final int MIN_INDEX = 0;
    private static final int RELOAD_FACTOR = 2;

    T[] heap;
    int size;

    public FourWayHeap() {
        heap = (T[]) new Comparable[INIT_CAPACITY + MIN_INDEX];
        size = 0;
    }

    public FourWayHeap(T[] initialElements) {
        heap = initialElements;
        size = initialElements.length;
        buildHeap();
    }

    @Override
    public void add(T element) {
        if(element == null) throw new IllegalArgumentException("Inserting null is not allowed.");
        if (isFull()) resize();

        heap[size] = element;

        int index = swim(size++);

        heap[index] = element;
    }

    @Override
    public T removeMin() {
        if (isEmpty()) return null;

        T min = heap[MIN_INDEX];

        heap[MIN_INDEX] = heap[--size];
        heap[size] = null;

        sink(MIN_INDEX);


        return min;
    }

    @Override
    public T peek() {
        return heap[MIN_INDEX];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        heap = (T[]) new Comparable[INIT_CAPACITY + MIN_INDEX];
        size = 0;
    }

    @Override
    public boolean contains(T element) {
        for (int index = 0; index < size; index++) {
            if (heap[index] == element)
                return true;
        }


        return false;
    }

    public String toString() {
        String content = "";

        for (int i = MIN_INDEX; i < heap.length; i++) {
            content += "\n[" + i + "] --> " + heap[i];
        }

        return content;
    }

    /************** HELPER METHODS *******************/
    private void buildHeap() {
        for (int i = size / 2; i > 0; i--) {
            sink(i);
        }
    }

    private void sink(int parent) {
        while (parent * 4 < size) {
            // Check math for getting children and parent
            int child = (parent * 4) + 1;

            for (int i = child; i < child + 4; i++) {
                if (i < size) {
                    if (heap[child].compareTo(heap[i]) < 0) {
                        child = i;
                    }
                }
            }

            if (heap[child].compareTo(heap[parent]) >= 0)
                break;

            swap(child, parent);
            parent = child;
        }
    }

    private int swim(int childIndex) {
        T parent = heap[(childIndex - 1) / 4];
        T child = heap[childIndex];

        if (childIndex == 0) {
            return 0;
        }

        while (childIndex > MIN_INDEX && child.compareTo(parent) < 0) {
            parent = heap[(childIndex - 1) / 4];
            child = heap[childIndex];
            swap(childIndex, (childIndex - 1) / 4);
            childIndex = (childIndex - 1) / 4;
        }

        return childIndex;
    }


    private boolean isFull() {
        return size == (heap.length - MIN_INDEX);
    }

    private void resize() {
        T[] oldHeap = heap;
        heap = (T[]) new Comparable[oldHeap.length * RELOAD_FACTOR];
        for (int index = 0; index < oldHeap.length; index++)
            heap[index] = oldHeap[index];
    }

    private void swap(int i, int j) {
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public static void main(String[] args) {
        FourWayHeap<Integer> heap = new FourWayHeap<>();

        Random random = new Random();
        int iterations = 50;

        for (int i = 0; i < iterations; i++) {
            heap.add(i < iterations / 2 ? i : null);
            System.out.println(i + " ADDED");
        }
    }
}
