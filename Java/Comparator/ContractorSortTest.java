import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.lang.*;
import java.util.Collections;

public class ContractorSortTest{
	
	public static void main(String[] args){
		ArrayList<Contractor> cList = new ArrayList<Contractor>();
		String fName;
		Scanner s = new Scanner(System.in);
		String line;
		String name = "";
		String location = "";
		Contractor newContractor;
		int ID = 0;
		System.out.println("Please enter the name of the input file with Contractor Data: ");
		fName = s.next();
		try{
			BufferedReader read = new BufferedReader(new FileReader(fName));
			while((line = read.readLine()) != null){
				String[] lineBits = line.split(" ", 3);
				name = lineBits[0];
				location = lineBits[1];
				ID = Integer.parseInt(lineBits[2]);
				newContractor = new Contractor(name, location, ID);
				cList.add(newContractor);
			}
			read.close();
		}
		catch(FileNotFoundException e){
			System.out.println("FileNotFound error: " + e);
		}
		catch (IOException ioe)
		{
 			System.out.println("IO error: " + ioe);
		}
		System.out.println("List Sorted By Contactor Name");
		Collections.sort(cList, new ComparatorSortName());
		for(Contractor c : cList){
			System.out.println(c);
		}
		System.out.println("");
		System.out.println("List Sorted By Location");
		Collections.sort(cList, new ComparatorSortLocation());
		for(Contractor c : cList){
			System.out.println(c);
		}
		System.out.println("");
		System.out.println("List Sorted By OwnerID");
		Collections.sort(cList, new ComparatorSortOwnerID());
		for(Contractor c : cList){
			System.out.println(c);
		}
		System.out.println("");
		
		Collections.sort(cList, new ComparatorMultiSort());
		System.out.println("List MultiSorted");
		for(Contractor c : cList){
			System.out.println(c);
		}
		
	}
}