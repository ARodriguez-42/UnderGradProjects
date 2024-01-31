import java.util.LinkedList;
import java.util.Random;
import java.util.Collections;

public class LinkedListInsert{
	
	public static void main(String[] args){
		//Create linked list 
		LinkedList<Integer> randomInts = new LinkedList<Integer>();
		//create random
		Random ran = new Random();
		int temp = 0;
		for (int i = 0; i < 25; i++){
			//assign random int(0-100) to temp and add the value to the linked list
			temp = ran.nextInt(101);
			randomInts.add(temp);
		}
		//Sorting the Linked list lowest to highest
		Collections.sort(randomInts);
		System.out.println(randomInts);
		
		int sum = 0;
		//Use for loop to get value of each int in the linked list and add them together
		for (int i = 0; i < 25; i++){
			temp = randomInts.get(i);
			sum = sum + temp;
		}
		//Print out sum
		System.out.println("Sum is: " + sum);
		double average = sum/25.00;
		//Print out average
		System.out.println("Average is: " + average);
		//creating a new linked list for the reverse order
		LinkedList<Integer> randomIntsTemp = new LinkedList<Integer>();
		int size = 24;
		for (int j = 0; j < 25; j++){
			//get value from the end of the end and place it in the front of the new list 
			randomIntsTemp.add(randomInts.get(size));
			size--;
		}
		System.out.println(randomIntsTemp);
			
	}
	
	
}