
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
import ro.sci.group5.dao.NeighbourhoodDao;
import ro.sci.group5.dao.ReviewDao;
import ro.sci.group5.db.JDBCDoctorDAO;
import ro.sci.group5.db.JDBCHospitalDAO;
import ro.sci.group5.db.JDBCLinkDAO;
import ro.sci.group5.db.JDBCLinkDHDAO;
import ro.sci.group5.db.JDBCNeighbourhoodDAO;
import ro.sci.group5.db.JDBCReviewDAO;
import ro.sci.group5.domain.JDBCConnection;

/**
 * This application is designed in order to help users give and get information
 * about doctors in Cluj. It offers you posibility to search for one doctor,
 * give and see reviews, to see doctors working in a given hospital and,
 * simulating an admin role, to add doctors to a certain hospital.
 * 
 * Host
 * ec2-54-247-74-242.eu-west-1.compute.amazonaws.com
 * Database
 * d99eekict3mn03
 * User
 * ejfedfpmjbfdib
 * Port
 * 5432
 * Password
 * cf34750375320bc6d5ef1d9f26e39d9c4d56a858c6e5e9c8a6f5cb3048692c57
 * URI
 * postgres://ejfedfpmjbfdib:cf34750375320bc6d5ef1d9f26e39d9c4d56a858c6e5e9c8a6f5cb3048692c57@ec2-54-247-74-242.eu-west-1.compute.amazonaws.com:5432/d99eekict3mn03
 * Heroku CLI
 * heroku pg:psql postgresql-aerodynamic-13557 --app meddexproject
 */

@Configuration
@EnableAutoConfiguration
@ComponentScan

public class Main {
	String host = "ec2-54-247-74-242.eu-west-1.compute.amazonaws.com";
	String port = "5432";
	String dbName = "d99eekict3mn03";
	String userName = "ejfedfpmjbfdib";
	String password = "cf34750375320bc6d5ef1d9f26e39d9c4d56a858c6e5e9c8a6f5cb3048692c57";

	// new JDBCReviewDAO("localhost", port, "test", "test", "test");
	/*String host = "localhost";
	String port = "5432";
	String dbName = "test";
	String userName = "test";
	String password = "test";*/


	JDBCConnection jdbcConnection = new JDBCConnection(host,port,dbName,userName,password);

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	/**
	 * Shows connection details from either local machine or heroku database
	 * 
	 * @return DoctorDao
	 */
	@Bean
	public DoctorDao doctorDao() {
		return // new JDBCDoctorDAO("localhost", "5432", "test", "test", "test");
		new JDBCDoctorDAO(jdbcConnection);

	}

	/**
	 * Shows connection details from either local machine or heroku database
	 * 
	 * @return ReviewDao
	 */
	@Bean
	public ReviewDao reviewDao() {
		return // new JDBCReviewDAO("localhost", port, "test", "test", "test");
		 new JDBCReviewDAO(jdbcConnection);

	}

	/**
	 * Shows connection details from either local machine or heroku database
	 * 
	 * @return LinkDoctorReviewDao
	 */
	@Bean
	public LinkDoctorReviewDao linkDao() {
		return  // new JDBCLinkDAO("localhost", port, "test", "test", "test");
		new JDBCLinkDAO(jdbcConnection);
	}
	/**
	 * Shows connection details from either local machine or heroku database
	 * 
	 * @return LinkDoctorReviewDao
	 */
	@Bean
	public HospitalDao hospitalDao() {
		return //  new JDBCHospitalDAO("localhost", port, "test", "test", "test");
		new JDBCHospitalDAO(jdbcConnection);

	}

	@Bean
	public LinkDoctorHospitalDao linkDHDao() {
		return // new JDBCLinkDHDAO("localhost", port, "test", "test", "test");
		new JDBCLinkDHDAO(jdbcConnection);

	}
	@Bean
	public NeighbourhoodDao neighbourhoodDao() {
		return   // new JDBCNeighbourhoodDAO("localhost", port, "test", "test", "test");
		new JDBCNeighbourhoodDAO(jdbcConnection);
	}
}
