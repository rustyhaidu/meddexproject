package ro.sci.group5.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.sci.group5.dao.LinkDoctorHospitalDao;
import ro.sci.group5.dao.LinkDoctorReviewDao;
import ro.sci.group5.domain.Doctor;
import ro.sci.group5.domain.LinkDoctorHospital;
import ro.sci.group5.domain.LinkDoctorReview;

/**
 * This class describes the actions that can be done on Link objects like save,
 * edit, delete
 *
 */

@Service
public class LinkDHService {
	@Autowired

	private LinkDoctorHospitalDao linkDHDao;

	/**
	 * Method used for saving and editing a link between a doctor and a review
	 * 
	 * @param link
	 * @return LinkDoctorReview
	 */
	public LinkDoctorHospital save(LinkDoctorHospital link) {
		return linkDHDao.update(link);
	}

	/**
	 * Method used for getting all the links between doctors and reviews
	 * 
	 * @return Collection<LinkDoctorReview>
	 */

	public Collection<LinkDoctorHospital> listAll() {
		return linkDHDao.getAll();
	}

	/**
	 * Method used for finding a link between doctor and review by its id
	 * 
	 * @param id
	 * @return LinkDoctorReview
	 */
	public LinkDoctorHospital findById(long id) {
		LinkDoctorHospital link = linkDHDao.findById(id);
		return link;
	}
	public LinkDoctorHospital findByHospitalId(long id) {
		LinkDoctorHospital link = linkDHDao.findById(id);
		return link;
	}
	/**
	 * Method used for deleting a Link by its id
	 * 
	 * @param id
	 * @return boolean
	 */

	public boolean delete(long id) {
		LinkDoctorHospital link = linkDHDao.findById(id);
		if (link == null) {
			return false;
		} else {
			return linkDHDao.delete(link);
		}
	}

}