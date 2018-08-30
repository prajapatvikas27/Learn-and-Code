
import java.util.*;
 
public class RefractoredMonkAndChamberOfSecrets {
	static Scanner in = new Scanner(System.in);
	 //No. of Spiders in the spiderQueue.
	static int nOfSpiders;
	//No. of iterations as well as no. of spiders to be dequed in every itterartion.
	static int X;
    //Array contains spiders to be dequed and enqued in each itteration 
	static Spider[] dequqEnqueSpiderArray;
	static Queue<Spider> spiderQueue = new LinkedList<>();
	static int j;
    //the index at which the spider with maximum power is found
	static int maxIndex;
    public static int getX() {
		return X;
	}


	public static void setX() {
		System.out.println("Enter X");
		X = in.nextInt();
	}


	private static class Spider {
        int power;
        int originalIndex;
    }
    

 
    public int getnOfSpiders() {
		return nOfSpiders;
	}


	public static void setnOfSpiders() {
		System.out.println("Enter no. of spiders");
		nOfSpiders = in.nextInt();
	}

	
	
	public static void main(String[] args) {
 
        setnOfSpiders();  
        setX();
        setUpSpiderQueue();
        setDequqEnqueSpiderArray();
        //Iterrating through queue X times
        for (int i = 0; i < X; i++){
            j = 0;
            maxIndex = 0;
            //loop to deque the X spiders and finding out the spider with maximum power.
            maxIndex = findMaxPowerSpider();
            printMaxPowerSpider();
            //making j equal to the no. of spiders left to get enqued again
            j--;
            enqueSpidersBack();
        }
	}


	private static void printMaxPowerSpider() {
		System.out.print(dequqEnqueSpiderArray[maxIndex].originalIndex + " ");
	}


	private static void enqueSpidersBack() {
		 for (int k = 0 ; k <= j; k++){
             if (dequqEnqueSpiderArray[k].power > 0){
                 dequqEnqueSpiderArray[k].power -= 1;
             }
             if (k != maxIndex){
                 spiderQueue.add(dequqEnqueSpiderArray[k]);
             }
         }
		
	}


	private static void setDequqEnqueSpiderArray() {
		System.out.println("Setting up Deque Enque");
		dequqEnqueSpiderArray = new Spider[X];
		
	}


	private static int findMaxPowerSpider() {
        int maxIndex = 0;
		while (!spiderQueue.isEmpty() && j < X){
             Spider dequqEnqueSpiderArraySpider = spiderQueue.remove();
             dequqEnqueSpiderArray[j] = dequqEnqueSpiderArraySpider;
             if (dequqEnqueSpiderArraySpider.power > dequqEnqueSpiderArray[maxIndex].power){
                 maxIndex = j;
             }
             j++;
         }
		return maxIndex;
	}


	private static void setUpSpiderQueue() {
		System.out.println("Enter Powers od spider");
		for (int i = 0; i < nOfSpiders; i++){
	            Spider s = new Spider();
	            s.power = in.nextInt();
	            s.originalIndex = i+1;
	            spiderQueue.add(s);
	        }
		
	}
}
 