package statistics;

import java.util.ArrayList;
import java.util.List;

import databasemanager.SalespersonSoldDetil_DBOption;
import entity.SalespersonSoldDetil_ER;

public class SalespersonSoldStatistics {
	private int id;
	private String date;
	private int locks;
	private int stocks;
	private int barrels;
	private int totalmoney;
	private int commision;
	
	public List<SalespersonSoldStatistics> getSoldStatistics(int id_) {
		List<SalespersonSoldDetil_ER>  slist = new SalespersonSoldDetil_DBOption().doQueryById(id_);
		List<String> liststrDate = new ArrayList<String>();
		for(int i = 0; i < slist.size(); i++) {
			if(slist.get(i).getId() == id_) {
				String[] temp = slist.get(i).getDate().split("-");
				String year = temp[0];
				String month = temp[1];
				String date_ = year + "-" + month;
				if(!liststrDate.contains(date_)) {
					liststrDate.add(date_);
				}
			}
		}
		List<SalespersonSoldStatistics> list = new ArrayList<SalespersonSoldStatistics>();
		for(int is = 0; is < liststrDate.size(); is++) {
			SalespersonSoldStatistics s = new SalespersonSoldStatistics();
			s.id = id_;
			s.date = liststrDate.get(is);
			s.locks = 0;
			s.stocks = 0;
			s.barrels = 0;
			for(int i = 0; i < slist.size(); i++) {
				if(slist.get(i).getId() == id_ && slist.get(i).getDate().startsWith(liststrDate.get(is))) {
					s.locks += slist.get(i).getLocks();
					s.stocks += slist.get(i).getStocks();
					s.barrels += slist.get(i).getBarrels();
				}
			}
			if(s.locks >= 1 && s.stocks >= 1 && s.barrels >= 1
					&& s.locks <= 70 && s.stocks <= 80 && s.barrels <= 90) {
				s.totalmoney = 45 * s.locks + 30 * s.stocks + 25 * s.barrels;
				if(s.totalmoney >= 800 && s.totalmoney < 1000) {
					double d = s.totalmoney * 0.15;
					s.commision = (int)d;
				}
				else if(s.totalmoney < 1800) {
					double d = s.totalmoney * 0.2;
					s.commision = (int)d;
				}
				else if(s.totalmoney >= 1800) {
					double d = s.totalmoney * 0.2;
					s.commision = (int)d;
				}
				else if(s.totalmoney >= 0 && s.totalmoney < 800) {
					double d = s.totalmoney * 0.1;
					s.commision = (int)d;
				}
				
				else {
					s.commision = 0;
				}
			}
			else {
				s.totalmoney = 45 * s.locks + 30 * s.stocks + 25 * s.barrels;
				s.commision = 0;
				System.out.println("Fail!");
			}
			list.add(s);
		}
		return list;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getLocks() {
		return locks;
	}

	public void setLocks(int locks) {
		this.locks = locks;
	}

	public int getStocks() {
		return stocks;
	}

	public void setStocks(int stocks) {
		this.stocks = stocks;
	}

	public int getBarrels() {
		return barrels;
	}

	public void setBarrels(int barrels) {
		this.barrels = barrels;
	}

	public int getTotalmoney() {
		return totalmoney;
	}

	public void setTotalmoney(int totalmoney) {
		this.totalmoney = totalmoney;
	}

	public int getCommision() {
		return commision;
	}

	public void setCommision(int commision) {
		this.commision = commision;
	}
	
	public static void main(String[] args) {
		List<SalespersonSoldStatistics> list = new SalespersonSoldStatistics().getSoldStatistics(1);
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).totalmoney);
		}
	}

	@Override
	public String toString() {
		return "SalespersonSoldStatistics [id=" + id + ", date=" + date + ", locks=" + locks + ", stocks=" + stocks
				+ ", barrels=" + barrels + ", totalmoney=" + totalmoney + ", commision=" + commision + "]";
	}
}
