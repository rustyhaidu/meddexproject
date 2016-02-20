package ro.sci.group5.domain;

/**
 * This class holds fields of LinkDoctorReview objects.
 *
 */

public class LinkDoctorReview extends AbstractModel {

	private long doctorID;
	private long reviewID;

	/**
	 * DoctorID getter
	 * 
	 * @return long
	 */
	public long getDoctorID() {
		return doctorID;
	}

	/**
	 * DoctorID setter
	 * 
	 * @param doctorID
	 */
	public void setDoctorID(long doctorID) {
		this.doctorID = doctorID;
	}

	/**
	 * ReviewID getter
	 * 
	 * @return long
	 */
	public long getReviewID() {
		return reviewID;
	}

	/**
	 * ReviewID setter
	 * 
	 * @param reviewID
	 */
	public void setReviewID(long reviewID) {
		this.reviewID = reviewID;
	}
}
