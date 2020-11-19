import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;

public class demo03 {
    private Connection conn = null;
    @Before
    public void conn(){
        try {
            Class.forName("com.msyql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql:///wei?serverTimezone=PRC&user=root");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    @Test
    public void add(String sql,Object...pream){

    }
}
