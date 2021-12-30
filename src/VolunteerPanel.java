

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class VolunteerPanel extends JPanel{
	
	public VolunteerPanel() {
		this.setLayout(new GridLayout(4,1));
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel(new FlowLayout());
		add(p1);add(p2);add(p3);add(p4);
		
		JButton add = new JButton("add a new volunteer");
		JButton delete = new JButton("delete a volunteer");
		JButton edit = new JButton("edit volunteer's information");
		JButton back = new JButton("back");
		p1.add(add); p2.add(delete); p3.add(edit); p4.add(back);
		
		add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	VolunteerTab.cardLayout.show(VolunteerTab.mainPanel,"add_editVolunPanel");
            }});
		
		delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	VolunteerTab.cardLayout.show(VolunteerTab.mainPanel,"deleteVolunPanel");
            }});
		
		edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	VolunteerTab.cardLayout.show(VolunteerTab.mainPanel,"add_editVolunPanel");
            }});
		
		back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	VolunteerTab.cardLayout.show(VolunteerTab.mainPanel,"VolunOrResi");
            }});
		
	}
}
