import java.util.ArrayList;
import java.util.List;
public class WildCardTester
{ 
	// method printArray to print Integer array 
	
	public static void addCat(List<? super RedCat> list , String name) 
	{ 
		RedCat x = new RedCat(name);
		list.add(x);
		System.out.println("Cat Added named " + name + " was added");
	}
	
	public static void deleteCat(List<? extends Animal> list , String name) 
	{ 
		list.remove(0);
		System.out.println("Cat Added named " + name + " was removed");
	}
	
	public static void printAll(List<?> list) 
	{ 
		System.out.println(list);
	}
	

	
	public static void main(String[] args) 
	{ 
		ArrayList<Animal> listAnimal = new ArrayList<Animal>();
		ArrayList<RedCat> listRedCat = new ArrayList<RedCat>();
		addCat(listAnimal, "Tiger");
		addCat(listRedCat, "Tom");
		addCat(listRedCat, "Siamese");
		addCat(listRedCat, "Tiger");
		System.out.println("The list of Animals: ");
		printAll(listAnimal);
		System.out.println("The list of RedCat: ");
		printAll(listRedCat);
		deleteCat(listRedCat, "Tom");
		System.out.println("The list of RedCat after deletion: ");
		printAll(listRedCat);
	} 
}