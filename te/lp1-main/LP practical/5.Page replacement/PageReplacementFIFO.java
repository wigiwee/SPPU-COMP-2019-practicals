import java.util.*;
import java.util.LinkedList;

public class PageReplacementFIFO {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of frames in memory:");
        int frameCount = sc.nextInt();

        LinkedList<Integer> frameQueue = new LinkedList<>();
        HashSet<Integer> frameSet = new HashSet<>();

        System.out.println("Enter the number of pages in the reference string:");
        int pageCount = sc.nextInt();
        int[] referenceString = new int[pageCount];

        System.out.println("Enter the page reference string:");
        for (int i = 0; i < pageCount; i++) {
            referenceString[i] = sc.nextInt();
        }

        int pageFaults = 0;
        System.out.println("Page Replacement Steps:");

        for (int page : referenceString) {
            if (!frameSet.contains(page)) {
                if (frameQueue.size() == frameCount) {
                    int removedPage = frameQueue.poll();
                    frameSet.remove(removedPage);
                }
                frameQueue.offer(page);
                frameSet.add(page);
                pageFaults++;
            }

            System.out.println("Page " + page + " -> Frames: " + frameQueue);
        }

        System.out.println("\nTotal Page Faults: " + pageFaults);

        sc.close();
    }
}