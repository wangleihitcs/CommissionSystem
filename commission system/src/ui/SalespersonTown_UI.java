package ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import databasemanager.SalespersonInformation_DBOption;
import statistics.TownsSoldStatistics;

public class SalespersonTown_UI extends JFrame {
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
	private JButton jbuttonSubmit;
	
	private List<String> gunsmith_list;
	private List<TownsSoldStatistics> townsold_list;
	private String gunsmith;
	
	public SalespersonTown_UI() {
		gunsmith_list = new SalespersonInformation_DBOption().doQueryGunsmith();
		townsold_list = new TownsSoldStatistics().getTownsSold();
				
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
		
		JLabel jlabelSoldInTown = new JLabel("Sold in Town");// table of sold in
																// town
		jlabelSoldInTown.setBounds(20, 10, 100, 25);
		contentPanel.add(jlabelSoldInTown);
		
		String[][] dataSoldInTown = new String[townsold_list.size()][4];
		for( int i = 0; i < townsold_list.size(); i++) {
			dataSoldInTown[i][0] = townsold_list.get(i).getTown_name();
			dataSoldInTown[i][1] = townsold_list.get(i).getLocks() + "";
			dataSoldInTown[i][2] = townsold_list.get(i).getStocks() + "";
			dataSoldInTown[i][3] = townsold_list.get(i).getBarrels() + "";
		}
		DefaultTableModel tbmodelSoldInTown = new DefaultTableModel(dataSoldInTown,
				new String[] { "Towns", "Locks", "Stocks", "Barrels" });
		JTable tableSoldInTown = new JTable(tbmodelSoldInTown);
		tableSoldInTown.setEnabled(false);
		JScrollPane scrollpaneSoldInTown = new JScrollPane(tableSoldInTown);
		contentPanel.add(scrollpaneSoldInTown);
		scrollpaneSoldInTown.setBounds(20, 40, 400, 200);
		
		jcomboboxGunsmith = new JComboBox<String>();
		jcomboboxGunsmith.setBounds(15, 260, 120, 25);
		jcomboboxGunsmith.addItem("--select--");
		for(int i = 0; i < gunsmith_list.size(); i++) {
			jcomboboxGunsmith.addItem(gunsmith_list.get(i));
		}
		contentPanel.add(jcomboboxGunsmith);

		jbuttonSubmit = new JButton("Submit");
		jbuttonSubmit.setBounds(140, 260, 80, 25);
		contentPanel.add(jbuttonSubmit);

		menuItemInformation.addActionListener(listen);
		menuItemTown.addActionListener(listen);
		menuItemGunsmithInformation.addActionListener(listen);
		menuItemGunsmithStatistics.addActionListener(listen);
		menuItemLogin.addActionListener(listen);
		jcomboboxGunsmith.addActionListener(listen);
		jbuttonSubmit.addActionListener(listen);
		gunsmith.length();
	}

	ActionListener listen = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == menuItemInformation) {
				try {
					SalespersonInformation_UI frame = new SalespersonInformation_UI(1);
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
					JOptionPane.showMessageDialog(null, "No Permission!", "Tip!", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource() == menuItemGunsmithStatistics) {
				try {
					JOptionPane.showMessageDialog(null, "No Permission!", "Tip!", JOptionPane.INFORMATION_MESSAGE);
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
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	};

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalespersonTown_UI frame = new SalespersonTown_UI();
					frame.setTitle("Comission System");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
