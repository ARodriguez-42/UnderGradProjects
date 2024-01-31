public class Contractor {

	private String name = ""; // The Contractor Name. Note: A single word
	private String location = ""; // City Location.
	private int ownerID = 0; // The Owner Id.
	
	//Constructor 
	public Contractor(){
		//does nothing 
	}
	
	public Contractor(String name, String location, int ownerID){
		//Creates Contractor with user defined inputs
		this.name = name;
		this.location = location;
		this.ownerID = ownerID;
	}
	
	//Accessor
	public String getName(){
		return name;				//return name value 
	}
	
	public String getLocation(){
		return location;			//return location value
	}
	
	public int getOwnerID(){
		return ownerID;				//return ownerID value
	}
	
	//Setter
	public void setName(String n){
		name = n;						//Set name to passed in String 
	}
	
	public void setLocation(String loc){
		location = loc;						//Set location to passed in String 
	}
	
	public void setOwnerID(int ID){
		ownerID = ID;						//Set ownerID to passed in int 
	}
	
	//Method
	public String toString(){
		String s = name + " " + location + " " + ownerID ;
		return s;
	}
	
}