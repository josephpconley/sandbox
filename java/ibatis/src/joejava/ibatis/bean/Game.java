package joejava.ibatis.bean;

import java.util.Date;

public class Game {
	private Integer gameId;
	private Date gameDate;
	private String awayTeam;
	private String homeTeam;
	private int awayScore;
	private int homeScore;
	private int q1Away; 
	private int q2Away; 
	private int q3Away; 
	private int q4Away; 
	private int otAway;
	private int q1Home;
	private int q2Home;
	private int q3Home;
	private int q4Home;
	private int otHome;
	
	public Integer getGameId() {
		return gameId;
	}
	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}
	public Date getGameDate() {
		return gameDate;
	}
	public void setGameDate(Date gameDate) {
		this.gameDate = gameDate;
	}
	public String getAwayTeam() {
		return awayTeam;
	}
	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}
	public String getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}
	public int getAwayScore() {
		return awayScore;
	}
	public void setAwayScore(int awayScore) {
		this.awayScore = awayScore;
	}
	public int getHomeScore() {
		return homeScore;
	}
	public void setHomeScore(int homeScore) {
		this.homeScore = homeScore;
	}
	public int getQ1Away() {
		return q1Away;
	}
	public void setQ1Away(int away) {
		q1Away = away;
	}
	public int getQ2Away() {
		return q2Away;
	}
	public void setQ2Away(int away) {
		q2Away = away;
	}
	public int getQ3Away() {
		return q3Away;
	}
	public void setQ3Away(int away) {
		q3Away = away;
	}
	public int getQ4Away() {
		return q4Away;
	}
	public void setQ4Away(int away) {
		q4Away = away;
	}
	public int getOtAway() {
		return otAway;
	}
	public void setOtAway(int otAway) {
		this.otAway = otAway;
	}
	public int getQ1Home() {
		return q1Home;
	}
	public void setQ1Home(int home) {
		q1Home = home;
	}
	public int getQ2Home() {
		return q2Home;
	}
	public void setQ2Home(int home) {
		q2Home = home;
	}
	public int getQ3Home() {
		return q3Home;
	}
	public void setQ3Home(int home) {
		q3Home = home;
	}
	public int getQ4Home() {
		return q4Home;
	}
	public void setQ4Home(int home) {
		q4Home = home;
	}
	public int getOtHome() {
		return otHome;
	}
	public void setOtHome(int otHome) {
		this.otHome = otHome;
	}
	
}
