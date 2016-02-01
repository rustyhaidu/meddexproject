package ro.sci.group5.dao.inmemory;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import ro.sci.group5.dao.HospitalDao;
import ro.sci.group5.domain.Hospital;

@Repository
public class IMHospitalDAO extends IMBaseDAO<Hospital> implements HospitalDao{

	@Override
	public Collection<Hospital> searchByName(String query) {
		if (StringUtils.isEmpty(query)) {
			return getAll();
		}
		
		Collection<Hospital> all = new LinkedList<Hospital>(getAll());
		for (Iterator<Hospital> it = all.iterator(); it.hasNext();) {
			Hospital hos = it.next();
			String ss = hos.getHospitalName() + " " + hos.getNeighbourhood();
			if (!ss.toLowerCase().contains(query.toLowerCase())) {
				it.remove();
			}
		}
		return all;
	}

	

}