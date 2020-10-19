package ro.sci.group5.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ro.sci.group5.domain.Hospital;
import ro.sci.group5.service.HospitalService;
import ro.sci.group5.service.NeighbourhoodService;

/**
 * This class is used for rendering the hospital_list and hospital_edit pages
 *
 */

@Controller
@RequestMapping("/hospitals")
public class HospitalController {
	@Autowired
	HospitalService hospitalService;
	@Autowired
	NeighbourhoodService neighbourhoodService;

	/**
	 * Method used for creating a view with all the available hospitals
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping("")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("hospital_list");
		view.addObject("neighbourhoods",neighbourhoodService.listAll());
		view.addObject("hospitals", hospitalService.listAll());
		return view;
	}

	/**
	 * Method used for saving or editing a hospital
	 * 
	 * @param hospital
	 *            and BindingResult used for handling exceptions
	 * @return ModelAndView
	 */

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView saveHospitals(Hospital hospital, BindingResult bindingResult) {
		ModelAndView result = null;
		Hospital test = new Hospital();
		try {
			System.out.println("Hospitals");
			System.out.println(hospital.getHospitalName());
		test =	hospitalService.save(hospital);
			System.out.println("ID spital"+" "+test.getId());
		} catch (Exception e) {
			result = renderEditPage(hospital.getId());
			bindingResult.addError(new ObjectError("hospital", e.getMessage()));

		}
		result = list();
		return result;
	}

	/**
	 * Method used for rendering the hospital_edit page
	 * 
	 * @param id
	 * @return ModelAndView
	 */

	@RequestMapping("/hospital_edit")
	public ModelAndView renderEditPage(Long id) {
		ModelAndView result = new ModelAndView("hospital_edit");
		Hospital hospital = new Hospital();
		if (id != null) {
			hospital = hospitalService.findById(id);
		}
		result.addObject("hospital", hospital);
		return result;
	}

	/**
	 * Method used for deleting a Hospital by its id
	 * 
	 * @param id
	 * @return ModelAndView
	 */

	@RequestMapping("/hospital_delete")
	public ModelAndView onDelete(long id) {
		ModelAndView result = list();
		if (!hospitalService.delete(id)) {
			result.addObject("error", "ERROR DELETING INEXISTENT HOSPITAL!");
		}
		result = list();
		return result;
	}

}
