import java.util.*;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class ParallelReduction {

    public static void main(String[] args) throws Exception {

        int[] arr = { 10, 20, 30, 40, 50, 60, 70, 80 };

        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Parallel Min
        Future<Integer> minTask = executor.submit(() -> IntStream.of(arr).parallel().min().getAsInt());

        // Parallel Max
        Future<Integer> maxTask = executor.submit(() -> IntStream.of(arr).parallel().max().getAsInt());

        // Parallel Sum
        Future<Integer> sumTask = executor.submit(() -> IntStream.of(arr).parallel().sum());

        // Parallel Average
        Future<Double> avgTask = executor.submit(() -> IntStream.of(arr).parallel().average().getAsDouble());

        int min = minTask.get();
        int max = maxTask.get();
        int sum = sumTask.get();
        double avg = avgTask.get();

        executor.shutdown();

        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Minimum: " + min);
        System.out.println("Maximum: " + max);
        System.out.println("Sum: " + sum);
        System.out.println("Average: " + avg);
    }
}