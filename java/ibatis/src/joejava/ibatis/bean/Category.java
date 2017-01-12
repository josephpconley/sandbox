package joejava.ibatis.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import joejava.ibatis.DAO;

public class Category extends Schema {
	private Date date; 
	private String label;
	private ArrayList<Note> notes;
	
	public ArrayList<Note> getNotes() {
		return notes;
	}
	public void setNotes(ArrayList<Note> notes) {
		this.notes = notes;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Category(String category) throws SQLException{
		this.date=date;
		this.label = category;
		
		notes = null;
		try{
			notes = (ArrayList<Note>)DAO.client.queryForList("getNotesByCategory",category);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}	
}
