package views;

import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;
import dao.UserDao;
import model.User;
import util.dbutil;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import util.dbutil;
import javax.swing.ImageIcon;
import model.User;

public  class ReaderAdd extends JFrame {

dbutil dbUtil = new dbutil();
UserDao userDao = new UserDao();
private static JPanel contentPane;
private JTextField userNameTxt;
private JTextField passwordTxt;
private JTextField userNameTxt1;
private JTextField passwordTxt2;


PreparedStatement statement = null;
ResultSet res = null;

public static void main(String[] args) {
EventQueue.invokeLater(new Runnable() {
	
public void run() {
try {
	ReaderAdd frame =new ReaderAdd();
frame.setVisible(true);
} catch (Exception e) {
e.printStackTrace();
}
}
});
}


public ReaderAdd() {
this.setLocationRelativeTo(null);//窗口在屏幕中间显示
setResizable(false);

setTitle("读者信息编辑");
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setBounds(100, 100, 500, 500);
contentPane =new JPanel();

contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
setContentPane(contentPane);
contentPane.setLayout(null);

JLabel label =new JLabel("读者信息编辑界面");
label.setFont(new Font("Monaco", Font.PLAIN, 16));
label.setBounds(168, 18, 176, 47);
contentPane.add(label);

JLabel label_1 =new JLabel("姓名：");
label_1.setBounds(71, 108, 72, 16);
contentPane.add(label_1);

JLabel label_2 =new JLabel("班级：");
label_2.setBounds(71, 154, 72, 16);
contentPane.add(label_2);

JLabel label_3 =new JLabel("年龄：");
label_3.setBounds(71, 200, 72, 16);
contentPane.add(label_3);

JLabel label_4 =new JLabel("id：");
label_4.setBounds(71, 246, 72, 16);
contentPane.add(label_4);




JTextField userNameTxt =new JTextField();
userNameTxt.setBounds(156, 103, 188, 26);
contentPane.add(userNameTxt);

JTextField passwordTxt =new JTextField();
passwordTxt.setBounds(156, 149, 188, 26);
contentPane.add(passwordTxt);

JTextField passwordTxt1 =new JTextField();
passwordTxt1.setBounds(156, 241, 188, 26);
contentPane.add(passwordTxt1);

JTextField userNameTxt1 =new JTextField();
userNameTxt1.setBounds(156, 195, 188, 26);
contentPane.add(userNameTxt1);

JButton fanhui =new JButton("返回");
fanhui.setBounds(258, 354, 117, 29);
contentPane.add(fanhui);
fanhui.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
	
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setVisible(false);
	new ReaderMessageMain().setVisible(true);
}


});



JButton reset =new JButton("增添");
reset.setBounds(82, 354, 117, 29);
contentPane.add(reset);

reset.addActionListener(new ActionListener() 
{
public void actionPerformed(ActionEvent e) 
{
	
String userName =userNameTxt.getText();
String password =passwordTxt.getText();
String userName1 =userNameTxt1.getText();
String password1 =passwordTxt1.getText();

if(userName.isEmpty ()) 
{
JOptionPane.showMessageDialog(null,"姓名不能为空！");
return;
}
if(password.isEmpty())
{
JOptionPane.showMessageDialog(null,"性别不能为空！");
return;
}if(userName1.isEmpty())
{
JOptionPane.showMessageDialog(null,"班级不能为空！");
return;
}
if(password1.isEmpty())
{
JOptionPane.showMessageDialog(null,"学号不能为空！");
return;
}


User resultUser=null;

String sql="select * from t_user ";
try {
	Connection con = dbUtil.getCon();
PreparedStatement pstmt = con.prepareStatement(sql);

ResultSet rs = null;

	rs = pstmt.executeQuery();

	if(rs.next()){
		String sql1 ="insert into user (mingzi,nianling,banji,id)values('"+userName+"','"+password+"','"+userName1+"','"+password1+"');";
	
			statement=con.prepareStatement(sql1);
		try{		
				User user =new User(userName,password);
				User currenUser =userDao.login(dbUtil.getCon(),user);
				if(currenUser!=null) 
				{
				JOptionPane.showMessageDialog(null, "对不起该用户名已经有了！");
				}else 
				{
				setDefaultCloseOperation(EXIT_ON_CLOSE);
				setVisible(false);
				JOptionPane.showMessageDialog(null,"增添成功！");
				new ReaderSearchMain().setVisible(true);
				statement.executeUpdate(sql1);

		  	}
		}catch(SQLException e1){
		  e1.printStackTrace();
		  }
		}
} catch (Exception e1) {
	e1.printStackTrace();
}
}






private void dispose() {
	//TODO Auto-generated method stub;
	}
	});
}





}

