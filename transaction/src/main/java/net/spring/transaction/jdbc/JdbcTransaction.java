package net.spring.transaction.jdbc;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class JdbcTransaction {
    String url = "jdbc:postgresql://localhost:5432/postgres";
    String user = "postgres";
    String password = "2011180062";

    @Test
    public void _jdbcTransactionTest0() throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(url,user,password);
        PreparedStatement stmt = connection.prepareStatement("insert into users(name, age, remark) values (?,?,?)");

        try {
            connection.setAutoCommit(false);
            stmt.setString(1, "lolog");
            stmt.setInt(2, 20);
            stmt.setString(3,"lolog");
            stmt.addBatch();

            if (true) {
                throw new RuntimeException("Stopping insert");
            }

            stmt.setString(1, "jonguo");
            stmt.setInt(2, 20);
            stmt.setString(3,"jonguo");
            stmt.addBatch();

            stmt.executeBatch();
            connection.commit();
        }
        catch (Exception ex) {
            connection.rollback();
            ex.printStackTrace();
        }
        finally {
            connection.close();
        }
    }

    @Test
    public void _jdbcTransactionTest1() throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(url,user,password);
        Statement stmt = connection.createStatement();

        try {
            connection.setAutoCommit(false);
            stmt.execute("insert into users(name, age, remark) values ('_lolog0',20,'_lolog0')");

            if(true) {
                throw new RuntimeException("Stopping");
            }

            stmt.execute("insert into users(name, age, remark) values ('_lolog1',20,'_lolog1')");
            connection.commit();
        }
        catch (Exception ex) {
            connection.rollback();
            ex.printStackTrace();
        }
        finally {
            connection.close();
        }
    }
}
