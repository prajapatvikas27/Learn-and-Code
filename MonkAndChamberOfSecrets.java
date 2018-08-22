
import java.util.*;
import java.util.LinkedList;
 
public class MonkAndChamberOfSecrets {
 
    private static class Spider {
        int power;
        int originalIndex;
    }
 
    public static void main(String[] args) {
 
        Scanner in = new Scanner(System.in);
        
        //No. of Spiders in the spiderQueue.
        int N = in.nextInt();
        //No. of iterations as well as no. of spiders to be dequed in every itterartion.
        int X = in.nextInt();
        
        Queue<Spider> spiderQueue = new LinkedList<>();
        for (int i = 0; i < N; i++){
            Spider s = new Spider();
            s.power = in.nextInt();
            s.originalIndex = i+1;
            spiderQueue.add(s);
        }
        //Array contains spiders to be dequed and enqued in each itteration  
        Spider[] dequqEnqueSpiderArray = new Spider[X];
        
        //Iterrating through queue X times
        for (int i = 0; i < X; i++){
            int j = 0;
            //the index at which the spider with maximum power is found
            int maxIndex = 0;
            //loop to deque the X spiders and finding out the spider with maximum power.
            while (!spiderQueue.isEmpty() && j < X){
                Spider dequqEnqueSpiderArraySpider = spiderQueue.remove();
                dequqEnqueSpiderArray[j] = dequqEnqueSpiderArraySpider;
                if (dequqEnqueSpiderArraySpider.power > dequqEnqueSpiderArray[maxIndex].power){
                    maxIndex = j;
                }
                j++;
            }
            System.out.print(dequqEnqueSpiderArray[maxIndex].originalIndex + " ");
            //making j equal to the no. of spiders left to get enqued again
            j--;
            //loop to enque spiders back in spiderQueue excluding spider with maximum power
            //in that itteration
            for (int k = 0 ; k <= j; k++){
                if (dequqEnqueSpiderArray[k].power > 0){
                    dequqEnqueSpiderArray[k].power -= 1;
                }
                if (k != maxIndex){
                    spiderQueue.add(dequqEnqueSpiderArray[k]);
                }
            }
        }
	}
}
 