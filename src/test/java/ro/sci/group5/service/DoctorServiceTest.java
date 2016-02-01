package ro.sci.group5.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ro.sci.group5.MeddexApplication;
import ro.sci.group5.domain.Doctor;
import ro.sci.group5.service.DoctorService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MeddexApplication.class)
public class DoctorServiceTest {

	@Autowired
	private DoctorService service;
	
	@After
	public void tearDown(){
		for (Doctor student:service.listAll()){
			service.delete(student.getId());
		}
	}

	@Test
	public void testSaveNewDoctor() {
		Doctor student = new Doctor();
		student.setFirstName("Gigi");
		student.setLastName("Beeeeecali");
		Doctor savedStudent = service.save(student);
		Assert.assertTrue(savedStudent.getId() > 0);
		Assert.assertEquals("Gigi", savedStudent.getFirstName());
		Assert.assertEquals("Beeeeecali", savedStudent.getLastName());
	}

	@Test
	public void testSaveExistingDoctor() {
		Doctor student = new Doctor();
		student.setFirstName("Gigi");
		student.setLastName("Beeeeecali");
		Doctor savedStudent = service.save(student);
		Assert.assertTrue(savedStudent.getId() > 0);
		Doctor savedStudent2 = service.save(student);
		Assert.assertEquals(savedStudent, savedStudent2);
	}
	@Test
	public void testDeleteStudent() {
		Doctor doctor = new Doctor();
		doctor.setFirstName("Gigi");
		doctor.setLastName("Beeeeecali");
		Doctor savedStudent = service.save(doctor);
		Assert.assertTrue(service.delete(savedStudent.getId()));		
		Assert.assertNull(service.findById(savedStudent.getId()));		
	}
	@Test
	public void testDoubleDeletionStudent() {
		Doctor doctor = new Doctor();
		doctor.setFirstName("Gigi");
		doctor.setLastName("Beeeeecali");
		Doctor doctorSaved = service.save(doctor); //save returns Doctor
		Assert.assertTrue(service.delete(doctorSaved.getId())); //delete and findById return boolean
		Assert.assertFalse(service.delete(doctorSaved.getId()));
		Assert.assertNull(service.findById(doctorSaved.getId()));		
	}
	@Test
	public void testDeleteInexistingStudent() {		
		Assert.assertFalse(service.delete(-1));				
	}

}
