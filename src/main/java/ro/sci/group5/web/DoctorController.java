package ro.sci.group5.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ro.sci.group5.domain.Doctor;
import ro.sci.group5.service.DoctorService;

/**
 * This class is used for rendering the doctor_list and doctor_edit pages
 *
 */

@Controller
@RequestMapping("/doctors")
public class DoctorController {
	@Autowired
	DoctorService doctorService;

	/**
	 * Method used for creating a view with all the available doctors
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping("")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("doctor_list");
		view.addObject("doctors", doctorService.listAll());
		return view;
	}

	/**
	 * Method used for saving or editing a doctor
	 * 
	 * @param Doctor
	 *            and BindingResult used for handling exceptions
	 * @return ModelAndView
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView saveDoctor(Doctor doctor, BindingResult bindingResult) {

		ModelAndView result = null;
		try {
			doctorService.save(doctor);
		} catch (Exception e) {
			result = renderEditPage(doctor.getId());
			bindingResult.addError(new ObjectError("doctor", e.getMessage()));

		}
		result = list();
		return result;
	}

	/**
	 * Method used for rendering the doctor_edit page
	 * 
	 * @param Long
	 * @return ModelAndView
	 */
	@RequestMapping("/doctor_edit")
	public ModelAndView renderEditPage(Long id) {
		ModelAndView result = new ModelAndView("doctor_edit");
		Doctor doctor = new Doctor();
		if (id != null) {
			doctor = doctorService.findById(id);
		}
		result.addObject("doctor", doctor);
		return result;
	}

	/**
	 * Method used for displaying doctors which are returned after search
	 * 
	 * @param String
	 *            (in firstName and LastName)
	 * @return ModelAndView
	 */

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(String query) {
		ModelAndView result = list();
		result.addObject("doctors", doctorService.findByName(query));
		return result;
	}

	/**
	 * Method used for deleting a Doctor by its id
	 * 
	 * @param id
	 * @return ModelAndView
	 */

	@RequestMapping("/doctor_delete")
	public ModelAndView onDelete(long id) {
		ModelAndView result = list();
		if (!doctorService.delete(id)) {
			result.addObject("error", "ERROR DELETING INEXISTENT DOCTOR!");
		}
		ModelAndView result2 = list();
		return result2;
	}
}