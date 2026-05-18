import java.util.*;
import java.util.concurrent.*;

public class ParallelSorting {

    // Sequential Bubble Sort
    static void bubbleSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {

                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Parallel Bubble Sort
    static void parallelBubbleSort(int[] arr) throws Exception {
        int n = arr.length;

        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (int i = 0; i < n; i++) {

            int start = i % 2;

            List<Callable<Void>> tasks = new ArrayList<>();

            for (int j = start; j < n - 1; j += 2) {

                int index = j;

                tasks.add(() -> {
                    if (arr[index] > arr[index + 1]) {
                        int temp = arr[index];
                        arr[index] = arr[index + 1];
                        arr[index + 1] = temp;
                    }
                    return null;
                });
            }

            executor.invokeAll(tasks);
        }

        executor.shutdown();
    }

    // Sequential Merge Sort
    static void mergeSort(int[] arr, int left, int right) {

        if (left < right) {

            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    // Parallel Merge Sort
    static void parallelMergeSort(int[] arr, int left, int right) {

        if (left < right) {

            int mid = (left + right) / 2;

            Thread t1 = new Thread(() -> parallelMergeSort(arr, left, mid));
            Thread t2 = new Thread(() -> parallelMergeSort(arr, mid + 1, right));

            t1.start();
            t2.start();

            try {
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            merge(arr, left, mid, right);
        }
    }

    // Merge Function
    static void merge(int[] arr, int left, int mid, int right) {

        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];

        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {

            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1)
            arr[k++] = L[i++];

        while (j < n2)
            arr[k++] = R[j++];
    }

    public static void main(String[] args) throws Exception {

        int size = 10000;
        Random rand = new Random();

        int[] arr1 = new int[size];
        int[] arr2 = new int[size];
        int[] arr3 = new int[size];
        int[] arr4 = new int[size];

        for (int i = 0; i < size; i++) {
            int value = rand.nextInt(100000);

            arr1[i] = value;
            arr2[i] = value;
            arr3[i] = value;
            arr4[i] = value;
        }

        // Sequential Bubble Sort
        long start = System.currentTimeMillis();
        bubbleSort(arr1);
        long end = System.currentTimeMillis();

        System.out.println("Sequential Bubble Sort Time: " + (end - start) + " ms");

        // Parallel Bubble Sort
        start = System.currentTimeMillis();
        parallelBubbleSort(arr2);
        end = System.currentTimeMillis();

        System.out.println("Parallel Bubble Sort Time: " + (end - start) + " ms");

        // Sequential Merge Sort
        start = System.currentTimeMillis();
        mergeSort(arr3, 0, arr3.length - 1);
        end = System.currentTimeMillis();

        System.out.println("Sequential Merge Sort Time: " + (end - start) + " ms");

        // Parallel Merge Sort
        start = System.currentTimeMillis();
        parallelMergeSort(arr4, 0, arr4.length - 1);
        end = System.currentTimeMillis();

        System.out.println("Parallel Merge Sort Time: " + (end - start) + " ms");
    }
}