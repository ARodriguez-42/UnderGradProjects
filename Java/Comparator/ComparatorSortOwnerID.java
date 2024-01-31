import java.util.Comparator;

public class ComparatorSortOwnerID implements Comparator <Contractor> {
	
	public int compare(Contractor a, Contractor b){
		return a.getOwnerID() - b.getOwnerID();
	}
}