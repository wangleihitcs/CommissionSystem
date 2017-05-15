package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//用于数据库连接、释放资源
public class DBManager {
	private static String host = "127.0.0.1";
	private static String port = "3306";
	private static String username = "root";
	private static String password = "123456";
	private static String database = "commision";
	
	//通过静态代码块加载数据库驱动程序
	static{
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//获取数据库连接
	public static Connection getConnection() {
		Connection connection = null;
		String url = "jdbc:mysql://" + host + "" + ":" + port +"/" + database;
		try{
			connection = DriverManager.getConnection(url, username, password);
		}catch(Exception e){
			e.printStackTrace();
		}
		return connection;
	}
	
	//释放资源
	public static void closeAll(Connection connection, Statement state, ResultSet result) {
		try{
			if(result != null){
				result.close();
			}
			if(state != null){
				state.close();
			}
			if(connection != null){
				connection.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Connection c = getConnection();
		if(c != null){
//			System.out.println("连接成功！");
		}
		else
			System.out.println("连接失败！");
	}
}
