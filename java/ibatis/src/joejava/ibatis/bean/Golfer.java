package joejava.ibatis.bean;

import java.util.Date;

public class Golfer {
	private double handicap;
	private String name;
	private String username;
	private String password;
	private String courseName;
	private Date bday;
	private String emailAddress;
	
	public double getHandicap() {
		return handicap;
	}
	public void setHandicap(double handicap) {
		this.handicap = handicap;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Date getBday() {
		return bday;
	}
	public void setBday(Date bday) {
		this.bday = bday;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
