import java.util.Comparator;

public class ComparatorSortLocation implements Comparator <Contractor> {
	
	public int compare(Contractor a, Contractor b){
		return a.getLocation().compareTo(b.getLocation());
	}
}