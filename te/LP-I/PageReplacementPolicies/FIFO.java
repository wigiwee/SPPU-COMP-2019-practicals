import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//first in first out
public class FIFO {

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

        Queue<Integer> pageFrame = new LinkedList<>();
        int pageHit =0;
        for(int i =0 ; i< n; i++){
            if(pageFrame.size() < size){
                pageFrame.add(pages[i]);
                continue;
            }
            if(pageFrame.contains(pages[i])){
                pageHit++;
            }else{
                pageFrame.remove();
                pageFrame.add(pages[i]);
            }
        }
        
        pageFault = n - pageHit;
        System.out.println("Page Hit : " + pageHit);
        System.out.println("Page fault : " + pageFault);
        System.out.println("Page hit ratio : " + (float) pageHit/n );
        System.out.println("Page fault ratio : " + (float) pageFault/n );

        in.close();
    }
    
}