package ro.sci.group5.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ro.sci.group5.service.DoctorService;
import ro.sci.group5.service.HospitalService;

@Controller
@RequestMapping("/doctorToHospitalAdd")
public class DoctorHospitalController {
	@Autowired
	HospitalService hospitalService;
	@Autowired
	DoctorService doctorService;

	@RequestMapping("")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("doctorToHospitalAdd");
		view.addObject("hospitals",hospitalService.listAll());
		view.addObject("doctors", doctorService.listAll());
		return view;
	}
}	