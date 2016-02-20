package ro.sci.group5.dao.inmemory;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import org.springframework.util.StringUtils;

import ro.sci.group5.dao.LinkDoctorReviewDao;
import ro.sci.group5.domain.LinkDoctorReview;

/**
 * This is the class used for storing In Memory information of LinkDoctorReview
 * type objects.
 */

// @Repository
public class IMLinkDoctorReviewDAO extends IMBaseDAO<LinkDoctorReview> implements LinkDoctorReviewDao {
	/**
	 * Method not used.
	 */
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