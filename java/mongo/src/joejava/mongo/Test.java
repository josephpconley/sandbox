package joejava.mongo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class Test {

	/**
	 * @param args
	 * @throws MongoException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws MongoException, FileNotFoundException, IOException {
		Mongo m = new Mongo();
		DB db = m.getDB("golf");
		
		DBCollection picks = db.getCollection("picks");
		
		DBCollection golfers = db.getCollection("golfers");
		DBCollection pros = db.getCollection("pros");
		DBCollection tournaments = db.getCollection("tournaments");
		
		BasicDBObject query = new BasicDBObject();
		query.put("name", "Joe Conley");
		
		BasicDBObject joe = (BasicDBObject) golfers.findOne(query);
		System.out.println(joe.toMap().toString());
		
		List<String> p = IOUtils.readLines(new FileReader("picks.csv"));
		int i = 1;
		for(String str : p){
			BasicDBObject g = new BasicDBObject();
			String[] s = str.split(",");
			g.put("_id", i);
			g.put("position", s[0]);
			g.put("startPosition", s[1]);
			g.put("thru", s[2]);
			g.put("total", getInt(s[3]));
			g.put("today", getInt(s[4]));
			g.put("totalScore", getInt(s[5]));
			g.put("round1", getInt(s[6]));
			g.put("round2", getInt(s[7]));
			g.put("round3", getInt(s[8]));
			g.put("round4", getInt(s[9]));
			g.put("timeUpdated", new Date());
			

			BasicDBObject pro = (BasicDBObject)pros.findOne(new BasicDBObject("name", s[11]));
			g.put("pro", pro);
			
			System.out.println(pro.toMap());
			
			BasicDBObject t = (BasicDBObject)tournaments.findOne(new BasicDBObject("name", s[12]));
			g.put("tournament", t);
			
			if(s.length == 14){
				BasicDBObject golfer = (BasicDBObject)golfers.findOne(new BasicDBObject("name", s[13]));
				g.put("golfer", golfer);	
			}
			
			picks.save(g);
			i++;
		}

		System.out.println(picks.count() + " picks inserted");
	}
	
	public static Integer getInt(String s){
		if(s != null && s.length() > 0){
			return Integer.valueOf(s);
		}
		
		return null;
	}
}
