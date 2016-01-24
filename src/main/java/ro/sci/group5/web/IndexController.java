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
	DoctorService studentService;
	
	Doctor student = new Doctor();

	@RequestMapping("")
	public ModelAndView index() {
		student.setFirstName("Claudiu");
		student.setLastName("Haidu"); 
		studentService.save(student);
		ModelAndView view = new ModelAndView("index");
		
		view.addObject(student);
		System.out.println("Apel Metoda Profile");
		return view;
	} 
	@RequestMapping(value="/",method = RequestMethod.POST, params = "action=add")
	public ModelAndView saveStudent(Doctor doctor) {
		System.out.println(doctor.getFirstName()+" "+doctor.getLastName());
		ModelAndView view = new ModelAndView("index");
		this.student.setFirstName(doctor.getFirstName());
		this.student.setLastName(doctor.getLastName());
		view.addObject(doctor);
		System.out.println("Apel Metoda SAVE Doctor");
		System.out.println(doctor.getFirstName()+" "+doctor.getLastName());
		return view;
	}
	
	
}
