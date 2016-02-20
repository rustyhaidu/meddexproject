package ro.sci.group5.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ro.sci.group5.domain.Doctor;
import ro.sci.group5.domain.Hospital;

import ro.sci.group5.service.DoctorService;
import ro.sci.group5.service.HospitalService;

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

	/**
	 * Method used for creating a view with all the available doctors of a
	 * hospital
	 * 
	 * @param id
	 * @return ModelAndView
	 */
	@RequestMapping("")
	public ModelAndView list(Long id) {
		ModelAndView view = new ModelAndView("doctorsOfTheHospital");

		Hospital hospital = new Hospital();
		if (id != null) {
			hospital = hospitalService.findById(id);
		}

		view.addObject("hospital", hospital);
		view.addObject("hospitalDoctors", hospital.listOfDoctors);
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
		ModelAndView result = list(hospitalID);
		try {
			System.out.println(hospitalID);
			System.out.println(doctorID);
			Hospital hospital = new Hospital();

			Doctor doctor = new Doctor();
			if (hospitalID != null) {
				hospital = hospitalService.findById(hospitalID);
			}
			if (doctorID != null) {
				doctor = doctorService.findById(doctorID);
			}

			hospital.listOfDoctors.add(doctor);

			hospitalService.save(hospital);

		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}

}