package ro.sci.group5.domain;

public class JDBCConnection {
    private String host;
    private String port;
    private String dbName;
    private String userName;
    private String pass;

    public JDBCConnection(String host, String port, String dbName, String userName, String pass) {
        this.host = host;
        this.port = port;
        this.dbName = dbName;
        this.userName = userName;
        this.pass = pass;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getDbName() {
        return dbName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPass() {
        return pass;
    }
}
