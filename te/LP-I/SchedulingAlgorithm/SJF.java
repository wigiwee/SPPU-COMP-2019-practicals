package SchedulingAlgorithm;

import java.util.ArrayList;
import java.util.Scanner;

//shortest job first scheduling algorithm
public class SJF {


    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();
        ArrayList<Integer> queue = new ArrayList<>();

        int pid[] = new int[n];
        int at[] = new int[n];
        int ct[] = new int[n];
        int bt[] = new int[n];
        int tat[] = new int[n];
        int wt[] = new int[n];

        float avgTAT = 0;
        float avgWT = 0;
        
        //taking input
        for(int i=0; i< n; i++){
            pid[i] = i+1;
            System.out.print("Enter P"+pid[i]+" arrival time: ");
            at[i] = scanner.nextInt();
            System.out.print("Enter P"+pid[i]+" burst time: ");
            bt[i] = scanner.nextInt();
        }
        System.out.println("\n");
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


        //calculating average
        for(int i = 0 ; i< n; i++){
            avgWT = avgWT + wt[i];
            avgTAT = avgTAT + tat[i];
        }
        avgWT = avgWT/n;
        avgTAT = avgTAT/n;
        System.out.println("  Avg\t\t\t\t"+avgTAT+"\t"+avgWT+"\t");
        
        scanner.close();
 

    }    
}
