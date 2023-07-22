package pro.sky.service;

import pro.sky.exception.InvalidIndexException;
import pro.sky.exception.NullItemException;
import pro.sky.exception.StorageIsFullException;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {
    private Integer[] arrayLists;
    private int size;

    public IntegerListImpl() {
        arrayLists = new Integer[10];
    }

    public IntegerListImpl(int initSize) {
        arrayLists = new Integer[initSize];
    }

    @Override
    public Integer add(Integer item) {
        growIfNeeded();
        validateItem(item);
        arrayLists[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        growIfNeeded();
        validateItem(item);
        validateIndex(index);

        if (index == size) {
            arrayLists[size++] = item;
            return item;
        }

        System.arraycopy(arrayLists, index, arrayLists, index + 1, size - index);
        arrayLists[index] = item;
        size++;

        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        arrayLists[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        validateItem(item);
        int index = indexOf(item);
        return remove(index);
    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);
        Integer item = arrayLists[index];
        if (index != size) {
            System.arraycopy(arrayLists, index + 1, arrayLists, index, size - index);
        }
        size--;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        Integer[] copyArr = toArray();
        quickSort(copyArr, 0, copyArr.length - 1);
        return binarySearch(copyArr, item);
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (arrayLists[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {
            if (arrayLists[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        validateIndex(index);
        return arrayLists[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
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
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(arrayLists, size);
    }

    private void validateItem(Integer item) {
        if (item == null) {
            throw new NullItemException();
        }
    }

    private void growIfNeeded() {
        if (size == arrayLists.length) {
            grow();
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index > 0) {
            throw new InvalidIndexException();
        }
    }

    private boolean binarySearch(Integer[] arr, Integer item) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item == arr[mid]) {
                return true;
            }

            if (item < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    public void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }

    private void swapElements(Integer[] arr, int indexOne, int indexTwo) {
        int tmp = arr[indexOne];
        arr[indexOne] = arr[indexTwo];
        arr[indexTwo] = tmp;
    }

    private void grow() {
        arrayLists = Arrays.copyOf(arrayLists, size + size /2);
    }
}
