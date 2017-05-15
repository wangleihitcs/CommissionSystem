package ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import databasemanager.Login_DBOption;
import entity.Login_ER;

public class Commsion_UI extends JFrame {
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
	
	private JTextField jtextfieldUsername;
	private JTextField jtextfieldPassword;
	
	private JButton jbuttonSale;
	private JButton jbuttonGunsmith;
	
	private int id;

	public Commsion_UI() {
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
		
		JLabel jlabelUsername = new JLabel("Username");
		jlabelUsername.setBounds(300, 150, 100, 25);
		contentPanel.add(jlabelUsername);
		jtextfieldUsername = new JTextField();
		jtextfieldUsername.setBounds(400, 150, 200, 25);
		contentPanel.add(jtextfieldUsername);
		
		JLabel jlabelPassword = new JLabel("Password");
		jlabelPassword.setBounds(300, 200, 100, 25);
		contentPanel.add(jlabelPassword);
		jtextfieldPassword = new JTextField();
		jtextfieldPassword.setBounds(400, 200, 200, 25);
		contentPanel.add(jtextfieldPassword);
		
		jbuttonSale = new JButton("SaleLogin");
		jbuttonSale.setBounds(330, 250, 120, 25);
		contentPanel.add(jbuttonSale);
		
		jbuttonGunsmith = new JButton("GunsmLogin");
		jbuttonGunsmith.setBounds(450, 250, 120, 25);
		contentPanel.add(jbuttonGunsmith);

		menuItemInformation.addActionListener(listen);
		menuItemTown.addActionListener(listen);
		menuItemGunsmithInformation.addActionListener(listen);
		menuItemGunsmithStatistics.addActionListener(listen);
		menuItemLogin.addActionListener(listen);
		jbuttonSale.addActionListener(listen);
		jbuttonGunsmith.addActionListener(listen);
	}

	ActionListener listen = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == menuItemInformation) {
				try {
					JOptionPane.showMessageDialog(null, "Please Login!", "Tip!", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource() == menuItemTown) {
				try {
					JOptionPane.showMessageDialog(null, "Please Login!", "Tip!", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource() == menuItemGunsmithInformation) {
				try {
					JOptionPane.showMessageDialog(null, "Please Login!", "Tip!", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource() == menuItemGunsmithStatistics) {
				try {
					JOptionPane.showMessageDialog(null, "Please Login!", "Tip!", JOptionPane.INFORMATION_MESSAGE);
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
			if(e.getSource() == jbuttonSale) {
				try {
					String user = jtextfieldUsername.getText();
					String pass = jtextfieldPassword.getText();
					
					Login_DBOption login = new Login_DBOption();
					Login_ER login_er = login.doQuery(user, pass, 1);
					
					if(login_er.getId() == 0) {
						JOptionPane.showMessageDialog(null, "Username or Password Wrong!", "Tip!", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						id = login_er.getId();
						SalespersonInformation_UI frame = new SalespersonInformation_UI(id);
						frame.setTitle("Salesperson Information");
						frame.setVisible(true);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			if(e.getSource() == jbuttonGunsmith) {
				try {
					String user = jtextfieldUsername.getText();
					String pass = jtextfieldPassword.getText();
					
					Login_DBOption login = new Login_DBOption();
					Login_ER login_er = login.doQuery(user, pass, 2);
					
					if(login_er.getId() == 0) {
						JOptionPane.showMessageDialog(null, "Username or Password Wrong!", "Tip!", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						id = login_er.getId();
						GunsmithInformation_UI frame = new GunsmithInformation_UI();
						frame.setTitle("Gunsmith Information");
						frame.setVisible(true);
					}
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
					Commsion_UI frame = new Commsion_UI();
					frame.setTitle("Comission System");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
