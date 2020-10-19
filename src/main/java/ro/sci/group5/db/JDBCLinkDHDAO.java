package ro.sci.group5.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ro.sci.group5.dao.LinkDoctorHospitalDao;
import ro.sci.group5.dao.LinkDoctorReviewDao;
import ro.sci.group5.domain.Doctor;
import ro.sci.group5.domain.JDBCConnection;
import ro.sci.group5.domain.LinkDoctorHospital;
import ro.sci.group5.domain.LinkDoctorReview;

/**
 * Pure JDBC implementation for {@link ro.sci.group5.dao.ReviewDao}.
 * 
 *
 */
public class JDBCLinkDHDAO extends JDBCAbstractDAO implements LinkDoctorHospitalDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(JDBCDoctorDAO.class);

	public JDBCLinkDHDAO(JDBCConnection jdbcConnection) {
		super(jdbcConnection);
	}


	/**
	 * This method returns all the LinkDoctorReview from link_doctor_review
	 * table in DB.
	 */
	@Override
	public Collection<LinkDoctorHospital> getAll() {
		Connection connection = newConnection();

		Collection<LinkDoctorHospital> result = new LinkedList<>();

		try (ResultSet rs = connection.createStatement().executeQuery("select * from link_doctor_hospital")) {

			while (rs.next()) {
				//result.add(extractDoctor(rs));
			}
			connection.commit();
		} catch (SQLException ex) {

			throw new RuntimeException("Error getting reviews.", ex);
		} finally {
			try {
				connection.close();
			} catch (Exception ex) {

			}
		}

		return result;
	}

	/**
	 * This method returns the links from link_doctor_review table in DB, based
	 * on id.
	 * 
	 * @param doctorID
	 * @return 
	 */
	@Override
	public LinkDoctorHospital findById(Long doctorID) {
		Connection connection = newConnection();

		List<LinkDoctorHospital> result = new LinkedList<>();

		try (ResultSet rs = connection.createStatement()
				.executeQuery("select r.* from doctors r join link_doctor_hospital lh on r.id = lh.id_doctor WHERE lh.id_hospital =  "
						+ doctorID)) {

			while (rs.next()) {
				//result.add(extractDoctor(rs));
			}
			connection.commit();
		} catch (SQLException ex) {

			throw new RuntimeException("Error getting doctors for hospital.", ex);
		} finally {
			try {
				connection.close();
			} catch (Exception ex) {

			}
		}

		if (result.size() > 1) {
			throw new IllegalStateException("Multiple doctors hospital for id: " + doctorID);
		}
		return result.isEmpty() ? null : result.get(0);
	}
	
	
	/**
	 * This method inserts/updates links in link_doctor_review table.
	 * 
	 * @param model
	 * @return LinkDoctorReview from DB
	 */
	@Override
	public LinkDoctorHospital update(LinkDoctorHospital model) {
		Connection connection = newConnection();
		try {
			PreparedStatement ps = null;
			if (model.getId() > 0) {
				ps = connection.prepareStatement(
						"update link_doctor_review set id_doctor=?, id_review=?" + "where id = ? returning id");

				
			} else {

				ps = connection.prepareStatement(
						"insert into link_doctor_hospital (id_doctor,id_hospital) " + "values (?, ?) returning id");
				
			}
			ps.setLong(1, model.getDoctorID());
			ps.setLong(2, model.getHospitalID());

			if (model.getId() > 0) {
				ps.setLong(3, model.getId());
			}

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				model.setId(rs.getLong(1));
			}
			rs.close();

			connection.commit();

		} catch (SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException("Error getting links for INSERT doctors hospitals.", ex);
			
		} finally {
			try {
				connection.close();
			} catch (Exception ex) {

			}
		}

		return model;
	}

	/**
	 * Connection to postgresql database
	 * 
	 * @return
	 */

	@Override
	public boolean delete(LinkDoctorHospital model) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Not used.
	 */
	@Override
	public Collection<LinkDoctorReview> searchByName(String query) {
		// TODO Auto-generated method stub
		return null;
	}

}
