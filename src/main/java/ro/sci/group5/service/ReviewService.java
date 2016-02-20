package ro.sci.group5.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sci.group5.dao.ReviewDAO;
import ro.sci.group5.domain.Review;

/**
 * This class describes the actions that can be done on Review objects like
 * save, edit, delete
 *
 */
@Service
public class ReviewService {
	@Autowired

	private ReviewDAO reviewDao;

	/**
	 * Method used for saving and editing a link between a doctor and a review
	 * 
	 * @param review
	 * @return Review
	 */
	public Review save(Review review) {
		return reviewDao.update(review);
	}

	/**
	 * Method used for getting all the reviews
	 * 
	 * @return Collection<Review>
	 */

	public Collection<Review> listAll() {
		return reviewDao.getAll();
	}

	/**
	 * Method used for finding a Review by its id
	 * 
	 * @param id
	 * @return Review
	 */
	public Review findById(long id) {
		Review review = reviewDao.findById(id);
		return review;
	}

	/**
	 * Method used for deleting a Review by its id
	 * 
	 * @param id
	 * @return boolean
	 */

	public boolean delete(long id) {
		Review review = reviewDao.findById(id);
		if (review == null) {
			return false;
		} else {
			return reviewDao.delete(review);
		}
	}
}