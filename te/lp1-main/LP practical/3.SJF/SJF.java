import java.util.Scanner;

public class SJF {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of processes:");
        int n = sc.nextInt();
        int pid[] = new int[n];
        int at[] = new int[n]; // Arrival time
        int bt[] = new int[n]; // Burst time

        for (int i = 0; i < n; i++) {
            System.out.println("Enter process " + (i + 1) + " arrival time:");
            at[i] = sc.nextInt();
            System.out.println("Enter process " + (i + 1) + " burst time:");
            bt[i] = sc.nextInt();
            pid[i] = i + 1;
        }

        // Sort the processes by burst time (SJF)
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (bt[i] > bt[j]) {
                    // Swap burst times
                    int temp = bt[i];
                    bt[i] = bt[j];
                    bt[j] = temp;

                    // Swap arrival times
                    temp = at[i];
                    at[i] = at[j];
                    at[j] = temp;

                    // Swap process IDs
                    temp = pid[i];
                    pid[i] = pid[j];
                    pid[j] = temp;
                }
            }
        }

        int ct[] = new int[n]; // Completion time
        int ta[] = new int[n]; // Turnaround time
        int wt[] = new int[n]; // Waiting time

        int st = 0;
        float avgwt = 0, avgta = 0;

        for (int i = 0; i < n; i++) {
            ct[i] = st + bt[i];
            ta[i] = ct[i] - at[i];
            wt[i] = ta[i] - bt[i];
            avgwt += wt[i];
            avgta += ta[i];
            st = ct[i];
        }

        System.out.println("\nPID  Arrival Burst  Completion Turnaround Waiting");
        for (int i = 0; i < n; i++) {
            System.out.println(pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + ta[i] + "\t" + wt[i]);
        }

        System.out.println("\nAverage Turnaround Time: " + (float) (avgta / n));
        System.out.println("Average Waiting Time: " + (float) (avgwt / n));

        sc.close();
    }
}
