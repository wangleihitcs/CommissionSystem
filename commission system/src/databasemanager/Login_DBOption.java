package databasemanager;
//ss
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entity.Login_ER;
import util.DBManager;

public class Login_DBOption {
	public Login_ER doQuery(String user, String pass, int flag) {
		Login_ER sp = new Login_ER();

		// 1.声明变量
		Connection connection = null;
		PreparedStatement state = null;
		ResultSet result = null;

		try {
			// 2.获取连接
			connection = DBManager.getConnection();

			// 3.创建sql语句对象，并执行语句
			String sql = "select * from login where username = ? and password = ? and flag = ?";
			state = connection.prepareStatement(sql);
			state.setString(1, user);
			state.setString(2, pass);
			state.setInt(3, flag);
			result = state.executeQuery(); // 返回查询结果

			// 4.分析执行结果
			while (result.next()) {
				// 记录存入list集合
				sp.setUsername(result.getString("username"));
				sp.setPassword(result.getString("password"));
				sp.setId(result.getInt("id"));
				sp.setFlag(result.getInt("flag"));
			}
		} catch (Exception e) {
			// 5.处理异常
			e.printStackTrace();
		} finally {
			// 6.释放资源
			DBManager.closeAll(connection, state, result);
		}

		return sp;
	}
	
	public static void main(String[] args) {
		Login_ER login = new Login_DBOption().doQuery("xiaowang", "123456", 1);
		System.out.println(login.toString());
	}
}
