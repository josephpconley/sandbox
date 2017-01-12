package joejava.ibatis.bean;

public class TeamStats {
	private int gameId;
	private String teamCode;
	private int firstDowns;
	private int fumblesLost;
	private int penalties;
	private int penaltyYds;
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public String getTeamCode() {
		return teamCode;
	}
	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}
	public int getFirstDowns() {
		return firstDowns;
	}
	public void setFirstDowns(int firstDowns) {
		this.firstDowns = firstDowns;
	}
	public int getFumblesLost() {
		return fumblesLost;
	}
	public void setFumblesLost(int fumblesLost) {
		this.fumblesLost = fumblesLost;
	}
	public int getPenalties() {
		return penalties;
	}
	public void setPenalties(int penalties) {
		this.penalties = penalties;
	}
	public int getPenaltyYds() {
		return penaltyYds;
	}
	public void setPenaltyYds(int penaltyYds) {
		this.penaltyYds = penaltyYds;
	}
}
