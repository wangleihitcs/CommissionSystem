package ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GunsmithStatistics_UI extends JFrame {
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

	private JLabel jlabel1;
	private JLabel jlabel2;
	private JLabel jlabel3;
	private JLabel jlabel4;

	public GunsmithStatistics_UI() {
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

		String path = System.getProperty("user.dir");
		
		JLabel jlabelImage1 = new JLabel("Three Month Recently Sold");
		jlabelImage1.setBounds(10, 10, 200, 25);
		contentPanel.add(jlabelImage1);
		try {
			ImageIcon icon1 = new ImageIcon(ImageIO.read(new File(path + "/bar.jpeg")));
			jlabel1 = new JLabel("");
			jlabel1.setIcon(icon1);
			jlabel1.setBounds(10, 35, 480, 275);
		} catch (Exception e1) {
			e1.getStackTrace();
		}
		contentPanel.add(jlabel1);

		JLabel jlabelImage2 = new JLabel("Recently Month Sold");
		jlabelImage2.setBounds(500, 10, 200, 25);
		contentPanel.add(jlabelImage2);
		try {
			ImageIcon icon2 = new ImageIcon(ImageIO.read(new File(path + "/pie.jpeg")));
			jlabel2 = new JLabel("");
			jlabel2.setIcon(icon2);
			jlabel2.setBounds(500, 35, 480, 275);
		} catch (Exception e2) {
			e2.getStackTrace();
		}
		contentPanel.add(jlabel2);

		JLabel jlabelImage3 = new JLabel("Five Month Recently Sold");
		jlabelImage3.setBounds(10, 350, 200, 25);
		contentPanel.add(jlabelImage3);
		try {
			ImageIcon icon3 = new ImageIcon(ImageIO.read(new File(path + "/line.jpeg")));
			jlabel3 = new JLabel("");
			jlabel3.setIcon(icon3);
			jlabel3.setBounds(10, 375, 480, 275);
		} catch (Exception e3) {
			e3.getStackTrace();
		}
		contentPanel.add(jlabel3);

		JLabel jlabelImage4 = new JLabel("Salesperson Total Num");
		jlabelImage4.setBounds(500, 350, 200, 25);
		contentPanel.add(jlabelImage4);
		try {
			ImageIcon icon4 = new ImageIcon(ImageIO.read(new File(path + "/salespersonbar.jpeg")));
			jlabel4 = new JLabel("");
			jlabel4.setIcon(icon4);
			jlabel4.setBounds(500, 375, 480, 275);
			contentPanel.add(jlabel4);
		} catch (Exception e4) {
			e4.printStackTrace();
		}

		menuItemInformation.addActionListener(listen);
		menuItemTown.addActionListener(listen);
		menuItemGunsmithInformation.addActionListener(listen);
		menuItemGunsmithStatistics.addActionListener(listen);
		menuItemLogin.addActionListener(listen);
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
		}
	};

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GunsmithStatistics_UI frame = new GunsmithStatistics_UI();
					frame.setTitle("Gunsmith Statistics");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public void setContentPanel(JPanel contentPanel) {
		this.contentPanel = contentPanel;
	}

	public JLabel getJlabel1() {
		return jlabel1;
	}

	public void setJlabel1(JLabel jlabel1) {
		this.jlabel1 = jlabel1;
	}

	public JLabel getJlabel2() {
		return jlabel2;
	}

	public void setJlabel2(JLabel jlabel2) {
		this.jlabel2 = jlabel2;
	}

	public JLabel getJlabel3() {
		return jlabel3;
	}

	public void setJlabel3(JLabel jlabel3) {
		this.jlabel3 = jlabel3;
	}

	public JLabel getJlabel4() {
		return jlabel4;
	}

	public void setJlabel4(JLabel jlabel4) {
		this.jlabel4 = jlabel4;
	}

}
