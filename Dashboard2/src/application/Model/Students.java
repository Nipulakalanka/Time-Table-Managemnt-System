package application.Model;

public class Students {

	private int id;
	private String ayrsem;
	private String prog;
	private String ggroup;
	private String sgroup;
	private String groupid;
	private String sgroupid;
	
	
	public Students(int id,String ayrsem, String prog, String ggroup, String sgroup, String groupid, String sgroupid) {
		super();
		this.id=id;
		this.ayrsem = ayrsem;
		this.prog = prog;
		this.ggroup = ggroup;
		this.sgroup = sgroup;
		this.groupid = groupid;
		this.sgroupid = sgroupid;
	}

	public int getId() {
		return id;
	}

	public String getAyrsem() {
		return ayrsem;
	}


	public String getProg() {
		return prog;
	}


	public String getGgroup() {
		return ggroup;
	}


	public String getSgroup() {
		return sgroup;
	}


	public String getGroupid() {
		return groupid;
	}


	public String getSgroupid() {
		return sgroupid;
	}
	
	
	
}
