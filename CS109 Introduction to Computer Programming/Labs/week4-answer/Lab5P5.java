import java.util.Scanner;

public class Lab5P5 {

	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		/** New array and initialize */
		System.out.print("How many numbers you will input:");
		int n=input.nextInt();
		int array[]=new int[n];
		for(int i=0;i<n;i++){
			array[i]=input.nextInt();
		}

		// Selection sort
		// read more at https://www.cnblogs.com/shen-hua/p/5424059.html
		int register;
		for(int i=0;i<n;i++){
			for(int j=0;j<n-i-1;j++){
				if(array[j]>array[j+1]){
					register=array[j+1];
					array[j+1]=array[j];
					array[j]=register;
				}
			}
		}
		
		for(int e:array){
			System.out.printf("%d ",e);
		}
	}

}
