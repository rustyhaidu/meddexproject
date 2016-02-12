package ro.sci.group5.domain;

public class Review extends AbstractModel{
	private Doctor doctor;
	private String firstNameR;	
	private String lastNameR;
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

	public String getLastNameR() {
		return lastNameR;
	}
	public void setLastNameR(String lastNameR) {
		this.lastNameR = lastNameR;
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
