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

import ro.sci.group5.dao.LinkDoctorReviewDao;
import ro.sci.group5.domain.JDBCConnection;
import ro.sci.group5.domain.LinkDoctorReview;

/**
 * Pure JDBC implementation for {@link ro.sci.group5.dao.ReviewDao}.
 * 
 *
 */
public class JDBCLinkDAO extends JDBCAbstractDAO implements LinkDoctorReviewDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(JDBCDoctorDAO.class);

	public JDBCLinkDAO(JDBCConnection jdbcConnection) {
		super(jdbcConnection);
	}

	/**
	 * This method returns all the LinkDoctorReview from link_doctor_review
	 * table in DB.
	 */
	@Override
	public Collection<LinkDoctorReview> getAll() {
		Connection connection = newConnection();

		Collection<LinkDoctorReview> result = new LinkedList<>();

		try (ResultSet rs = connection.createStatement().executeQuery("select * from link_doctor_review")) {

			while (rs.next()) {
				result.add(extractReview(rs));
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
	 * @return LinkDoctorReview
	 */
	@Override
	public LinkDoctorReview findById(Long id) {
		Connection connection = newConnection();

		List<LinkDoctorReview> result = new LinkedList<>();

		try (ResultSet rs = connection.createStatement()
				.executeQuery("select * from link_doctor_review where id = " + id)) {

			while (rs.next()) {
				result.add(extractReview(rs));
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

		if (result.size() > 1) {
			throw new IllegalStateException("Multiple doctors for id: " + id);
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
	public LinkDoctorReview update(LinkDoctorReview model) {
		Connection connection = newConnection();
		try {
			PreparedStatement ps = null;
			if (model.getId() > 0) {
				ps = connection.prepareStatement(
						"update link_doctor_review set id_doctor=?, id_review=?" + "where id = ? returning id");

				System.out.println("!?!?");

			} else {

				ps = connection.prepareStatement(
						"insert into link_doctor_review (id_doctor,id_review) " + "values (?, ?) returning id");
				
			}
			ps.setLong(1, model.getDoctorID());
			ps.setLong(2, model.getReviewID());

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

			throw new RuntimeException("Error updating LinkDoctorReview in JDBCLinkDAO.", ex);
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

	private LinkDoctorReview extractReview(ResultSet rs) throws SQLException {
		LinkDoctorReview link = new LinkDoctorReview();
		link.setId(rs.getLong("id_doctor"));
		link.setId(rs.getLong("id_review"));

		return link;
	}

	/**
	 * Not used.
	 */
	@Override
	public boolean delete(LinkDoctorReview model) {
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
