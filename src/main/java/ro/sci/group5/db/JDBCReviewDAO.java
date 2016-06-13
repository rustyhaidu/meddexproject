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
import ro.sci.group5.dao.ReviewDao;
import ro.sci.group5.domain.Review;

/**
 * Pure JDBC implementation for {@link ReviewDao}.
 */

public class JDBCReviewDAO implements ReviewDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(JDBCDoctorDAO.class);

	private String host;
	private String port;
	private String dbName;
	private String userName;
	private String pass;

	/**
	 * Constructor
	 */
	public JDBCReviewDAO() {
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
	public JDBCReviewDAO(String host, String port, String dbName, String userName, String pass) {
		this.host = host;
		this.userName = userName;
		this.pass = pass;
		this.port = port;
		this.dbName = dbName;
	}

	/**
	 * This method returns all the reviews from reviews table in DB.
	 */
	@Override
	public Collection<Review> getAll() {
		Connection connection = newConnection();

		Collection<Review> result = new LinkedList<>();

		try (ResultSet rs = connection.createStatement().executeQuery("select * from reviews")) {

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
	 * This method returns the review from reviews table in DB, based on id.
	 * 
	 * @param id
	 * @return Review
	 */
	@Override
	public Review findById(Long doctorID) {
		Connection connection = newConnection();
		System.out.println(doctorID);
		List<Review> result = new LinkedList<>();

		try (ResultSet rs = connection.createStatement().executeQuery(
				"select r.* from reviews r join link_doctor_review lk on r.id = lk.id_review WHERE lk.id_doctor = "
						+ doctorID)) {

			while (rs.next()) {
				result.add(extractReview(rs));
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

		/*if (result.size() > 1) {
			throw new IllegalStateException("Multiple doctors for id: " + doctorID);
		}*/
		return result.isEmpty() ? null : result.get(0);
	}

	/**
	 * This method inserts/updates reviews in reviews table.
	 * 
	 * @param model
	 * @return Reviews from DB
	 */
	@Override
	public Review update(Review model) {
		Connection connection = newConnection();
		try {
			PreparedStatement ps = null;

			if (model.getId() > 0) {
				ps = connection.prepareStatement(
						"update reviews set reviewer_first_name=?, reviewer_last_name=?, reviewer_email=?,review_content=?,review_grade=?"
								+ "where id = ? returning id");

			} else {

				ps = connection.prepareStatement(
						"insert into reviews (reviewer_first_name, reviewer_last_name, reviewer_email,review_content,review_grade) "
								+ "values (?, ?, ?, ?, ?) returning id");
			}
			ps.setString(1, model.getFirstNameR());
			ps.setString(2, model.getLastNameR());
			ps.setString(3, model.getrEmail());
			ps.setString(4, model.getReviewContent());
			ps.setFloat(5, model.getGrade());

			System.out.println(model.getFirstNameR());

			if (model.getId() > 0) {
				ps.setLong(6, model.getId());
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

	private Review extractReview(ResultSet rs) throws SQLException {
		Review review = new Review();
		review.setId(rs.getLong("id"));
		review.setFirstNameR(rs.getString("reviewer_first_name"));
		review.setLastNameR(rs.getString("reviewer_last_name"));
		review.setrEmail(rs.getString("reviewer_email"));
		review.setReviewContent(rs.getString("review_content"));
		review.setGrade(rs.getFloat("review_grade"));
		return review;
	}

	/**
	 * Not used.
	 */
	@Override
	public boolean delete(Review model) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Not used.
	 */
	@Override
	public Collection<Review> searchByName(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Review> findByDoctorID(Long doctorID) {
		Connection connection = newConnection();
		System.out.println(doctorID);
		List<Review> result = new LinkedList<>();

		try (ResultSet rs = connection.createStatement().executeQuery(
				"select r.* from reviews r join link_doctor_review lk on r.id = lk.id_review WHERE lk.id_doctor = "
				
						+ doctorID)) {

			while (rs.next()) {
				result.add(extractReview(rs));
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

		/*if (result.size() > 1) {
			throw new IllegalStateException("Multiple doctors for id: " + doctorID);
		}*/
		return result;
	}

}
