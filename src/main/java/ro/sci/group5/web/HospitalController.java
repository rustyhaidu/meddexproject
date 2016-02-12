package ro.sci.group5.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ro.sci.group5.domain.Hospital;
import ro.sci.group5.service.HospitalService;

@Controller
@RequestMapping("/hospitals")
public class HospitalController {
	@Autowired
	HospitalService hospitalService;

	@RequestMapping("")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("hospital_list");
		view.addObject("hospitals",hospitalService.listAll());
		return view;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView saveHospitals(Hospital hospital,BindingResult bindingResult) {
		ModelAndView result = null;
	
		try {
			hospitalService.save(hospital);
		} catch (Exception e) {			
			result = renderEditPage(hospital.getId());	
			bindingResult.addError(new ObjectError("hospital",e.getMessage()));
			
		}
		result = list();
		return result;
	}
	
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
	@RequestMapping("/hospital_delete")
	public ModelAndView onDelete(long id) {
		ModelAndView result = null;
		if (!hospitalService.delete(id)) {					
			result.addObject("error", "ERROR DELETING INEXISTENT HOSPITAL!");
		}
		result = list();
		return result;
	}
	
	

}
