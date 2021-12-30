

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class VolunteerGenerator  {
	
	public static Volunteer[] volunteers=VolunteerTab.volunteers;
	public static String filename = AddVolunPanel.filename ;
	
	public void vNameFile() {
		
	}
	
//	public void readFile() {
//		
//	}
	
	 
	 public static void save_RenewVolun(Volunteer[] volunteers) throws IOException {
		 PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
		 for (int i = 1;i < volunteers.length;i++) {
			 writer.println(volunteers[i].name+"\t"+volunteers[i].age+volunteers[i].gender);
		 }writer.close();
	 }
}
