package databasemanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.SalespersonInformation_ER;
import util.DBManager;

public class SalespersonInformation_DBOption {
	// 查询数据
	public List<SalespersonInformation_ER> doQuery() {
		List<SalespersonInformation_ER> list = new ArrayList<SalespersonInformation_ER>();

		// 1.声明变量
		Connection connection = null;
		PreparedStatement state = null;
		ResultSet result = null;

		try {
			// 2.获取连接
			connection = DBManager.getConnection();

			// 3.创建sql语句对象，并执行语句
			String sql = "select * from salespersoninformation order by id";
			state = connection.prepareStatement(sql);
			result = state.executeQuery(); // 返回查询结果

			// 4.分析执行结果
			while (result.next()) {
				// 记录存入list集合
				SalespersonInformation_ER sale = new SalespersonInformation_ER();
				sale.setId(result.getInt("id"));
				sale.setName(result.getString("salesname"));
				sale.setCommision(result.getInt("commision"));
				sale.setGunsmith(result.getString("gunsmith"));
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

	public SalespersonInformation_ER doQueryById(int id) {
		SalespersonInformation_ER sp = new SalespersonInformation_ER();

		// 1.声明变量
		Connection connection = null;
		PreparedStatement state = null;
		ResultSet result = null;

		try {
			// 2.获取连接
			connection = DBManager.getConnection();

			// 3.创建sql语句对象，并执行语句
			String sql = "select * from salespersoninformation where id = " + id;
			state = connection.prepareStatement(sql);
			result = state.executeQuery(); // 返回查询结果

			// 4.分析执行结果
			while (result.next()) {
				// 记录存入list集合
				sp.setId(result.getInt("id"));
				sp.setName(result.getString("salesname"));
				sp.setCommision(result.getInt("commision"));
				sp.setGunsmith(result.getString("gunsmith"));
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
	public List<SalespersonInformation_ER> doQueryByGunsmith(String gunsmith) {
		List<SalespersonInformation_ER> list = new ArrayList<SalespersonInformation_ER>();
		
		// 1.声明变量
		Connection connection = null;
		PreparedStatement state = null;
		ResultSet result = null;

		try {
			// 2.获取连接
			connection = DBManager.getConnection();

			// 3.创建sql语句对象，并执行语句
			String sql = "select * from salespersoninformation where gunsmith = '" + gunsmith + "'";
			state = connection.prepareStatement(sql);
			result = state.executeQuery(); // 返回查询结果

			// 4.分析执行结果
			while (result.next()) {
				SalespersonInformation_ER sp = new SalespersonInformation_ER();
				// 记录存入list集合
				sp.setId(result.getInt("id"));
				sp.setName(result.getString("salesname"));
				sp.setCommision(result.getInt("commision"));
				sp.setGunsmith(result.getString("gunsmith"));
				list.add(sp);
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
	public List<String> doQueryGunsmith() {
		List<String> list = new ArrayList<String>();
		// 1.声明变量
		Connection connection = null;
		PreparedStatement state = null;
		ResultSet result = null;

		try {
			// 2.获取连接
			connection = DBManager.getConnection();

			// 3.创建sql语句对象，并执行语句
			String sql = "select gunsmith from salespersoninformation";
			state = connection.prepareStatement(sql);
			result = state.executeQuery(); // 返回查询结果

			// 4.分析执行结果
			while (result.next()) {
				// 记录存入list集合
				if(!list.contains(result.getString("gunsmith"))) {
					list.add(result.getString("gunsmith"));
				}
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

	public boolean doUpdateById(int id, int commision_) {
		boolean flag = false;
		// 1.声明变量
		Connection connection = null;
		PreparedStatement state = null;
		ResultSet result = null;

		try {
			// 2.获取连接
			connection = DBManager.getConnection();

			// 3.创建sql语句对象，并执行语句
			String sql = "update salespersoninformation set commision = " + commision_ + " where id = " + id;
			state = connection.prepareStatement(sql);
			if (!state.execute()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.closeAll(connection, state, result);
		}

		return flag;
	}
	
	public boolean doUpdateFlagById(int id, int commision_, int fflag) {
		boolean flag = false;
		// 1.声明变量
		Connection connection = null;
		PreparedStatement state = null;
		ResultSet result = null;

		try {
			// 2.获取连接
			connection = DBManager.getConnection();

			// 3.创建sql语句对象，并执行语句
			String sql = "update salespersoninformation set commision = " + commision_ + ", flag = " + fflag + 
					" where id = " + id;
			state = connection.prepareStatement(sql);
			if (!state.execute()) {
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
		SalespersonInformation_DBOption saleop = new SalespersonInformation_DBOption();
		List<SalespersonInformation_ER> list = saleop.doQuery();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
		SalespersonInformation_ER sp = saleop.doQueryById(1);
		System.out.println(sp.toString());

		// if(saleop.doUpdateById(1, 19)) {
		// System.out.println("ww");
		// }
	}
}
