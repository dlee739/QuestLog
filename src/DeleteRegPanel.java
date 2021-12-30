

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

public class DeleteRegPanel extends JPanel {
	public Volunteer[] volunteers = VolunteerTab.volunteers;
	public static String filename = "Volunteer.txt";

	public DeleteRegPanel() {
		this.setLayout(new GridLayout(5, 1));
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();
		add(p1);
		add(p2);
		add(p3);
		add(p4);
		add(p5);

		JLabel name = new JLabel("Name:");
		JLabel gender = new JLabel("Gender:");
		JLabel age = new JLabel("Age:");
		JLabel residenceId = new JLabel("Residence ID:");
		JTextField answer1 = new JTextField(25);
		JTextField answer2 = new JTextField(25);
		JTextField answer3 = new JTextField(25);
		JTextField answer4 = new JTextField(25);
		JButton submit = new JButton("submit");
		p1.add(name, BorderLayout.WEST);
		p2.add(gender, BorderLayout.WEST);
		p3.add(age, BorderLayout.WEST);
		p4.add(residenceId, BorderLayout.WEST);
		p1.add(answer1, BorderLayout.EAST);
		p2.add(answer2, BorderLayout.EAST);
		p3.add(answer3, BorderLayout.EAST);
		p4.add(answer4, BorderLayout.EAST);
		p5.add(submit, new FlowLayout());

		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tryAgain_Reg listener2 = new tryAgain_Reg();
				submit.addActionListener(listener2);

				File fileVolun = new File(filename);
				try {
					fileVolun.createNewFile();
					PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
					writer.println("Volunteer's form" + "\n" + "Name \tAge \tGender");
					BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
					if (in.ready() == true) {
						for (int i = 0; i < volunteers.length; i++) {
							writer.println(volunteers[i].name + "\t" + volunteers[i].age + "\t"+volunteers[i].gender);
						}
					}
					writer.close();
					in.close();
					VolunteerTab.cardLayout.show(VolunteerTab.mainPanel, "VolunOrResi");
				} catch (IOException e1) {
					System.out.println(e1);
					e1.printStackTrace();
				}

			}

		});

	}
}

//class tryAgain_Reg implements ActionListener {
//	public void actionPerformed(ActionEvent e) {
//		JOptionPane.showMessageDialog(null, "Fill in all the information to ensure that there are no empty box.",
//				"Try Again", JOptionPane.INFORMATION_MESSAGE);
//	}
//}
