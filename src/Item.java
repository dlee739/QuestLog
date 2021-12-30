/**
***********************************************
@Author : Dan Lee
@Last_Modified: 2020-06-20
@Description: Inventory Tab of QuestLog
***********************************************
*/
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.swing.JTextArea;

public class Item {
	static int ATTRIBUTES_NUM = 9;

	private String ID;
	private String name;
	private String Category;
	private String Location;
	private String quantity;
	private String price;
	private String Expiry_date;
	private String description;
	private String ImageLink;
	
	boolean isNew;

	public Item(String itemname, String Category, String price, boolean isNew) {
		this.ID = UUID.randomUUID().toString().substring(0,7);
		this.name = itemname;
		this.Category = Category;
		this.Location = "-";
		this.price = price;
		this.quantity = "-";
		this.Expiry_date = "-";
		this.ImageLink = "-"; // must erase
		
		this.isNew = isNew;
		
	}
	
	public Item(boolean isNew) {
		this.isNew = isNew;
	}
	
	public String[] toArray() {
		return new String[] {ID, name, Category, Location, quantity, price, Expiry_date, description, ImageLink};
	}
	
	public String[] toTableArray() {
		return new String[] {ID, name, Category, Location, quantity, price, Expiry_date};
	}
	
	public boolean canExpire() {
		if (this.Category == "Food") {
			return true;
		}
		return false;
	}
	
	public void JTAreaToDesc(JTextArea jta) {
		description = jta.getText();
		description = description.replaceAll(System.getProperty("line.separator"), "\n");
	}
	
	public int countDash(String str) {
		int i = str.indexOf('/');
		if (i == -1) { 
			return 0;
		} else {
			return 1 + countDash(str.substring(i + 1));
		}
		
	}
	
	public Item clone() {
		Item newItem = new Item(false);
		newItem.setID(ID);
		newItem.setName(name);
		newItem.setCategory(Category);
		newItem.setLocation(Location);
		newItem.setQuantity(quantity);
		newItem.setPrice(price);
		newItem.setExpiry_date(Expiry_date);
		newItem.setDescription(description);
		newItem.setImageLink(ImageLink);
		newItem.isNew = isNew;
		return newItem;
	}
	
	// getters and setters
	public String getImageLink() {
		return ImageLink;
	}

	public void setImageLink(String imageLink) {
		ImageLink = imageLink;
	}
	
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getExpiry_date() {
		return Expiry_date;
	}

	public void setExpiry_date(String expiry_date) {
		Expiry_date = expiry_date;
	}

	public String getDescription() {
		String desc = description;
		desc = desc.replaceAll("\\\\n", System.getProperty("line.separator")); //https://stackoverflow.com/questions/19385884/converting-from-jtextarea-to-string-and-back
		return desc;
	}
	
	public String getDescription_file() {
		
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}