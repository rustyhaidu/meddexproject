package ro.sci.group5.dao.inmemory;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import org.springframework.util.StringUtils;

import ro.sci.group5.dao.ReviewDAO;
import ro.sci.group5.domain.Review;

/**
 * This is the class used for storing In Memory information of Review type
 * objects.
 */
// @Repository
public class IMReviewDAO extends IMBaseDAO<Review> implements ReviewDAO {
	/**
	 * Method not used.
	 */
	@Override
	public Collection<Review> searchByName(String query) {
		if (StringUtils.isEmpty(query)) {
			return getAll();
		}

		Collection<Review> all = new LinkedList<Review>(getAll());
		for (Iterator<Review> it = all.iterator(); it.hasNext();) {
			Review rev = it.next();
			String ss = rev.getFirstNameR() + " " + rev.getLastNameR() + " " + rev.getrEmail() + " "
					+ rev.getReviewContent() + " " + rev.getGrade();
			if (!ss.toLowerCase().contains(query.toLowerCase())) {
				it.remove();
			}
		}
		return all;
	}

	@Override
	public Collection<Review> findByDoctorID(Long doctorID) {
		// TODO Auto-generated method stub
		return null;
	}

}