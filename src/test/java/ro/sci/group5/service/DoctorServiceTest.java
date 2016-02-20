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
import ro.sci.group5.service.DoctorService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTests.class)
public class DoctorServiceTest {

	@Autowired
	private DoctorService service;

	@After
	public void tearDown() {
		for (Doctor doctor : service.listAll()) {
			service.delete(doctor.getId());
		}
	}

	@Test
	public void testSaveNewDoctor() {
		Doctor doctor = new Doctor();
		doctor.setFirstName("User1");
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

		Doctor savedDoctor = service.save(doctor);
		Assert.assertTrue(savedDoctor.getId() > 0);
		Assert.assertEquals("User1", savedDoctor.getFirstName());
		Assert.assertEquals("User1Surname", savedDoctor.getLastName());
	}

	@Test
	public void testSaveExistingDoctor() {
		Doctor doctor = new Doctor();
		doctor.setFirstName("User1");
		doctor.setLastName("User1Surname");
		Doctor savedDoctor = service.save(doctor);
		Assert.assertTrue(savedDoctor.getId() > 0);
		Doctor savedDoctor2 = service.save(doctor);
		Assert.assertEquals(savedDoctor, savedDoctor2);
	}

	@Test
	public void testDeleteDoctor() {
		Doctor doctor = new Doctor();
		doctor.setFirstName("User1");
		doctor.setLastName("User1Surname");
		Doctor savedDoctor = service.save(doctor);
		Assert.assertTrue(service.delete(savedDoctor.getId()));
		Assert.assertNull(service.findById(savedDoctor.getId()));

	}

	@Test
	public void testDoubleDeletionDoctor() {
		Doctor doctor = new Doctor();
		doctor.setFirstName("User1");
		doctor.setLastName("User1Surname");
		Doctor doctorSaved = service.save(doctor); // save returns Doctor
		Assert.assertTrue(service.delete(doctorSaved.getId())); // delete always
																// returns false
																// in DB
		Assert.assertFalse(service.delete(doctorSaved.getId()));
		Assert.assertNull(service.findById(doctorSaved.getId()));
	}

	@Test
	public void testDeleteInexistingDoctor() {
		Assert.assertFalse(service.delete(-1));
	}

}
