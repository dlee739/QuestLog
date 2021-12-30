/**
***********************************************
@Author : Dan Lee
@Last_Modified: 2020-06-20
@Description: Inventory Tab of QuestLog
***********************************************
*/

import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class InventoryTab extends JPanel implements ActionListener, MouseListener {
	/********* CONSTANTS **********/
	static final Font basicFont = new Font("Tahoma", Font.PLAIN, 11); // basic font of the components and texts displayed

	/******** Swing Variables **********/
	JTextField textItemName;
	JTextArea textAreaItemDesc;
	JTable InventoryList;
	DefaultTableModel model; // table where the inventory is shown
	JFrame frameNewItem, frameOrder;
	JPanel paneNewItem;
	JButton btnPlaceOrder, btnUtilize, btnNewItem, btnEditItem, btnDeleteItem, btnBackToMainMenu;
	JButton btnExistingType, btnNewType;
	JSeparator separator_1, separator_2; // separator for design purposes only
	JScrollPane scrollPaneItemDesc;
	JSpinner spinner_utilize;
	OrderWindow OW; // additional window for ordering stocks
	EditWindow EW; // additional window for editing fields.
	ImagePanel paneImage;
	

	/******** Global Variables **********/
	String[] header = { "ID", "Item", "Category", "Location", "Quantity", "Price", "Expiry Date" }; // header for inventory table
	String filename;
	String DEFAULT_LOCATION = "Kampala";
	String[][] tableData;
	ArrayList<Item> ItemTypes, ItemList;

	public InventoryTab() {
		readData();
		initialize();
	}
	
	void initialize() {
		new JPanel();
		setLayout(null);

		textItemName = new JTextField();
		textItemName.setText("Name of Item");
		textItemName.setEditable(false);
		textItemName.setBounds(10, 11, 160, 20);
		add(textItemName);
		textItemName.setColumns(10);
		
		paneImage = new ImagePanel("ItemDefault.jpg");
		paneImage.setBounds(10, 42, 160, 120);
		add(paneImage);

		btnPlaceOrder = new JButton("Place Order");
		btnPlaceOrder.setBounds(10, 354, 160, 50);
		add(btnPlaceOrder);
		btnPlaceOrder.addActionListener(this);

		btnUtilize = new JButton("Utilize");
		btnUtilize.setBounds(10, 415, 160, 50);
		add(btnUtilize);
		btnUtilize.addActionListener(this);

		textAreaItemDesc = new JTextArea();
		textAreaItemDesc.setText("Item Description");
		textAreaItemDesc.setEditable(false);

		scrollPaneItemDesc = new JScrollPane(textAreaItemDesc);
		scrollPaneItemDesc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneItemDesc.setBounds(10, 173, 160, 170);
		add(scrollPaneItemDesc);

		separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.GRAY);
		separator_1.setBackground(Color.LIGHT_GRAY);
		separator_1.setBounds(180, 11, 2, 459);
		add(separator_1);

		separator_2 = new JSeparator();
		separator_2.setForeground(Color.GRAY);
		separator_2.setBackground(Color.LIGHT_GRAY);
		separator_2.setBounds(190, 50, 584, 50);
		add(separator_2);

		btnNewItem = new JButton("New Item");
		btnNewItem.setFont(basicFont);
		btnNewItem.setBounds(192, 10, 80, 29);
		add(btnNewItem);
		btnNewItem.addActionListener(this);

		btnEditItem = new JButton("Edit");
		btnEditItem.setFont(basicFont);
		btnEditItem.setBounds(282, 10, 80, 29);
		add(btnEditItem);
		btnEditItem.addActionListener(this);

		btnDeleteItem = new JButton("Delete");
		btnDeleteItem.setFont(basicFont);
		btnDeleteItem.setBounds(372, 10, 80, 29);
		add(btnDeleteItem);
		btnDeleteItem.addActionListener(this);

		btnBackToMainMenu = new JButton("Back");
		btnBackToMainMenu.setFont(basicFont);
		btnBackToMainMenu.setBounds(712, 10, 60, 29);
		add(btnBackToMainMenu);
		btnBackToMainMenu.addActionListener(this);
		
		spinner_utilize = new JSpinner();
		spinner_utilize.setBounds(175, 425, 57, 29);
		spinner_utilize.setVisible(false);
		add(spinner_utilize);
		
		tableData = new String[ItemList.size()][Item.ATTRIBUTES_NUM];
		tableData = arrayListToArray(ItemList, tableData);
		model = new DefaultTableModel(tableData, header);

		InventoryList = new JTable(model);
		InventoryList.addMouseListener(new MouseAdapter() { // any row of Inventory is selected
			public void mouseClicked(MouseEvent e) {
				int index = findIndexOf(InventoryList.getValueAt(InventoryList.getSelectedRow(), 0).toString(), ItemList);
				textItemName.setText(ItemList.get(index).getName());// update item name
				textAreaItemDesc.setText(ItemList.get(index).getDescription());// update description
				if (ItemList.get(index).isNew) {
					paneImage.setImage("ImageNew.jpg"); // update image
				} else {
					paneImage.setImage(ItemList.get(index).getImageLink()); // update image
				}
				
				paneImage.repaint();
				
			}
		});

		JScrollPane scrollPaneItemList = new JScrollPane(InventoryList);
		scrollPaneItemList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneItemList.setBounds(192, 64, 582, 401);
		add(scrollPaneItemList);

		/***** pane for add item (Inventory) *****/
		// frame.setBounds(100, 100, 260, 238);
		paneNewItem = new JPanel();
		paneNewItem.setLayout(null);

		btnExistingType = new JButton("Add From Existing Item Type");
		btnExistingType.setBounds(10, 11, 224, 83);
		paneNewItem.add(btnExistingType);
		btnExistingType.addActionListener(this);

		btnNewType = new JButton("Create a New Item Type");
		btnNewType.setBounds(10, 105, 224, 83);
		paneNewItem.add(btnNewType);
		btnNewType.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		int index_model = -1;
		int index_list = -1;
		int quantity;
		if (!InventoryList.getSelectionModel().isSelectionEmpty()) {
		index_model = InventoryList.getSelectedRow();
		index_list = findIndexOf(InventoryList.getValueAt(index_model, 0).toString(), ItemList);
		} 
		
		if (cmd.equals("New Item")) {
			frameNewItem = new JFrame();
			frameNewItem.setResizable(false); // window is automatically resized later on, but is not resized by user
			frameNewItem.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frameNewItem.setTitle("QuestLog - New Item");
			frameNewItem.setBounds(100, 100, 260, 238);
			frameNewItem.remove(frameNewItem.getContentPane());
			frameNewItem.add(paneNewItem);
			frameNewItem.setVisible(true);
			frameNewItem.validate();
			return;
		} else if (cmd.equals("Back")) {
			saveEverything();// save current progress to the database
			QuestLog.window.setTitle("QuestLog - Main Menu");
			QuestLog.window.remove(QuestLog.window.getContentPane());
			QuestLog.window.setContentPane(QuestLog.paneMainMenu);
			QuestLog.window.setBounds(100, 100, 400, 461);
			QuestLog.window.validate();
			return;
		
		} else if (cmd.equals("Edit") && !InventoryList.getSelectionModel().isSelectionEmpty()) {
			EW = new EditWindow(index_model, index_list);
			return;
		} else if (cmd.equals("Delete") && !InventoryList.getSelectionModel().isSelectionEmpty()) {
			model.removeRow(index_model);
			ItemList.remove(index_list);
			return;
		} else if (cmd.equals("Place Order") && !InventoryList.getSelectionModel().isSelectionEmpty()) {
			OW = new OrderWindow(index_model, index_list);
			return;
		} else if (cmd.equals("Utilize") && !InventoryList.getSelectionModel().isSelectionEmpty()) {
			if (spinner_utilize.isVisible()) { // spinner is visible
				int spinnerValue = (int) spinner_utilize.getValue();
				spinner_utilize.setVisible(false);
				if (spinnerValue >= 0) { // valid spinner value
					quantity = Integer.parseInt(ItemList.get(index_list).getQuantity());
					if (quantity >= spinnerValue) { // enough quantity in stock
						ItemList.get(index_list).setQuantity(Integer.toString((quantity - spinnerValue)));
						InventoryList.setValueAt(ItemList.get(index_list).getQuantity(), index_model, 4);
					}
				}
			} else { // spinner is hidden
				spinner_utilize.setValue(0);
				spinner_utilize.setVisible(true);
			}
			return;
		}
		
		/***** New Item Pane *****/
		else if (cmd.equals("Add From Existing Item Type")) {
			AddItemTab w = new AddItemTab(ItemTypes);
			frameNewItem.setBounds(100, 100, 323, 228);
			frameNewItem.remove(paneNewItem);
			frameNewItem.add(w);
			frameNewItem.validate();
			return;
		} else if (cmd.equals("Create a New Item Type")) {
			CreateItemTypeTab w = new CreateItemTypeTab();
			frameNewItem.setBounds(100, 100, 280, 310);
			frameNewItem.remove(paneNewItem);
			frameNewItem.add(w);
			frameNewItem.validate();
			return;
		} 
	}

	public void readData() {
		ItemTypes = new ArrayList<Item>();
		ItemList = new ArrayList<Item>();

		/***** Read Existing Item Types *****/
		ItemTypes = readItemFile("ExistingItemTypes.txt", ItemTypes);

		/***** Read Item List *****/
		ItemList = readItemFile("ItemList.txt", ItemList);
		
		/*************/
	}

	// method to save current progress to file
	public void saveEverything() {
		writeTextFile(ItemList, "ItemList.txt");// save itemlist
		writeTextFile(ItemTypes, "ExistingItemTypes.txt");// save itemtypes
		writeTextFile(QuestLog.balance, "balance.txt");// save balance
	}
	
	public void writeTextFile(ArrayList<Item> list, String filename) {

		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(filename));
			for (int i = 0; i < list.size(); i++) { // write item data
				out.write(list.get(i).getID());out.newLine();
				out.write(list.get(i).getName());out.newLine();
				out.write(list.get(i).getCategory());out.newLine();
				out.write(list.get(i).getLocation());out.newLine();
				out.write(list.get(i).getQuantity());out.newLine();
				out.write(list.get(i).getPrice());out.newLine();
				out.write(list.get(i).getExpiry_date());out.newLine();
				out.write(list.get(i).getDescription_file());out.newLine();
				out.write(list.get(i).getImageLink());out.newLine();		
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeTextFile(Balance balance, String filename) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(filename));
			out.write(balance.getOwner());out.newLine();
			out.write(Double.toString(balance.getAmountNoFormat()));
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	// method to read file
	public ArrayList<Item> readItemFile(String filename, ArrayList<Item> list) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
			while (in.ready()) {
				Item item = new Item(false);
				item.setID(in.readLine());
				item.setName(in.readLine());
				item.setCategory(in.readLine());
				item.setLocation(in.readLine());
				item.setQuantity(in.readLine());
				item.setPrice(in.readLine());
				item.setExpiry_date(in.readLine());
				item.setDescription(in.readLine());
				item.setImageLink(in.readLine());
				list.add(item);
			}
			in.close();
		} catch (IOException iox) {
			// iox
			return null;
		}
		return list;
	}

	public String[][] arrayListToArray(ArrayList<Item> list, String[][] array) {
		for (int i = 0; i < list.size(); i++) {
			array[i] = list.get(i).toArray();
		}
		return array;
	}
	
	public int findIndexOf(String ID, ArrayList<Item> ItemList) {
		for (int i = 0; i < ItemList.size(); i++) {
			if (ItemList.get(i).getID().equals(ID)) {
				return i;
			}
		}
		return -1;
	}
	
	/** Method Name: infoBox
	* @Author https://stackoverflow.com/questions/7080205/popup-message-boxes
	* @Modified May 02, 2020
	* @Description Method to display pop up error messages
	* @Parameters String infoMessage: Message to display, String titleBar: title bar of the pop up window
	* @Returns void; N/A
	**/
	public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "Error: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}





}
