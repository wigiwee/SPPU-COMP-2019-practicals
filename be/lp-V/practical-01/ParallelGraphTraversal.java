import java.util.*;
import java.util.concurrent.*;

public class ParallelGraphTraversal {

    static class Graph {
        int vertices;
        List<Integer>[] adj;

        Graph(int v) {
            vertices = v;
            adj = new ArrayList[v];

            for (int i = 0; i < v; i++) {
                adj[i] = new ArrayList<>();
            }
        }

        void addEdge(int u, int v) {
            adj[u].add(v);
            adj[v].add(u); // undirected graph
        }

        // Parallel BFS using ExecutorService
        void parallelBFS(int start) {
            boolean[] visited = new boolean[vertices];
            Queue<Integer> queue = new LinkedList<>();

            visited[start] = true;
            queue.add(start);

            ExecutorService executor = Executors.newFixedThreadPool(4);

            System.out.println("Parallel BFS:");

            while (!queue.isEmpty()) {
                int node = queue.poll();
                System.out.print(node + " ");

                List<Callable<Void>> tasks = new ArrayList<>();

                for (int neighbor : adj[node]) {
                    tasks.add(() -> {
                        synchronized (visited) {
                            if (!visited[neighbor]) {
                                visited[neighbor] = true;
                                synchronized (queue) {
                                    queue.add(neighbor);
                                }
                            }
                        }
                        return null;
                    });
                }

                try {
                    executor.invokeAll(tasks);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            executor.shutdown();
            System.out.println();
        }

        // Parallel DFS
        void parallelDFS(int start) {
            boolean[] visited = new boolean[vertices];

            ExecutorService executor = Executors.newFixedThreadPool(4);

            System.out.println("Parallel DFS:");

            parallelDFSUtil(start, visited, executor);

            executor.shutdown();
            System.out.println();
        }

        void parallelDFSUtil(int node, boolean[] visited, ExecutorService executor) {
            synchronized (visited) {
                if (visited[node])
                    return;

                visited[node] = true;
            }

            System.out.print(node + " ");

            List<Callable<Void>> tasks = new ArrayList<>();

            for (int neighbor : adj[node]) {
                tasks.add(() -> {
                    parallelDFSUtil(neighbor, visited, executor);
                    return null;
                });
            }

            try {
                executor.invokeAll(tasks);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        Graph g = new Graph(6);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 5);

        g.parallelBFS(0);

        g.parallelDFS(0);
    }
}