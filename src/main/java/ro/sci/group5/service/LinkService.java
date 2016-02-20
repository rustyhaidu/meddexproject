package ro.sci.group5.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.sci.group5.dao.LinkDoctorReviewDao;
import ro.sci.group5.domain.LinkDoctorReview;

/**
 * This class describes the actions that can be done on Link objects like save,
 * edit, delete
 *
 */

@Service
public class LinkService {
	@Autowired

	private LinkDoctorReviewDao linkDao;

	/**
	 * Method used for saving and editing a link between a doctor and a review
	 * 
	 * @param link
	 * @return LinkDoctorReview
	 */
	public LinkDoctorReview save(LinkDoctorReview link) {
		return linkDao.update(link);
	}

	/**
	 * Method used for getting all the links between doctors and reviews
	 * 
	 * @return Collection<LinkDoctorReview>
	 */

	public Collection<LinkDoctorReview> listAll() {
		return linkDao.getAll();
	}

	/**
	 * Method used for finding a link between doctor and review by its id
	 * 
	 * @param id
	 * @return LinkDoctorReview
	 */
	public LinkDoctorReview findById(long id) {
		LinkDoctorReview link = linkDao.findById(id);
		return link;
	}

	/**
	 * Method used for deleting a Link by its id
	 * 
	 * @param id
	 * @return boolean
	 */

	public boolean delete(long id) {
		LinkDoctorReview link = linkDao.findById(id);
		if (link == null) {
			return false;
		} else {
			return linkDao.delete(link);
		}
	}

}