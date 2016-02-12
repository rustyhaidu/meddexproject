package ro.sci.group5.web;

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
public class ReviewController {
	@Autowired	
	ReviewService reviewService;
	@Autowired	
	DoctorService doctorService;
	@Autowired	
	LinkService linkService;

	@RequestMapping("")
	public ModelAndView list(Long doctorID) {
		ModelAndView result = new ModelAndView("review_list");
		Doctor doctor = new Doctor();
		if (doctorID != null) {
			doctor = doctorService.findById(doctorID);
		}
		
		result.addObject("doctor", doctor);
		result.addObject("reviews", reviewService.findById(doctorID));
		return result;
	}
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView saveReview(Long doctorID,Review review,BindingResult bindingResult) {
		Review rev = new Review();
		ModelAndView result = null;
		LinkDoctorReview link = new LinkDoctorReview();
		try {
			System.out.println("DOCTORID"+" "+doctorID);
			rev =reviewService.save(review);
			link.setDoctorID(doctorID);
			link.setReviewID(rev.getId());
			linkService.save(link);
			System.out.println(rev.getId());
		} catch (Exception e) {			
			result = renderEditPage(doctorID,review.getId());	
			bindingResult.addError(new ObjectError("doctor",e.getMessage()));
			
		}
		result = list(doctorID);
		return result;
	}

	@RequestMapping("/review_add")
	public ModelAndView renderEditPage(Long doctorID, Long reviewID) {
		ModelAndView result = new ModelAndView("review_add");
		Review review= new Review();
		if (reviewID != null) {
			review = reviewService.findById(reviewID);			
		}
		Doctor doctor = new Doctor();
		if (doctorID != null) {
			doctor = doctorService.findById(doctorID);
		}		
		result.addObject("doctor", doctor);
		result.addObject("review", review);
		return result;
	}
	
}