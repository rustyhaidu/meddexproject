package ro.sci.group5.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ro.sci.group5.domain.Doctor;
import ro.sci.group5.service.DoctorService;
import ro.sci.group5.service.HospitalService;
import ro.sci.group5.service.NeighbourhoodService;
import ro.sci.group5.service.ReviewService;

/**
 * This class is used for rendering the index.html page
 *
 */
@Controller
public class IndexController {

	@Autowired
	DoctorService doctorService;
	@Autowired
	HospitalService hospitalService;
	@Autowired
	ReviewService reviewService;
	@Autowired
	NeighbourhoodService neighbourhoodService;

	Doctor doctor = new Doctor();

	/**
	 * Method used for creating a view with the profile page
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping("")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("index");
		view.addObject("neighbourhoods",neighbourhoodService.listAll());
		view.addObject(doctor);

		return view;
	}

	/**
	 * Method used for saving or editing a doctor
	 * 
	 * @param doctor
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, params = "action=add")
	public ModelAndView saveDoctor(Doctor doctor) {
		System.out.println(doctor.getFirstName() + " " + doctor.getLastName());
		ModelAndView view = new ModelAndView("index");
		this.doctor.setFirstName(doctor.getFirstName());
		this.doctor.setLastName(doctor.getLastName());
		this.doctor.setDoctorEmail(doctor.getDoctorEmail());
		this.doctor.setPhoneNumber(doctor.getPhoneNumber());
		this.doctor.setHospital1(doctor.getHospital1());
		this.doctor.setHospital2(doctor.getHospital2());
		this.doctor.setShowPhoneNumber(doctor.isShowPhoneNumber());
		this.doctor.setShowPhoneNumber(doctor.isShowPhoneNumber());
		this.doctor.setSpecialization1(doctor.getSpecialization1());
		this.doctor.setSpecialization2(doctor.getSpecialization2());
		this.doctor.setShowEmail(doctor.isShowEmail());
		this.doctor.setTitleDoctor(doctor.getTitleDoctor());
		view.addObject(doctor);
		return view;
	}

}
