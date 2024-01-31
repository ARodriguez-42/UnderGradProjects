import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Random;
public class PrintQueueSimulation{
	 //Data
	protected int numberOfJobs;
	protected int numberOfPrinters;
	protected int totalWaitTime;
	public Queue<Job> waitQueue;
	public Queue<Job> finishedQueue;
	protected int time = 0;
	public Random randy = new Random();
	Printer[] printers;
	
	//Constructor
	public PrintQueueSimulation(int j, int p, int s){
		numberOfJobs = j;
		numberOfPrinters = p;
		randy.setSeed(s);
		waitQueue = new PriorityQueue<Job>(numberOfJobs);
		finishedQueue = new PriorityQueue<Job>(numberOfJobs);
		totalWaitTime = 0;
		printers = new Printer[numberOfPrinters];
		for (int i = 0; i < numberOfPrinters; i++){
			printers[i] = new Printer("Printer" + i);
		}
	}
	
	//Methods
	public void simulate(){
		//Variables
		int temp = numberOfJobs;
		int numPrint = numberOfPrinters;
		int ranTime;
		int ranPri;
		//Boolean that will be used to keep track if the simulation is done
		boolean flagDone = false;
		while (flagDone != true){
			//if there are more jobs that need to be created and if it's been 10 seconds 
			//since the last job creation, it will create a new job
			if (temp > 0 && (time % 10 == 0)){
				//assigning a value between 10 and 500 for the job time 
				ranTime = (randy.nextInt(491) + 10);
				//assign random value between 1 and 10 for the priority
				ranPri = this.randy.nextInt(10) + 1;
				//create new job
				Job a = new Job(time, ranTime, ranPri);
				//push job onto the waitQueue
				waitQueue.add(a);
				//subtract one from the remaining jobs that need to be created 
				temp--;
			}
			for (int i = 0; i < numPrint; i++){
				/*if the printer has a job, check if the job is complete
				if it is complete, add to the finishedQueue and set the
				printer's job to null     */
				if(printers[i].getJob() != null){
					if(printers[i].getJob().getTimeForTheJob() == (time - printers[i].getJob().getStartTime())){
						printers[i].getJob().setEndTIme(time);
						finishedQueue.add(printers[i].getJob());
						printers[i].job = null;
						printers[i].setStartIdleTime(time);
					}			
				}
			}
			for (int i = 0; i < numPrint; i++){
				/* if the printer's job is ready for a new job or doesn't have a job 
				assigned yet, remove the top priority job from the waitQueue and 
				replace it with the current printer's job value      */
				if(printers[i].getTotalInUseTime() == time || printers[i].getJob() == null){
					if(waitQueue.peek() != null){
						printers[i].setJob(waitQueue.remove());
						printers[i].getJob().setStartTime(time);
						printers[i].setStartInUseTime(printers[i].getJob().getTimeForTheJob());
						totalWaitTime = totalWaitTime + printers[i].getJob().waitTime;
					}
				}
			}
			//increase time by 1
			time++;
			/*If all the jobs have been assigned to printers and all jobs are
			in the finishedQueue, set flagDone to true and end simulation     */
			if (temp == 0 && finishedQueue.size() == numberOfJobs){
				flagDone = true;
			}
		}
		//call displayStatistics method
		displayStatistics();
	}
	
	//Display Stats of the simulation after it completes
	public void displayStatistics(){
		System.out.println();
		//printing the simulation results
		System.out.println("Simulation Results");
		System.out.println("Simulation with " + numberOfPrinters + " printers lasted " + time + " seconds processed " + numberOfJobs + " jobs");
		System.out.println("The average time in the wait queue for a job is " + totalWaitTime/numberOfJobs + " seconds");
		System.out.println();
		System.out.println("Printer Statistics");
		//printing the Printer stats 
		System.out.printf("%-15s %-15s %-15s %-15s", "Name", "Jobs Processed", "Time in Use", "Time Idle");
		// for each printer, print out the required values
		for (int i = 0; i < numberOfPrinters; i++){
			System.out.println();
			System.out.printf("%-15s %-15s %-15s %-15s", printers[i].getPrinterName(), printers[i].getTotalJobsProcessed(), printers[i].getTotalInUseTime(), printers[i].getTotalIdleTime());
		}
		System.out.println();
		System.out.println();
		//printing the Job stats 
		System.out.println("Job Statistics");
		System.out.printf("%-10s %-10s %-10s %-16s %-14s %-10s", "Jobs No.", "Priority", "Wait Time", "Length of Job", "Arrival Time", "Start Time");
		// for each job, print out the required values
		while(!finishedQueue.isEmpty()){
			Job x = finishedQueue.remove();
			System.out.println();
			System.out.printf("%-10s %-10s %-10s %-16s %-14s %-10s", x.getJobID(), x.getPriority(), x.getWaitTime(), x.getTimeForTheJob(), x.getArrivalTime(), x.getStartTime());
			
		}
	}
}