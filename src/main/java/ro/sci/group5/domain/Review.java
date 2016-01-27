package ro.sci.group5.domain;

public class Review extends AbstractModel{
	private Doctor doctor;
	private String firstNameR;	

	private String name;
	private String address;
	private String reviewContent;
	private float grade;
	private String rEmail;

	public String getFirstNameR() {
		return firstNameR;
	}
	public void setFirstNameR(String firstNameR) {
		this.firstNameR = firstNameR;
	}
	
	public String getrEmail() {
		return rEmail;
	}
	public void setrEmail(String rEmail) {
		this.rEmail = rEmail;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	public String getName() {
		//System.out.println("get name"+" "+name);
		return name;
	}
	public void setName(String name) {
		//System.out.println("set name"+" "+name);
		this.name = name;
	}		

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public float getGrade() {
		return grade;
	}
	public void setGrade(float grade) {
		this.grade = grade;
	}
	public Review(long id) {
		setId(id);
	}

	public Review() {
		this(0);
	}	

}
