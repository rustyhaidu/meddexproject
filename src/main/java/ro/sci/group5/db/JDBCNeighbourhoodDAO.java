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
import ro.sci.group5.dao.NeighbourhoodDao;
import ro.sci.group5.domain.Neighbourhood;


/**
 * Pure JDBC implementation for {@link DoctorDao}.
 *
 */

public class JDBCNeighbourhoodDAO implements NeighbourhoodDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(JDBCDoctorDAO.class);

	private String host;
	private String port;
	private String dbName;
	private String userName;
	private String pass;


	public JDBCNeighbourhoodDAO() {
		super();
	}

	
	public JDBCNeighbourhoodDAO(String host, String port, String dbName, String userName, String pass) {
		this.host = host;
		this.userName = userName;
		this.pass = pass;
		this.port = port;
		this.dbName = dbName;
	}

	/**
	 * This method returns all the Neighbourhoods from doctors table in DB.
	 */
	@Override
	public Collection<Neighbourhood> getAll() {
		Connection connection = newConnection();

		Collection<Neighbourhood> result = new LinkedList<>();

		try (ResultSet rs = connection.createStatement().executeQuery("select * from neighbourhoods")) {

			while (rs.next()) {
				result.add(extractNeighbourhood(rs));
			}
			connection.commit();
		} catch (SQLException ex) {

			throw new RuntimeException("Error getting neighbourhoods in NeighbourhoodDAO.", ex);
		} finally {
			try {
				connection.close();
			} catch (Exception ex) {

			}
		}
		
		for (Neighbourhood neighbourhood:result){
			System.out.println("JDBC neigh "+neighbourhood.getNeighbourhoodName()+' '+neighbourhood.getId());
		}

		return result;
	}
	
	@Override
	public Neighbourhood findById(Long id) {
		Connection connection = newConnection();

		List<Neighbourhood> result = new LinkedList<>();

		try (ResultSet rs = connection.createStatement().executeQuery("select * from neighbourhoods where id = " + id)) {

			while (rs.next()) {
				result.add(extractNeighbourhood(rs));
			}
			connection.commit();
		} catch (SQLException ex) {

			throw new RuntimeException("Error getting neighbourhood by id in JDBCNeighbourhoodDAO.", ex);
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
	public Neighbourhood update(Neighbourhood model) {
		Connection connection = newConnection();
		try {
			PreparedStatement ps = null;
			if (model.getId() > 0) {
				ps = connection.prepareStatement(
						"update Neighbourhoods set Neighbourhood_name=?, street_name=?,  street_number=?, neighbourhood=?, Neighbourhood_email=?,  phone_number=?,"								
								+ "where id = ? returning id");

			} else {

				ps = connection.prepareStatement(
						"INSERT INTO public.Neighbourhoods(Neighbourhood_name, street_name, street_number, neighbourhood,Neighbourhood_email, phone_number) "
   + "VALUES (?, ?, ?, ?, ?,?) returning id");

			}
			ps.setString(1, model.getNeighbourhoodName());			
			

			if (model.getId() > 0) {
				ps.setLong(2, model.getId());
			}

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				model.setId(rs.getLong(1));
			}
			rs.close();			
			connection.commit();

		} catch (SQLException ex) {
			System.out.println("eroare"+ex);
			throw new RuntimeException("Error getting Neighbourhoods.", ex);
		} finally {
			try {
				connection.close();
			} catch (Exception ex) {

			}
		}

		return model;
	}
	
	@Override
	public boolean delete(Neighbourhood model) {
		boolean result = false;
		Connection connection = newConnection();
		try {
			Statement statement = connection.createStatement();
			result = statement.execute("delete from Neighbourhoods where id = " + model.getId());
			connection.commit();
		} catch (SQLException ex) {

			throw new RuntimeException("Error updating Neighbourhoods.", ex);
		} finally {
			try {
				connection.close();
			} catch (Exception ex) {

			}
		}
		return result;

	}
	
	@Override
	public Collection<Neighbourhood> searchByName(String query) {
		if (query == null) {
			query = "";
		} else {
			query = query.trim();
		}

		Connection connection = newConnection();

		Collection<Neighbourhood> result = new LinkedList<>();

		try (ResultSet rs = connection.createStatement()
				.executeQuery("select * from Neighbourhoods where lower(first_name || ' ' || last_name) like '%"
						+ query.toLowerCase() + "%'")) {

			while (rs.next()) {
				result.add(extractNeighbourhood(rs));
			}
			connection.commit();
		} catch (SQLException ex) {

			throw new RuntimeException("Error getting Neighbourhoods.", ex);
		}

		return result;
	}
	
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

	private Neighbourhood extractNeighbourhood(ResultSet rs) throws SQLException {
		Neighbourhood Neighbourhood = new Neighbourhood();
		
		Neighbourhood.setId(rs.getLong("id"));
		Neighbourhood.setNeighbourhoodName(rs.getString("Neighbourhood_name"));
		
		return Neighbourhood;
	}

}
