package jdbc1;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.time.LocalDate;
import java.util.Collection;

public class util {
    private Connection conn=null;
    @Before
    public void ac(){

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wei?serverTimezone=PRC","root", "");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Test
    public void add(){
        String sql = "select * from stt where id in (?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,1);
            ps.setInt(2,2);
            ps.setInt(3,3);
            ResultSet se = ps.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Test
    public void preinsp2(){
        try {
            String sql = "int into ee1 values(null,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,"李丽");
            ps.setString(2,"2000-11-10 19:00:00");
            LocalDate bir = LocalDate.of(1999, 7, 17);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Test
    public void insert() throws SQLException {
        String sql = "insert into stt values(null,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,"赵六");
        ps.setBoolean(2,false);
        ps.setString(3,"男");
        ps.setInt(4,20);
        ps.setString(5,"河南郑州");
        ps.setInt(6,950);
        ps.setDate(7,Date.valueOf(LocalDate.now()));
        ps.setString(8,"马克思主义");
        int i = ps.executeUpdate();

    }
}
