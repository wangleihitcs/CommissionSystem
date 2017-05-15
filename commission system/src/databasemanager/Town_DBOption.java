package databasemanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Town_ER;
import util.DBManager;

public class Town_DBOption {
	// 查询数据
		public List<Town_ER> doQuery() {
			List<Town_ER> list = new ArrayList<Town_ER>();

			// 1.声明变量
			Connection connection = null;
			PreparedStatement state = null;
			ResultSet result = null;

			try {
				// 2.获取连接
				connection = DBManager.getConnection();

				// 3.创建sql语句对象，并执行语句
				String sql = "select * from town";
				state = connection.prepareStatement(sql);
				result = state.executeQuery(); // 返回查询结果

				// 4.分析执行结果
				while (result.next()) {
					// 记录存入list集合
					Town_ER sale = new Town_ER();
					sale.setTown_id(result.getInt("town_id"));
					sale.setTown_name(result.getString("town_name"));
					list.add(sale);
				}
			} catch (Exception e) {
				// 5.处理异常
				e.printStackTrace();
			} finally {
				// 6.释放资源
				DBManager.closeAll(connection, state, result);
			}

			return list;
		}
}
