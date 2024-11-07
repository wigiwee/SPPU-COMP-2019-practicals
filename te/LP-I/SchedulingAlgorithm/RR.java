import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


//round robin scheduling algorithm
public class RR {
        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();
        System.out.print("Enter the quantum: ");
        int quantum = scanner.nextInt();

        Queue<Integer> readyQueue = new LinkedList<>();
        
        int pid[] = new int[n];
        int at[] = new int[n];
        int ct[] = new int[n];
        int bt[] = new int[n];
        int tat[] = new int[n];
        int wt[] = new int[n];
        int remainingTime[] = new int[n];


        float avgWT =0;
        float avgTAT=0;
        
        for(int i=0; i< n; i++){
            pid[i] = i+1;
            System.out.print("Enter P"+pid[i]+" arrival time: ");
            at[i] = scanner.nextInt();
            System.out.print("Enter P"+pid[i]+" burst time: ");
            bt[i] = scanner.nextInt();
            remainingTime[i] = bt[i];
        }
        System.out.println("\n");
        
        // sort the processes by arrival time
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
                    
                    remainingTime[i] = bt[i];
                }
            }
        }

        int completed = 0;
        int i =0;
        int currTime = 0;
        while(completed < n){
            if(readyQueue.isEmpty()){
                currTime = at[i];
                readyQueue.add(i);
                i++;
            }

            if(remainingTime[readyQueue.peek()] <= quantum){
                currTime += remainingTime[readyQueue.peek()];
                remainingTime[readyQueue.peek()] = 0;
                completed ++;
                ct[readyQueue.peek()] = currTime;
                readyQueue.remove();
            }else{
                remainingTime[readyQueue.peek()] -= quantum;
                currTime += quantum;
                readyQueue.add(readyQueue.remove());
            }
            
            for(int a = i; a < n; a++){
                if(at[i] <= currTime){
                    readyQueue.add(i);
                    i++;
                }
            }
        }

        //calculating turn around time tat=ct-at
        for( i = 0 ; i< n; i++)
            tat[i] = ct[i] - at[i];

        //calculating turn around time wt=tat-bt
        for( i = 0 ; i< n; i++)
            wt[i] = tat[i] - bt[i];
        

        System.out.println("  Pid\tAT\tBT\tCT\tTAT\tWT\t ");
        System.out.println("---------------------------------------------");
        for( i = 0 ; i< n; i++){
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

        for( i = 0 ; i< n; i++){
            avgWT = avgWT + wt[i];
            avgTAT = avgTAT + tat[i];
        }
        
        avgWT = avgWT/n;
        avgTAT = avgTAT/n;

        System.out.println("  Avg\t\t\t\t"+avgTAT+"\t"+avgWT+"\t");
        scanner.close();
    }
}