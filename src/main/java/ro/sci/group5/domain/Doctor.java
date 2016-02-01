package ro.sci.group5.domain;

import java.util.ArrayList;

import groovyjarjarantlr.collections.List;

public class Doctor extends AbstractModel {
	private String firstName;
	private String lastName;
	private String hospital1;
	private String hospital2;
	private String titleDoctor;
	private int phoneNumber;
	private String doctorEmail;
	
	private boolean showPhoneNumber;
	

	private boolean showEmail;
	public boolean isShowEmail() {
		return showEmail;
	}

	public void setShowEmail(boolean showEmail) {
		this.showEmail = showEmail;
	}

	private String specialization1;
	private String specialization2;
	
	public ArrayList<Review> reviewList= new ArrayList<>();
	
	public boolean isShowPhoneNumber() {
		return showPhoneNumber;
	}

	public void setShowPhoneNumber(boolean showPhoneNumber) {
		this.showPhoneNumber = showPhoneNumber;
	}
	
	public ArrayList<Review> getReviewList() {
		return reviewList;
	}

	public void setReviewList(ArrayList<Review> reviewList) {
		this.reviewList = reviewList;
	}

	public String getSpecialization1() {
		return specialization1;
	}

	public void setSpecialization1(String specialization1) {
		this.specialization1 = specialization1;
	}

	public String getSpecialization2() {
		return specialization2;
	}

	public void setSpecialization2(String specialization2) {
		this.specialization2 = specialization2;
	}

	public String getHospital1() {
		return hospital1;
	}

	public void setHospital1(String hospital1) {
		this.hospital1 = hospital1;
	}

	public String getHospital2() {
		return hospital2;
	}

	/*public void setHospital2(Hospital hospital2) {
		this.hospital2 = hospital2;
	}*/

	public String getTitleDoctor() {
		return titleDoctor;
	}

	public void setTitleDoctor(String titleDoctor) {
		this.titleDoctor = titleDoctor;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}	


	public  Doctor(String firstName, String lastName){
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDoctorEmail() {
		return doctorEmail;
	}

	public void setDoctorEmail(String doctorEmail) {
		this.doctorEmail = doctorEmail;
	}

	
	public Doctor(long id) {
		setId(id);
	}

	public Doctor() {
		this(0);
	}	

}
