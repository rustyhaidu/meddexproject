package ro.sci.group5.dao.inmemory;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import ro.sci.group5.dao.DoctorDao;
import ro.sci.group5.domain.Doctor;

@Repository
public class IMDoctorDAO extends IMBaseDAO<Doctor> implements DoctorDao {

	@Override
	public Collection<Doctor> searchByName(String query) {
		if (StringUtils.isEmpty(query)) {
			return getAll();
		}
		
		Collection<Doctor> all = new LinkedList<Doctor>(getAll());
		for (Iterator<Doctor> it = all.iterator(); it.hasNext();) {
			Doctor emp = it.next();
			String ss = emp.getFirstName() + " " + emp.getLastName();
			if (!ss.toLowerCase().contains(query.toLowerCase())) {
				it.remove();
			}
		}
		return all;
	}

	

}
