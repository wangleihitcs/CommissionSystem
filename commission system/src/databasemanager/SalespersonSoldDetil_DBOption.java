package databasemanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.SalespersonSoldDetil_ER;
import util.DBManager;

public class SalespersonSoldDetil_DBOption {
	// 查询数据
	public List<SalespersonSoldDetil_ER> doQuery() {
		List<SalespersonSoldDetil_ER> list = new ArrayList<SalespersonSoldDetil_ER>();

		// 1.声明变量
		Connection connection = null;
		PreparedStatement state = null;
		ResultSet result = null;

		try {
			// 2.获取连接
			connection = DBManager.getConnection();

			// 3.创建sql语句对象，并执行语句
			String sql = "select * from salespersonsolddetil";
			state = connection.prepareStatement(sql);
			result = state.executeQuery(); // 返回查询结果

			// 4.分析执行结果
			while (result.next()) {
				// 记录存入list集合
				SalespersonSoldDetil_ER sale = new SalespersonSoldDetil_ER();
				sale.setId(result.getInt("id"));
				sale.setDate(result.getString("date_"));
				sale.setLocks(result.getInt("locks_"));
				sale.setStocks(result.getInt("stocks_"));
				sale.setBarrels(result.getInt("barrels_"));
				sale.setTown(result.getString("town_"));
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
	public List<SalespersonSoldDetil_ER> doQueryById(int id) {
		List<SalespersonSoldDetil_ER> list = new ArrayList<SalespersonSoldDetil_ER>();

		// 1.声明变量
		Connection connection = null;
		PreparedStatement state = null;
		ResultSet result = null;

		try {
			// 2.获取连接
			connection = DBManager.getConnection();

			// 3.创建sql语句对象，并执行语句
			String sql = "select * from salespersonsolddetil where id = " + id;
			state = connection.prepareStatement(sql);
			result = state.executeQuery(); // 返回查询结果

			// 4.分析执行结果
			while (result.next()) {
				// 记录存入list集合
				SalespersonSoldDetil_ER sale = new SalespersonSoldDetil_ER();
				sale.setId(result.getInt("id"));
				sale.setDate(result.getString("date_"));
				sale.setLocks(result.getInt("locks_"));
				sale.setStocks(result.getInt("stocks_"));
				sale.setBarrels(result.getInt("barrels_"));
				sale.setTown(result.getString("town_"));
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
	
	public List<SalespersonSoldDetil_ER> doQueryByGunsmith(String gunsmith) {
		List<SalespersonSoldDetil_ER> list = new ArrayList<SalespersonSoldDetil_ER>();

		// 1.声明变量
		Connection connection = null;
		PreparedStatement state = null;
		ResultSet result = null;

		try {
			// 2.获取连接
			connection = DBManager.getConnection();

			// 3.创建sql语句对象，并执行语句
			String sql = "select s1.id,s1.date_,s1.locks_,s1.stocks_,s1.barrels_,s1.town_ from salespersonsolddetil s1, salespersoninformation s2 where s1.id = s2.id and s2.gunsmith = '" + gunsmith + "'";
			state = connection.prepareStatement(sql);
			result = state.executeQuery(); // 返回查询结果

			// 4.分析执行结果
			while (result.next()) {
				// 记录存入list集合
				SalespersonSoldDetil_ER sale = new SalespersonSoldDetil_ER();
				sale.setId(result.getInt("id"));
				sale.setDate(result.getString("date_"));
				sale.setLocks(result.getInt("locks_"));
				sale.setStocks(result.getInt("stocks_"));
				sale.setBarrels(result.getInt("barrels_"));
				sale.setTown(result.getString("town_"));
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

	
	public boolean doUpdateData(SalespersonSoldDetil_ER sale) {
		boolean flag = false;
		// 1.声明变量
		Connection connection = null;
		PreparedStatement state = null;
		ResultSet result = null;

		try {
			// 2.获取连接
			connection = DBManager.getConnection();

			// 3.创建sql语句对象，并执行语句
			String sql = "insert into salespersonsolddetil values(?, ?, ?, ?, ?, ?)";
			state = connection.prepareStatement(sql);
			state.setInt(1, sale.getId());
//			java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(sale.getDate());
//			System.out.println(sale.getDate());
			state.setDate(2, java.sql.Date.valueOf(sale.getDate()));
			state.setInt(3, sale.getLocks());
			state.setInt(4, sale.getStocks());
			state.setInt(5, sale.getBarrels());
			state.setString(6, sale.getTown());
			if(state.executeUpdate() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.closeAll(connection, state, result);
		}

		return flag;
	}
	public static void main(String[] args) {
		List<SalespersonSoldDetil_ER>  list = new SalespersonSoldDetil_DBOption().doQueryByGunsmith("Missouri1");
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}
