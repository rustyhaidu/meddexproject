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
public class IndexController {
	
	@Autowired
	DoctorService doctorService;
	
	Doctor doctor = new Doctor();

	@RequestMapping("")
	public ModelAndView index() {
		doctor.setFirstName("Claudiu");
		doctor.setLastName("Haidu"); 
		doctorService.save(doctor);
		ModelAndView view = new ModelAndView("index");
		
		view.addObject(doctor);
		//System.out.println("Apel Metoda Profile");
		return view;
	} 
	@RequestMapping(value="/",method = RequestMethod.POST, params = "action=add")
	public ModelAndView saveDoctor(Doctor doctor) {
		System.out.println(doctor.getFirstName()+" "+doctor.getLastName());
		ModelAndView view = new ModelAndView("index");
		this.doctor.setFirstName(doctor.getFirstName());
		this.doctor.setLastName(doctor.getLastName());
		view.addObject(doctor);
		System.out.println("Apel Metoda SAVE Doctor");
		System.out.println(doctor.getFirstName()+" "+doctor.getLastName());
		return view;
	}
	
	
}
