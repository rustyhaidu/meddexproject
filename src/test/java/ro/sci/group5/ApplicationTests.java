package ro.sci.group5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ro.sci.group5.dao.DoctorDao;
import ro.sci.group5.dao.LinkDoctorReviewDao;
import ro.sci.group5.dao.ReviewDao;
import ro.sci.group5.dao.inmemory.IMDoctorDAO;
import ro.sci.group5.dao.inmemory.IMLinkDoctorReviewDAO;
import ro.sci.group5.dao.inmemory.IMReviewDAO;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class ApplicationTests {
	public static void main(String[] args) {
		SpringApplication.run(ApplicationTests.class, args);

	}

	@Bean
	public DoctorDao doctorDao() {
		return new IMDoctorDAO();
		// new JDBCDoctorDAO("localhost", "5432", "test", "test", "test");
	}

	@Bean
	public ReviewDao reviewDao() {
		return new IMReviewDAO();
		// new JDBCDoctorDAO("localhost", "5432", "test", "test", "test");
	}

	@Bean
	public LinkDoctorReviewDao linkDao() {
		return new IMLinkDoctorReviewDAO();
		// new JDBCDoctorDAO("localhost", "5432", "test", "test", "test");
	}

}
