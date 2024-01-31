import java.util.Scanner;
public class TestPrintQueueSimulation{
	
	public static void main(String[] args){
		int numPrint;
		int numJobs;
		int seed;
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the number of printers for the simulation: ");
		numPrint = sc.nextInt();
		System.out.println("Please enter the number of printer jobs for the simulation: ");
		numJobs = sc.nextInt();
		System.out.println("Please enter a random number seed for the simulation: ");
		seed = sc.nextInt(); 
		//create new PrintQueueSimulation with the user inputted parameters 
		PrintQueueSimulation PQS = new PrintQueueSimulation(numJobs, numPrint, seed);
		//call simulate method
		PQS.simulate();
	}
}