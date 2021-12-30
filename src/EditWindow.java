
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class EditWindow extends JFrame implements ActionListener {
	/********* CONSTANTS **********/
	static final Font basicFont = new Font("Tahoma", Font.PLAIN, 11); // basic font of the components and texts
																		// displayed

	/******** Swing Variables **********/
	JTextField textFieldItemName, textFieldLocation, textFieldQuantity, textFieldPrice;
	JTextArea textAreaDescription;

	/******** Global Variables **********/
	int index_model;
	Item selectedItem;

	public EditWindow(int index_model, int index_list) {
		this.index_model = index_model;
		this.selectedItem = QuestLog.IT.ItemList.get(index_list); // fetch selected item for referencing
		initialize();
		setVisible(true);
	}

	void initialize() {
		new JFrame();
		setBounds(100, 100, 243, 362);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		/******* Set Up Pane for Editing ********/
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblEditInfo = new JLabel("Edit Item Information");
		lblEditInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEditInfo.setBounds(20, 11, 180, 37);
		panel.add(lblEditInfo);
		
		JLabel lblItemName = new JLabel("Item Name: ");
		lblItemName.setFont(basicFont);
		lblItemName.setBounds(20, 59, 71, 14);
		panel.add(lblItemName);
		
		textFieldItemName = new JTextField();
		textFieldItemName.setText(selectedItem.getName());
		textFieldItemName.setBounds(87, 56, 113, 20);
		panel.add(textFieldItemName);
		textFieldItemName.setColumns(10);
		
		JLabel lblLocation = new JLabel("Location:");
		lblLocation.setFont(basicFont);
		lblLocation.setBounds(20, 87, 71, 14);
		panel.add(lblLocation);
		
		textFieldLocation = new JTextField();
		textFieldLocation.setText(selectedItem.getLocation());
		textFieldLocation.setColumns(10);
		textFieldLocation.setBounds(87, 84, 113, 20);
		panel.add(textFieldLocation);
		
		JLabel lblQuantity = new JLabel("Quantity: ");
		lblQuantity.setFont(basicFont);
		lblQuantity.setBounds(20, 115, 71, 14);
		panel.add(lblQuantity);
		
		textFieldQuantity = new JTextField();
		textFieldQuantity.setText(selectedItem.getQuantity());
		textFieldQuantity.setColumns(10);
		textFieldQuantity.setBounds(87, 112, 113, 20);
		panel.add(textFieldQuantity);
		
		JLabel lblPrice = new JLabel("Price: ");
		lblPrice.setFont(basicFont);
		lblPrice.setBounds(20, 143, 71, 14);
		panel.add(lblPrice);
		
		textFieldPrice = new JTextField();
		textFieldPrice.setText(selectedItem.getPrice());
		textFieldPrice.setColumns(10);
		textFieldPrice.setBounds(87, 140, 113, 20);
		panel.add(textFieldPrice);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(basicFont);
		lblDescription.setBounds(20, 168, 71, 14);
		panel.add(lblDescription);
		
		textAreaDescription = new JTextArea(selectedItem.getDescription());
		textAreaDescription.setText(selectedItem.getDescription());
		
		JScrollPane scrollPaneItemList = new JScrollPane(textAreaDescription);
		scrollPaneItemList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneItemList.setBounds(20, 193, 180, 67);
		panel.add(scrollPaneItemList);
		
		JButton btnSaveChanges = new JButton("Save Changes");
		btnSaveChanges.setFont(basicFont);
		btnSaveChanges.setBounds(20, 271, 180, 41);
		panel.add(btnSaveChanges);
		btnSaveChanges.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		if (cmd.equals("Save Changes")) {
			selectedItem.JTAreaToDesc(textAreaDescription);
			
			if (!textFieldItemName.getText().isEmpty() && !textFieldLocation.getText().isEmpty() && !textFieldQuantity.getText().isEmpty() && !textFieldPrice.getText().isEmpty()) {
				selectedItem.setName(textFieldItemName.getText());
				selectedItem.setLocation(textFieldLocation.getText());
				selectedItem.setQuantity(textFieldQuantity.getText());
				selectedItem.setPrice(textFieldPrice.getText());
				selectedItem.JTAreaToDesc(textAreaDescription);
				
				QuestLog.IT.model.removeRow(index_model);// update table
				QuestLog.IT.model.addRow(selectedItem.toTableArray());
				QuestLog.IT.EW.dispose();
			} else {
				return;
			}

		}
	}
}
