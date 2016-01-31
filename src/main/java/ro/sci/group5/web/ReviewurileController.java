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
@RequestMapping("/reviewurile")
public class ReviewurileController {
	@Autowired
	DoctorService doctorService;
	ReviewService reviewService;

	@RequestMapping("")	
	public ModelAndView list(Long id) {
		ModelAndView result = new ModelAndView("reviewurile");
		Doctor doctor= new Doctor();
		if (id != null) {
			doctor = doctorService.findById(id);			
		}
		result.addObject("doctor", doctor);
		result.addObject("reviews", doctor.reviewList);
		return result;
	}
	

}