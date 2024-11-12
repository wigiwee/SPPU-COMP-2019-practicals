import java.util.ArrayList;
import java.util.Scanner;

public class OPT {

    public static void main(String[] args) {
        

        Scanner in = new Scanner(System.in);
        System.out.print("Enter the number of pages you want to insert: ");
        int n = in.nextInt();
        System.out.print("Enter the size of frame: ");
        int frameSize = in.nextInt();
        int pages[] = new int[n];

        int pageHit =0;
        int pageFault = 0;

        for(int i =0; i < n; i++){
            System.out.print("enter process no. "+i +" : ");
            pages[i] = in.nextInt();
        }


        // int[] pageFrame = new int[frameSize];
        ArrayList<Integer> pageFrame = new ArrayList<>();

        for(int i = 0; i < n; i++){
            if( pageFrame.size() < frameSize){
                pageFrame.add(pages[i]);
                continue;
            }
            if(pageFrame.contains(pages[i])){
                pageHit++;
                continue;
            }else{
                int maxIndex = 0;
                for(int j =0; j< frameSize; j++){
                    int maxDistance = n+1;
                    int k;
                    for(k = i+1; k < n; k++){
                        if(pages[k] == pages[i])
                            break;
                    }
                    if(maxDistance < k){
                        maxDistance = k;
                        maxIndex = j;
                    }
                }
                pageFrame.set(maxIndex, pages[i]);
            }
        }
        
        pageFault = n - pageHit;

        System.out.println("Page Hit : " + pageHit);
        System.out.println("Page fault : " + pageFault);
        System.out.println("Page hit ratio : "+(float) pageHit/n );
        System.out.println("Page fault ratio : "+ (float) pageFault/n );
        in.close();
    }
}