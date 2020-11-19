package jdbc1;

import lombok.Data;

import java.sql.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;



@Data
public class dbUtils {
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://192.168.1.50:3306/netjic?serverTimezone=PRC&useUnicode=true&characterEncoding=tutf8";
    private String user = "root";
    private String password = "";

    private Connection conn = null;
    private int recordCount = 0;

    public void close(){
        try {
            this.conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public dbUtils(){
        try {
            Class.forName(driver);
            this.conn = DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public int insert(String sql,Object...params){
        int result = 0;
        try {
            PreparedStatement psr = conn.prepareStatement(sql);
            for (Object o : params){
                int index = 1;
                psr.setObject(index++,0);
            }
            result = psr.executeUpdate();
            psr.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }


    public int add(String tablename, Map<String, Object> values){
        int result = 0;
        Set<String>set = values.keySet();
        String fs = set.toString().replace(" ","");
        fs = fs.substring(1,fs.length()-1);
        String vs = set.stream().map(e->"?").collect(Collectors.toList()).toString().replace(" ","");
        vs = vs.substring(1,vs.length()-1);
        String sql = String.format("insert into %s(%s) values(%s)",tablename,fs,vs);

            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                int index = 1;
                for (String k:set) {
                    ps.setObject(index++,values.get(k));
                }
                result = ps.executeUpdate();
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        return result;
    }


    public String getpk(String tableName){
        String pk = "id";
        String sql = String.format("show index from %s where key_name = ?",tableName);
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,"PRIMARY");
            ResultSet rs = ps.executeQuery();
            rs.next();
            pk = rs.getString("cloumn_name");
            rs.close();
            ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pk;
    }



    public int delete(String tablename,int id){
        int result = 0;
        String sql = String.format("delete from %s where %s = ?",tablename,getpk(tablename));
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return result;

    }
    public int select(String tablename,String where){
        try {
            String sql =String.format("sqlect count(*) from %s %s",tablename,where);
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            recordCount = rs.getInt(1);
        } catch (SQLException throwables) {

        }
        return this.recordCount;

    }
    //public int updateInc(String tablename,String field,int num,String where,Object...params){
      //  String sql = String.format("update % s set %s = ifnull(%s,0)+%d %s",tablename,field,field,num,where);

      //  return update(sql , params);

    //}

}
