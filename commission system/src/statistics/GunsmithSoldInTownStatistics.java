package statistics;

import java.util.ArrayList;
import java.util.List;

import databasemanager.SalespersonSoldDetil_DBOption;
import entity.SalespersonSoldDetil_ER;

public class GunsmithSoldInTownStatistics {
	private String date;
	private int locks;
	private int stocks;
	private int barrels;
	private String town;
	private int totalmoney;
	
	public static void main(String[] args) {
		List<GunsmithSoldInTownStatistics> list = new GunsmithSoldInTownStatistics().getStatistics("Missouri2");
		for (GunsmithSoldInTownStatistics g : list) {
			System.out.println(g);
		}
	}

	public List<GunsmithSoldInTownStatistics> getSoldStatistics(String gunsmith, String town) {
		List<SalespersonSoldDetil_ER> slist = new SalespersonSoldDetil_DBOption().doQueryByGunsmith(gunsmith);
		List<String> liststrDate = new ArrayList<String>();
		for (int i = 0; i < slist.size(); i++) {
			String[] temp = slist.get(i).getDate().split("-");
			String year = temp[0];
			String month = temp[1];
			String date_ = year + "-" + month;
			if (!liststrDate.contains(date_)) {
				liststrDate.add(date_);
			}
		}

		List<GunsmithSoldInTownStatistics> list = new ArrayList<GunsmithSoldInTownStatistics>();
		for (int i = 0; i < liststrDate.size(); i++) {
			GunsmithSoldInTownStatistics s = new GunsmithSoldInTownStatistics();
			s.date = liststrDate.get(i);
			s.town = town;
			s.locks = 0;
			s.stocks = 0;
			s.barrels = 0;
			for (int k = 0; k < slist.size(); k++) {
				if (slist.get(k).getDate().startsWith(s.date) && slist.get(k).getTown().equals(s.town)) {
					s.locks += slist.get(k).getLocks();
					s.stocks += slist.get(k).getStocks();
					s.barrels += slist.get(k).getBarrels();
				}
			}
			if (!(s.locks == 0 && s.stocks == 0 && s.barrels == 0)) {
				list.add(s);
			}
		}
		return list;
	}
	
	public List<GunsmithSoldInTownStatistics> getStatistics(String gunsmith) {
		List<SalespersonSoldDetil_ER> slist = new SalespersonSoldDetil_DBOption().doQueryByGunsmith(gunsmith);
		List<String> liststrDate = new ArrayList<String>();
		for (int i = 0; i < slist.size(); i++) {
			String[] temp = slist.get(i).getDate().split("-");
			String year = temp[0];
			String month = temp[1];
			String date_ = year + "-" + month;
			if (!liststrDate.contains(date_)) {
				liststrDate.add(date_);
			}
		}

		List<GunsmithSoldInTownStatistics> list = new ArrayList<GunsmithSoldInTownStatistics>();
		for (int i = 0; i < liststrDate.size(); i++) {
			GunsmithSoldInTownStatistics s = new GunsmithSoldInTownStatistics();
			s.date = liststrDate.get(i);
			s.town = town;
			s.locks = 0;
			s.stocks = 0;
			s.barrels = 0;
			for (int k = 0; k < slist.size(); k++) {
				if (slist.get(k).getDate().startsWith(s.date)) {
					s.locks += slist.get(k).getLocks();
					s.stocks += slist.get(k).getStocks();
					s.barrels += slist.get(k).getBarrels();
				}
			}
			s.totalmoney = 45 * s.locks + 30 * s.stocks + 25 * s.barrels;
			if (!(s.locks == 0 && s.stocks == 0 && s.barrels == 0)) {
				list.add(s);
			}
		}
		return list;
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

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public int getTotalmoney() {
		return totalmoney;
	}

	public void setTotalmoney(int totalmoney) {
		this.totalmoney = totalmoney;
	}

	@Override
	public String toString() {
		return "GunsmithSoldInTownStatistics [date=" + date + ", locks=" + locks + ", stocks=" + stocks + ", barrels="
				+ barrels + ", town=" + town + ", totalmoney=" + totalmoney + "]";
	}
}
