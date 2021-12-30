/**
***********************************************
@Author : Dan Lee
@Last_Modified: 2020-06-20
@Description: Inventory Tab of QuestLog
***********************************************
*/

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CreateItemTypeTab extends JPanel implements ActionListener {
	/********* CONSTANTS **********/
	static final Font basicFont = new Font("Tahoma", Font.PLAIN, 11); // basic font of the components and texts
																		// displayed

	/******** Swing Variables **********/
	JTextField txtCreateANew, textFieldItemName, textFieldPrice, textFieldImageLink;
	JTextArea txtrFillDesc;
	JLabel lblItemName, lblItemCategory, lblPrice;
	JComboBox comboBoxItemCategory;
	JButton btnCreate, btnUploadImage;

	/******** Global Variables **********/
	String[] itemCategory = { "Food", "Clothing", "Medical Supply", "Fuel", "Other" };
	File file;
	BufferedImage image;

	public CreateItemTypeTab() {
		initialize();
	}

	void initialize() {
		new JPanel();
		setLayout(null);

		txtCreateANew = new JTextField();
		txtCreateANew.setEditable(false);
		txtCreateANew.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtCreateANew.setText("Create a New Item Type");
		txtCreateANew.setBounds(10, 11, 258, 29);
		add(txtCreateANew);
		txtCreateANew.setColumns(10);

		lblItemName = new JLabel("Item Name: ");
		lblItemName.setBounds(10, 51, 65, 16);
		add(lblItemName);
		lblItemName.setFont(basicFont);

		textFieldItemName = new JTextField();
		textFieldItemName.setBounds(72, 48, 196, 22);
		add(textFieldItemName);
		textFieldItemName.setColumns(10);

		lblItemCategory = new JLabel("Item Category:");
		lblItemCategory.setBounds(10, 81, 86, 16);
		add(lblItemCategory);
		lblItemCategory.setFont(basicFont);

		comboBoxItemCategory = new JComboBox(itemCategory);
		comboBoxItemCategory.setBounds(95, 78, 173, 22);
		add(comboBoxItemCategory);

		lblPrice = new JLabel("Price:  $");
		lblPrice.setBounds(10, 111, 52, 16);
		add(lblPrice);
		lblPrice.setFont(basicFont);

		textFieldPrice = new JTextField();
		textFieldPrice.setBounds(62, 108, 128, 22);
		add(textFieldPrice);
		textFieldPrice.setColumns(10);

		btnCreate = new JButton("Create");
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCreate.setBounds(10, 244, 258, 29);
		add(btnCreate);
		btnCreate.addActionListener(this);

		textFieldImageLink = new JTextField();
		textFieldImageLink.setEditable(false);
		textFieldImageLink.setBounds(119, 139, 149, 20);
		add(textFieldImageLink);
		textFieldImageLink.setColumns(10);

		btnUploadImage = new JButton("Upload Image");
		btnUploadImage.setBounds(10, 138, 105, 23);
		btnUploadImage.setFont(basicFont);
		add(btnUploadImage);
		btnUploadImage.addActionListener(this);

		txtrFillDesc = new JTextArea();
		txtrFillDesc.setText("Fill in Description");
		txtrFillDesc.setBounds(10, 172, 258, 61);
		add(txtrFillDesc);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		if (cmd.equals("Upload Image")) {
			file = null;
			try {
				file = new UploadImageFile().getFile();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if (file != null) {
				textFieldImageLink.setText(file.getAbsolutePath());
			} else {
				return;
			}

		} else if (cmd.equals("Create")) {
			if (textFieldItemName.getText().isEmpty() || textFieldPrice.getText().isEmpty()) {
				InventoryTab.infoBox("Please fill in all the parts", "Error");
				return;
			} 
			Item newItemType = new Item(textFieldItemName.getText(), (String) comboBoxItemCategory.getSelectedItem(), textFieldPrice.getText(), true);
			newItemType.JTAreaToDesc(txtrFillDesc);
			if (file == null) { // use default image
				newItemType.setImageLink("ItemDefault.jpg");
			} else {
				newItemType.setImageLink(newItemType.getID() + ".jpg");// copy and rename the image file
				try {
					image = fileToImage(file);
					saveToFile(newItemType, image);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			QuestLog.IT.ItemTypes.add(newItemType);// link it to the existing item types
			QuestLog.IT.frameNewItem.dispose();
		}
	}
	
	public BufferedImage fileToImage(File file) throws IOException{//https://stackoverflow.com/questions/10391778/create-a-bufferedimage-from-file-and-make-it-type-int-argb
		BufferedImage image;
		BufferedImage in;
		in = ImageIO.read(file);
		image = new BufferedImage(160, 120, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g = image.createGraphics();
		g.drawImage(in, 0, 0, 160, 120, null);
		g.dispose();

		return image;
	}
	
	public void saveToFile(Item item, BufferedImage img) throws FileNotFoundException, IOException {
		File outputfile = new File(item.getID() + ".jpg");
		ImageIO.write(img, "png", outputfile);
	}
	


}
