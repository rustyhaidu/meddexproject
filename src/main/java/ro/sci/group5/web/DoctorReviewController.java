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
public class DoctorReviewController {
	@Autowired
	DoctorService doctorService;
	ReviewService reviewService;

	@RequestMapping("")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("doctor_list");
		view.addObject("doctors", doctorService.listAll());
		return view;
	}	

	/*@RequestMapping(value="",method = RequestMethod.POST)
	public ModelAndView saveTheDoctor(Doctor doctor,BindingResult bindingResult) {
		ModelAndView result = list();		
		System.out.println("save The Doctor"+" "+doctor.getFirstName()+" "+doctor.getLastName());
		try {
			doctorService.save(doctor);
			System.out.println("save The Doctor"+" "+doctor.getFirstName()+" "+doctor.getLastName());
		} catch (Exception e) {			
			result = renderAddPage(doctor.getId());	
			bindingResult.addError(new ObjectError("doctor",e.getMessage()));
			
		}
		return result;
	}*/
	@RequestMapping(value="/search",method= RequestMethod.POST)
	public ModelAndView search(String query) {
		ModelAndView result = list();
		result.addObject("doctors",doctorService.findByName(query));
		return result;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView saveDoctor(Long id,Review review,BindingResult bindingResult) {
		ModelAndView result = list();
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

	@RequestMapping("/doctor_edit")
	public ModelAndView renderEditPage(Long id, Review review) {
		ModelAndView result = new ModelAndView("doctor_edit");
		Doctor doctor= new Doctor();
		if (id != null) {
			doctor = doctorService.findById(id);			
		}
		result.addObject("doctor", doctor);
		return result;
	}
	
	/*@RequestMapping("/doctor_add")
	public ModelAndView renderAddPage(Long id) {
		ModelAndView result = new ModelAndView("doctor_add");
		Doctor doctor= new Doctor();
		if (id != null) {
			doctor = doctorService.findById(id);			
		}
		System.out.println("renderAddPage"+" "+doctor.getFirstName()+" "+doctor.getLastName());
		result.addObject("doctor", doctor);
		return result;
	}*/

	@RequestMapping("/doctor_delete")
	public ModelAndView onDelete(long id) {
		ModelAndView result = list();
		if (!doctorService.delete(id)) {
			// bindingResult.addError(new ObjectError("doctor","ERROR DELETING
			// INEXISTENT STUDENT!"));
			result.addObject("error", "ERROR DELETING INEXISTENT STUDENT!");
		}
		return result;
	}

}