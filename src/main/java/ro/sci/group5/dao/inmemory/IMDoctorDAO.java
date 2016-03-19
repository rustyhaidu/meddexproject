package ro.sci.group5.dao.inmemory;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import org.springframework.util.StringUtils;

import ro.sci.group5.dao.DoctorDao;
import ro.sci.group5.domain.Doctor;

/**
 * This is the class used for storing In Memory information of Doctor type
 * objects.
 */

// @Repository
public class IMDoctorDAO extends IMBaseDAO<Doctor> implements DoctorDao {
	/**
	 * This method implements the search of Doctors using as input a string
	 * which is verified if exists in firstName and lastName of a Doctor.
	 * 
	 * @param query
	 * @return Collection<Doctor>
	 * 
	 */
	@Override
	public Collection<Doctor> searchByName(String query) {
		if (StringUtils.isEmpty(query)) {
			return getAll();
		}

		Collection<Doctor> all = new LinkedList<Doctor>(getAll());
		for (Iterator<Doctor> it = all.iterator(); it.hasNext();) {
			Doctor doc = it.next();
			String ss = doc.getFirstName() + " " + doc.getLastName();
			if (!ss.toLowerCase().contains(query.toLowerCase())) {
				it.remove();
			}
		}
		return all;
	}


	@Override
	public Collection<Doctor> findByHospitalID(Long hospitalID) {
		// TODO Auto-generated method stub
		return null;
	}

}
