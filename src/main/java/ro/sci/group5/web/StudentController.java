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
@RequestMapping("/doctors/doctor_add")
public class StudentController {
	@Autowired
	DoctorService studentService;

	
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("student_list");
		view.addObject("students", studentService.listAll());
		return view;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView saveStudent(Doctor student,BindingResult bindingResult) {
		ModelAndView result = list();
		System.out.println("save Student");
		try {
			studentService.save(student);
		} catch (Exception e) {			
			result = renderEditPage(student.getId());	
			bindingResult.addError(new ObjectError("student",e.getMessage()));
			
		}
		return result;
	}

	@RequestMapping("")
	public ModelAndView renderEditPage(Long id) {
		System.out.println("Inainte Render Edit page");
		//ModelAndView result = new ModelAndView("doctor_add");
		ModelAndView result = new ModelAndView("student_edit");
		
		Doctor doctor= new Doctor();
		if (id != null) {
			doctor = studentService.findById(id);			
		}
		result.addObject("doctor", doctor);
		System.out.println("Render Edit page"+" "+ doctor.getFirstName()+" "+doctor.getLastName());
		return result;
	}

	@RequestMapping("/student_delete")
	public ModelAndView onDelete(long id) {
		ModelAndView result = list();
		if (!studentService.delete(id)) {
			// bindingResult.addError(new ObjectError("student","ERROR DELETING
			// INEXISTENT STUDENT!"));
			result.addObject("error", "ERROR DELETING INEXISTENT STUDENT!");
		}
		return result;
	}

}