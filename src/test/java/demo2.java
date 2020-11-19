import org.junit.Test;

import java.sql.Statement;
import java.sql.*;
public class demo2 {
    private Connection conn;

    public void resultok() {

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wei?serverTimezone=PRC", "root", "ry");
            Statement sr = conn.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    }

