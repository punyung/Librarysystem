package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.User;//这个文件中要导入User.java

// 登陆
public class UserDao{

public User login(Connection con,User user)throws Exception{

	User resultUser=null;

String sql="select * from t_user where userName=? and password=?";//查询表中所有记录"

PreparedStatement pstmt=con.prepareStatement(sql);
pstmt.setString(1,user.getUserName());
pstmt.setString(2,user.getPassword());
ResultSet rs=pstmt.executeQuery();

if(rs.next()){
resultUser=new User();
resultUser.setUserName(rs.getString("userName"));
resultUser.setPassword(rs.getString("password"));
}
return resultUser;
}
}
