package ro.sci.group5.dao;

import ro.sci.group5.dao.*;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.sci.group5.domain.Doctor;

@Service
public class DoctorReviewDao {
	@Autowired
	private DoctorDao doctorDao;
	private ReviewDAO reviewDao;

}
