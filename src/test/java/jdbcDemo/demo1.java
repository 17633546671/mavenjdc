package jdbcDemo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.sql.SQLException;

public class demo1 {
    private Connection conn;

   @Before
    public void conn(){
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wei?serverTimezone=PRC","root","" );
            System.out.println(1);
        }catch (SQLException throwables){
            throwables.printStackTrace();
            System.out.println("1341");
        }

    }




    @Test
    //查看
    public void ada() {

            try {
                Statement ss = conn.createStatement();
                ResultSet rs = ss.executeQuery("select  * from stu order by id desc ");
                while (rs.next()){
                    System.out.printf("编号: %d, 姓名: %s, 性别: %s, 专业: %s. \n",rs.getInt(1),rs.getString("name"),rs.getString("sex"),rs.getString("dept"));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }



    }
    @Test
    public void sc(){
        System.out.println(1+1);
    }
    @Test
    //增加
    public void insert(){
        try {
            Statement ss = conn.createStatement();
         //  String sql = "insert into stt values(null ,'李四',true,'男',28,'天津市',600,'1999-10-12','艺术')";
            String name = "憨批";
            boolean isgood = false;
            String sex = "男";
            String address = "郑州";
            double money = 6000.00;
            int age = 22;
            String dept = "数学";
            String sql = "insert into stt(name,isgood,sex,age,address,money,dept)values ('"+name+"',"+isgood+",'"+sex+"',"+age+",'"+address+"',"+money+",'"+dept+"')";
            int i = ss.executeUpdate(sql);
            System.out.println(i);
            ss.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    //获取主键
    public void addrsr(){
        try {
            Statement stmt = conn.createStatement(1003,1007);
           // int i = stmt.executeUpdate("insert into str values (null ,'lisi',70)",1);
//            System.out.println(i);

            stmt.executeUpdate("insert into str values (null,'sgh',100)",1);
            ResultSet sf = stmt.getGeneratedKeys();
            sf = stmt.executeQuery("select * from str column_name ");
            sf.next();
            System.out.println(sf.getCursorName());
            if (sf.next()){
                System.out.println(sf.getInt(1));

                System.out.println(sf.getStatement().getGeneratedKeys());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Test
    //获取主键
    public void addrr(){
        try {

            PreparedStatement stmt = conn.prepareStatement("insert into str values(null,?,?)",Statement.RETURN_GENERATED_KEYS);
            // int i = stmt.executeUpdate("insert into str values (null ,'lisi',70)",1);
//            System.out.println(i);
            stmt.setString(1,"王五");
            stmt.setInt(2,80);
            stmt.execute();
            ResultSet sy = stmt.getGeneratedKeys();
            sy.next();
            int key = sy.getInt(1);
            System.out.println(key);
          //  stmt.executeUpdate("insert into str values (null,'vs',100)",1);
//            ResultSet sf = stmt.getGeneratedKeys();
//            sf = stmt.executeQuery("select * from str column_name ");
//            sf.next();
//            System.out.println(sf.getCursorName());
//            if (sf.next()){
//                System.out.println(sf.getInt(1));
//
//                System.out.println(sf.getStatement().getGeneratedKeys());
//            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Test
    //修改,删除
    public void update(){
        try {
            Statement ss = conn.createStatement();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void select(){
        try {
            Statement sr = conn.createStatement();
            ResultSet rs = sr.executeQuery("select dept ,id,name,sex,money from stt order by id desc ");
            while (rs.next()){
                System.out.printf("专业: %s,编号: %d,姓名: %s,性别 : %s, 金钱: %d ,\n",rs.getString("dept"), rs.getInt("id"),rs.getString("name"),rs.getString("sex"),rs.getInt("money"));
            }

            rs= sr.executeQuery("select count(*) nb from stt ");
            rs.next();
            System.out.println(String.format("总人数; %d个",rs.getInt("nb")));
            rs= sr.executeQuery("select count(*) nb from stt where dept = '数学'");
            rs.next();
            System.out.println(String.format("总人数; %d个",rs.getInt("nb")));
            rs.close();
            sr.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Test
    public void resultook(){
        try {
            Statement s = conn.createStatement();
            ResultSet se = s.executeQuery("select id 学号,name as 姓名,money 金币 from stt as ssttt where true order by id desc");
            if (se.isBeforeFirst()){
                System.out.println("有数据");
                se.next();
                se.previous();
                se.absolute(3);
                System.out.println(se.getString("姓名"));
                se.last();
                se.first();
                System.out.println(se.getString("姓名"));
                se.beforeFirst();
                while (se.next()){
                    System.out.println(se.getObject(1));
                }
            }else {
                System.out.println("没有查询结果");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Test
    public void cdd(){

    }
    @After
    public void close(){
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
