package ro.sci.group5.dao;

import java.util.Collection;

import ro.sci.group5.domain.LinkDoctorHospital;
import ro.sci.group5.domain.LinkDoctorReview;
/**
* LinkDoctorReviewDao interface extends BaseDAO methods, with LinkDoctorReview
* object types instead of T.
*
*/
public interface LinkDoctorHospitalDao extends BaseDAO<LinkDoctorHospital> {
/**
 * Not used
 */
	Collection<LinkDoctorReview> searchByName(String query);
}
