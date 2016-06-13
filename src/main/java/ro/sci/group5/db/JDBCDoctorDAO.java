package ro.sci.group5.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.sci.group5.dao.DoctorDao;
import ro.sci.group5.domain.Doctor;


/**
 * Pure JDBC implementation for {@link DoctorDao}.
 *
 */
public class JDBCDoctorDAO implements DoctorDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(JDBCDoctorDAO.class);

	private String host;
	private String port;
	private String dbName;
	private String userName;
	private String pass;

	/**
	 * Constructor
	 */
	public JDBCDoctorDAO() {
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
	public JDBCDoctorDAO(String host, String port, String dbName, String userName, String pass) {
		this.host = host;
		this.userName = userName;
		this.pass = pass;
		this.port = port;
		this.dbName = dbName;
	}

	/**
	 * This method returns all the doctors from doctors table in DB.
	 */
	@Override
	public Collection<Doctor> getAll() {
		Connection connection = newConnection();

		Collection<Doctor> result = new LinkedList<>();

		try (ResultSet rs = connection.createStatement().executeQuery("select * from doctors")) {

			while (rs.next()) {
				result.add(extractDoctor(rs));
			}
			connection.commit();
		} catch (SQLException ex) {

			throw new RuntimeException("Error getting doctors.", ex);
		} finally {
			try {
				connection.close();
			} catch (Exception ex) {

			}
		}

		/*for (Doctor neighbourhood:result){
			System.out.println("JDBC neigh "+neighbourhood.getFirstName()+' '+neighbourhood.getId());
		}*/
		return result;
	}

	/**
	 * This method returns the doctor from doctors table in DB, based on id.
	 * 
	 * @param id
	 * @return Doctor
	 */
	@Override
	public Doctor findById(Long id) {
		Connection connection = newConnection();

		List<Doctor> result = new LinkedList<>();

		try (ResultSet rs = connection.createStatement().executeQuery("select * from doctors where id = " + id)) {

			while (rs.next()) {
				result.add(extractDoctor(rs));
			}
			connection.commit();
		} catch (SQLException ex) {

			throw new RuntimeException("Error getting doctors.", ex);
		} finally {
			try {
				connection.close();
			} catch (Exception ex) {

			}
		}

		if (result.size() > 1) {
			throw new IllegalStateException("Multiple doctors for id: " + id);
		}
		return result.isEmpty() ? null : result.get(0);
	}

	/**
	 * This method inserts/updates doctors in doctors table.
	 * 
	 * @param model
	 * @return Doctor from DB
	 */
	@Override
	public Doctor update(Doctor model) {
		Connection connection = newConnection();
		try {
			PreparedStatement ps = null;
			if (model.getId() > 0) {
				ps = connection.prepareStatement(
						"update doctors set first_name=?, last_name=?,  hospital1=?, hospital2=?, title_doctor=?,  phone_number=?,"
								+ "doctor_email=?,show_phone_number=?,show_email=?,specialization1=?,specialization2=?"
								+ "where id = ? returning id");

			} else {

				ps = connection.prepareStatement(
						"insert into doctors (first_name, last_name, hospital1, hospital2, title_doctor,phone_number, doctor_email, show_phone_number, show_email, specialization1,specialization2) "
								+ "values (?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?) returning id");

			}
			ps.setString(1, model.getFirstName());
			ps.setString(2, model.getLastName());
			ps.setString(3, model.getHospital1());
			ps.setString(4, model.getHospital2());
			ps.setString(5, model.getTitleDoctor());
			ps.setString(6, model.getPhoneNumber());
			ps.setString(7, model.getDoctorEmail());
			ps.setBoolean(8, model.isShowPhoneNumber());
			ps.setBoolean(9, model.isShowEmail());
			ps.setString(10, model.getSpecialization1());
			ps.setString(11, model.getSpecialization2());

			if (model.getId() > 0) {
				ps.setLong(12, model.getId());
			}

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				model.setId(rs.getLong(1));
			}
			rs.close();
			connection.commit();

		} catch (SQLException ex) {

			throw new RuntimeException("Error getting doctors.", ex);
		} finally {
			try {
				connection.close();
			} catch (Exception ex) {

			}
		}

		return model;
	}

	/**
	 * This method deletes doctor from doctors table.
	 * 
	 * @param model
	 * @return boolean
	 */
	@Override
	public boolean delete(Doctor model) {
		boolean result = false;
		Connection connection = newConnection();
		try {
			Statement statement = connection.createStatement();
			result = statement.execute("delete from doctors where id = " + model.getId());
			connection.commit();
		} catch (SQLException ex) {

			throw new RuntimeException("Error updating doctors.", ex);
		} finally {
			try {
				connection.close();
			} catch (Exception ex) {

			}
		}
		return result;

	}

	/**
	 * This method returns doctors from DB, by FirstName or LastName.
	 * 
	 * @param query
	 * @return collection of Doctors
	 */
	@Override
	public Collection<Doctor> searchByName(String query) {
		if (query == null) {
			query = "";
		} else {
			query = query.trim();
		}

		Connection connection = newConnection();

		Collection<Doctor> result = new LinkedList<>();

		try (ResultSet rs = connection.createStatement()
				.executeQuery("select * from doctors where lower(first_name || ' ' || last_name) like '%"
						+ query.toLowerCase() + "%'")) {

			while (rs.next()) {
				result.add(extractDoctor(rs));
			}
			connection.commit();
		} catch (SQLException ex) {

			throw new RuntimeException("Error getting doctors.", ex);
		}

		return result;
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
	
	@Override
	public Collection<Doctor> findByHospitalID(Long hospitalID) {
		Connection connection = newConnection();

		Collection<Doctor> result = new LinkedList<>();

		try (ResultSet rs = connection.createStatement().executeQuery(
				"select d.* from doctors d join link_doctor_hospital lh on d.id = lh.id_doctor WHERE lh.id_hospital =  "
						+ hospitalID)) {

			while (rs.next()) {
				result.add(extractDoctor(rs));
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
		return result;
	}
}