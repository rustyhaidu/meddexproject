package ro.sci.group5.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ro.sci.group5.domain.Doctor;
import ro.sci.group5.domain.Hospital;
import ro.sci.group5.domain.Review;
import ro.sci.group5.domain.Type;
import ro.sci.group5.service.DoctorService;
import ro.sci.group5.service.HospitalService;

@Controller
@RequestMapping("/doctorsOfTheHospital")
public class DoctorsOfHospitalController {
	@Autowired
	HospitalService hospitalService;
	@Autowired
	DoctorService doctorService;

	@RequestMapping("")
	public ModelAndView list(Long id) {
		ModelAndView view = new ModelAndView("doctorsOfTheHospital");
		Hospital hospital = new Hospital();
		if (id != null) {
			hospital = hospitalService.findById(id);
		}
		view.addObject("hospital", hospital);
		return view;
	}
	/*@RequestMapping(method = RequestMethod.POST)
	public ModelAndView saveReview(Long id, Review review, BindingResult bindingResult) {
		ModelAndView result = list(id);
		try {
			Doctor doctor = new Doctor();
			if (id != null) {
				doctor = doctorService.findById(id);				
			}
			doctor.reviewList.add(review);

			for (Review rev : doctor.reviewList) {
				System.out.println("Review-ul fn" + " " + rev.getFirstNameR());
				System.out.println("Review-ul n" + " " + rev.getName());
				System.out.println("Review-ul em" + " " + rev.getrEmail());
				System.out.println("Review-ul rc" + " " + rev.getReviewContent());
				System.out.println("Review-ul g" + " " + rev.getGrade());
			}
			doctorService.save(doctor);

		} catch (Exception e) {
			// result = renderEditPage(doctor.getId());
			bindingResult.addError(new ObjectError("doctor", e.getMessage()));

		}
		return result;
	}*/
}	