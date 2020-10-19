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
import ro.sci.group5.dao.HospitalDao;
import ro.sci.group5.domain.Doctor;
import ro.sci.group5.domain.Hospital;
import ro.sci.group5.domain.JDBCConnection;

/**
 * Pure JDBC implementation for {@link DoctorDao}.
 *
 */

public class JDBCHospitalDAO extends JDBCAbstractDAO implements HospitalDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(JDBCDoctorDAO.class);

	public JDBCHospitalDAO(JDBCConnection jdbcConnection) {
		super(jdbcConnection);
	}

	/**
	 * This method returns all the hospitals from doctors table in DB.
	 */
	@Override
	public Collection<Hospital> getAll() {
		Connection connection = newConnection();

		Collection<Hospital> result = new LinkedList<>();

		try (ResultSet rs = connection.createStatement().executeQuery("select * from hospitals")) {

			while (rs.next()) {
				result.add(extractHospital(rs));
			}
			connection.commit();
		} catch (SQLException ex) {

			throw new RuntimeException("Error getting hospitals in JDBCHospitalDAO.", ex);
		} finally {
			try {
				connection.close();
			} catch (Exception ex) {

			}
		}

		return result;
	}
	
	@Override
	public Hospital findById(Long id) {
		Connection connection = newConnection();

		List<Hospital> result = new LinkedList<>();

		try (ResultSet rs = connection.createStatement().executeQuery("select * from hospitals where id = " + id)) {

			while (rs.next()) {
				result.add(extractHospital(rs));
			}
			connection.commit();
		} catch (SQLException ex) {

			throw new RuntimeException("Error getting hospitals.", ex);
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
	
	@Override
	public Hospital update(Hospital model) {
		Connection connection = newConnection();
		try {
			PreparedStatement ps = null;
			if (model.getId() > 0) {
				ps = connection.prepareStatement(
						"update hospitals set hospital_name=?, street_name=?,  street_number=?, neighbourhood=?, hospital_email=?,  phone_number=?,"								
								+ "where id = ? returning id");

			} else {

				ps = connection.prepareStatement(
						"INSERT INTO public.hospitals(hospital_name, street_name, street_number, neighbourhood,hospital_email, phone_number) "
   + "VALUES (?, ?, ?, ?, ?,?) returning id");

			}
			ps.setString(1, model.getHospitalName());
			ps.setString(2, model.getStreetName());
			ps.setInt(3, model.getStreetNumber());
			ps.setString(4, model.getNeighbourhood());
			ps.setString(5, model.getHospitalEmail());
			ps.setString(6, model.getPhoneNumber());
			

			if (model.getId() > 0) {
				ps.setLong(7, model.getId());
			}

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				model.setId(rs.getLong(1));
			}
			rs.close();			
			connection.commit();

		} catch (SQLException ex) {
			System.out.println("eroare"+ex);
			throw new RuntimeException("Error getting hospitals.", ex);
		} finally {
			try {
				connection.close();
			} catch (Exception ex) {

			}
		}

		return model;
	}
	
	@Override
	public boolean delete(Hospital model) {
		boolean result = false;
		Connection connection = newConnection();
		try {
			Statement statement = connection.createStatement();
			result = statement.execute("delete from hospitals where id = " + model.getId());
			connection.commit();
		} catch (SQLException ex) {

			throw new RuntimeException("Error updating hospitals.", ex);
		} finally {
			try {
				connection.close();
			} catch (Exception ex) {

			}
		}
		return result;

	}
	
	@Override
	public Collection<Hospital> searchByName(String query) {
		if (query == null) {
			query = "";
		} else {
			query = query.trim();
		}

		Connection connection = newConnection();

		Collection<Hospital> result = new LinkedList<>();

		try (ResultSet rs = connection.createStatement()
				.executeQuery("select * from hospitals where lower(first_name || ' ' || last_name) like '%"
						+ query.toLowerCase() + "%'")) {

			while (rs.next()) {
				result.add(extractHospital(rs));
			}
			connection.commit();
		} catch (SQLException ex) {

			throw new RuntimeException("Error getting hospitals.", ex);
		}

		return result;
	}

	private Hospital extractHospital(ResultSet rs) throws SQLException {
		Hospital hospital = new Hospital();
		
		hospital.setId(rs.getLong("id"));
		hospital.setHospitalName(rs.getString("hospital_name"));
		hospital.setStreetName(rs.getString("street_name"));
		hospital.setStreetNumber(rs.getInt("street_number"));
		hospital.setNeighbourhood(rs.getString("neighbourhood"));
		hospital.setHospitalEmail(rs.getString("hospital_email"));
		hospital.setPhoneNumber(rs.getString("phone_number"));
		return hospital;
	}

}
