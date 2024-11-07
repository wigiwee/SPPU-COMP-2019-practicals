import java.util.Scanner;

public class Priority {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter the number of processes:");
        int n = in.nextInt();

        int pid[] = new int[n];     // Process IDs
        int at[] = new int[n];      // Arrival times
        int bt[] = new int[n];      // Burst times
        int prio[] = new int[n];    // Priorities (lower number = higher priority)
        int ct[] = new int[n];      // Completion times
        int tat[] = new int[n];     // Turnaround times
        int wt[] = new int[n];      // Waiting times

        boolean isCompleted[] = new boolean[n];

        float avgTAT =0;
        float avgWT = 0;

        // Input process details
        for (int i = 0; i < n; i++) {
            pid[i] = i + 1;
            System.out.println("Enter arrival time for P" + pid[i] + ": ");
            at[i] = in.nextInt();
            System.out.println("Enter burst time for P" + pid[i] + ": ");
            bt[i] = in.nextInt();
            System.out.println("Enter priority for P" + pid[i] + ": ");
            prio[i] = in.nextInt();
            isCompleted[i] = false;
        }

        int currentTime = 0, completed = 0;

        while (completed < n) {
            int maxPriority = Integer.MAX_VALUE;
            int maxIndex = -1;

            for (int i = 0; i < n; i++) {
                if (!isCompleted[i] && at[i] <= currentTime && prio[i] < maxPriority) {
                    maxPriority = prio[i];
                    maxIndex = i;
                }
            }

            if (maxIndex == -1) {
                currentTime++;
            } else {
                ct[maxIndex] = currentTime + bt[maxIndex];

                currentTime += bt[maxIndex];
                isCompleted[maxIndex] = true;
                completed++;
            }
        }


        //calculating turn around time tat=ct-at
        for(int i = 0 ; i< n; i++)
            tat[i] = ct[i] - at[i];

        //calculating turn around time wt=tat-bt
        for(int i = 0 ; i< n; i++)
            wt[i] = tat[i] - bt[i];
        

        System.out.println("  Pid\tAT\tBT\tCT\tTAT\tWT\t ");
        System.out.println("---------------------------------------------");
        for(int i = 0 ; i< n; i++){
            StringBuilder string = new StringBuilder();
            string.append("  P"+pid[i]).append("\t");
            string.append(at[i]).append("\t");
            string.append(bt[i]).append("\t");
            string.append(ct[i]).append("\t");
            string.append(tat[i]).append("\t");
            string.append(wt[i]).append("\t");
            System.out.println(string.toString());
        }
        System.out.println("---------------------------------------------");

        for(int i = 0 ; i< n; i++){
            avgWT = avgWT + wt[i];
            avgTAT = avgTAT + tat[i];
        }
        
        avgWT = avgWT/n;
        avgTAT = avgTAT/n;

        System.out.println("  Avg\t\t\t\t"+avgTAT+"\t"+avgWT+"\t");
        in.close();
    }
}
