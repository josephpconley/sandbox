package joejava.ibatis.bean;

public class Course {
	private int courseId;
	private int addressId;
	private String courseName;
	private int par;
	private double rating;
	private int slope;
	private String tees;
	
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public int getSlope() {
		return slope;
	}
	public void setSlope(int slope) {
		this.slope = slope;
	}
	public int getPar() {
		return par;
	}
	public void setPar(int par) {
		this.par = par;
	}
	public String getTees() {
		return tees;
	}
	public void setTees(String tees) {
		this.tees = tees;
	}
	
}
