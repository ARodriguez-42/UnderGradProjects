public class Job implements Comparable<Job>{
	
	//Data
	public static int ID = 1;
	protected int arrivalTime;
	protected int timeForTheJob;
	protected int priority;
	protected int startTime;
	protected int waitTime;
	protected int endTime;
	protected int jobID;
	
	//Constructor
	public Job(int at, int tftj, int p){
		arrivalTime = at;
		timeForTheJob = tftj;
		priority = p;
		jobID = ID;
		ID++;
		startTime = 0;
		waitTime = 0;
		endTime = 0;
	}
	
	//Accessors
	
	//return int value of arrival time
	public int getArrivalTime(){
		return arrivalTime;
	}
	//return int value of time for the job
	public int getTimeForTheJob(){
		return timeForTheJob;
	}
	//return int value of priority
	public int getPriority(){
		return priority;
	}
	//return int value of job ID
	public int getJobID(){
		return jobID;
	}
	//return int value of start time
	public int getStartTime(){
		return startTime;
	}
	//return int value of wait time
	public int getWaitTime(){
		return waitTime;
	}
	//return int value of end time
	public int getEndTIme(){
		return endTime;
	}
	
	//Mutators
	//set arrival time to passed in int value
	public void setArrivalTime(int a){
		arrivalTime = a;
	}
	//set time for the job to passed in int value
	public void setTimeForTheJob(int s){
		timeForTheJob = s;
	}
	//set priority to passed in int value
	public void setPriority(int p){
		priority = p;
	}
	//set start time to passed in int value
	//set the wait time by comparing the start time to arrival time
	public void setStartTime(int t){
		startTime = t;
		waitTime = startTime - arrivalTime;
	}
	//set end time to passed in int value
	public void setEndTIme(int e){
		endTime = e;
	}

	//Methods
	//Override the compareTo method of Comparable to sort by priority
	public int compareTo(Job j){
		if(priority == j.priority){
			return 0;
		}
		else if(priority < j.priority){
			return 1;
		}
		else{
			return -1;
		}
	}

	public String toString(){
		return "Job ID: " + jobID;
	}
	
}