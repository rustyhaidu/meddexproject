package ro.sci.group5.service;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ro.sci.group5.dao.DoctorDao;
import ro.sci.group5.domain.Doctor;


@RestController
@RequestMapping("/rest/doctors")
public class DoctorJSON {
	private static final Logger LOGGER = LoggerFactory.getLogger(DoctorService.class);

	@Autowired
	//@Qualifier("employeeDao")
	private DoctorDao dao;

	@RequestMapping(method = RequestMethod.GET)
	public Collection<Doctor> listAll() {
		return dao.getAll();
	}

	@RequestMapping(method = RequestMethod.GET, params = "query")
	Collection<Doctor> search(@RequestParam(value = "query") String query) {
		LOGGER.debug("Searching for " + query);
		return dao.searchByName(query);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean delete(@PathVariable("id") Long id) {
		LOGGER.debug("Deleting employee for id: " + id);
		Doctor emp = dao.findById(id);
		if (emp != null) {
			dao.delete(emp);
			return true;
		}

		return false;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Doctor get(@PathVariable("id") Long id) {
		LOGGER.debug("Getting employee for id: " + id);
		return dao.findById(id);

	}

	@RequestMapping(method = RequestMethod.POST)
	public void save(Doctor doctor) throws ValidationException {
		LOGGER.debug("Saving: " + doctor);
		//validate(doctor);

		dao.update(doctor);
	}

	/*private void validate(Doctor doctor) throws ValidationException {
		Date currentDate = new Date();
		List<String> errors = new LinkedList<String>();
		if (StringUtils.isEmpty(doctor.getFirstName())) {
			errors.add("First Name is Empty");
		}

		if (StringUtils.isEmpty(doctor.getLastName())) {
			errors.add("Last Name is Empty");
		}

		if (doctor.getGender() == null) {
			errors.add("Gender is Empty");
		}

		if (StringUtils.isEmpty(doctor.getJobTitle())) {
			errors.add("JobTitle is Empty");
		}

		if (doctor.getBirthDate() == null) {
			errors.add("BirthDate is Empty");
		} else {
			if (currentDate.before(employee.getBirthDate())) {
				errors.add("Birthdate in future");
			}
			
			Calendar c = GregorianCalendar.getInstance();
			c.setTime(new Date());
			c.set(Calendar.YEAR, c.get(Calendar.YEAR) - 18);
			if (employee.getBirthDate().after(c.getTime())) {
				errors.add("Too young to get employeed");
			}
			
			c.set(Calendar.YEAR, 1901);
			if (employee.getBirthDate().before(c.getTime())) {
				errors.add("Too old to get employeed");
			}
			
		}

		if (employee.getEmploymentDate() == null) {
			errors.add("EmploymentDate is Empty");
		} else {
			if (currentDate.before(employee.getEmploymentDate())) {
				errors.add("EmploymentDate in future");
			}
		}
		
		if (employee.getBirthDate() != null && employee.getEmploymentDate() != null) {
			if (employee.getEmploymentDate().before(employee.getBirthDate())) {
				errors.add("EmploymentDate before BirthDate");
			}
		}
		
		

		if (!errors.isEmpty()) {
			throw new ValidationException(errors.toArray(new String[] {}));
		}
	}*/
}
