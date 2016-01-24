package ro.sci.group2.service;

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
public class StudentServiceTest {

	@Autowired
	private DoctorService service;
	
	@After
	public void tearDown(){
		for (Doctor student:service.listAll()){
			service.delete(student.getId());
		}
	}

	@Test
	public void testSaveNewStudent() {
		Doctor student = new Doctor();
		student.setFirstName("Gigi");
		student.setLastName("Beeeeecali");
		Doctor savedStudent = service.save(student);
		Assert.assertTrue(savedStudent.getId() > 0);
		Assert.assertEquals("Gigi", savedStudent.getFirstName());
		Assert.assertEquals("Beeeeecali", savedStudent.getLastName());
	}

	@Test
	public void testSaveExistingStudent() {
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
		Doctor student = new Doctor();
		student.setFirstName("Gigi");
		student.setLastName("Beeeeecali");
		Doctor savedStudent = service.save(student);
		Assert.assertTrue(service.delete(savedStudent.getId()));		
		Assert.assertNull(service.findById(savedStudent.getId()));		
	}
	@Test
	public void testDoubleDeletionStudent() {
		Doctor student = new Doctor();
		student.setFirstName("Gigi");
		student.setLastName("Beeeeecali");
		Doctor savedStudent = service.save(student); //save returns Doctor
		Assert.assertTrue(service.delete(savedStudent.getId())); //delete and findById return boolean
		Assert.assertFalse(service.delete(savedStudent.getId()));
		Assert.assertNull(service.findById(savedStudent.getId()));		
	}
	@Test
	public void testDeleteInexistingStudent() {		
		Assert.assertFalse(service.delete(-1));				
	}

}
