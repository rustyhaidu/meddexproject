package ro.sci.group5.domain;

/**
 * This class holds fields of Review objects.
 *
 */
public class Review extends AbstractModel {
	private String firstNameR;
	private String lastNameR;
	private String reviewContent;
	private float grade;
	private String rEmail;

	/**
	 * FirstName getter
	 * 
	 * @return String
	 */
	public String getFirstNameR() {
		return firstNameR;
	}

	/**
	 * FirstName setter
	 * 
	 * @param firstNameR
	 */
	public void setFirstNameR(String firstNameR) {
		this.firstNameR = firstNameR;
	}

	/**
	 * Email getter
	 * 
	 * @return String
	 */
	public String getrEmail() {
		return rEmail;
	}

	/**
	 * Email setter
	 * 
	 * @param rEmail
	 */
	public void setrEmail(String rEmail) {
		this.rEmail = rEmail;
	}

	/**
	 * LastName getter
	 * 
	 * @return String
	 */
	public String getLastNameR() {
		return lastNameR;
	}

	/**
	 * LastName setter
	 * 
	 * @param lastNameR
	 */
	public void setLastNameR(String lastNameR) {
		this.lastNameR = lastNameR;
	}

	/**
	 * Review Content getter
	 * 
	 * @return String
	 */
	public String getReviewContent() {
		return reviewContent;
	}

	/**
	 * Review Content setter
	 * 
	 * @param reviewContent
	 */
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	/**
	 * Grade getter
	 * 
	 * @return float
	 */
	public float getGrade() {
		return grade;
	}

	/**
	 * Grade setter
	 * 
	 * @param grade
	 */
	public void setGrade(float grade) {
		this.grade = grade;
	}

	/**
	 * Id setter
	 * 
	 * @param id
	 */
	public Review(long id) {
		setId(id);
	}

	/**
	 * Constructor
	 */
	public Review() {
		this(0);
	}

}
