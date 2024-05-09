import java.util.Scanner;

public class RoundRobin {
    static void findWaitingTime(String processes[], int n, int bt[], int at[], int wt[], int tat[], int ct, int quantum) {
        int rem_bt[] = new int[n];
        for (int i = 0; i < n; i++)
            rem_bt[i] = bt[i];

        int t = 0;
        boolean done = false;
        while (!done) {
            done = true;

            for (int i = 0; i < n; i++) {
                if (rem_bt[i] > 0 && at[i] <= t) {
                    done = false;
                    if (rem_bt[i] > quantum) {
                        t += quantum;
                        rem_bt[i] -= quantum;
                    } else {
                        t = t + rem_bt[i];
                        wt[i] = t - bt[i] - at[i];
                        rem_bt[i] = 0;
                        ct = t;  // Completion time for this process
                        tat[i] = bt[i] + wt[i];
                    }
                }
            }
        }
    }

    static void findTurnAroundTime(String processes[], int n, int bt[], int wt[], int tat[]) {
        for (int i = 0; i < n; i++)
            tat[i] = bt[i] + wt[i];
    }

    static void findavgTime(String processes[], int n, int at[], int bt[], int quantum) {
        Scanner scanner = new Scanner(System.in);
        int wt[] = new int[n];
        int tat[] = new int[n];
        int total_wt = 0, total_tat = 0;
        int ct = 0;  // Completion time initially set to 0

        for (int i = 0; i < n; i++) {
            System.out.print("Enter arrival time for process " + processes[i] + ": ");
            at[i] = scanner.nextInt();
            System.out.print("Enter burst time for process " + processes[i] + ": ");
            bt[i] = scanner.nextInt();
        }

        findWaitingTime(processes, n, bt, at, wt, tat, ct, quantum);
        findTurnAroundTime(processes, n, bt, wt, tat);

        System.out.println("PN\tAT\tBT\tCT\tWT\tTAT");
        for (int i = 0; i < n; i++) {
            ct = at[i] + tat[i];  // Completion time is arrival time + turnaround time
            total_wt += wt[i];
            total_tat += tat[i];
            System.out.println(processes[i] + "\t" + at[i] + "\t" + bt[i] + "\t" + ct + "\t" + wt[i] + "\t" + tat[i]);
        }

        System.out.println("Average waiting time = " + (float) total_wt / (float) n);
        System.out.println("Average turn around time = " + (float) total_tat / (float) n);

        scanner.close();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();
        String processes[] = new String[n];
        int at[] = new int[n];
        int bt[] = new int[n];

        for (int i = 0; i < n; i++) {
            processes[i] = "P" + (i + 1);
        }

        System.out.print("Enter time quantum: ");
        int quantum = scanner.nextInt();

        findavgTime(processes, n, at, bt, quantum);

        scanner.close();
    }
}
