import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AddItemTab extends JPanel implements ActionListener{
	/********* CONSTANTS **********/
	static final Font basicFont = new Font("Tahoma", Font.PLAIN, 11); // basic font of the components and texts displayed

	/******** Swing Variables **********/
	JTextField txtMM, txtYYYY, txtSelectTheItem;
	JSpinner spinnerQuantityAdd;
	JLabel lblExpDateInv, label, lblQuantityInv;
	JComboBox comboBoxItemType;
	JButton btnAddItem;

	/******** Global Variables **********/
	String DEFAULT_LOCATION = "Kampala";
	ArrayList<Item> ItemTypes;

	public AddItemTab(ArrayList<Item> ItemTypes) {
		this.ItemTypes = ItemTypes;
		initialize();
	}

	void initialize() {
		// frame.setBounds(100, 100, 323, 228);
		new JPanel();
		setLayout(null);

		txtSelectTheItem = new JTextField();
		txtSelectTheItem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSelectTheItem.setText("Select the Item Type That is Being Added:");
		txtSelectTheItem.setEditable(false);
		txtSelectTheItem.setBounds(10, 11, 288, 26);
		add(txtSelectTheItem);
		txtSelectTheItem.setColumns(10);

		lblExpDateInv = new JLabel("Expiry Date: ");
		lblExpDateInv.setBounds(15, 115, 69, 19);
		add(lblExpDateInv);
		lblExpDateInv.setFont(basicFont);

		txtMM = new JTextField();
		txtMM.setText("MM");
		txtMM.setBounds(83, 115, 25, 20);
		add(txtMM);
		txtMM.setColumns(2);

		label = new JLabel("/");
		label.setBounds(118, 117, 11, 17);
		add(label);

		txtYYYY = new JTextField();
		txtYYYY.setHorizontalAlignment(SwingConstants.LEFT);
		txtYYYY.setText("YYYY");
		txtYYYY.setColumns(4);
		txtYYYY.setBounds(130, 115, 40, 20);
		add(txtYYYY);

		comboBoxItemType = new JComboBox();
		// JComboBox for Existing Item Types
		for (int i = 0; i < ItemTypes.size(); i++) {
			comboBoxItemType.addItem(ItemTypes.get(i).getName());
		}
				comboBoxItemType.addItemListener(new ItemListener() { // Expiry Date is only enabled for food entries.
					public void itemStateChanged(ItemEvent e) {
						if (e.getStateChange() == ItemEvent.SELECTED) {
							int i = findIndexOf((String) e.getItem(), ItemTypes);
							if (ItemTypes.get(i).getCategory().equalsIgnoreCase("Food")) {
								txtMM.setEnabled(true);
								txtYYYY.setEnabled(true);
							} else {
								txtMM.setEnabled(false);
								txtYYYY.setEnabled(false);
							}
						}
					}
				});
				comboBoxItemType.setBounds(10, 48, 288, 26);
				add(comboBoxItemType);

				lblQuantityInv = new JLabel("Quantity: ");
				lblQuantityInv.setBounds(15, 85, 55, 19);
				add(lblQuantityInv);
				lblQuantityInv.setFont(basicFont);

				spinnerQuantityAdd = new JSpinner();
				spinnerQuantityAdd.setBounds(83, 84, 63, 20);
				spinnerQuantityAdd.setValue(1);
				add(spinnerQuantityAdd);

				btnAddItem = new JButton("Add Item");
				btnAddItem.setFont(new Font("Tahoma", Font.PLAIN, 14));
				btnAddItem.setBounds(10, 145, 288, 33);
				add(btnAddItem);
				btnAddItem.addActionListener(this);

			}

			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();

				if (cmd.equals("Add Item")) {
					int index = findIndexOf((String) comboBoxItemType.getSelectedItem(), ItemTypes);
					Item item = ItemTypes.get(index).clone();

					if ((int) spinnerQuantityAdd.getValue() < 0) { // spinner has invalid value
						InventoryTab.infoBox("Spinner value invalid", "QuestLog-Error");
						return;
					}
					if (item.getCategory().equalsIgnoreCase("Food")) {
				if (txtMM.getText().isEmpty() || txtYYYY.getText().isEmpty() || !isInteger(txtMM.getText()) || !isInteger(txtYYYY.getText())) { // expiry field is invalid
					InventoryTab.infoBox("Please fill in all the parts appropriately", "QuestLog-Error");
					return;
				} else {
					item.setExpiry_date(txtYYYY.getText() + "-" + txtMM.getText());
				}
			} else {
				item.setExpiry_date("-");
			}

			item.setLocation(DEFAULT_LOCATION);
			item.setQuantity(Integer.toString((int) spinnerQuantityAdd.getValue()));
			QuestLog.IT.ItemList.add(item);
			QuestLog.IT.model.addRow(item.toTableArray());
			QuestLog.IT.frameNewItem.dispose();
		}
	}

	public int findIndexOf(String itemname, ArrayList<Item> ItemList) {
		for (int i = 0; i < ItemList.size(); i++) {
			if (ItemList.get(i).getName().equalsIgnoreCase(itemname)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Method Name: isInteger
	 * 
	 * @Author Dan Lee
	 * @Modified May 02, 2020
	 * @Description Method to check if the given string can be parsed into integer
	 *              value.
	 * @Parameters String str: string to check
	 * @Returns boolean; returns true if the string can be parsed into integer
	 **/
	public boolean isInteger(String str) {
		try { // try/catch clause to check for Number format exception
			Integer.parseInt(str); // parse string to integer
		} catch (NumberFormatException ne) {
			return false; // returns false if the exception occurred
		}
		return true; // string can be parsed into integer
	}
}
