import java.util.*;
import java.util.concurrent.*;

// Mini Project: Parallel Database Query Optimization

public class ParallelQueryOptimization {

    // Sample database table
    static List<Integer> database = new ArrayList<>();

    // Sequential Search
    static List<Integer> sequentialSearch(int target) {

        List<Integer> result = new ArrayList<>();

        for (int value : database) {
            if (value == target) {
                result.add(value);
            }
        }

        return result;
    }

    // Parallel Search
    static List<Integer> parallelSearch(int target) throws Exception {

        List<Integer> result = Collections.synchronizedList(new ArrayList<>());

        int numThreads = 4;

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        int chunkSize = database.size() / numThreads;

        List<Callable<Void>> tasks = new ArrayList<>();

        for (int t = 0; t < numThreads; t++) {

            int start = t * chunkSize;
            int end = (t == numThreads - 1) ? database.size() : start + chunkSize;

            tasks.add(() -> {

                for (int i = start; i < end; i++) {

                    if (database.get(i) == target) {
                        result.add(database.get(i));
                    }
                }

                return null;
            });
        }

        executor.invokeAll(tasks);

        executor.shutdown();

        return result;
    }

    public static void main(String[] args) throws Exception {

        Random random = new Random();

        // Fill database with random values
        for (int i = 0; i < 1000000; i++) {
            database.add(random.nextInt(100));
        }

        int target = 50;

        // Sequential Query
        long start = System.currentTimeMillis();

        List<Integer> seqResult = sequentialSearch(target);

        long end = System.currentTimeMillis();

        System.out.println("Sequential Search Time: " + (end - start) + " ms");
        System.out.println("Records Found: " + seqResult.size());

        // Parallel Query
        start = System.currentTimeMillis();

        List<Integer> parResult = parallelSearch(target);

        end = System.currentTimeMillis();

        System.out.println("Parallel Search Time: " + (end - start) + " ms");
        System.out.println("Records Found: " + parResult.size());
    }
}
