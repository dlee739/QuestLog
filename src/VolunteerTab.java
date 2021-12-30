
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VolunteerTab {										
	
	public static int WindowWidth = 1000;
	public static int WindowHeight = 300;
	public static int ButtonSize;
	private JButton editVolunteer;
	private JButton editResidence;
	private JButton back;
	public static JPanel mainPanel;
	public static CardLayout cardLayout;
	private VolunteerPanel volunteerPanel;
	private ResdiencePanel resdiencePanel;
	private AddVolunPanel addVolunPanel;
	private DeleteVolunPanel deleteVolunPanel;
	private AddRegPanel addRegPanel;
	private DeleteRegPanel deleteRegPanel;
	public static Volunteer[] volunteers =new Volunteer[0];
	public static Residence[] residences =new Residence[0];
	
	
	VolunteerTab(){
		JFrame frame = new JFrame("Volunteer Management");
        frame.setSize(WindowWidth,WindowHeight);
        frame.setLocationRelativeTo(null);        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        Container c = frame.getContentPane();
        editVolunteer = new JButton("Edit Volunteer");
        editResidence = new JButton("Edit Residence");
        back = new JButton("Back To Menu");
        JLabel empty = new JLabel();							// a empty space for keep the format
        
        JPanel jp = new JPanel(new GridLayout(4,1));
        JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		jp.add(p1); jp.add(p2); jp.add(p3); jp.add(p4);
		p1.add(empty); p2.add(editVolunteer); p3.add(editResidence);p4.add(back);
        
        cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);
		c.add(mainPanel, BorderLayout.CENTER);
		
		volunteerPanel = new VolunteerPanel();
		resdiencePanel = new ResdiencePanel();
		addVolunPanel = new AddVolunPanel();					//edit and add are the same panel
		deleteVolunPanel = new DeleteVolunPanel();
		addRegPanel = new AddRegPanel();						//same as volunteer panel
		deleteRegPanel = new DeleteRegPanel();	
		
		
		mainPanel.add(jp,"VolunOrResi");						//choose to edit volunteer or residence,	1 page	A
		mainPanel.add(volunteerPanel, "volunteerPanel");		//choose add,delete or edit volunteer,		2 page	B
		mainPanel.add(resdiencePanel, "resdiencePanel");		//choose add,delete or edit residence,		2 page	C
		mainPanel.add(addVolunPanel,"add_editVolunPanel");		//add or edit volunteer(detail),			3 page	D  //命名和功能不符，可选择改名或改进功能
		mainPanel.add(deleteVolunPanel,"deleteVolunPanel");		//delete volunteer(detail),					3 page	E
		mainPanel.add(addRegPanel,"add_editRegPanel");			//add or edit residence(detail),			3 page	F  //同上（无edit）
		mainPanel.add(deleteRegPanel,"deleteRegPanel");			//delete residence(detail),					3 page	G
		
        
        editVolunteer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cardLayout.show(mainPanel,"volunteerPanel");	//change to A
            }});
        
        editResidence.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cardLayout.show(mainPanel,"resdiencePanel");	//change to B
            }});
        
        back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				QuestLog.window.setVisible(true);
				
			}
		});
         

	}
	



}
