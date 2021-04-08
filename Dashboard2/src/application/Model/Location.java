package application.Model;

public class Location {
	
	private int id;
	private String bname;
	private String rname;
	private String rtype;
	private int cap;
	
	
	public Location(int id,String bname, String rname, String rtype, int cap) {
		super();
		
		this.id=id;
		this.bname = bname;
		this.rname = rname;
		this.rtype = rtype;
		this.cap = cap;
	}
	
	

	

	public int getId(){
		return id;
		
	}


	public String getBname() {
		return bname;
	}


	public String getRname() {
		return rname;
	}


	public String getRtype() {
		return rtype;
	}


	public int getCap() {
		return cap;
	}



	
	

	
}
