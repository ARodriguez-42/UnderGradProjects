import java.util.Comparator;
import java.util.Arrays;
import java.util.List;
public class ComparatorMultiSort implements Comparator <Contractor> {
	
	public int compare(Contractor a, Contractor b){	
		if(a.getName().compareTo(b.getName()) == 0){
			if (a.getLocation().compareTo(b.getLocation()) == 0){
					return a.getOwnerID() - b.getOwnerID();
			}
			else{
				return a.getLocation().compareTo(b.getLocation());
			}
		}
		else{
			return a.getName().compareTo(b.getName());
		}
			
	}
}