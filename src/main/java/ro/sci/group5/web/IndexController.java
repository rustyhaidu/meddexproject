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

@Controller
public class IndexController {
	
	@Autowired
	DoctorService doctorService;
	
	Doctor doctor = new Doctor();

	@RequestMapping("")
	public ModelAndView index() {
		doctor.setFirstName("Claudiu");
		doctor.setLastName("Haidu");
		
		// hardcode of a review
		Review review = new Review();
		review.setFirstNameR("Reviewer first name");
		review.setName("Reviewer last name");
		review.setGrade(2);
		review.setrEmail("Reviewer@yahoo.com");
		review.setReviewContent("minunat");
		
		doctor.reviewList.add(review);
		
		doctorService.save(doctor);
		
		ModelAndView view = new ModelAndView("index");
		view.addObject("reviews", doctor.reviewList);
		
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
