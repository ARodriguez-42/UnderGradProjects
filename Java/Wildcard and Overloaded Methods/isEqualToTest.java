public class isEqualToTest
{ 
	// method printArray to print Integer array 
	
	public static <T> boolean isEqualTo(T inNum1, T inNum2) 
	{ 
		return inNum1.equals(inNum2);		
	} 

	
	public static void main(String[] args) 
	{ 
		int num1 = 5;
		int num2 = 5;
		if (isEqualTo(num1, num2)){
			System.out.println("The integers are equal.");
		}
		double do1 = 5.0;
		double do2 = 5.0;
		if (isEqualTo(do1, do2)){
			System.out.println("The doubles are equal.");
		}
		String s1 = "5";
		String s2 = "5";
		if (isEqualTo(s1, s2)){
			System.out.println("The strings are equal.");
		}
		Object obj1 = new Object();
		Object obj2 = obj1;
		if (isEqualTo(obj1, obj2)){
			System.out.println("The objects are equal.");
		}
	} 
}