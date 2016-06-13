package ro.sci.group5.dao;

import java.util.Collection;

import ro.sci.group5.domain.Review;
/**
* ReviewDao interface extends BaseDAO methods, with Review object types instead of T.
*
*/
public interface ReviewDao extends BaseDAO<Review> {
/**
 * Not used
 */
	Collection<Review> searchByName(String query);
	Collection<Review> findByDoctorID(Long doctorID);
}
