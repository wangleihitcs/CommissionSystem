package entity;

public class SalespersonSoldDetil_ER {
	private int id;
	private String date;
	private int locks;
	private int stocks;
	private int barrels;
	private String town;
	
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
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	@Override
	public String toString() {
		return "SalespersonSoldDetial_ER [id=" + id + ", date=" + date + ", locks=" + locks + ", stocks=" + stocks
				+ ", barrels=" + barrels + ", town=" + town + "]";
	}
}
