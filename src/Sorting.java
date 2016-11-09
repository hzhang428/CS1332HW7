import java.util.*;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Hao Zhang
 * @version 1.0
 */
public class Sorting {

    /**
     * Your implementation of bubble sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable).
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("input arr or comparator cannot be null");
        }
        boolean swapped = true;
        int size = arr.length;
        while (swapped) {
            swapped = false;
            for (int i = 0; i < size - 1; i++) {
                if (comparator.compare(arr[i + 1], arr[i]) < 0) {
                    swap(arr, i+1, i);
                    swapped = true;
                }
            }
            size -= 1;
        }
    }

    /**
     * Your implementation of insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable).
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("input arr or comparator cannot be null");
        }
        for (int start = 1; start < arr.length; start++) {
            int i = start;
            while (i > 0 && comparator.compare(arr[i], arr[i - 1]) < 0) {
                swap(arr, i - 1, i);
                i -= 1;
            }
        }
    }

    /**
     * Your implementation of selection sort.
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n^2)
     *
     * Note that there may be duplicates in the array, but they may not
     * necessarily stay in the same relative order.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("input arr or comparator cannot be null");
        }
        for (int start = 0; start < arr.length - 1; start++) {
            int minIndex = start;
            for (int i = start + 1; i < arr.length; i++) {
                if (comparator.compare(arr[i], arr[minIndex]) < 0) {
                    minIndex = i;
                }
            }
            if (start != minIndex) {
                swap(arr, minIndex, start);
            }
        }
    }

    /**
     * Your implementation of quick sort.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * Note that there may be duplicates in the array, but they may not
     * necessarily stay in the same relative order.
     * 
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not use the one we have taught you!
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator, Random rand) {
        if (arr == null || comparator == null || rand == null) {
            throw new IllegalArgumentException("input arr or comparator cannot be null");
        }
        quickSortPrivate(arr, 0, arr.length, comparator, rand);

    }

    private static <T> void quickSortPrivate(T[] arr, int start, int end, Comparator<T> comparator, Random rand) {
        if (start >= end) return;
        int pivot = rand.nextInt(end - start) + start;
        System.out.println("pivot: " + pivot);
        swap(arr, start, pivot);
        int i = start + 1;
        System.out.println("start: " + (start));
        int j = end - 1;
        System.out.println("end: " + (end));
        int next = 0;
        boolean crossed = false;
        while (!crossed) {
            while (i <= j && comparator.compare(arr[i], arr[start]) <= 0) {
                i++;
                System.out.println("i: " + i);
            }
            if (i == j) j--;
            while (i <= j && comparator.compare(arr[j], arr[start]) >= 0) {
                j--;
                if (i == j) j--;
                System.out.println("j: " + j);
            }
            if (i > j) {
                swap(arr, start, j);
                next = j;
                System.out.println("Next: " + next);
                crossed = true;
            } else {
                swap(arr, i, j);
            }
        }
        quickSortPrivate(arr, start, next, comparator, rand);
        quickSortPrivate(arr, next + 1, end, comparator, rand);
    }

    /**
     * Your implementation of merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    @SuppressWarnings("unchecked")
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("input arr or comparator cannot be null");
        }
        T[] workingArray = (T[]) new Object[arr.length];
        splitMerge(arr, workingArray, 0, arr.length, comparator);
    }

    private static <T> void splitMerge(T[] A, T[] B, int start, int end, Comparator<T> comparator) {
        if (end - start < 2) return;
        int middle = (start + end) / 2;
        splitMerge(A, B, start, middle, comparator);
        splitMerge(A, B, middle, end, comparator);
        Merge(A, B, start, middle, end, comparator);
        copyArray(A, B, start, end);
    }

    private static <T> void Merge(T[] A, T[] B, int start, int middle, int end, Comparator<T> comparator) {
        int i = start;
        int j = middle;
        for (int k = start; k < end; k++) {
            if (i < middle && (j >= end || comparator.compare(A[i], A[j]) <= 0)) {
                B[k] = A[i];
                i += 1;
            } else {
                B[k] = A[j];
                j += 1;
            }
        }
    }

    private static <T> void copyArray(T[] A, T[] B, int start, int end) {
        for (int i = start; i < end; i++) {
            A[i] = B[i];
        }
    }

    /**
     * Your implementation of LSD (least significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code!
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     *
     * Do NOT use {@code Math.pow()} in your sort. Instead, if you need to, use
     * the provided {@code pow()} method below.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     * @return the sorted array
     */
    public static int[] lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("input arr cannot be null");
        } else if (arr.length == 0) return arr;
        LinkedList<Integer>[] counter = new LinkedList[19];
        for (int i = 0; i < 19; i++) {
            counter[i] = new LinkedList<>();
        }
        int max = getMax(arr);
        int exp = 0;
        while (max/pow(10, exp) != 0) {
            for (int j = 0; j < arr.length; j++) {
                int bucket = arr[j]/pow(10, exp)%10;
                if (bucket == 0) counter[9].add(arr[j]);
                else counter[bucket + 9].add(arr[j]);
            }
            int index = 0;
            for (int j = 0; j < counter.length; j++) {
                while (counter[j].peek() != null) {
                    arr[index] = counter[j].pollFirst();
                    index += 1;
                }
            }
            exp += 1;
        }
        return arr;
    }

    private static int getMax(int[] arr) {
        int max = Math.abs(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(max, Math.abs(arr[i]));
        }
        return max;
    }

    /**
     * swap the two elements in the input array
     *
     * @param arr the arrays to be swapped
     * @param a index of arr
     * @param b index of arr
     * @param <T> data type to sort
     */
    private static <T> void swap(T[] arr, int a, int b) {
        T temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    
    /**
     * Calculate the result of a number raised to a power. Use this method in
     * your radix sorts instead of {@code Math.pow()}.
     * 
     * DO NOT MODIFY THIS METHOD.
     *
     * @throws IllegalArgumentException if both {@code base} and {@code exp} are
     * 0
     * @throws IllegalArgumentException if {@code exp} is negative
     * @param base base of the number
     * @param exp power to raise the base to. Must be 0 or greater.
     * @return result of the base raised to that power
     */
    private static int pow(int base, int exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("Exponent cannot be negative.");
        } else if (base == 0 && exp == 0) {
            throw new IllegalArgumentException(
                    "Both base and exponent cannot be 0.");
        } else if (exp == 0) {
            return 1;
        } else if (exp == 1) {
            return base;
        }
        int result = pow(base, exp / 2);
        if (exp % 2 == 1) {
            return result * result * base;
        } else {
            return result * result;
        }
    }
}
