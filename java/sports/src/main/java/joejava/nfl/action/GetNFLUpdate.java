package joejava.nfl.action;

import java.net.URL;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

//import joejava.ibatis.bean.Game;
//import joejava.ibatis.bean.Team;
//import joejava.ibatis.DAO;
//import joejava.util.EmailUtility;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class GetNFLUpdate {
//
//	public static void main (String[] args) {
//		getLiveUpdate();
//	}
//
//	public static void getLiveUpdate(){
//		try{
//			URL feed = new URL("http://www.nfl.com//liveupdate/scorestrip/ss.xml");
//
//			Document dom;
//			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//			DocumentBuilder db = dbf.newDocumentBuilder();
//
//			//parse using builder to get DOM representation of the XML file
//			dom = db.parse(feed.toString());
//
//			//get the root element
//			Element root = dom.getDocumentElement();
//
//			//get a nodelist of  elements
//			NodeList nl = (NodeList) root.getElementsByTagName("g");
//
//			//Update live table
//			System.out.println("Looking for updates...");
//			String body = "";
//
//			for(int i = 0 ; i < nl.getLength();i++) {
//				Element el = (Element)nl.item(i);
//				Game game = new Game();
//				Integer gameId = Integer.valueOf(el.getAttribute("eid"));
//				String awayTeam = el.getAttribute("v");
//				String homeTeam = el.getAttribute("h");
//				//Team awayTeam = (Team)DAO.client.queryForObject("getTeamByCode",el.getAttribute("v"));
//				//Team homeTeam = (Team)DAO.client.queryForObject("getTeamByCode",el.getAttribute("h"));
//				Integer awayScore = Integer.valueOf(el.getAttribute("vs"));
//				Integer homeScore = Integer.valueOf(el.getAttribute("hs"));
//				//System.out.println(awayTeam + " " + awayScore + " - " + homeTeam + " " + homeScore);
//
//				try{
//					DAO.client.startTransaction();
//
//					//Set bean
//					game.setGameId(gameId);
//					game.setAwayScore(awayScore);
//					game.setAwayTeam(awayTeam);
//					game.setHomeScore(homeScore);
//					game.setHomeTeam(homeTeam);
//
//					//Insert game in live table if new
//					int gameExists = (Integer)DAO.client.queryForObject("liveGameCheck",gameId);
//					if(gameExists == 0){
//						DAO.client.insert("insertLiveGame",game);
//					}else{
//						//Determine if this score is different than previous report
//						Integer newTotal = awayScore + homeScore;
//						Integer oldTotal = (Integer)DAO.client.queryForObject("getPreviousScore",gameId);
//
//						if(newTotal - oldTotal != 0){
//							System.out.println("Updating");
//							DAO.client.update("updateLiveGame",game);	//Trigger on db will send email
//
//							body += game.getAwayTeam() + " " + game.getAwayScore() + " " +
//											game.getHomeTeam() + " " + game.getHomeScore() + " ";
//						}
//					}
//					DAO.client.commitTransaction();
//				}catch(SQLException e){
//					e.printStackTrace();
//				}finally{
//					DAO.client.endTransaction();
//				}
//			}
//			//Send update if it's a certain game
//
//			if(body.length() > 0){
//				EmailUtility.sendSimpleEmail("6104163219@txt.att.net","","NFL",body);
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//
//	public void getUpdate(){
//		try{
//			//URLConnection link = new URL("http://www.nfl.com//liveupdate/scorestrip/ss.xml").openConnection();
//			//link.connect();
//
//			URL feed = new URL("http://www.nfl.com//liveupdate/scorestrip/ss.xml");
//
//			Document dom;
//			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//			DocumentBuilder db = dbf.newDocumentBuilder();
//
//			//parse using builder to get DOM representation of the XML file
//			dom = db.parse(feed.toString());
//
//			//get the root element
//			Element root = dom.getDocumentElement();
//
//			//get a nodelist of  elements
//			NodeList nl = (NodeList) root.getElementsByTagName("g");
//
//			for(int i = 0 ; i < nl.getLength();i++) {
//				Element el = (Element)nl.item(i);
//				System.out.println(el.getAttribute("h") + " "+el.getAttribute("hs") + " " +
//						el.getAttribute("v") + " " + el.getAttribute("vs"));
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
}