package ro.sci.group5.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ro.sci.group5.MeddexApplication;
import ro.sci.group5.domain.Hospital;
import ro.sci.group5.domain.Doctor;
import ro.sci.group5.service.HospitalService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MeddexApplication.class)

public class HospitalServiceTest {

	@Autowired
	private HospitalService service;

	@After
	public void tearDown() {
		for (Hospital hospital : service.listAll()) {
			service.delete(hospital.getId());
		}
	}

	@Test
	public void testSaveNewHospital() {
		Hospital hospital = new Hospital();
		hospital.setHospitalName("Spitalul de nebuni");
		hospital.setStreetName("Strada Nebunilor");
		hospital.setStreetNumber(10);
		hospital.setNeighbourhood("Manastur");
		hospital.setPhoneNumber("123");
		hospital.setHospitalEmail("nebuni@meddex.ro");

		Hospital savedHospital = service.save(hospital);
		Assert.assertTrue(savedHospital.getId() > 0);
		Assert.assertEquals("Spitalul de nebuni", savedHospital.getHospitalName());

	}

	@Test
	public void testSaveExistingHospital() {
		Hospital hospital = new Hospital();
		hospital.setHospitalName("Spitalul de nebuni");
		hospital.setStreetName("Strada Nebunilor");
		hospital.setStreetNumber(10);
		hospital.setNeighbourhood("Manastur");
		hospital.setPhoneNumber("123");
		hospital.setHospitalEmail("nebuni@meddex.ro");

		Hospital savedHospital = service.save(hospital);
		Assert.assertTrue(savedHospital.getId() > 0);
		Hospital savedHospital2 = service.save(hospital);
		Assert.assertEquals(savedHospital, savedHospital2);
	}

	@Test
	public void testDeleteHospital() {
		Hospital hospital = new Hospital();
		hospital.setHospitalName("Spitalul de nebuni");
		hospital.setStreetName("Strada Nebunilor");
		hospital.setStreetNumber(10);
		hospital.setNeighbourhood("Manastur");
		hospital.setPhoneNumber("123");
		hospital.setHospitalEmail("nebuni@meddex.ro");
		Hospital savedHospital = service.save(hospital);
		Assert.assertTrue(service.delete(savedHospital.getId()));
		Assert.assertNull(service.findById(savedHospital.getId()));
	}

	@Test
	public void testDoubleDeletionHospital() {
		Hospital hospital = new Hospital();
		hospital.setHospitalName("Spitalul de nebuni");
		hospital.setStreetName("Strada Nebunilor");
		hospital.setStreetNumber(10);
		hospital.setNeighbourhood("Manastur");
		hospital.setPhoneNumber("123");
		hospital.setHospitalEmail("nebuni@meddex.ro");

		Hospital savedHospital = service.save(hospital);
		Assert.assertTrue(service.delete(savedHospital.getId()));
		Assert.assertFalse(service.delete(savedHospital.getId()));
		Assert.assertNull(service.findById(savedHospital.getId()));

	}

	@Test
	public void testDeleteInexistingHospital() {
		Assert.assertFalse(service.delete(-1));
	}

	@Test
	public void testAddDoctorToHospital() {
		Hospital hospital = new Hospital();
		hospital.setHospitalName("Spitalul de nebuni");
		hospital.setStreetName("Strada Nebunilor");
		hospital.setStreetNumber(10);
		hospital.setNeighbourhood("Manastur");
		hospital.setPhoneNumber("123");
		hospital.setHospitalEmail("nebuni@meddex.ro");

		Doctor doctor = new Doctor();
		doctor.setFirstName("Doctor");
		doctor.setLastName("House");

		hospital.listOfDoctors.add(doctor);

		Hospital savedHospital1 = service.save(hospital);
		Assert.assertEquals(1, savedHospital1.listOfDoctors.size());
		Assert.assertEquals("House", savedHospital1.listOfDoctors.get(0).getLastName());

	}

}
