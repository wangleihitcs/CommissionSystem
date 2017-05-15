package ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import databasemanager.SalespersonInformation_DBOption;
import databasemanager.SalespersonSoldDetil_DBOption;
import databasemanager.Town_DBOption;
import entity.SalespersonInformation_ER;
import entity.SalespersonSoldDetil_ER;
import entity.Town_ER;
import statistics.SalespersonSoldStatistics;

public class SalespersonInformation_UI1 extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private JMenuBar menuBar;
	private JMenu menuSalesperson;
	private JMenu menuGunsmith;
	private JMenu menuSystem;
	
	private JMenuItem menuItemInformation;
	private JMenuItem menuItemTown;
	
	private JMenuItem menuItemGunsmithInformation;
	private JMenuItem menuItemGunsmithStatistics;
	
	private JMenuItem menuItemLogin;
	
	private JTextField jtextNo;
	private JTextField jtextName;
	private JTextField jtextCommision;
	private JTextField jtextGunsmith;
	private JTextField jtextLocks;
	private JTextField jtextStocks;
	private JTextField jtextBarrels;
	
	private JTable tableSoldInTown;
	private JScrollPane scrollpaneSoldInTown;
	private JTable tableStatistics;
	private JScrollPane scrollpaneStatistics;
	
	private JComboBox<String> jcomboboxSalesperson;
	private JComboBox<String> jcomboboxTowns;
	private JButton jbuttonQuerySalesperson;
	private JButton jbuttonGetCommision;
	private JButton jbuttonSubmit;
	
	private List<SalespersonInformation_ER> sale_list;
	private List<Town_ER> town_list;
	private SalespersonInformation_ER personinformation;
	private List<SalespersonSoldStatistics> listSoldStatistics;
	
	private String id;
	private String town_name;
//	int sid;
	
	public SalespersonInformation_UI1() {
//		System.out.println(loginid);
		//初始化数据
		sale_list = new SalespersonInformation_DBOption().doQuery();
		town_list = new Town_DBOption().doQuery();
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 1000, 700);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		menuSalesperson = new JMenu("Salesperson");
		menuItemInformation = new JMenuItem("Information");
		menuSalesperson.add(menuItemInformation);
		menuItemTown = new JMenuItem("Town");
		menuSalesperson.add(menuItemTown);

		menuGunsmith = new JMenu("Gunsmith");
		menuItemGunsmithInformation = new JMenuItem("Information");
		menuGunsmith.add(menuItemGunsmithInformation);
		menuItemGunsmithStatistics = new JMenuItem("Statistics");
		menuGunsmith.add(menuItemGunsmithStatistics);
		
		menuSystem = new JMenu("System");
		menuItemLogin = new JMenuItem("Login");
		menuSystem.add(menuItemLogin);
		
		menuBar = new JMenuBar();
		menuBar.add(menuSalesperson);
		menuBar.add(menuGunsmith);
		menuBar.add(menuSystem);
		setJMenuBar(menuBar);
		
		//下拉菜单1，表示卖枪人
		jcomboboxSalesperson = new JComboBox<String>();
		jcomboboxSalesperson.setBounds(10, 10, 120, 25);
		jcomboboxSalesperson.addItem("--select--");
		for(int i = 0; i < sale_list.size(); i++) {
			jcomboboxSalesperson.addItem("person" + sale_list.get(i).getId());
		}
//		jcomboboxSalesperson.addItem("person" + loginid);
		contentPanel.add(jcomboboxSalesperson);
		
		jbuttonQuerySalesperson = new JButton("Query");
		jbuttonQuerySalesperson.setBounds(140, 10, 85, 25);
		contentPanel.add(jbuttonQuerySalesperson);
		jbuttonGetCommision = new JButton("Get Commision");
		jbuttonGetCommision.setBounds(230, 10, 150, 25);
		contentPanel.add(jbuttonGetCommision);
		
		JLabel jlabelNo = new JLabel("No");
		jlabelNo.setBounds(20, 45, 80, 25);
		contentPanel.add(jlabelNo);
		jtextNo = new JTextField("");
		jtextNo.setBounds(105, 45, 220, 25);
		jtextNo.setEditable(false);
		contentPanel.add(jtextNo);
		
		JLabel jlabelName = new JLabel("Name");
		jlabelName.setBounds(20, 80, 80, 25);
		contentPanel.add(jlabelName);
		jtextName = new JTextField("");
		jtextName.setBounds(105, 80, 220, 25);
		jtextName.setEditable(false);
		contentPanel.add(jtextName);
		
		JLabel jlabelCommision = new JLabel("Commision");
		jlabelCommision.setBounds(20, 115, 80, 25);
		contentPanel.add(jlabelCommision);
		jtextCommision = new JTextField("");
		jtextCommision.setBounds(105, 115, 220, 25);
		jtextCommision.setEditable(false);
		contentPanel.add(jtextCommision);
		
		JLabel jlabelGunsmith = new JLabel("Gunsmith");
		jlabelGunsmith.setBounds(20, 150, 80, 25);
		contentPanel.add(jlabelGunsmith);
		jtextGunsmith = new JTextField("");
		jtextGunsmith.setBounds(105, 150, 220, 25);
		jtextGunsmith.setEditable(false);
		contentPanel.add(jtextGunsmith);
		
		jcomboboxTowns = new JComboBox<String>();
		jcomboboxTowns.setBounds(10, 190, 120, 25);
		jcomboboxTowns.addItem("--select--");
		for(int i = 0; i < town_list.size(); i++) {
			jcomboboxTowns.addItem(town_list.get(i).getTown_name());
		}
		contentPanel.add(jcomboboxTowns);
		
		JLabel jlabelLocks = new JLabel("Locks");
		jlabelLocks.setBounds(135, 190, 50, 25);
		contentPanel.add(jlabelLocks);
		jtextLocks = new JTextField("0");
		jtextLocks.setBounds(190, 190, 50, 25);
		contentPanel.add(jtextLocks);
		
		JLabel jlabelStocks = new JLabel("Stocks");
		jlabelStocks.setBounds(250, 190, 50, 25);
		contentPanel.add(jlabelStocks);
		jtextStocks = new JTextField("0");
		jtextStocks.setBounds(305, 190, 50, 25);
		contentPanel.add(jtextStocks);
		
		JLabel jlabelBarrels = new JLabel("Barrels");
		jlabelBarrels.setBounds(360, 190, 50, 25);
		contentPanel.add(jlabelBarrels);
		jtextBarrels = new JTextField("0");
		jtextBarrels.setBounds(415, 190, 50, 25);
		contentPanel.add(jtextBarrels);
		
		jbuttonSubmit = new JButton("Submit");
		jbuttonSubmit.setBounds(490, 190, 80, 25);
		contentPanel.add(jbuttonSubmit);
		
		JLabel jlabelSoldInTown = new JLabel("Sold in Town");//table of sold in town
		jlabelSoldInTown.setBounds(20, 230, 100, 25);
		contentPanel.add(jlabelSoldInTown);
		DefaultTableModel tbmodelSoldInTown = new DefaultTableModel(null, new String[] { "Dates", "Locks", "Stocks", "Barrels", "Towns"});
		tableSoldInTown = new JTable(tbmodelSoldInTown);
		tableSoldInTown.setEnabled(false);
		scrollpaneSoldInTown = new JScrollPane(tableSoldInTown);
		contentPanel.add(scrollpaneSoldInTown);
		scrollpaneSoldInTown.setBounds(20, 260, 540, 170);
		
		JLabel jlabelStatistics = new JLabel("Statistics");//table of sold in town
		jlabelStatistics.setBounds(20, 440, 100, 25);
		contentPanel.add(jlabelStatistics);
		DefaultTableModel tbmodelStatistics = new DefaultTableModel(null, new String[] { "Dates", "Locks", "Stocks", "Barrels", "Total Money", "Commision"});
		tableStatistics = new JTable(tbmodelStatistics);
		tableStatistics.setEnabled(false);
		scrollpaneStatistics = new JScrollPane(tableStatistics);
		contentPanel.add(scrollpaneStatistics);
		scrollpaneStatistics.setBounds(20, 470, 540, 150);
		
		//监听事件
		menuItemInformation.addActionListener(listen);
		menuItemTown.addActionListener(listen);
		menuItemGunsmithInformation.addActionListener(listen);
		menuItemGunsmithStatistics.addActionListener(listen);
		menuItemLogin.addActionListener(listen);
		jcomboboxSalesperson.addActionListener(listen);
		jbuttonQuerySalesperson.addActionListener(listen);
		jbuttonGetCommision.addActionListener(listen);
		jcomboboxTowns.addActionListener(listen);
		jbuttonSubmit.addActionListener(listen);
	}
	
	//响应事件
	ActionListener listen = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == menuItemInformation) {
				try {
					SalespersonInformation_UI1 frame = new SalespersonInformation_UI1();
					frame.setTitle("Salesperson Information");
					frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource() == menuItemTown) {
				try {
					SalespersonTown_UI frame = new SalespersonTown_UI();
					frame.setTitle("Salesperson Town");
					frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource() == menuItemGunsmithInformation) {
				try {
					GunsmithInformation_UI frame = new GunsmithInformation_UI();
					frame.setTitle("Gunsmith Information");
					frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource() == menuItemGunsmithStatistics) {
				try {
					GunsmithStatistics_UI frame = new GunsmithStatistics_UI();
					frame.setTitle("Gunsmith Statistics");
					frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource() == menuItemLogin) {
				try {
					Commsion_UI frame = new Commsion_UI();
					frame.setTitle("Comission System");
					frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource() == jcomboboxSalesperson) {
				try {
					id = jcomboboxSalesperson.getSelectedItem().toString();
				} catch (Exception e1) {
					e1.printStackTrace();
				}	
			}
			if (e.getSource() == jbuttonQuerySalesperson) {
				try {
					SalespersonInformation_DBOption saleop = new SalespersonInformation_DBOption();
					personinformation = saleop.doQueryById(Integer.parseInt(id.substring(6)));
					jtextNo.setText("SP" + new DecimalFormat("000000").format(personinformation.getId()));
					jtextName.setText(personinformation.getName());
					jtextCommision.setText("" + personinformation.getCommision());
					jtextGunsmith.setText(personinformation.getGunsmith());
					
					List<SalespersonSoldDetil_ER>  listSold = new SalespersonSoldDetil_DBOption().doQueryById(Integer.parseInt(id.substring(6)));
					String[][] dataSoldDetil = new String[listSold.size()][5];
					String[] columnSoldDetil = new String[] { "Dates", "Locks", "Stocks", "Barrels", "Towns"};
					for(int i = 0; i < listSold.size(); i++) {
						dataSoldDetil[i][0] = listSold.get(i).getDate();
						dataSoldDetil[i][1] = listSold.get(i).getLocks() + "";
						dataSoldDetil[i][2] = listSold.get(i).getStocks() + "";
						dataSoldDetil[i][3] = listSold.get(i).getBarrels() + "";
						dataSoldDetil[i][4] = listSold.get(i).getTown();
					}
					TableModel tmSoldDetil = new DefaultTableModel(dataSoldDetil, columnSoldDetil);
					tableSoldInTown.setModel(tmSoldDetil);
					
					int totalCommision = 0;
					listSoldStatistics = new SalespersonSoldStatistics().getSoldStatistics(Integer.parseInt(id.substring(6)));
					String[][] dataSoldStatistics = new String[listSoldStatistics.size()][6];
					String[] columnSoldStatistics = new String[] {"Dates", "Locks", "Stocks", "Barrels", "Total Money", "Commision"};
					for(int i = 0; i < listSoldStatistics.size(); i++) {
						dataSoldStatistics[i][0] = listSoldStatistics.get(i).getDate();
						dataSoldStatistics[i][1] = listSoldStatistics.get(i).getLocks() + "";
						dataSoldStatistics[i][2] = listSoldStatistics.get(i).getStocks() + "";
						dataSoldStatistics[i][3] = listSoldStatistics.get(i).getBarrels() + "";
						dataSoldStatistics[i][4] = listSoldStatistics.get(i).getTotalmoney() + "";
						dataSoldStatistics[i][5] = listSoldStatistics.get(i).getCommision() + "";
						totalCommision += listSoldStatistics.get(i).getCommision();
					}
					TableModel tmSoldStatistics = new DefaultTableModel(dataSoldStatistics, columnSoldStatistics);
					tableStatistics.setModel(tmSoldStatistics);
					jtextCommision.setText("" + totalCommision);
					
					saleop.doUpdateById(Integer.parseInt(id.substring(6)), totalCommision);
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource() == jcomboboxTowns) {
				try {
					town_name = jcomboboxTowns.getSelectedItem().toString();
				} catch (Exception e1) {
					e1.printStackTrace();
				}	
			}
			if (e.getSource() == jbuttonSubmit) {
				try {
					String strpersonid = id.substring(6);
					String strLocks = jtextLocks.getText();
					String strStocks = jtextStocks.getText();
					String strBarrels = jtextBarrels.getText();
					String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) ;
					int k = 0;
					for(int i = 0; i < listSoldStatistics.size(); i++) {
						if(currentDate.startsWith(listSoldStatistics.get(i).getDate())) {
							k = i;
							break;
						}
					}
					int locks_ = Integer.parseInt(strLocks);
					int stocks_ = Integer.parseInt(strStocks);
					int barrels_ = Integer.parseInt(strBarrels);
					if( (listSoldStatistics.get(k).getLocks() + locks_) > 80 || (listSoldStatistics.get(k).getStocks() + stocks_ > 80) ||
							(listSoldStatistics.get(k).getBarrels() + barrels_ > 90) ) {
						JOptionPane.showMessageDialog(null, "Locks not outnumber 80, Stocks 80, Barrels 90!", "Tip!", JOptionPane.INFORMATION_MESSAGE);
					}
					else if(locks_ < 0 || stocks_ < 0 || barrels_ < 0 || 
							(locks_ == 0 && stocks_ == 0 && barrels_ == 0)
							) {
						JOptionPane.showMessageDialog(null, "Input Error!", "Tip!", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						SalespersonSoldDetil_ER sale = new SalespersonSoldDetil_ER();
						sale.setId(Integer.parseInt(strpersonid));
						sale.setDate(currentDate);
						sale.setLocks(locks_);
						sale.setStocks(stocks_);
						sale.setBarrels(barrels_);
						sale.setTown(town_name);
						if(new SalespersonSoldDetil_DBOption().doUpdateData(sale)) {
							JOptionPane.showMessageDialog(null, "Submit Success!", "Tip!", JOptionPane.INFORMATION_MESSAGE);
						};
					}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource() == jbuttonGetCommision) {
				try {
					String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
					int k = 0;
					for(int i = 0; i < listSoldStatistics.size(); i++) {
						if(currentDate.startsWith(listSoldStatistics.get(i).getDate())) {
							k = i;
							break;
						}
					}
					if(listSoldStatistics.get(k).getLocks() >= 1 && listSoldStatistics.get(k).getStocks() >= 1 && 
							listSoldStatistics.get(k).getBarrels() >= 1) {
						String str = "ClearingDate:" + currentDate + ", Commision:" + listSoldStatistics.get(k).getCommision();
						JOptionPane.showMessageDialog(null, str, "Tip!", JOptionPane.INFORMATION_MESSAGE);
					}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	};
	public void setPersoninformation(SalespersonInformation_ER personinformation) {
		this.personinformation = personinformation;
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalespersonInformation_UI1 frame = new SalespersonInformation_UI1();
					frame.setTitle("Salesperson Information");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
