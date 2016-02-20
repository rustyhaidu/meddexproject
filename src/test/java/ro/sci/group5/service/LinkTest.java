package ro.sci.group5.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ro.sci.group5.ApplicationTests;
import ro.sci.group5.domain.Doctor;
import ro.sci.group5.domain.LinkDoctorReview;
import ro.sci.group5.domain.Review;
import ro.sci.group5.service.DoctorService;
import ro.sci.group5.service.HospitalService;
import ro.sci.group5.service.LinkService;
import ro.sci.group5.service.ReviewService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTests.class)
public class LinkTest {

	@Autowired
	DoctorService doctorService;
	@Autowired
	HospitalService hospitalService;
	@Autowired
	ReviewService reviewService;
	@Autowired
	LinkService linkService;

	@After
	public void tearDown() {
		for (LinkDoctorReview link : linkService.listAll()) {
			linkService.delete(link.getId());
		}
		for (Doctor doctor : doctorService.listAll()) {
			doctorService.delete(doctor.getId());
		}
		for (Review review : reviewService.listAll()) {
			reviewService.delete(review.getId());
		}
	}

	@Test
	public void testSaveNewReview() {
		Review review = new Review();
		review.setFirstNameR("User1Review");
		review.setLastNameR("SurnameUser1");
		review.setrEmail("user@user.com");
		review.setGrade(4);
		review.setReviewContent("Acest doctor este minunat");

		Doctor doctor = new Doctor();
		doctor.setFirstName("User1FirstName");
		doctor.setLastName("User1Surname");
		doctor.setHospital1("Spital1");
		doctor.setHospital2("Spital2");
		doctor.setPhoneNumber("077787799");
		doctor.setDoctorEmail("doctor@doctor.com");
		doctor.setShowEmail(true);
		doctor.setShowPhoneNumber(true);
		doctor.setTitleDoctor("Primar");
		doctor.setSpecialization1("kinetoterapie");
		doctor.setSpecialization2("chirurgie");

		Doctor savedDoctor = doctorService.save(doctor);
		Review savedReview = reviewService.save(review);

		LinkDoctorReview link = new LinkDoctorReview();
		link.setDoctorID(savedDoctor.getId());
		link.setReviewID(savedReview.getId());

		LinkDoctorReview savedLink = linkService.save(link);

		Assert.assertTrue(savedLink.getId() > 0);
		Assert.assertEquals("User1FirstName",
				doctorService.findById(linkService.findById(savedLink.getId()).getDoctorID()).getFirstName());
		Assert.assertEquals("User1Surname",
				doctorService.findById(linkService.findById(savedLink.getId()).getDoctorID()).getLastName());

	}

	@Test
	public void testSaveExistingDoctor() {
		Review review = new Review();
		review.setFirstNameR("User1");
		review.setLastNameR("SurnameUser1");
		review.setrEmail("user@user.com");
		review.setGrade(4);
		review.setReviewContent("Acest doctor este minunat");

		Doctor doctor = new Doctor();
		doctor.setFirstName("User1FirstName");
		doctor.setLastName("User1Surname");
		doctor.setHospital1("Spital1");
		doctor.setHospital2("Spital2");
		doctor.setPhoneNumber("077787799");
		doctor.setDoctorEmail("doctor@doctor.com");
		doctor.setShowEmail(true);
		doctor.setShowPhoneNumber(true);
		doctor.setTitleDoctor("Primar");
		doctor.setSpecialization1("kinetoterapie");
		doctor.setSpecialization2("chirurgie");

		Doctor savedDoctor = doctorService.save(doctor);
		Review savedReview = reviewService.save(review);

		LinkDoctorReview link = new LinkDoctorReview();
		link.setDoctorID(savedDoctor.getId());
		link.setReviewID(savedReview.getId());

		LinkDoctorReview savedLink = linkService.save(link);
		Assert.assertTrue(savedLink.getId() > 0);
		LinkDoctorReview savedLink2 = linkService.save(link);
		Assert.assertEquals(savedLink, savedLink2);
	}

	@Test
	public void testDeleteReview() {
		Review review = new Review();
		review.setFirstNameR("User1");
		review.setLastNameR("SurnameUser1");
		review.setrEmail("user@user.com");
		review.setGrade(4);
		review.setReviewContent("Acest doctor este minunat");

		Doctor doctor = new Doctor();
		doctor.setFirstName("User1FirstName");
		doctor.setLastName("User1Surname");
		doctor.setHospital1("Spital1");
		doctor.setHospital2("Spital2");
		doctor.setPhoneNumber("077787799");
		doctor.setDoctorEmail("doctor@doctor.com");
		doctor.setShowEmail(true);
		doctor.setShowPhoneNumber(true);
		doctor.setTitleDoctor("Primar");
		doctor.setSpecialization1("kinetoterapie");
		doctor.setSpecialization2("chirurgie");

		Doctor savedDoctor = doctorService.save(doctor);
		Review savedReview = reviewService.save(review);

		LinkDoctorReview link = new LinkDoctorReview();
		link.setDoctorID(savedDoctor.getId());
		link.setReviewID(savedReview.getId());

		LinkDoctorReview savedLink = linkService.save(link);

		Assert.assertTrue(linkService.delete(savedLink.getId()));
		Assert.assertNull(linkService.findById(savedLink.getId()));

	}

	@Test
	public void testDoubleDeletionDoctor() {
		Review review = new Review();
		review.setFirstNameR("User1");
		review.setLastNameR("SurnameUser1");
		review.setrEmail("user@user.com");
		review.setGrade(4);
		review.setReviewContent("Acest doctor este minunat");

		Doctor doctor = new Doctor();
		doctor.setFirstName("User1FirstName");
		doctor.setLastName("User1Surname");
		doctor.setHospital1("Spital1");
		doctor.setHospital2("Spital2");
		doctor.setPhoneNumber("077787799");
		doctor.setDoctorEmail("doctor@doctor.com");
		doctor.setShowEmail(true);
		doctor.setShowPhoneNumber(true);
		doctor.setTitleDoctor("Primar");
		doctor.setSpecialization1("kinetoterapie");
		doctor.setSpecialization2("chirurgie");

		Doctor savedDoctor = doctorService.save(doctor);
		Review savedReview = reviewService.save(review);

		LinkDoctorReview link = new LinkDoctorReview();
		link.setDoctorID(savedDoctor.getId());
		link.setReviewID(savedReview.getId());

		LinkDoctorReview savedLink = linkService.save(link);

		Assert.assertTrue(linkService.delete(savedLink.getId())); // delete
																	// always
																	// returns
																	// false in
																	// DB
		Assert.assertFalse(linkService.delete(savedLink.getId()));
		Assert.assertNull(linkService.findById(savedLink.getId()));
	}

	@Test
	public void testDeleteInexistingReview() {
		Assert.assertFalse(linkService.delete(-1));
	}

}
