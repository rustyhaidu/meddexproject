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

@Controller
@RequestMapping("/doctors")
public class DoctorController {
	@Autowired
	DoctorService doctorService;

	@RequestMapping("")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("doctor_list");
		view.addObject("doctors", doctorService.listAll());
		return view;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView saveStudent(Doctor doctor,BindingResult bindingResult) {
		ModelAndView result = list();
	
		try {
			doctorService.save(doctor);
		} catch (Exception e) {			
			result = renderEditPage(doctor.getId());	
			bindingResult.addError(new ObjectError("student",e.getMessage()));
			
		}
		return result;
	}

	@RequestMapping("/doctor_edit")
	public ModelAndView renderEditPage(Long id) {
		ModelAndView result = new ModelAndView("doctor_edit");
		Doctor doctor= new Doctor();
		if (id != null) {
			doctor = doctorService.findById(id);			
		}
		result.addObject("doctor", doctor);
		return result;
	}

	@RequestMapping("/doctor_delete")
	public ModelAndView onDelete(long id) {
		ModelAndView result = list();
		if (!doctorService.delete(id)) {
			// bindingResult.addError(new ObjectError("student","ERROR DELETING
			// INEXISTENT STUDENT!"));
			result.addObject("error", "ERROR DELETING INEXISTENT STUDENT!");
		}
		return result;
	}

}