

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddRegPanel extends JPanel {

	public Residence[] residences = VolunteerTab.residences;
	public static String filename = "Residence.txt";

	public AddRegPanel() {
		this.setLayout(new GridLayout(4, 2));
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();
		JPanel p6 = new JPanel();
		JPanel p7 = new JPanel();
		JPanel p8 = new JPanel();
		add(p1);
		add(p2);
		add(p3);
		add(p4);
		add(p5);
		add(p6);
		add(p7);
		add(p8);

		JLabel country = new JLabel("Country:");
		JLabel province = new JLabel("Province:");
		JLabel city = new JLabel("City:");
		JLabel address = new JLabel("Address:");
		JLabel costPerDay = new JLabel("Cost Per Day:");
		JLabel NumOfVolunteers = new JLabel("Volunteers(have):");
		JLabel needNumOfVolunteers = new JLabel("Volunteers(need)");
		JTextField answer1 = new JTextField(25);
		JTextField answer2 = new JTextField(25);
		JTextField answer3 = new JTextField(25);
		JTextField answer4 = new JTextField(25);
		JTextField answer5 = new JTextField(25);
		JTextField answer6 = new JTextField(25);
		JTextField answer7 = new JTextField(25);
		JButton submit = new JButton("submit");
		p1.add(country, BorderLayout.WEST);
		p2.add(province, BorderLayout.WEST);
		p3.add(city, BorderLayout.WEST);
		p4.add(address, BorderLayout.WEST);
		p5.add(costPerDay, BorderLayout.WEST);
		p6.add(NumOfVolunteers, BorderLayout.WEST);
		p7.add(needNumOfVolunteers, BorderLayout.WEST);

		p1.add(answer1, BorderLayout.EAST);
		p2.add(answer2, BorderLayout.EAST);
		p3.add(answer3, BorderLayout.EAST);
		p4.add(answer4, BorderLayout.EAST);
		p5.add(answer5, BorderLayout.EAST);
		p6.add(answer6, BorderLayout.EAST);
		p7.add(answer7, BorderLayout.EAST);
		p8.add(submit, new FlowLayout());

		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				File fileVolun = new File(filename);
				try {
					fileVolun.createNewFile();
					PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
					writer.println("Residence Form\nCountry\t\tProvince\tCity\tAddress\t\tCost Per Day\tVolunteers(want)\tVolunteers(need)");
					BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
					if (in.ready() == true) {
						for (int i = 1; i < residences.length; i++) {
							writer.println(residences[i].country+"\t\t"+residences[i].province+"\t\t"+residences[i].city+"\t"+residences[i].address+"\t\t"+residences[i].costPerDay+"\t"+residences[i].needNumOfVolunteers+"\t"+residences[i].NumOfVolunteers);
						}
					}
					writer.close();
					in.close();
					VolunteerTab.cardLayout.show(VolunteerTab.mainPanel, "VolunOrResi");
				} catch (IOException e1) {
					explainListenerClass listener2 = new explainListenerClass();
					submit.addActionListener(listener2);
					e1.printStackTrace();
				}

			}

		});

	}
}

class explainListenerClass implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "Fill in all the information to ensure that there are no empty box.",
				"Try Again", JOptionPane.INFORMATION_MESSAGE);
	}
}
