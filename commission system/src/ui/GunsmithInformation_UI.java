package ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import databasemanager.Town_DBOption;
import entity.SalespersonInformation_ER;
import entity.Town_ER;
import statistics.GunsmithSoldInTownStatistics;
import statistics.JChartBarSalespersonStatistics;
import statistics.JChartBarStatistics;
import statistics.JChartLineStatistics;
import statistics.JChartPieStatistics;
import statistics.SalespersonSoldStatistics;

public class GunsmithInformation_UI extends JFrame {
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
	
	private JComboBox<String> jcomboboxGunsmith;
	private JTextField jtextNo;
	private JTextField jtextName;
	private JComboBox<String> jcomboboxTown;
	private JTable tableSoldInTown;
	private JComboBox<String> jcomboboxSalesperson;
	private JTable tableSoldInSalesperson;
	private JTable tableStatistics;
	private JTable tablePerson;
	private JButton jbuttonPerson;
	
	private List<String> gunsmith_list;
	private String gunsmith;
	private List<Town_ER> town_list;
	private String town;
	private List<SalespersonInformation_ER> sale_list;
	private int pid;
	private List<Integer> idlist;
	
	public GunsmithInformation_UI() {
		gunsmith_list = new SalespersonInformation_DBOption().doQueryGunsmith();
		town_list = new Town_DBOption().doQuery();
//		sale_list = new SalespersonInformation_DBOption().doQueryByGunsmith(gunsmith);
		
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
		
		jcomboboxGunsmith = new JComboBox<String>();
		jcomboboxGunsmith.setBounds(10, 10, 120, 25);
		jcomboboxGunsmith.addItem("--select--");
		for(int i = 0; i < gunsmith_list.size(); i++) {
			jcomboboxGunsmith.addItem(gunsmith_list.get(i));
		}
		contentPanel.add(jcomboboxGunsmith);
		
		JLabel jlabelNo = new JLabel("No");
		jlabelNo.setBounds(20, 45, 80, 25);
		contentPanel.add(jlabelNo);
		jtextNo = new JTextField("");
		jtextNo.setBounds(105, 45, 120, 25);
		jtextNo.setEditable(false);
		contentPanel.add(jtextNo);
		
		JLabel jlabelName = new JLabel("Gunsmith");
		jlabelName.setBounds(20, 80, 80, 25);
		contentPanel.add(jlabelName);
		jtextName = new JTextField("");
		jtextName.setBounds(105, 80, 120, 25);
		jtextName.setEditable(false);
		contentPanel.add(jtextName);
		
		JLabel jlabelSoldInTown = new JLabel("Sold in Town");//table of sold in town
		jlabelSoldInTown.setBounds(20, 170, 100, 25);
		contentPanel.add(jlabelSoldInTown);
		jcomboboxTown = new JComboBox<String>();
		jcomboboxTown.addItem("--select--");
		jcomboboxTown.setBounds(139, 170, 130, 25);
		for(int i = 0; i < town_list.size(); i++) {
			jcomboboxTown.addItem(town_list.get(i).getTown_name());
		}
		contentPanel.add(jcomboboxTown);
		DefaultTableModel tbmodelSoldInTown = new DefaultTableModel(null, new String[] {"Dates", "Locks", "Stocks", "Barrels", "Towns"});
		tableSoldInTown = new JTable(tbmodelSoldInTown);
		tableSoldInTown.setEnabled(false);
		JScrollPane scrollpaneSoldInTown = new JScrollPane(tableSoldInTown);
		contentPanel.add(scrollpaneSoldInTown);
		scrollpaneSoldInTown.setBounds(20, 200, 450, 200);
		
		JLabel jlabelSoldInSalesperson = new JLabel("Sold in Salesperson");//table of sold in town
		jlabelSoldInSalesperson.setBounds(500, 170, 150, 25);
		contentPanel.add(jlabelSoldInSalesperson);
		jcomboboxSalesperson = new JComboBox<String>();
		jcomboboxSalesperson.addItem("--select--");
//		for(int i = 0; i < sale_list.size(); i++) {
//			jcomboboxSalesperson.addItem("SP" + new DecimalFormat("000000").format(sale_list.get(i).getId()));
//		}
		jcomboboxSalesperson.setBounds(660, 170, 130, 25);
		contentPanel.add(jcomboboxSalesperson);
		DefaultTableModel tbmodelSoldInSalesperson = new DefaultTableModel(null, new String[] { "Dates", "Locks", "Stocks", "Barrels", "Salesperson"});
		tableSoldInSalesperson = new JTable(tbmodelSoldInSalesperson);
		tableSoldInTown.setEnabled(false);
		JScrollPane scrollpaneSoldInSalesperson = new JScrollPane(tableSoldInSalesperson);
		contentPanel.add(scrollpaneSoldInSalesperson);
		scrollpaneSoldInSalesperson.setBounds(500, 200, 450, 200);
		
		JLabel jlabelStatistics = new JLabel("Statistics");//table of sold in town
		jlabelStatistics.setBounds(20, 400, 100, 25);
		contentPanel.add(jlabelStatistics);
		DefaultTableModel tbmodelStatistics = new DefaultTableModel(null, new String[] { "Dates", "Locks", "Stocks", "Barrels", "Total Money"});
		tableStatistics = new JTable(tbmodelStatistics);
		tableStatistics.setEnabled(false);
		JScrollPane scrollpaneStatistics = new JScrollPane(tableStatistics);
		contentPanel.add(scrollpaneStatistics);
		scrollpaneStatistics.setBounds(20, 430, 450, 200);
		
		JLabel jlabelPerson = new JLabel("Commission Clear Request");
		jlabelPerson.setBounds(500, 400, 200, 25);
		contentPanel.add(jlabelPerson);
		jbuttonPerson = new JButton("Manage");
		jbuttonPerson.setBounds(700, 400, 100, 25);
		contentPanel.add(jbuttonPerson);
		DefaultTableModel tbmodelPerson = new DefaultTableModel(null, new String[] { "id", "name", "Gunsmith", "commision", "message"});
		tablePerson = new JTable(tbmodelPerson);
		tablePerson.setEnabled(false);
		JScrollPane scrollpanePerson = new JScrollPane(tablePerson);
		contentPanel.add(scrollpanePerson);
		scrollpanePerson.setBounds(500, 430, 450, 200);
		
		menuItemInformation.addActionListener(listen);
		menuItemTown.addActionListener(listen);
		menuItemGunsmithInformation.addActionListener(listen);
		menuItemGunsmithStatistics.addActionListener(listen);
		menuItemLogin.addActionListener(listen);
		jcomboboxGunsmith.addActionListener(listen);
		jcomboboxTown.addActionListener(listen);
		jcomboboxSalesperson.addActionListener(listen);
		jbuttonPerson.addActionListener(listen);
	}
	
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
					SalespersonTown_UI1 frame = new SalespersonTown_UI1();
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
			if (e.getSource() == jcomboboxGunsmith) {
				try {
					gunsmith = jcomboboxGunsmith.getSelectedItem().toString();
					int gid = Integer.parseInt(gunsmith.substring(8));
					jtextNo.setText("GS" + new DecimalFormat("000000").format(gid));
					jtextName.setText(gunsmith);
					
					idlist = new ArrayList<Integer>();
					idlist.clear();
//					jcomboboxSalesperson.removeAll();
//					jcomboboxSalesperson.addItem("--select--");
					sale_list = new SalespersonInformation_DBOption().doQueryByGunsmith(gunsmith);
					for(int i = 0; i < sale_list.size(); i++) {
						jcomboboxSalesperson.addItem("SP" + new DecimalFormat("000000").format(sale_list.get(i).getId()));
						idlist.add(sale_list.get(i).getId());
					}
					
					List<GunsmithSoldInTownStatistics> list = new GunsmithSoldInTownStatistics().getStatistics(gunsmith);
					Collections.sort(list,new Comparator<GunsmithSoldInTownStatistics>(){  
			            public int compare(GunsmithSoldInTownStatistics arg0, GunsmithSoldInTownStatistics arg1) {  
			                return arg0.getDate().compareTo(arg1.getDate());  
			            }  
			        });
					String[][] data = new String[list.size()][5];
					for(int i = 0; i < list.size(); i++) {
						data[i][0] = list.get(i).getDate();
						data[i][1] = list.get(i).getLocks()+"";
						data[i][2] = list.get(i).getStocks()+"";
						data[i][3] = list.get(i).getBarrels()+"";
						data[i][4] = list.get(i).getTotalmoney()+"";
					}
					TableModel tm = new DefaultTableModel(data, new String[] {"Dates", "Locks", "Stocks", "Barrels", "Total Money"});
					tableStatistics.setModel(tm);
					
					SalespersonInformation_DBOption saa = new SalespersonInformation_DBOption();
					List<SalespersonInformation_ER> saerlist = new ArrayList<SalespersonInformation_ER>();
					for(int i = 0; i < idlist.size(); i++) {
//						System.out.println(idlist.get(i));
						SalespersonInformation_ER saer = saa.doQueryById(idlist.get(i));
//						System.out.println(saer.toString());
						if(saer.getFlag() == -1) {
							saerlist.add(saer);
						}
					}
					String[][] data1 = new String[saerlist.size()][5];
					for(int i = 0; i < saerlist.size(); i++) {
//						System.out.println(saerlist.get(i).toString());
						data1[i][0] = saerlist.get(i).getId()+"";
						data1[i][1] = saerlist.get(i).getName();
						data1[i][2] = saerlist.get(i).getGunsmith();
						data1[i][3] = saerlist.get(i).getCommision()+"";
						data1[i][4] = saerlist.get(i).getFlag()+"";
					}
					TableModel tmperson = new DefaultTableModel(data1, new String[] { "id", "name", "Gunsmith", "commision", "message"});
					tablePerson.setModel(tmperson);
					
//					System.out.println(gunsmith);
					JChartBarStatistics jc1 = new JChartBarStatistics();
					jc1.setGunsmith(gunsmith);
					jc1.execute();
					
					JChartPieStatistics jc2 = new JChartPieStatistics();
					jc2.setGunsmith(gunsmith);
					jc2.execute();
					
					JChartLineStatistics jc3 = new JChartLineStatistics();
					jc3.setGunsmith(gunsmith);
					jc3.execute();
					
					JChartBarSalespersonStatistics jc4 = new JChartBarSalespersonStatistics();
					jc4.setSalelist(idlist);
					jc4.execute();
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource() == jcomboboxTown) {
				try {
					town = jcomboboxTown.getSelectedItem().toString();
					List<GunsmithSoldInTownStatistics> list = new GunsmithSoldInTownStatistics().getSoldStatistics(gunsmith, town);
					String[][] data = new String[list.size()][5];
					for(int i = 0; i < list.size(); i++) {
						data[i][0] = list.get(i).getDate();
						data[i][1] = list.get(i).getLocks()+"";
						data[i][2] = list.get(i).getStocks()+"";
						data[i][3] = list.get(i).getBarrels()+"";
						data[i][4] = list.get(i).getTown();
					}
					TableModel tm = new DefaultTableModel(data, new String[] {"Dates", "Locks", "Stocks", "Barrels", "Towns"});
					tableSoldInTown.setModel(tm);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource() == jcomboboxSalesperson) {
				try {
					pid = Integer.parseInt(jcomboboxSalesperson.getSelectedItem().toString().substring(2));
					List<SalespersonSoldStatistics> listSoldStatistics = new SalespersonSoldStatistics().getSoldStatistics(pid);
					String[][] dataSoldStatistics = new String[listSoldStatistics.size()][5];
					for(int i = 0; i < listSoldStatistics.size(); i++) {
						dataSoldStatistics[i][0] = listSoldStatistics.get(i).getDate();
						dataSoldStatistics[i][1] = listSoldStatistics.get(i).getLocks() + "";
						dataSoldStatistics[i][2] = listSoldStatistics.get(i).getStocks() + "";
						dataSoldStatistics[i][3] = listSoldStatistics.get(i).getBarrels() + "";
						dataSoldStatistics[i][4] = "GS" + new DecimalFormat("000000").format(listSoldStatistics.get(i).getId());
					}
					TableModel tmSoldStatistics = new DefaultTableModel(dataSoldStatistics, new String[] { "Dates", "Locks", "Stocks", "Barrels", "Salesperson"});
					tableSoldInSalesperson.setModel(tmSoldStatistics);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource() == jbuttonPerson) {
				JOptionPane.showMessageDialog(null, "Handle Success", "Tip!", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	};

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GunsmithInformation_UI frame = new GunsmithInformation_UI();
					frame.setTitle("Gunsmith Information");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public List<String> getGunsmith_list() {
		return gunsmith_list;
	}

	public void setGunsmith_list(List<String> gunsmith_list) {
		this.gunsmith_list = gunsmith_list;
	}

	public String getGunsmith() {
		return gunsmith;
	}

	public void setGunsmith(String gunsmith) {
		this.gunsmith = gunsmith;
	}

	public List<Integer> getIdlist() {
		return idlist;
	}

	public void setIdlist(List<Integer> idlist) {
		this.idlist = idlist;
	}
	
}
