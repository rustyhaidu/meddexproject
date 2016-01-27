package ro.sci.group5.dao.inmemory;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import ro.sci.group5.dao.ReviewDAO;
import ro.sci.group5.domain.Review;

@Repository
public class IMReviewDAO extends IMBaseDAO<Review> implements ReviewDAO{

	@Override
	public Collection<Review> searchByName(String query) {
		if (StringUtils.isEmpty(query)) {
			return getAll();
		}
		
		Collection<Review> all = new LinkedList<Review>(getAll());
		for (Iterator<Review> it = all.iterator(); it.hasNext();) {
			Review rev = it.next();
			String ss = rev.getFirstNameR() + " " + rev.getName()+" "+ rev.getrEmail()+ " "+ rev.getReviewContent()+ " "+rev.getGrade();
			if (!ss.toLowerCase().contains(query.toLowerCase())) {
				it.remove();
			}
		}
		return all;
	}

	

}