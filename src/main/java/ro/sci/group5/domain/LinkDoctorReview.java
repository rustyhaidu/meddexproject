package ro.sci.group5.domain;

public class LinkDoctorReview extends AbstractModel{
	
	private long doctorID;
	private long reviewID;
	
	public long getDoctorID() {
		return doctorID;
	}
	public void setDoctorID(long doctorID) {
		this.doctorID = doctorID;
	}
	public long getReviewID() {
		return reviewID;
	}
	public void setReviewID(long reviewID) {
		this.reviewID = reviewID;
	}
}
