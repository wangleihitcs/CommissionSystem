package statistics;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import databasemanager.SalespersonSoldDetil_DBOption;
import entity.SalespersonSoldDetil_ER;

public class TownsSoldStatistics {
	private String town_name;
	private int locks;
	private int stocks;
	private int barrels;
	
	public List<TownsSoldStatistics> getTownsSold() {
		String currentDate = new SimpleDateFormat("yyyy-MM").format(new Date());
		List<SalespersonSoldDetil_ER>  slist = new SalespersonSoldDetil_DBOption().doQuery();
		List<String> town_list = new ArrayList<String>();
		for(int i = 0; i < slist.size(); i++) {
			if(slist.get(i).getDate().startsWith(currentDate)) {
				if(!town_list.contains(slist.get(i).getTown())) {
					town_list.add(slist.get(i).getTown());
				}
			}
		}
		List<TownsSoldStatistics> list = new ArrayList<TownsSoldStatistics>();
		for(int it = 0; it < town_list.size(); it++) {
			TownsSoldStatistics t = new TownsSoldStatistics();
			t.town_name = town_list.get(it);
			int locks_ = 0;
			int stocks_ = 0;
			int barrels_ = 0;
			for(int i = 0; i < slist.size(); i++) {
				if(slist.get(i).getDate().startsWith(currentDate) && slist.get(i).getTown().equals(t.town_name)) {
					locks_ += slist.get(i).getLocks();
					stocks_ += slist.get(i).getStocks();
					barrels_ += slist.get(i).getBarrels();
				}
			}
			t.locks = locks_;
			t.stocks = stocks_;
			t.barrels = barrels_;
			list.add(t);
		}
		return list;
	}

	public String getTown_name() {
		return town_name;
	}

	public void setTown_name(String town_name) {
		this.town_name = town_name;
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

	@Override
	public String toString() {
		return "TownsSoldStatistics [town_name=" + town_name + ", locks=" + locks + ", stocks=" + stocks + ", barrels="
				+ barrels + "]";
	}
}
