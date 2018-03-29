import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * @author cao
 *
 */
public class 把文件内容写入数据库中 {
	public static void main(String[] args) throws IOException,ClassNotFoundException,SQLException{
		//1.加载MYSQL驱动到JAVA虚拟机
		Class.forName("com.mysql.jdbc.Driver");
		//2.创建数据库连接（根据ID，端口号，用户名以及密码进行数据库连接）
		Connection conn = DriverManager.getConnection
				("jdbc:mysql://localhost/q","root","123456");
		//3.使用sql语句
		Statement sqlduixiang = conn.createStatement();
		String sqlyuju = "insert into xuebaqinshi(name,age,size,id,sex) values(";
		InputStreamReader isr = new InputStreamReader(new FileInputStream("C:\\Users\\cao\\Desktop\\JDBC连接数据库练习\\写入到数据库数据表.txt"),"GB2312");
		BufferedReader bf = new BufferedReader(isr);
		String s = "";
		while((s = bf.readLine()) != null){
			List<Map<String,String>> list = getDataMap(s);
			for(Map<String,String> data : list){
				String sql = sqlyuju + "'" + data.get("name") + "'," + data.get("age") + "," + data.get("size") + "," + data.get("id") + ",'" + data.get("sex") + "')";
				System.out.println(sql);
				sqlduixiang.execute(sql);
			}		
		}
		//关流
		bf.close();
		isr.close();
		sqlduixiang.close();
		conn.close();
	}
	//创建方法getDataMap
	public static List<Map<String,String>> getDataMap(String s){
		ArrayList<Map<String,String>> list = new ArrayList<Map<String,String>>();
		String[] arr = s.split("\\|");
		for(int i = 0; i < arr.length; i++){
			String[] arr2 = arr[i].split(",");
			HashMap<String,String> data = new HashMap<String,String>();
			for(int j = 0; j < arr2.length; j++){
				String[] arr3 = arr2[j].split(":");
				data.put(arr3[0].trim(), arr3[1]);
			}
			list.add(data);
		}
		return list;
	}	
}
