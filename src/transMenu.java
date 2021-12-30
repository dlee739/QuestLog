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


public class transMenu {

	public static void main(String[] args) {

	JFrame frame = new JFrame("QuestLog");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(800, 400);
	
	JButton button = new JButton("Register a new Car");
	button.addActionListener(new ActionListener() { 
		  public void actionPerformed(ActionEvent e) { 
			  frame.setVisible(false);
			  transLog tlog = new transLog();
			  transLog.main(args);
		  }});
	JButton button1 = new JButton("Calculate gas needed");
	button1.addActionListener(new ActionListener() { 
		  public void actionPerformed(ActionEvent e) { 
			  frame.setVisible(false);
			  Calc clog1 = new Calc();
			  try {
				clog1.main(args);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		  }});
	JButton button2 = new JButton("Go Back");
	button2.addActionListener(new ActionListener() { 
		  public void actionPerformed(ActionEvent e) { 
			  frame.setVisible(false);
			  QuestLog.window.setVisible(true);
		  }});

	JPanel pan1 = new JPanel();
	pan1.setLayout(new BorderLayout());

	button.setSize(300, 30);
	button.setLocation(250, 70);
	
	button1.setSize(300, 30);
	button1.setLocation(250, 150);
	
	button2.setSize(300, 30);
	button2.setLocation(250, 230);
	
	JPanel pan3 = new JPanel();
	pan3.setLayout(new GridLayout(1,2));
	pan3.add(new JLabel(" QuestLog"));
	pan3.add(new JLabel(" Transportation"));
	
	pan1.add(pan3, BorderLayout.NORTH);
	frame.add(button);
	frame.add(button1);
	frame.add(button2);
	frame.add(pan1);
	frame.setVisible(true);
	
}
}
