public class Printer{
	
	//Data
	protected Job job;
	protected String printerName;
	protected int startInUsetime;
	protected int startIdleTime;
	protected int totalIdleTime;
	protected int totalInUseTime;
	protected int totalJobsProcessed;
	
	//Constructor
	public Printer(String pn){
		printerName = pn;
		job = null;
		startInUsetime = 0;
		startIdleTime = 0;
		totalIdleTime = 0;
		totalInUseTime = 0;
		totalJobsProcessed = 0;
	}
	
	public Printer(String pn, Job j){
		printerName = pn;
		job = j;
		startInUsetime = 0;
		startIdleTime = 0;
		totalIdleTime = 0;
		totalInUseTime = 0;
		totalJobsProcessed = 0;
	}
	
	//Accessors
	//return String value of printer name
	public String getPrinterName(){
		return printerName;
	}
	//return Job value of job
	public Job getJob(){
		return job;
	}
	//return int value of total idle time
	public int getTotalIdleTime(){
		return totalIdleTime;
	}
	//return int value of total in use time
	public int getTotalInUseTime(){
		return totalInUseTime;
	}
	//return int value of total jobs processed
	public int getTotalJobsProcessed(){
		return totalJobsProcessed;
	}
	
	//Mutators
	//set printer name to passed in String value
	public void setPrinterName(String n){
		printerName = n;
	}
	//set job to passed in Job value
	public void setJob(Job j){
		job = j;
	}
	//set start in use time to passed in int value
	public void setStartInUseTime(int p){
		startInUsetime = p;
		totalJobsProcessed = totalJobsProcessed + 1;
	}
	//set start idle time to passed in int value
	public void setStartIdleTime(int t){
		startIdleTime = t;
		totalInUseTime = totalInUseTime + t;
	}
	
	public String toString(){
		return printerName;
	}
}
	
