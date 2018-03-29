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
public class �����ݿ���д�����ݵ��ļ��� {
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		//1.����MYSQL������JAVA�����
		Class.forName("com.mysql.jdbc.Driver");
		//2.�������ݿ����ӣ�����ID���˿ںţ��û����Լ�����������ݿ����ӣ�
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/q","root","123456");
		//3.ʹ��sql���
		Statement sqlduixiang = conn.createStatement();
		//4.��sql��丳ֵ������rst
		ResultSet rst = sqlduixiang.executeQuery("select * from xuebaqinshi");
		
	
		
		//ת��·���ļ����ݵ��ֽ�Ϊ�ַ�����ֵ��д�����osw���ַ������
		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("C:\\Users\\cao\\Desktop\\JDBC�������ݿ���ϰ\\��ȡ�����ļ����ݱ�.txt"));
		
		
		
		
		
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
//			osw.write("����Ϊ��" + name + "\t����Ϊ��" + age + "\t��СΪ��" + size + "\tѧ��Ϊ��" + id + "\t�Ա�Ϊ��" + sex + "\r\n\r\n");
		}
		System.out.println(list);
		System.out.println(list2);
		//����
		osw.close();
		rst.close();
		sqlduixiang.close();
		conn.close();
	}
}
