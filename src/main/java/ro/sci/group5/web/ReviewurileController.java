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
/*	@RequestMapping("")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("reviewurile");
		view.addObject("doctors", doctorService.listAll());
		return view;
	}*/	
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView saveDoctor(Long id,Review review,BindingResult bindingResult) {
		ModelAndView result = list(id);
		//ModelAndView result = new ModelAndView("doctor_edit");
		//ModelAndView resultR = listReview();
		try {
			Doctor doctor= new Doctor();
			if (id != null) {
				doctor = doctorService.findById(id);			
			}
			doctor.reviewList.add(review);
			
			for (Review rev: doctor.reviewList){
				System.out.println("Review-ul fn"+" "+rev.getFirstNameR());
				System.out.println("Review-ul n"+" "+rev.getName());				
				System.out.println("Review-ul em"+" "+rev.getrEmail());
				System.out.println("Review-ul rc"+" "+rev.getReviewContent());
				System.out.println("Review-ul g"+" "+rev.getGrade());
			}
			doctorService.save(doctor);			
			
		} catch (Exception e) {			
			//result = renderEditPage(doctor.getId());	
			bindingResult.addError(new ObjectError("doctor",e.getMessage()));
			
		}
		return result;
	} 
	

}