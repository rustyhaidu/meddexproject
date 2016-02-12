package ro.sci.group5.dao.inmemory;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import ro.sci.group5.dao.LinkDoctorReviewDao;
import ro.sci.group5.dao.ReviewDAO;
import ro.sci.group5.domain.LinkDoctorReview;
import ro.sci.group5.domain.Review;

//@Repository
public class IMLinkDoctorReviewDAO extends IMBaseDAO<LinkDoctorReview> implements LinkDoctorReviewDao{

	@Override
	public Collection<LinkDoctorReview> searchByName(String query) {
		if (StringUtils.isEmpty(query)) {
			return getAll();
		}
		
		Collection<LinkDoctorReview> all = new LinkedList<LinkDoctorReview>(getAll());
		for (Iterator<LinkDoctorReview> it = all.iterator(); it.hasNext();) {
			LinkDoctorReview link = it.next();
			String ss = link.getDoctorID() + " " + link.getReviewID();
			if (!ss.contains(query)) {
				it.remove();
			}
		}
		return all;
	}

	

}