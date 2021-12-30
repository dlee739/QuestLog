import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class transLog {

public static void main(String[] args) {
	JFrame frame = new JFrame();
	frame.setBounds(100, 100, 800, 600);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	
	JLabel lblNewLabel_2 = new JLabel("Transportation");
	lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 20));
	lblNewLabel_2.setBounds(339, 32, 170, 60);
	frame.getContentPane().add(lblNewLabel_2);
	
	JLabel lblNewLabel = new JLabel("QuestLog");
	lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
	lblNewLabel.setBounds(10, 11, 80, 22);
	frame.getContentPane().add(lblNewLabel);
	
	JButton btnNewButton_2 = new JButton("Go back");
	btnNewButton_2.setBounds(634, 420, 89, 23);
	btnNewButton_2.addActionListener(new ActionListener() { 
		  public void actionPerformed(ActionEvent e) { 
			  
			  frame.setVisible(false);
			  transMenu tmen = new transMenu();
			  tmen.main(args);
			  
		  }});
	frame.getContentPane().add(btnNewButton_2);
	
	JLabel lblNewLabel_1 = new JLabel(" Enter the car's type and model: ");
	lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblNewLabel_1.setBounds(48, 175, 240, 30);
	frame.getContentPane().add(lblNewLabel_1);
	
	JLabel label = new JLabel(" Enter the car's tank size: ");
	label.setFont(new Font("Tahoma", Font.PLAIN, 15));
	label.setBounds(48, 233, 200, 30);
	frame.getContentPane().add(label);
	
	JLabel label_1 = new JLabel(" Enter the car's fuel economy per 100km: ");
	label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
	label_1.setBounds(48, 288, 296, 30);
	frame.getContentPane().add(label_1);
	
	JTextField textField = new JTextField();
	textField.setBounds(269, 179, 240, 23);
	frame.getContentPane().add(textField);
	textField.setColumns(10);
	
	JTextField textField_1 = new JTextField();
	textField_1.setColumns(10);
	textField_1.setBounds(240, 239, 240, 23);
	frame.getContentPane().add(textField_1);
	
	JTextField textField_2 = new JTextField();
	textField_2.setColumns(10);
	textField_2.setBounds(339, 288, 240, 23);
	frame.getContentPane().add(textField_2);
	
	JButton btnNewButton_1 = new JButton("Register");
	btnNewButton_1.setBounds(48, 420, 225, 23);
	btnNewButton_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			try {
				//code from https://www.roseindia.net/java/javafile/How-to-Write-to-a-File-in-Java-without-overwriting.shtml
				FileWriter fstream = new FileWriter("cars.txt",true);
				BufferedWriter writer = new BufferedWriter(fstream);
				writer.write(textField.getText() + "\n");
				writer.write(textField_1.getText() + "\n");
				writer.write(textField_2.getText() + "\n");
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				JOptionPane.showMessageDialog(frame, "Car registered!");
				writer.close();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
		}});
	frame.getContentPane().add(btnNewButton_1);
	
	frame.setVisible(true);
}

}

