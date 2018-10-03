package net.safedata.spring.jdbc.intro.repository;

import net.safedata.spring.jdbc.intro.model.Product;
import org.h2.tools.Server;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductRepository {

    private static final String H2_SERVER_JDBC_URL = "jdbc:h2:~/test";
    private static final String H2_SERVER_USER = "sa";
    private static final String H2_SERVER_PASSWORD = "";

    static {
        try {
            Server.createTcpServer();

            // load the H2 driver
            Class.forName("org.h2.Driver");
        } catch (final ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.exit(13);
        }
    }

    public ProductRepository() {
        try (Connection connection = getConnection()) {
            buildCallableStatement(connection, "DROP TABLE Product IF EXISTS;").execute();
            buildCallableStatement(connection, "CREATE TABLE Product(id INTEGER NOT NULL, name CHAR(30), PRIMARY KEY (id))").execute();

            buildCallableStatement(connection, "INSERT INTO Product (id, name) VALUES (1, 'Dell XPS')").execute();
            buildCallableStatement(connection, "INSERT INTO Product (id, name) VALUES (2, 'Asus UX530')").execute();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public int getCount() {
        final String query = "SELECT count(*) FROM Product";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = getResultSet(statement, query)) {

            resultSet.next();
            return resultSet.getInt(1);
        } catch (final SQLException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    public Product getProduct(final int id) {
        final String query = "SELECT * FROM Product WHERE ID = " + id;

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = getResultSet(statement, query)){

            if (!resultSet.next())
                throw new IllegalArgumentException("There is no product with the ID " + id);

            return new Product(resultSet.getInt("id"), resultSet.getString("name"));
        } catch (final SQLException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    private Connection getConnection() {
        try {
            return DriverManager.getConnection(H2_SERVER_JDBC_URL, H2_SERVER_USER, H2_SERVER_PASSWORD);
        } catch (final SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private CallableStatement buildCallableStatement(final Connection connection, final String query) throws SQLException {
        return connection.prepareCall(query);
    }

    private ResultSet getResultSet(final Statement statement, final String query) throws SQLException {
        return statement.executeQuery(query);
    }
}
