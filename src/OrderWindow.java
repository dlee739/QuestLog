import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;

public class OrderWindow extends JFrame implements ActionListener {
	/********* CONSTANTS **********/
	static final Font basicFont = new Font("Tahoma", Font.PLAIN, 11); // basic font of the components and texts
																		// displayed

	/******** Swing Variables **********/
	JSpinner spinnerQuantity;
	JLabel lblItemTotalPrice;

	/******** Global Variables **********/
	int index_model;
	Item selectedItem;
	double total_price = -1;
	
	Balance default_balance = QuestLog.balance;

	public OrderWindow(int index_model, int index_list) {
		this.index_model = index_model;
		this.selectedItem = QuestLog.IT.ItemList.get(index_list); // fetch selected item for referencing
		initialize();
		setVisible(true);
	}

	void initialize() {
		setBounds(100, 100, 452, 217);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));

		/******* Set Up Pane for Ordering ********/
		JPanel paneOrder = new JPanel();
		getContentPane().add(paneOrder, BorderLayout.CENTER);
		paneOrder.setLayout(null);

		JLabel lblBalance = new JLabel("Balance: " + default_balance.getAmount());
		lblBalance.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBalance.setBounds(280, 15, 260, 20);
		paneOrder.add(lblBalance);

		JLabel lblMaO = new JLabel("Make an Order");
		lblMaO.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMaO.setBounds(10, 11, 141, 27);
		paneOrder.add(lblMaO);

		JLabel lblItem = new JLabel("Item");
		lblItem.setFont(basicFont);
		lblItem.setBounds(30, 49, 46, 14);
		paneOrder.add(lblItem);

		JSeparator separator = new JSeparator();
		separator.setBounds(20, 64, 388, 7);
		paneOrder.add(separator);

		JLabel lblquantity = new JLabel("Quantity");
		lblquantity.setFont(basicFont);
		lblquantity.setBounds(234, 49, 46, 14);
		paneOrder.add(lblquantity);

		JLabel lblprice = new JLabel("Price");
		lblprice.setFont(basicFont);
		lblprice.setBounds(362, 49, 46, 14);
		paneOrder.add(lblprice);

		spinnerQuantity = new JSpinner();
		spinnerQuantity.setBounds(228, 74, 56, 26);
		paneOrder.add(spinnerQuantity);
		
		// getting the item name
		JLabel lblItemName = new JLabel(selectedItem.getName());
		
		lblItemName.setBounds(30, 74, 188, 26);
		paneOrder.add(lblItemName);

		JLabel lblItemPrice = new JLabel(Balance.applyFormat(Double.parseDouble(selectedItem.getPrice())));
		lblItemPrice.setBounds(349, 74, 77, 26);
		paneOrder.add(lblItemPrice);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(20, 111, 388, 7);
		paneOrder.add(separator_1);

		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.setFont(basicFont);
		btnCalculate.setBounds(30, 125, 89, 31);
		paneOrder.add(btnCalculate);
		btnCalculate.addActionListener(this);

		JButton btnOrder = new JButton("Order");
		btnOrder.setFont(basicFont);
		btnOrder.setBounds(129, 125, 89, 31);
		paneOrder.add(btnOrder);
		btnOrder.addActionListener(this);

		lblItemTotalPrice = new JLabel("=");
		lblItemTotalPrice.setBounds(339, 127, 87, 26);
		paneOrder.add(lblItemTotalPrice);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		if (cmd.equals("Calculate")) {
			total_price = Double.parseDouble(selectedItem.getPrice()) * (int) spinnerQuantity.getValue();
			lblItemTotalPrice.setText(Balance.applyFormat(total_price));
		} else if (cmd.equals("Order")) {
			if (total_price == -1) { // no calculation has been made yet
				// infobox
			} else if (total_price > QuestLog.balance.getAmountNoFormat()){ // no money available
				// infobox
			} else {
				int finalQuantity = Integer.parseInt(selectedItem.getQuantity()) + (int) spinnerQuantity.getValue();
				selectedItem.setQuantity(Integer.toString(finalQuantity)); // list update
				QuestLog.balance.setAmount(QuestLog.balance.getAmountNoFormat() - total_price); // balance update
				QuestLog.IT.model.removeRow(index_model);// update table
				QuestLog.IT.model.addRow(selectedItem.toTableArray());
				QuestLog.IT.OW.dispose();
			}
		}

	}

}
