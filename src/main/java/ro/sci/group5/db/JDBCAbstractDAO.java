package ro.sci.group5.db;

import ro.sci.group5.domain.JDBCConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCAbstractDAO {
    JDBCConnection jdbcConnection;

    public JDBCAbstractDAO(JDBCConnection jdbcConnection) {
        this.jdbcConnection = jdbcConnection;
    }

    /**
     * Connection to postgresql database
     *
     * @return
     */
    protected Connection newConnection() {
        try {
            Class.forName("org.postgresql.Driver").newInstance();

            String url = new StringBuilder().append("jdbc:").append("postgresql").append("://").append(jdbcConnection.getHost()).append(":")
                    .append(jdbcConnection.getPort()).append("/").append(jdbcConnection.getDbName()).append("?user=").append(jdbcConnection.getUserName()).append("&password=")
                    .append(jdbcConnection.getPass()).toString();
            Connection result = DriverManager.getConnection(url);
            result.setAutoCommit(false);

            return result;
        } catch (Exception ex) {
            throw new RuntimeException("Error getting DB connection", ex);
        }

    }
}
