import java.util.Arrays;
import java.util.List;
public class ToListWithWrapperClass{ 
	
	public static void main(String[] args){
		//Declaring array of Integers
		Integer[] intArray = new Integer[4];
		intArray [0] = 429;
		intArray [1] = 123;
		intArray [2] = 568;
		intArray [3] = 777;
		//turning the array into a list
		List<Integer> intList = Arrays.asList(intArray);
		System.out.println("The array as a List: " + intList);
		//changing the second value or the array
		intArray[1] = 57468;
		System.out.println("The array as a List after change: " + intList);
	}
}