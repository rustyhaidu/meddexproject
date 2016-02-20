package ro.sci.group5.domain;

import java.util.ArrayList;

/**
 * This class holds fields of Doctor objects.
 *
 */
public class Doctor extends AbstractModel {
	private String firstName;
	private String lastName;
	private String hospital1;
	private String hospital2;
	private String titleDoctor;
	private String phoneNumber;
	private String doctorEmail;
	private boolean showPhoneNumber;
	private boolean showEmail;
	private String specialization1;
	private String specialization2;

	public ArrayList<Review> reviewList = new ArrayList<>();

	/**
	 * This method shows if the user as Doctor wants his email to be shown or
	 * not.
	 * 
	 * @return boolean
	 */
	public boolean isShowEmail() {
		return showEmail;
	}

	/**
	 * showEmail setter
	 * 
	 * @param showEmail
	 */
	public void setShowEmail(boolean showEmail) {
		this.showEmail = showEmail;
	}

	/**
	 * This method shows if the user as Doctor wants his phone number to be
	 * shown or not.
	 * 
	 * @return boolean
	 */
	public boolean isShowPhoneNumber() {
		return showPhoneNumber;
	}

	/**
	 * showPhoneNumber setter
	 * 
	 * @param showPhoneNumber
	 */
	public void setShowPhoneNumber(boolean showPhoneNumber) {
		this.showPhoneNumber = showPhoneNumber;
	}

	/**
	 * ReviewList getter
	 * 
	 * @return ArrayList<Review>
	 */
	public ArrayList<Review> getReviewList() {
		return reviewList;
	}

	/**
	 * ReviewList setter
	 * 
	 * @param reviewList
	 */
	public void setReviewList(ArrayList<Review> reviewList) {
		this.reviewList = reviewList;
	}

	/**
	 * Specialization1 getter
	 * 
	 * @return String
	 */
	public String getSpecialization1() {
		return specialization1;
	}

	/**
	 * Specialization1 setter
	 * 
	 * @param specialization1
	 */
	public void setSpecialization1(String specialization1) {
		this.specialization1 = specialization1;
	}

	/**
	 * Specialization2 getter
	 * 
	 * @return String
	 */
	public String getSpecialization2() {
		return specialization2;
	}

	/**
	 * Specialization2 setter
	 * 
	 * @param specialization2
	 */
	public void setSpecialization2(String specialization2) {
		this.specialization2 = specialization2;
	}

	/**
	 * Hospital1 getter
	 * 
	 * @return String
	 */
	public String getHospital1() {
		return hospital1;
	}

	/**
	 * Hospital1 setter
	 * 
	 * @param hospital1
	 */
	public void setHospital1(String hospital1) {
		this.hospital1 = hospital1;
	}

	/**
	 * Hospital2 getter
	 * 
	 * @return String
	 */
	public String getHospital2() {
		return hospital2;
	}

	/**
	 * Hospital2 setter
	 * 
	 * @param hospital2
	 */
	public void setHospital2(String hospital2) {
		this.hospital2 = hospital2;
	}

	/**
	 * TitleDoctor getter
	 * 
	 * @return String
	 */
	public String getTitleDoctor() {
		return titleDoctor;
	}

	/**
	 * TitleDoctor setter
	 * 
	 * @param titleDoctor
	 */
	public void setTitleDoctor(String titleDoctor) {
		this.titleDoctor = titleDoctor;
	}

	/**
	 * phoneNumber getter
	 * 
	 * @return String
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * TitleDoctor setter
	 * 
	 * @param titleDoctor
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Constructor
	 * 
	 * @param firstName
	 * @param lastName
	 */
	public Doctor(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * FirstName getter
	 * 
	 * @return String
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * FirstName setter
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * LastName getter
	 * 
	 * @return String
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * LastName setter
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * DoctorEmail getter
	 * 
	 * @return String
	 */
	public String getDoctorEmail() {
		return doctorEmail;
	}

	/**
	 * DoctorEmail setter
	 * 
	 * @param doctorEmail
	 */
	public void setDoctorEmail(String doctorEmail) {
		this.doctorEmail = doctorEmail;
	}

	/**
	 * Id setter
	 * 
	 * @param id
	 */
	public Doctor(long id) {
		setId(id);
	}

	/**
	 * Constructor
	 */
	public Doctor() {
		this(0);
	}

}
