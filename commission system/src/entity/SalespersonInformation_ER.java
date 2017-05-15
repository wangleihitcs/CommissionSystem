package entity;

public class SalespersonInformation_ER {
	private int id;
	private String name;
	private String gunsmith;
	private int commision;
	private int flag;
	
	public SalespersonInformation_ER() {
		super();
	}
	public SalespersonInformation_ER(int id, String name, String gunsmith, int commision) {
		this.id = id;
		this.name = name;
		this.gunsmith = gunsmith;
		this.commision = commision;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGunsmith() {
		return gunsmith;
	}
	public void setGunsmith(String gunsmith) {
		this.gunsmith = gunsmith;
	}
	public int getCommision() {
		return commision;
	}
	public void setCommision(int commision) {
		this.commision = commision;
	}
	
	@Override
	public String toString() {
		return "SalespersonInformation_ER [id=" + id + ", name=" + name + ", gunsmith=" + gunsmith + ", commision="
				+ commision + ", flag=" + flag + "]";
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
}
