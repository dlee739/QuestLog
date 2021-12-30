import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.UUID;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class QuestLog extends JFrame implements ActionListener {
	/********* CONSTANTS **********/
	static final Font basicFont = new Font("Tahoma", Font.PLAIN, 11); // basic font of the components and texts displayed
	static final int wHeight = 200, wWidth = 330; // window height and window width, respectively.
	static final int compHeight = 30; // regular components' (button, text fields, labels, etc) height

	/******** Swing Variables **********/
	JButton btnVolunteerTab, btnTransportationTab, btnInventory;
	public static InventoryTab IT;
	public static QuestLog window;
	public static JPanel paneMainMenu;
	
	/******** Global Variables **********/
	public static Balance balance;

	public static void main(String[] args) {
		window = new QuestLog(args); // launch application

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
	        public void run() {
	            if (window.getContentPane() == IT) {
	            	IT.saveEverything();
	            }
	        }
	    }, "Shutdown-thread"));
	}

	/*
	 * Constructor
	 */
	public QuestLog(String[] args) {
		readBalance();
		initialize(args);
		setVisible(true);
	}

	void initialize(String[] args) {
		/******* Set Up for Window Frame ********/
		new JFrame();
		setTitle("QuestLog");
		setBounds(100, 100, 400, 461);
		setResizable(false); // window is automatically resized later on, but is not resized by user
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/******* Set Up Pane for The Main Menu ********/
		paneMainMenu = new JPanel();
		paneMainMenu.setLayout(null); // Absolute Layout
		
		ImagePanel image = new ImagePanel("QuestLog.jpg");
		image.setBounds(37, 27, 306, 202);
		paneMainMenu.add(image);
		
		btnVolunteerTab = new JButton("Manage Volunteers");
		btnVolunteerTab.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		btnVolunteerTab.setBounds(98, 252, 168, 41);
		paneMainMenu.add(btnVolunteerTab);
		btnVolunteerTab.addActionListener(this);
		
		btnTransportationTab = new JButton("Transportation");
		btnTransportationTab.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		btnTransportationTab.setBounds(98, 304, 168, 41);
		paneMainMenu.add(btnTransportationTab);
		btnTransportationTab.addActionListener((new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  	setVisible(false);
					transMenu tmen = new transMenu();
					tmen.main(args);
			  }}));
		
		btnInventory = new JButton("Inventory");
		btnInventory.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		btnInventory.setBounds(98, 356, 168, 41);
		paneMainMenu.add(btnInventory);
		btnInventory.addActionListener(this);

		add(paneMainMenu);
	}

	/*
	 * Default method for Action events from implementation of ActionListener
	 */
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		if (cmd.equals("Manage Volunteers")) {
			setVisible(false);
			new VolunteerTab();
		} else if (cmd.equals("Inventory")) {
			IT = new InventoryTab();
			setTitle("QuestLog - Manage Inventory");
			setBounds(100, 100, 800, 520);
			remove(getContentPane());
			setContentPane(IT);
			validate();
		}
	}
	
	public void readBalance() {
		String Owner = null;
		String amount = null;
		
		try {
			BufferedReader in = new BufferedReader(new FileReader("balance.txt"));
			Owner = in.readLine();
			amount = in.readLine();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		balance = new Balance(Owner, Double.parseDouble(amount));
		
		// read balance
	}
}