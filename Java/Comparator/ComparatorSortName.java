import java.util.Comparator;

public class ComparatorSortName implements Comparator <Contractor> {
	
	public int compare(Contractor a, Contractor b){
		return a.getName().compareTo(b.getName());
	}
}