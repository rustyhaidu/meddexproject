/*package ro.sci.group5.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ro.sci.group5.domain.Doctor;
import ro.sci.group5.domain.LinkDoctorReview;
import ro.sci.group5.domain.Review;
import ro.sci.group5.service.DoctorService;
import ro.sci.group5.service.LinkService;
import ro.sci.group5.service.ReviewService;

@Controller
@RequestMapping("/reviews")
public class ReviewurileController {
	
	@Autowired	
	ReviewService reviewService;
	@Autowired	
	DoctorService doctorService;
	@Autowired	
	LinkService linkService;

	@RequestMapping("")
	public ModelAndView list(Long id) {
		ModelAndView result = new ModelAndView("review_list");
		Doctor doctor = new Doctor();
		if (id != null) {
			doctor = doctorService.findById(id);
		}
		result.addObject("doctor", doctor);
		result.addObject("reviews", reviewService.listAll());
		return result;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView saveReview(Long doctorID,Review review,BindingResult bindingResult) {
		
		ModelAndView result = null;
		LinkDoctorReview link = new LinkDoctorReview();
		try {			
			reviewService.save(review);
			//link.setDoctorID(doctorID);
			//link.setReviewID(review.getId());
			System.out.println(review.getId()+" "+review.getFirstNameR()+" "+review.getGrade());
			linkService.save(link);
		} catch (Exception e) {			
			result = renderEditPage(review.getId());	
			bindingResult.addError(new ObjectError("review",e.getMessage()));
			
		}
		result = list(doctorID);
		return result;
	}

	@RequestMapping("/review_add")
	public ModelAndView renderEditPage(Long id) {
		ModelAndView result = new ModelAndView("review_add");
		Review review= new Review();
		Doctor doctor = new Doctor();
		if (id != null) {
			doctor = doctorService.findById(id);
		}
		result.addObject("doctor", doctor);
		result.addObject("review", review);
		return result;
	}
}
*/