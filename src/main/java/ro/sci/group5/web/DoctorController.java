/*package ro.sci.group5.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ro.sci.group5.domain.Doctor;
import ro.sci.group5.domain.Student;
import ro.sci.group5.service.DoctorService;
import ro.sci.group5.service.StudentService;

@Controller
@RequestMapping("/doctors/doctor_add")
public class DoctorController {
	@Autowired
	DoctorService doctorService;
	
	@RequestMapping("")	
	public ModelAndView renderAddPage() {
		ModelAndView result = new ModelAndView("doctor_add");		
		result.addObject("doctors", doctorService.listAll());
		return result;
	}	
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView saveTheDoctor(Doctor doctor,BindingResult bindingResult) {
		ModelAndView result = new ModelAndView("doctor_add");	
		result.addObject("doctors", doctorService.listAll());
		System.out.println("save The Doctor"+" "+doctor.getFirstName()+" "+doctor.getLastName());
		try {
			doctorService.save(doctor);
			System.out.println("save The Doctor"+" "+doctor.getFirstName()+" "+doctor.getLastName());
		} catch (Exception e) {			
			//result = renderAddPage(doctor.getId());	
			bindingResult.addError(new ObjectError("doctor",e.getMessage()));
			
		}
		return result;
	}

	

}*/