package ro.sci.group5.web;

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
@RequestMapping("/students")
public class StudentsControl {
	@Autowired
	DoctorService studentService;

	@RequestMapping("")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("student_list");
		view.addObject("students", studentService.listAll());
		return view;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView saveStudent(Doctor student,BindingResult bindingResult) {
		ModelAndView result = list();
	
		try {
			studentService.save(student);
		} catch (Exception e) {			
			//result = renderEditPage(student.getId());	
			bindingResult.addError(new ObjectError("student",e.getMessage()));
			
		}
		return result;
	}

	@RequestMapping("/student_edit")
	public ModelAndView renderEditPage(Long id) {
		ModelAndView result = new ModelAndView("student_edit");
		Doctor student= new Doctor();
		if (id != null) {
			student = studentService.findById(id);			
		}
		result.addObject("doctor", student);
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
	@RequestMapping(value="/search",method= RequestMethod.POST)
	public ModelAndView search(String query) {
		ModelAndView result = list();
		result.addObject("doctors",studentService.findByName(query));
		return result;
	}

}