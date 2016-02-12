package ro.sci.group5.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.sci.group5.dao.LinkDoctorReviewDao;
import ro.sci.group5.domain.LinkDoctorReview;


@Service
public class LinkService {
	@Autowired
	
	private LinkDoctorReviewDao linkDao;

	public LinkDoctorReview save(LinkDoctorReview link) {
		return linkDao.update(link);
	}

	public Collection<LinkDoctorReview> listAll() {
		return linkDao.getAll();
	}
	
	public LinkDoctorReview findById(long id) {
		LinkDoctorReview link = linkDao.findById(id);
		return link;
	}

}