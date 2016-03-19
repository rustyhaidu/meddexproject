package ro.sci.group5.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ro.sci.group5.domain.Doctor;
import ro.sci.group5.domain.Hospital;
import ro.sci.group5.domain.LinkDoctorHospital;
import ro.sci.group5.domain.LinkDoctorReview;
import ro.sci.group5.service.DoctorService;
import ro.sci.group5.service.HospitalService;
import ro.sci.group5.service.LinkDHService;

/**
 * This class is used for rendering the doctorsOfTheHospital page
 *
 */

@Controller
@RequestMapping("/doctorsOfTheHospital")
public class DoctorsOfHospitalController {
	@Autowired
	HospitalService hospitalService;
	@Autowired
	DoctorService doctorService;
	@Autowired
	LinkDHService linkDHService;

	/**
	 * Method used for creating a view with all the available doctors of a
	 * hospital
	 * 
	 * @param id
	 * @return ModelAndView
	 */
	@RequestMapping("")
	public ModelAndView list(Long hospitalID) {
		ModelAndView view = new ModelAndView("doctorsOfTheHospital");

		Hospital hospital = new Hospital();
		if (hospitalID != null) {
			hospital = hospitalService.findById(hospitalID);
		}
		//System.out.println("Am id-ul"+ " "+hospitalID);
		
		view.addObject("hospital", hospital);
		view.addObject("hospitalDoctors", doctorService.findByHospitalId(hospitalID));
		//System.out.println(doctorService.findByHospitalId(hospitalID).size());
		return view;
	}

	/**
	 * Method used for saving or editing a doctor to an hospital
	 * 
	 * @param doctorID,
	 *            hospitalID
	 * @return ModelAndView
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView saveDoctor(Long doctorID, Long hospitalID) {		
		ModelAndView result = null;
		try {
			System.out.println(hospitalID);
			System.out.println(doctorID);
			
			LinkDoctorHospital link = new LinkDoctorHospital();		
			
			
			link.setDoctorID(doctorID);
			link.setHospitalID(hospitalID);
			linkDHService.save(link);

		} catch (Exception e) {
			System.out.println(e);
		}
		result = list(hospitalID);
		return result;
	}

}