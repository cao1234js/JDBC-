import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @author cao
 *
 */
public class 从数据库中写出数据到文件中 {
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		//1.加载MYSQL驱动到JAVA虚拟机
		Class.forName("com.mysql.jdbc.Driver");
		//2.创建数据库连接（根据ID，端口号，用户名以及密码进行数据库连接）
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/q","root","123456");
		//3.使用sql语句
		Statement sqlduixiang = conn.createStatement();
		//4.把sql语句赋值给对象rst
		ResultSet rst = sqlduixiang.executeQuery("select * from xuebaqinshi");
		
	
		
		//转换路径文件内容的字节为字符并赋值给写入对象osw，字符输出流
		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("C:\\Users\\cao\\Desktop\\JDBC连接数据库练习\\读取到本文件数据表.txt"));
		
		
		
		
		
		ArrayList<Student> list = new ArrayList<Student>();
		ArrayList<Map<String,Object>> list2 = new ArrayList<Map<String,Object>>();
		while(rst.next()){
			String name = rst.getString("name");
			int age = rst.getInt("age");
			int size = rst.getInt("size");
			int id = rst.getInt("id");
			String sex = rst.getString("sex");
			
			
			Student s = new Student(name, age, size, id, sex);
			list.add(s);			
			
			
			HashMap<String,Object> stu = new HashMap<String,Object>();
			stu.put("name", name);
			stu.put("age", age);
			stu.put("size", size);
			stu.put("id", id);
			stu.put("sex", sex);
			list2.add(stu);			
//			osw.write("姓名为：" + name + "\t年龄为：" + age + "\t大小为：" + size + "\t学号为：" + id + "\t性别为：" + sex + "\r\n\r\n");
		}
		System.out.println(list);
		System.out.println(list2);
		//关流
		osw.close();
		rst.close();
		sqlduixiang.close();
		conn.close();
	}
}
