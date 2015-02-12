package joejava.ibatis.bean;

import java.util.Date;

public class Round {
	
	private int roundId;
	private int courseId;
	private String username;
	private int score;
	private Date roundDate;
	private double differential;
	
	public int getRoundId() {
		return roundId;
	}
	public void setRoundId(int roundId) {
		this.roundId = roundId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Date getRoundDate() {
		return roundDate;
	}
	public void setRoundDate(Date roundDate) {
		this.roundDate = roundDate;
	}
	public double getDifferential() {
		return differential;
	}
	public void setDifferential(double differential) {
		this.differential = differential;
	}
}
