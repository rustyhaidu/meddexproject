package ro.sci.group5.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ro.sci.group5.domain.Type;
import ro.sci.group5.service.DoctorService;
import ro.sci.group5.service.ReviewService;

@Controller
@RequestMapping("/hospitals/doctorToHospitalAdd")
public class DoctorHospitalController {
	@Autowired
	DoctorService doctorService;
	ReviewService reviewService;

	@RequestMapping("")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("doctor_list");
		view.addObject("doctors", doctorService.listAll());
		return view;
	}	
	
	
	/*@RequestMapping("")
    public List<Type> populateTypes() { 
        Type type1 = new Type(); 
        type1.setId(1); 
        type1.setType("OUTDOOR"); 

        Type type2 = new Type(); 
        type2.setId(2); 
        type2.setType("INDOOR"); 

        List<Type> tipos = new ArrayList<Type>(); 
        tipos.add(type1); 
        tipos.add(type2); 
        return tipos; 
    } */

}
