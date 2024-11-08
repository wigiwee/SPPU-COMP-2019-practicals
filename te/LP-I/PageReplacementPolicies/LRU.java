import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class LRU {

    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the number of pages you want to insert: ");
        int n = in.nextInt();
        System.out.print("Enter the size of frame: ");
        int size = in.nextInt();
        int pages[] = new int[n];

        int pageFault;
        
        for(int i =0; i < n; i++){
            System.out.print("enter process no. "+i +" : ");
            pages[i] = in.nextInt();
        }

        int pageHit =0;
        Queue<Integer> pageFrame = new LinkedList<>(); 
        
        for(int i =0 ; i< n; i++){
            if(pageFrame.size() < size){
                pageFrame.add(pages[i]);
                continue;
            }
            if(pageFrame.contains(pages[i])){
                pageHit++;
                int temp;
                while(pageFrame.peek() != pages[i]){
                    pageFrame.add(pageFrame.remove());
                }
                pageFrame.add(pageFrame.remove());
            }else{
                pageFrame.remove();
                pageFrame.add(pages[i]);
            }
        }


        pageFault = n - pageHit;

        System.out.println("Page Hit : " + pageHit);
        System.out.println("Page fault : " + pageFault);
        System.out.println("Page hit ratio : "+(pageHit * 100)/n + "%");
        System.out.println("Page fault ratio : "+( pageFault * 100)/n + "%");


        in.close();
    }
}