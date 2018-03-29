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
public class ���ļ�����д�����ݿ��� {
	public static void main(String[] args) throws IOException,ClassNotFoundException,SQLException{
		//1.����MYSQL������JAVA�����
		Class.forName("com.mysql.jdbc.Driver");
		//2.�������ݿ����ӣ�����ID���˿ںţ��û����Լ�����������ݿ����ӣ�
		Connection conn = DriverManager.getConnection
				("jdbc:mysql://localhost/q","root","123456");
		//3.ʹ��sql���
		Statement sqlduixiang = conn.createStatement();
		String sqlyuju = "insert into xuebaqinshi(name,age,size,id,sex) values(";
		InputStreamReader isr = new InputStreamReader(new FileInputStream("C:\\Users\\cao\\Desktop\\JDBC�������ݿ���ϰ\\д�뵽���ݿ����ݱ�.txt"),"GB2312");
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
		//����
		bf.close();
		isr.close();
		sqlduixiang.close();
		conn.close();
	}
	//��������getDataMap
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
