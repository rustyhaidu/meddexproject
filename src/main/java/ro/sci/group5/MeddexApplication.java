
package ro.sci.group5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ro.sci.group5.dao.DoctorDao;
import ro.sci.group5.dao.HospitalDao;
import ro.sci.group5.dao.LinkDoctorHospitalDao;
import ro.sci.group5.dao.LinkDoctorReviewDao;
import ro.sci.group5.dao.ReviewDAO;
import ro.sci.group5.db.JDBCDoctorDAO;
import ro.sci.group5.db.JDBCHospitalDAO;
import ro.sci.group5.db.JDBCLinkDAO;
import ro.sci.group5.db.JDBCLinkDHDAO;
import ro.sci.group5.db.JDBCReviewDAO;

/**
 * This application is designed in order to help users give and get information
 * about doctors in Cluj. It offers you posibility to search for one doctor,
 * give and see reviews, to see doctors working in a given hospital and,
 * simulating an admin role, to add doctors to a certain hospital.
 */

@Configuration
@EnableAutoConfiguration
@ComponentScan

public class MeddexApplication {
	public static void main(String[] args) {
		SpringApplication.run(MeddexApplication.class, args);
	}

	/**
	 * Shows connection details from either local machine or heroku database
	 * 
	 * @return DoctorDao
	 */
	@Bean
	public DoctorDao doctorDao() {
		return  /*new JDBCDoctorDAO("localhost", "5432", "test", "test", "test"); */
		new JDBCDoctorDAO("ec2-54-217-208-102.eu-west-1.compute.amazonaws.com", "5432", "d74krrcumptmn8",
				"krqitchudqnhbc", "2AC2l0pO26pUmP-6kQprCguYQr");

	}

	/**
	 * Shows connection details from either local machine or heroku database
	 * 
	 * @return ReviewDAO
	 */
	@Bean
	public ReviewDAO reviewDao() {
		return  /*new JDBCReviewDAO("localhost", "5432", "test", "test", "test");*/
		 new JDBCReviewDAO("ec2-54-217-208-102.eu-west-1.compute.amazonaws.com", "5432", "d74krrcumptmn8",
				"krqitchudqnhbc", "2AC2l0pO26pUmP-6kQprCguYQr");

	}

	/**
	 * Shows connection details from either local machine or heroku database
	 * 
	 * @return LinkDoctorReviewDao
	 */
	@Bean
	public LinkDoctorReviewDao linkDao() {
		return   /*new JDBCLinkDAO("localhost", "5432", "test", "test", "test"); */
		new JDBCLinkDAO("ec2-54-217-208-102.eu-west-1.compute.amazonaws.com", "5432", "d74krrcumptmn8",
				"krqitchudqnhbc", "2AC2l0pO26pUmP-6kQprCguYQr");
	}
	/**
	 * Shows connection details from either local machine or heroku database
	 * 
	 * @return LinkDoctorReviewDao
	 */
	@Bean
	public HospitalDao hospitalDao() {
		return /* new JDBCHospitalDAO("localhost", "5432", "test", "test", "test");*/
		new JDBCHospitalDAO("ec2-54-217-208-102.eu-west-1.compute.amazonaws.com", "5432", "d74krrcumptmn8",
				"krqitchudqnhbc", "2AC2l0pO26pUmP-6kQprCguYQr");

	}

	@Bean
	public LinkDoctorHospitalDao linkDHDao() {
		return  /*new JDBCLinkDHDAO("localhost", "5432", "test", "test", "test");*/
		new JDBCLinkDHDAO("ec2-54-217-208-102.eu-west-1.compute.amazonaws.com", "5432", "d74krrcumptmn8",
				"krqitchudqnhbc", "2AC2l0pO26pUmP-6kQprCguYQr");

	}
}
