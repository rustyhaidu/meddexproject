package ro.sci.group5.domain;

/**
 * This class holds fields of LinkDoctorReview objects.
 *
 */

public class LinkDoctorHospital extends AbstractModel {

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
	public long getHospitalID() {
		return reviewID;
	}

	/**
	 * ReviewID setter
	 * 
	 * @param reviewID
	 */
	public void setHospitalID(long reviewID) {
		this.reviewID = reviewID;
	}
}
