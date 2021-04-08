package application.Model;

public class Tags {

	private int id;
	private String subjectn;
	private String subjectc;
	private String tagr;
	
	
	public Tags(int id,String subjectn, String subjectc, String tagr) {
		super();
		this.id= id;
		this.subjectn = subjectn;
		this.subjectc = subjectc;
		this.tagr = tagr;
	}


	public int getId() {
		return id;
	}


	public String getSubjectn() {
		return subjectn;
	}


	public String getSubjectc() {
		return subjectc;
	}


	public String getTagr() {
		return tagr;
	}
	
	
}
