package ro.sci.group5.dao;

import java.util.Collection;

import ro.sci.group5.domain.Review;
/**
* ReviewDAO interface extends BaseDAO methods, with Review object types instead of T.
*
*/
public interface ReviewDAO extends BaseDAO<Review> {
/**
 * Not used
 */
	Collection<Review> searchByName(String query);
}
