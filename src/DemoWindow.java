import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DemoWindow {

	private JFrame frame;
	private JTable table;
	private JTextField txtCreateANew;


	private JTextField textFieldItemName;
	private JTextField textFieldLocation;
	private JTextField textFieldQuantity;
	private JTextField textFieldPrice;
	DefaultTableModel model;
	JFrame frameNewItem, frameOrder;
	JPanel paneNewItem;
	JButton btnExistingType, btnNewType;
	JSpinner spinner_utilize;
	OrderWindow OW;
	EditWindow EW;
	ImagePanel paneImage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DemoWindow window = new DemoWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DemoWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 400, 461);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 784, 481);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(37, 27, 306, 202);
		panel_1.add(panel);
		
		JButton btnVolunteerTab = new JButton("Manage Volunteers");
		btnVolunteerTab.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		btnVolunteerTab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnVolunteerTab.setBounds(98, 252, 168, 41);
		panel_1.add(btnVolunteerTab);
		
		JButton btnTransportationTab = new JButton("Transportation");
		btnTransportationTab.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		btnTransportationTab.setBounds(98, 304, 168, 41);
		panel_1.add(btnTransportationTab);
		
		JButton btnInventory = new JButton("Inventory");
		btnInventory.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		btnInventory.setBounds(98, 356, 168, 41);
		panel_1.add(btnInventory);
		


		

	}
}
