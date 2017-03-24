package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import bean.User;
import util.DataBaseUtils;

public class UserDAO {
	public static boolean login(User user){
        boolean flag = false;//flag的错误值可以随意赋，但是你要保证错误值不和你生成的id有交集
        //flag错误值也可以表示多种错误，你可以在网页的js处理并展示
        String sql = "SELECT * FROM User WHERE UserName='"+user.getUsername()+"'";
        ResultSet rs =  DataBaseUtils.query(sql);//conn是连接数据库类的实例,这里不赘述
        try{
            if(rs.next()){
                String pwd = user.getPassword();
                //int userid = rs.getInt("id");
                if(pwd.equals(rs.getString("password"))){
                    //user.setidentify(rs.getInt(9));
                    flag = true;

                }}
        }catch(SQLException se){
            se.printStackTrace();
            flag = false;
        }
//        finally{
//        	 DataBaseUtils.closeConnection(connection, statement, null);
//        }
        return flag;
    }
}
