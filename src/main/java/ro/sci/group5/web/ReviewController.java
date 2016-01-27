package ro.sci.group5.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ro.sci.group5.domain.Review;
import ro.sci.group5.service.DoctorService;
import ro.sci.group5.service.ReviewService;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
	@Autowired
	ReviewService reviewService;
	DoctorService doctorService;
	

	@RequestMapping("")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("review_list");
		view.addObject("reviews",reviewService.listAll());
		return view;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView saveReview(Review review,BindingResult bindingResult) {
		ModelAndView result = list();
		
		try {			
			reviewService.save(review);			
		} catch (Exception e) {			
			result = renderEditPage(review.getId());	
			bindingResult.addError(new ObjectError("review",e.getMessage()));
			
		}
		return result;
	}
	
	@RequestMapping("/review_add")
	public ModelAndView renderEditPage(Long id) {
		ModelAndView result = new ModelAndView("review_add");
		Review review = new Review();
		if (id != null) {
			review = reviewService.findById(id);
		}
		result.addObject("review", review);
		return result;
	}
	

}
