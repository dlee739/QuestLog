

public class Residence {
	String country;
	String province;
	String city;
	String address;
	double costPerDay;
//	double size;
	int needNumOfVolunteers;
	int NumOfVolunteers=0;
	int residenceId;
	
	
	public Residence(String country, String province, String city, String address, double costPerDay,
//			double size,
			int needNumOfVolunteers, int NumOfVolunteers) {
		this.country = country;
		this.province = province;
		this.city = city;
		this.address = address;
		this.costPerDay = costPerDay;
//		this.size = size;
		this.needNumOfVolunteers = needNumOfVolunteers;
		this.NumOfVolunteers = NumOfVolunteers;
	}
	
	
}
