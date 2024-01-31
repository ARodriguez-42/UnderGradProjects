import java.util.ArrayList;
import java.util.List;
public class HasNullsTest
{ 
	// method printArray to print Integer array 
	
	public static boolean hasNulls(List<?> list) 
	{ 
		return list.contains(null); 		
	} 

	
	public static void main(String[] args) 
	{ 
		ArrayList<Integer> list1 = new ArrayList<>();
		ArrayList<Integer> list2 = new ArrayList<>();
		for (int i = 0; i < 10; i++){
			list1.add(i);
			list2.add(null);
		}
		if (hasNulls(list1)){
			System.out.println("List 1 does have nulls");
		}
		else{
			System.out.println("List 1 does not have nulls");
		}
		if (hasNulls(list2)){
			System.out.println("List 2 does have nulls");
		}
		else{
			System.out.println("List 1 does not have nulls");
		}
	} 
}