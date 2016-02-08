package ro.sci.group5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ro.sci.group5.dao.DoctorDao;
import ro.sci.group5.db.JDBCDoctorDAO;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class MeddexApplication {
	public static void main(String[] args) {
		SpringApplication.run(MeddexApplication.class, args);		
	}
	
	@Bean
	public DoctorDao doctorDao() {
		return new JDBCDoctorDAO("localhost", "5432", "test", "test", "test");
				/*new JDBCDoctorDAO("ec2-54-235-152-114.compute-1.amazonaws.com",
				"5432", "debvjrj4023nfd",
				"ypsrfyzvblpwsg", 
				"ISY2KvbIISXDHjwdVd-7egvk2o");*/					
				
	}

}
