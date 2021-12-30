import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Calc {
	
	public static int getRecs (String fileName) {
		int i=0;	
		String car;
		try{
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            while(in.ready()==true){                       
					car = in.readLine();
					i++;
				}	// End loop
			in.close();
		} catch (IOException e) {};	// End try-catch statement.
		return i;
	}
	
	public static int selectedIndex (String search, String[] array) {
		
		for(int i = 0; i < array.length; i++) {
			
			if(array[i].equals(search)) {
				return i;
			}
			
		}
		
		return 999;
	}
	

	public static void main(String[] args) throws IOException {
		
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//Image img = new ImageIcon(this.getClass().getResource("logo.png")).getImage();
		
		JLabel lblNewLabel_1 = new JLabel("Transportation");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_1.setBounds(339, 32, 170, 60);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Select Car:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(48, 111, 150, 40);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Car's type and model: ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(48, 148, 170, 40);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Car's tank size: ");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(48, 179, 150, 40);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Car's fuel economy per 100km: ");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(48, 213, 225, 40);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Distance to be covered in meters: ");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_6.setBounds(48, 244, 268, 40);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Total weight being transported in kg: ");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_7.setBounds(48, 275, 268, 40);
		frame.getContentPane().add(lblNewLabel_7);
		
		int recs = getRecs("cars.txt");
		
		String[] cars = new String[recs];
		
		int i2=0;	
		
		try{
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("cars.txt")));
            while(in.ready()==true){                       
					cars[i2] = in.readLine();
					i2++;
				}	// End loop
			in.close();
		} catch (IOException e) {};
		
		
		JComboBox comboBox = new JComboBox();
		
		int qw = 0;
		
		while (qw < recs) {
			comboBox.addItem(cars[qw]);
			qw+=3;
		}
		
		
		comboBox.setBounds(137, 122, 279, 22);
		frame.getContentPane().add(comboBox);
		
		
		JButton btnNewButton = new JButton("Calculate Distance");
		btnNewButton.setBounds(291, 255, 153, 23);
		btnNewButton.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  
				  Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
				    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
				        try {
				            desktop.browse(new URI("https://www.mapdevelopers.com/distance_from_to.php"));
				        } catch (Exception e1) {
				            e1.printStackTrace();
				        }
				    }
				  
			  }});
		frame.getContentPane().add(btnNewButton);
		
		JTextField textField = new JTextField();
		textField.setBounds(460, 256, 66, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("meters");
		lblNewLabel_8.setBounds(536, 256, 56, 20);
		frame.getContentPane().add(lblNewLabel_8);
		
		JTextField textField_1 = new JTextField();
		textField_1.setBounds(294, 287, 80, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("kilograms");
		lblNewLabel_9.setBounds(378, 289, 66, 17);
		frame.getContentPane().add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setBounds(201, 163, 49, 14);
		frame.getContentPane().add(lblNewLabel_10);
		
		JTextField textField_2 = new JTextField();
		textField_2.setBounds(200, 160, 174, 20);
		textField_2.setEditable(false);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JTextField textField_3 = new JTextField();
		textField_3.setBounds(154, 191, 49, 20);
		textField_3.setEditable(false);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("liters");
		lblNewLabel_11.setBounds(208, 194, 49, 14);
		frame.getContentPane().add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("QuestLog");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_12.setBounds(10, 11, 80, 22);
		frame.getContentPane().add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("Gas needed:");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_13.setBounds(48, 387, 106, 22);
		frame.getContentPane().add(lblNewLabel_13);
		
		JTextField textField_4 = new JTextField();
		textField_4.setBounds(141, 390, 49, 20);
		textField_4.setEditable(false);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JLabel label = new JLabel("liters");
		label.setBounds(193, 393, 56, 14);
		frame.getContentPane().add(label);
		
		JTextField textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(261, 225, 38, 20);
		frame.getContentPane().add(textField_5);
		
		JButton btnNewButton_3 = new JButton("Fill in car details");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String selected = comboBox.getSelectedItem().toString();
				
				int indexOfSelected = selectedIndex(selected, cars);
				
				textField_2.setText(cars[indexOfSelected]);
				textField_3.setText(cars[indexOfSelected+1]);
				textField_5.setText(cars[indexOfSelected+2]);
				
			}
		});
		btnNewButton_3.setBounds(426, 122, 166, 23);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_1 = new JButton("Calculate Gas needed");
		btnNewButton_1.setBounds(48, 420, 225, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int tank = Integer.parseInt(textField_3.getText());
				int fuel = Integer.parseInt(textField_5.getText());
				int dist = Integer.parseInt(textField.getText());
				float weig = Float.parseFloat(textField_1.getText());
				double gasNeeded = ((fuel + (weig * 0.006)) * dist)/100000;
			    textField_4.setText(Double.toString(gasNeeded + 2));
				
			}});
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblLiterskm = new JLabel("liters/100km");
		lblLiterskm.setBounds(309, 228, 74, 14);
		frame.getContentPane().add(lblLiterskm);
		
		JButton btnNewButton_2 = new JButton("Go back");
		btnNewButton_2.setBounds(634, 420, 89, 23);
		btnNewButton_2.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  
				  frame.setVisible(false);
				  transMenu tmen = new transMenu();
				  tmen.main(args);
				  
			  }});
		frame.getContentPane().add(btnNewButton_2);
		frame.setVisible(true);
	}
}
