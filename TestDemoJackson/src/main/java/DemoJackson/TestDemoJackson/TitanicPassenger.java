package DemoJackson.TestDemoJackson;

public class TitanicPassenger {
	private String pclass;
	private String survived;
	private String name;
	private String sex;
	private float age;
	private String sibsp;
	private String parch;
	private String ticket;
	private float fare;
	private String cabin;
	private String embarked;
	private String boat;
	private String body;
	private String home_dest;
	
	public TitanicPassenger() {
		
	}
	

	public TitanicPassenger (String pclass, String survived, String name, String sex, float age, String sibsp, String parch, String ticket, float fare, String cabin, String embarked, String boat, String body, String dest) {
		this.pclass = pclass;
		this.survived = survived;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.sibsp = sibsp;
		this.parch = parch;
		this.ticket = ticket;
		this.fare = fare;
		this.cabin = cabin;
		this.embarked = embarked;
		this.boat = boat;
		this.body = body;
		this.home_dest = dest;
	}
	
	public String getPclass() {
		return pclass;
	}
	
	public String getSurvived() {
		return survived;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSex() {
		return sex;
	}
	
	public float getAge() {
		return age;
	}
	
	public String getSibsp() {
		return sibsp;
	}
	
	public String getParch() {
		return parch;
	}
	
	public String getTicket() {
		return ticket;
	}
	
	public float getFare() {
		return fare;
	}
	
	public String getCabin() {
		return cabin;
	}
	
	public String getEmbarked() {
		return embarked;
	}
	
	public String getBoat() {
		return boat;
	}
	
	public String getBody() {
		return body;
	}
	
	public String getHome_dest() {
		return home_dest;
	}
}
