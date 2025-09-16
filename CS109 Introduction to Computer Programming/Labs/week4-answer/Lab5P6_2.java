// test input:2 3 5 6 9 10 12 13 15 16
import java.util.Scanner;

public class Lab5P6_2 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in); // Create a Scanner
		
		// Prompt the user to enter how many integers
		System.out.print("Enter how many numbers: ");
		int n=input.nextInt();
		int[] numbers = new int[n]; // Create an array of length ten
		
		System.out.printf("Enter %d numbers: \n",n);

		// calculate the average value
		int total=0;
		for (int i = 0; i < n; i++){
			numbers[i] = input.nextInt();
			total+=numbers[i];
		}
		long current1=System.currentTimeMillis();
		double avg=total/(double)n;
		System.out.println("average="+avg);
		avg*=2;
		int count=0;

		// integrate the idea of binary search
		// rather faster than solution 1
		// time complexity O(nlogn)
		int left,right,mid,smallest;
		
		for(int i=0;i<numbers.length-1;i++){		
			left=i+1;
			right=n-1;
			// aim: find the smallest number that satisifies
			// numbers[i]+numbers[smallest]>avg*2, numbers[i]+numbers[smallest-1]<avg*2
			smallest=n;
			
			while(left<=right){
				mid=(left+right)/2;
				if((numbers[i]+numbers[mid])>avg){
					right=mid-1;
					smallest=mid;	
				}
				else{
					left=mid+1;
				}
			}
			count+=(n-smallest);	
		}
		System.out.println("The number of pairs of integer is "+ count);
		long current2=System.currentTimeMillis();
		System.out.printf("The running time is %.3f second",(current2-current1)/1000.0d);
	}

}
