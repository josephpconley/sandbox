package joejava.ibatis.bean;

public class Team {
	private int teamId;
	private String teamCode;
	private String teamCity;
	private String teamNickname;
	private String division;
	
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public String getTeamCode() {
		return teamCode;
	}
	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}
	public String getTeamCity() {
		return teamCity;
	}
	public void setTeamCity(String teamCity) {
		this.teamCity = teamCity;
	}
	public String getTeamNickname() {
		return teamNickname;
	}
	public void setTeamNickname(String teamNickname) {
		this.teamNickname = teamNickname;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	
	
}
