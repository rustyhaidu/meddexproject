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
import ro.sci.group5.domain.LinkDoctorHospital;
import ro.sci.group5.domain.LinkDoctorReview;

/**
 * Pure JDBC implementation for {@link ReviewDao}.
 * 
 *
 */
public class JDBCLinkDHDAO implements LinkDoctorHospitalDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(JDBCDoctorDAO.class);

	private String host;
	private String port;
	private String dbName;
	private String userName;
	private String pass;

	/**
	 * Constructor
	 */
	public JDBCLinkDHDAO() {
		super();
	}

	/**
	 * Construct with connection input
	 * 
	 * @param host
	 * @param port
	 * @param dbName
	 * @param userName
	 * @param pass
	 */
	public JDBCLinkDHDAO(String host, String port, String dbName, String userName, String pass) {
		this.host = host;
		this.userName = userName;
		this.pass = pass;
		this.port = port;
		this.dbName = dbName;
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
	 * @param id
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

	protected Connection newConnection() {
		try {
			Class.forName("org.postgresql.Driver").newInstance();

			String url = new StringBuilder().append("jdbc:").append("postgresql").append("://").append(host).append(":")
					.append(port).append("/").append(dbName).append("?user=").append(userName).append("&password=")
					.append(pass).toString();
			Connection result = DriverManager.getConnection(url);
			result.setAutoCommit(false);

			return result;
		} catch (Exception ex) {
			throw new RuntimeException("Error getting DB connection", ex);
		}

	}

	private Doctor extractDoctor(ResultSet rs) throws SQLException {
		Doctor doctor = new Doctor();
		doctor.setId(rs.getLong("id"));
		doctor.setFirstName(rs.getString("first_name"));
		doctor.setLastName(rs.getString("last_name"));
		doctor.setHospital1(rs.getString("hospital1"));
		doctor.setHospital2(rs.getString("hospital2"));
		doctor.setTitleDoctor(rs.getString("title_doctor"));
		doctor.setPhoneNumber(rs.getString("phone_number"));
		doctor.setDoctorEmail(rs.getString("doctor_email"));
		doctor.setShowPhoneNumber(rs.getBoolean("show_phone_number"));
		doctor.setShowEmail(rs.getBoolean("show_email"));
		doctor.setSpecialization1(rs.getString("specialization1"));
		doctor.setSpecialization1(rs.getString("specialization2"));

		return doctor;
	}

	/**
	 * Not used.
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
