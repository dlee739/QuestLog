

class Volunteer {
	String name;
	String gender;
	int age;
//	int volunteerId;

	Volunteer(String name, String gender, int age) {
		this.name = name;
		this.gender = gender;
		this.age = age;
	}

	public Volunteer(Volunteer v) {
		this.age = v.age;
		this.gender = v.gender;
		this.name = v.name;
	}

	public static Volunteer[] remove(Volunteer[] volunteers, String name) {
		Volunteer[] newVolunteers = new Volunteer[volunteers.length - 1];
		for (int i = 0; i <= newVolunteers.length; i++) {
			if (volunteers[i].name.equals(name)) {
				break;
			} else {
				newVolunteers[i] = new Volunteer(volunteers[i]);
			}
			newVolunteers[i] = new Volunteer(volunteers[i + 1]);
		}
		return newVolunteers;
	}

	public static Volunteer[] add(Volunteer[] volunteers, String name, String gender, int age) {
		Volunteer[] newVolunteers = new Volunteer[volunteers.length + 1];
		for (int i = 0; volunteers.length < i; i++) {
			newVolunteers[i] = new Volunteer(volunteers[i]);
		}
		newVolunteers[volunteers.length] = new Volunteer(name, gender, age);
		return newVolunteers;
	}

}
