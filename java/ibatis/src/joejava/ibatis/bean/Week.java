package joejava.ibatis.bean;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import joejava.ibatis.DAO;

public class Week extends Schema {
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
	
	public Week(Date date) throws SQLException{
		this.date=date;
		
		DateFormat dFormat = new SimpleDateFormat("EEEE, MMMM d");
		this.label = dFormat.format(date);
		
		notes = (ArrayList<Note>)DAO.client.queryForList("getNotesByDay",date);
		
		
		//Handle birthday notes
		HashMap<String,Integer> params = new HashMap<String,Integer>();
		params.put("month",date.getMonth()+1);
		params.put("day", date.getDate());
		
		ArrayList<Note> bdays = (ArrayList<Note>)DAO.client.queryForList("getBirthdays",params);
		if(bdays != null){
			notes.addAll(bdays);
		}
	}
}
