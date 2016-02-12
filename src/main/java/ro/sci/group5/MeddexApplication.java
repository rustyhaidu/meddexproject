package ro.sci.group5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ro.sci.group5.dao.DoctorDao;
import ro.sci.group5.dao.LinkDoctorReviewDao;
import ro.sci.group5.dao.ReviewDAO;
import ro.sci.group5.db.JDBCDoctorDAO;
import ro.sci.group5.db.JDBCLinkDAO;
import ro.sci.group5.db.JDBCReviewDAO;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class MeddexApplication {
	public static void main(String[] args) {
		SpringApplication.run(MeddexApplication.class, args);		
	}
	
	@Bean
	public DoctorDao doctorDao() {
		return //new JDBCDoctorDAO("localhost", "5432", "test", "test", "test");
				new JDBCDoctorDAO("ec2-54-217-208-102.eu-west-1.compute.amazonaws.com",
				"5432", "d74krrcumptmn8",
				"krqitchudqnhbc", 
				"2AC2l0pO26pUmP-6kQprCguYQr");					
				
	}
	@Bean
	public ReviewDAO reviewDao() {
		return //new JDBCReviewDAO("localhost", "5432", "test", "test", "test");
				new JDBCReviewDAO("ec2-54-217-208-102.eu-west-1.compute.amazonaws.com",
				"5432", "d74krrcumptmn8",
				"krqitchudqnhbc", 
				"2AC2l0pO26pUmP-6kQprCguYQr");				
				
	}
	@Bean
	public LinkDoctorReviewDao linkDao() {
		return //new JDBCLinkDAO("localhost", "5432", "test", "test", "test");
				new JDBCLinkDAO("ec2-54-217-208-102.eu-west-1.compute.amazonaws.com",
				"5432", "d74krrcumptmn8",
				"krqitchudqnhbc", 
				"2AC2l0pO26pUmP-6kQprCguYQr");				
				
	}

}
