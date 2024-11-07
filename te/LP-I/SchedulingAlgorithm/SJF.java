import java.util.Scanner;

//shortest job first
public class SJF {
    public static void main(String[] args) {
    
        Scanner in = new Scanner(System.in);

        System.out.println("Enter the number of processes");
        int n = in.nextInt();
    
        int pid[] = new int[n];
        int at[] = new int[n];
        int ct[] = new int[n];
        int bt[] = new int[n];
        int tat[] = new int[n];
        int wt[] = new int[n];
        
        boolean isCompleted[] = new boolean[n];
        
        float avgTAT =0;
        float avgWT =0;

        for(int i =0; i < n; i++){
            pid[i] = i;
            System.out.println("Enter the arrival time for P"+i);
            at[i] = in.nextInt();
            System.out.println("Enter the burst time for P" + i);
            bt[i] = in.nextInt();
            isCompleted[i] = false;
        }

        // Sort the processes
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (at[i] > at[j]) {

                    int temp = bt[i];
                    bt[i] = bt[j];
                    bt[j] = temp;

                    temp = at[i];
                    at[i] = at[j];
                    at[j] = temp;

                    temp = pid[i];
                    pid[i] = pid[j];
                    pid[j] = temp;

                    boolean t = isCompleted[i];
                    isCompleted[i] = isCompleted[j];
                    isCompleted[j] = t;
                }
            }
        }

        int currentTime = 0, completed = 0;
        while (completed < n) {
            int minBurst = Integer.MAX_VALUE;
            int minIndex = -1;

            for (int i = 0; i < n; i++) {
                if (!isCompleted[i] && at[i] <= currentTime && bt[i] < minBurst) {
                    minBurst = bt[i];
                    minIndex = i;
                }
            }

            if (minIndex == -1) {
                currentTime++;
            } else {
                ct[minIndex] = currentTime + bt[minIndex];
                currentTime += bt[minIndex];
                isCompleted[minIndex] = true;
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
