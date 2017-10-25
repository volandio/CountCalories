package dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class SQLPoolConnection implements IConnectionManager {

    private static final SQLPoolConnection INSTANCE = new SQLPoolConnection();
    private Connection connection;

    private InitialContext ic;
    private DataSource ds;

    public Connection getConnection() throws SQLException {
        try {
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:/comp/env/jdbc/counting_calories");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return ds.getConnection();
    }

    public static synchronized SQLPoolConnection getInstance() {
        return INSTANCE;
    }
}