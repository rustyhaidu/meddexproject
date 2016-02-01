package ro.sci.group5.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ro.sci.group5.domain.Doctor;
import ro.sci.group5.domain.Review;
import ro.sci.group5.service.DoctorService;
import ro.sci.group5.service.ReviewService;

@Controller
@RequestMapping("/doctors")
public class DoctorController {
	@Autowired
	DoctorService doctorService;
	ReviewService reviewService;

	@RequestMapping("")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("doctor_list");
		view.addObject("doctors", doctorService.listAll());
		return view;
	}
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView saveDoctor(Doctor doctor,BindingResult bindingResult) {
		ModelAndView result = list();
	
		try {
			doctorService.save(doctor);
		} catch (Exception e) {			
			result = renderEditPage(doctor.getId());	
			bindingResult.addError(new ObjectError("doctor",e.getMessage()));
			
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


	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(String query) {
		ModelAndView result = list();
		result.addObject("doctors", doctorService.findByName(query));
		return result;
	}

	@RequestMapping("/review_add")
	public ModelAndView renderAddReview(Long id, Review review) {
		ModelAndView result = new ModelAndView("review_add");
		Doctor doctor = new Doctor();
		if (id != null) {
			doctor = doctorService.findById(id);
		}
		result.addObject("doctor", doctor);
		result.addObject("reviews", doctor.reviewList);
		return result;
	}

	@RequestMapping("/doctor_delete")
	public ModelAndView onDelete(long id) {
		ModelAndView result = list();
		if (!doctorService.delete(id)) {					
			result.addObject("error", "ERROR DELETING INEXISTENT DOCTOR!");
		}
		return result;
	}
}