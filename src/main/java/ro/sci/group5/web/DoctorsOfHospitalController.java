package ro.sci.group5.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
		view.addObject("hospitalDoctors", hospital.listOfDoctors);
		return view;
	}
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView saveDoctor(Long doctorID, Long hospitalID) {
		ModelAndView result = list(hospitalID);
		try {
			System.out.println(hospitalID);
			System.out.println(doctorID);
			/*System.out.println(hospital.getId());
			System.out.println(hospital.getHospitalName());
			System.out.println(doctor.getFirstName());*/
			Hospital hospital = new Hospital();
			//System.out.println("Doctorul-ul cu" + " " +id+" "+ hospital.getHospitalName());
			
			Doctor doctor = new Doctor();
			if (hospitalID != null) {
				hospital = hospitalService.findById(hospitalID);	
				System.out.println("Found ittt Hospital"+" "+hospital.getHospitalName());
			}
			if (doctorID != null) {
				doctor = doctorService.findById(doctorID);	
				System.out.println("Found it Doctor"+" "+doctor.getFirstName());
			}
			
			//hospital.listOfDoctors.put(doctorID, doctor);
			hospital.listOfDoctors.add(doctor);
			System.out.println("Lista contine "+hospital.listOfDoctors.size());

			/*for (Doctor doc : hospital.listOfDoctors) {
				System.out.println("Doctorul-ul fn" + " " + doc.getFirstName());
				System.out.println("Doctorul-ul n" + " " + doc.getLastName());
				
			}*/
			/*for (Map.Entry<Long, Doctor> entry : hospital.listOfDoctors.entrySet()) {
				System.out.println("Doctorul-ul fn" + " " + entry.getValue().getFirstName());
				System.out.println("Doctorul-ul n" + " " + entry.getValue().getLastName());
			}*/
			hospitalService.save(hospital);

		} catch (Exception e) {
			// result = renderEditPage(doctor.getId());
			
		}
		return result;
	}
	
}	