package joejava.nfl.action;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.StringTokenizer;

//import joejava.ibatis.bean.Defense;
//import joejava.ibatis.bean.Game;
//import joejava.ibatis.bean.Offense;
//import joejava.ibatis.bean.Player;
//import joejava.ibatis.bean.TeamStats;
//import joejava.ibatis.DAO;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.lexer.Lexer;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class GetNFLScores {

//	public static void main (String[] args) throws MalformedURLException, IOException, ParserException, SQLException {
//
//		ArrayList<String> games = new ArrayList<String>();
//
//		for(int n=1980;n<2010;n++){
//			URLConnection link = new URL("http://www.pro-football-reference.com/years/"+n+"/games.htm").openConnection();
//			link.connect();
//			Lexer lexer = new Lexer(link);
//			Node node = null;
//			NodeList list = new NodeList();
//
//			//Parse entire web page - returns body as text
//			while( (node = lexer.nextNode()) != null){
//				list.add(node);
//			}
//		//Parse body as text into nodes, read scores
//			//Need to account for ties
//			int x = 0;
//			int y = 0;
//			Node[] nodeArray = list.toNodeArray();
//			Lexer bodyLexer = new Lexer(nodeArray[34].getText());
//			while((node = bodyLexer.nextNode()) != null){
//				String box = "a href=\"/boxscores";
//
//				if(node.getText().contains(box)){
//					String boxLink = "http://www.pro-football-reference.com" +
//											node.getText().substring(8,node.getText().length()-1);
//					if(y > 0){ //skip initial link, which is meaningless
//						games.add(boxLink);
//					}
//					y++;
//				}
//			}
//		}
//
//		//getBoxScore("http://www.pro-football-reference.com/boxscores/200910110phi.htm");
//		//getBoxScore("http://www.pro-football-reference.com/boxscores/200909130car.htm");
//		for(String box : games){
//			getBoxScore(box);
//		}
//	}
//
//	private static void getBoxScore(String boxLink) throws SQLException{
//		Game game = new Game();
//		TeamStats awayStats = new TeamStats();
//		TeamStats homeStats = new TeamStats();
//		HashMap<String,Object> params = new HashMap<String,Object>();
//
//		try{
//			SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMMM d, yyyy");
//			int[] awayScores = new int[5];
//			int[] homeScores = new int[5];
//			int[] offense = new int[14];
//			int[] defenseST = new int[15];
//
//			URLConnection link = new URL(boxLink).openConnection();
//			link.connect();
//			Parser parser = new Parser(link);
//			Node htmlNode = parser.parse(null).elementAt(2);
//			Node headNode = htmlNode.getLastChild();
//			Node bodyNode = headNode.getChildren().elementAt(23);
//
//			//db website is malformed, use Lexer to re-nodify, must slog through to find each node
//			Parser bodyParser = new Parser(new Lexer(bodyNode.getText()));
//			Node bodyTag = bodyParser.parse(null).elementAt(3);
//			Node pageContainer = bodyTag.getChildren().elementAt(5);
//			Node pageContent = pageContainer.getChildren().elementAt(5);
//
//			Node dateNode = pageContent.getChildren().elementAt(3);
//			Date gameDate = format.parse(dateNode.getFirstChild().getText());
//
//			DAO.client.startTransaction();
//
//			//Get box score
//			TableTag boxScore = (TableTag)pageContent.getChildren().elementAt(14);
//			TableRow[] row = boxScore.getRows();
//			for(int i=1;i<row.length;i++){
//				TableColumn[] cols = row[i].getColumns();
//				for(int j=1;j<cols.length - 1;j++){
//					if(i==1){
//						awayScores[j-1] = Integer.valueOf(cols[j].getFirstChild().getText());
//					}else{
//						homeScores[j-1] = Integer.valueOf(cols[j].getFirstChild().getText());
//					}
//				}
//
//				//System.out.println(cols.length + " " + boxLink);
//
//				if(cols.length == 7){
//					if(i==1){
//						awayScores[4] = Integer.valueOf(cols[5].getFirstChild().getText());
//					}else{
//						homeScores[4] = Integer.valueOf(cols[5].getFirstChild().getText());
//					}
//				}
//			}
//
//			//Get team stats
//			TableTag teamStats = (TableTag)pageContent.getChildren().elementAt(26);
//			row = teamStats.getRows();
//			String awayTeam = row[0].getChildren().elementAt(1).getFirstChild().getText();
//			String homeTeam = row[0].getChildren().elementAt(2).getFirstChild().getText();
//
//			for(int i=1;i<row.length;i++){
//				TableColumn[] cols = row[i].getColumns();
//				if(i == 1){//first downs
//					awayStats.setFirstDowns(Integer.valueOf(cols[1].getFirstChild().getText()));
//					homeStats.setFirstDowns(Integer.valueOf(cols[2].getFirstChild().getText()));
//				}else if(i == 7){//fumbles lost
//					StringTokenizer st1 = new StringTokenizer(cols[1].getFirstChild().getText(),"-");
//					st1.nextToken();
//					awayStats.setFumblesLost(Integer.valueOf(st1.nextToken()));
//
//					StringTokenizer st2 = new StringTokenizer(cols[2].getFirstChild().getText(),"-");
//					st2.nextToken();
//					homeStats.setFumblesLost(Integer.valueOf(st2.nextToken()));
//				}else if(i == 9){//penalties-yds
//					StringTokenizer st1 = new StringTokenizer(cols[1].getFirstChild().getText(),"-");
//					awayStats.setPenalties(Integer.valueOf(st1.nextToken()));
//					awayStats.setPenaltyYds(Integer.valueOf(st1.nextToken()));
//
//					StringTokenizer st2 = new StringTokenizer(cols[2].getFirstChild().getText(),"-");
//					homeStats.setPenalties(Integer.valueOf(st2.nextToken()));
//					homeStats.setPenaltyYds(Integer.valueOf(st2.nextToken()));
//				}
//
//			}
//			Integer gameId = (Integer)DAO.client.queryForObject("getNextGameId", null);
//
//			//Insert game
//			game.setGameId(gameId);
//			game.setQ1Away(awayScores[0]);
//			game.setQ2Away(awayScores[1]);
//			game.setQ3Away(awayScores[2]);
//			game.setQ4Away(awayScores[3]);
//			game.setOtAway(awayScores[4]);
//			game.setAwayTeam(awayTeam);
//			game.setGameDate(gameDate);
//			game.setQ1Home(homeScores[0]);
//			game.setQ2Home(homeScores[1]);
//			game.setQ3Home(homeScores[2]);
//			game.setQ4Home(homeScores[3]);
//			game.setOtHome(homeScores[4]);
//			game.setHomeTeam(homeTeam);
//
//			//System.out.println(awayTeam + " " +Arrays.toString(awayScores));
//			//System.out.println(homeTeam + " " +Arrays.toString(homeScores));
//
//			DAO.client.insert("insertGame",game);
//
//			awayStats.setGameId(gameId);
//			awayStats.setTeamCode(awayTeam);
//			homeStats.setGameId(gameId);
//			homeStats.setTeamCode(homeTeam);
//
//			DAO.client.insert("insertStats",awayStats);
//			DAO.client.insert("insertStats",homeStats);
//
//			//Get offensive stats
//			Node tableContainer = pageContent.getChildren().elementAt(32);
//			TableTag offenseStats = (TableTag)tableContainer.getChildren().elementAt(1);
//
//			row = offenseStats.getRows();
//			for(int i=2;i<row.length;i++){
//				TableColumn[] cols = row[i].getColumns();
//				Player player = new Player();
//				//Player name
//				if(cols.length > 0){
//					if(cols[0].getFirstChild() != null){	//if name is missing, ignore row
//						String playerName = cols[0].getFirstChild().getFirstChild().getText();
//						String playerId = cols[0].getFirstChild().getText().substring(19,27);
//						StringTokenizer st = new StringTokenizer(playerName);
//						String firstName = "";
//						if(st.countTokens() == 3){
//							firstName = st.nextToken() + st.nextToken();
//						}else{
//							firstName = st.nextToken();
//						}
//						String lastName = st.nextToken();
//						player = (Player)DAO.client.queryForObject("getPlayerById",playerId);
//						if(player == null){
//							player = new Player();
//							player.setPlayerId(playerId);
//							player.setFirstName(firstName);
//							player.setLastName(lastName);
//
//							DAO.client.insert("insertPlayer",player);
//						}
//					}
//					String team = cols[1].getFirstChild().getText();
//					for(int j=2;j<cols.length;j++){
//						if(cols[j].getFirstChild() != null){
//							offense[j-2] = Integer.valueOf(cols[j].getFirstChild().getText());
//						}else{
//							offense[j-2] = 0;
//						}
//					}
//
//					//Insert offensive stat
//					Offense offenseStat = new Offense();
//					offenseStat.setGameId(gameId);
//					offenseStat.setPlayerId(player.getPlayerId());
//					offenseStat.setTeamCode(team);
//					offenseStat.setPassCmp(offense[0]);
//					offenseStat.setPassAtt(offense[1]);
//					offenseStat.setPassYds(offense[2]);
//					offenseStat.setPassTds(offense[3]);
//					offenseStat.setInts(offense[4]);
//					offenseStat.setLongPass(offense[5]);
//					offenseStat.setRushAtt(offense[6]);
//					offenseStat.setRushYds(offense[7]);
//					offenseStat.setRushTds(offense[8]);
//					offenseStat.setLongRush(offense[9]);
//					offenseStat.setReceptions(offense[10]);
//					offenseStat.setRecYds(offense[11]);
//					offenseStat.setRecTds(offense[12]);
//					offenseStat.setLongRec(offense[13]);
//
//					//System.out.println(StringUtility.printBean(offenseStat));
//
//					DAO.client.insert("insertOffense", offenseStat);
//				}else{
//					i++;
//				}
//			}
//
//			//Get defense/special teams stats
//			Node tableContainer2 = pageContent.getChildren().elementAt(36);
//			TableTag defenseStats = (TableTag)tableContainer2.getChildren().elementAt(1);
//			row = defenseStats.getRows();
//			for(int i=2;i<row.length;i++){
//				TableColumn[] cols = row[i].getColumns();
//				Player player = new Player();
//				//Player name
//				if(cols.length > 0){
//					if(cols[0].getFirstChild() != null){	//if name is missing, ignore row
//						String playerName = cols[0].getFirstChild().getFirstChild().getText();
//						String playerId = cols[0].getFirstChild().getText().substring(19,27);
//						StringTokenizer st = new StringTokenizer(playerName);
//						String firstName = "";
//						if(st.countTokens() == 3){
//							firstName = st.nextToken() + st.nextToken();
//						}else{
//							firstName = st.nextToken();
//						}
//						String lastName = st.nextToken();
//						player = (Player)DAO.client.queryForObject("getPlayerById",playerId);
//						if(player == null){
//							player = new Player();
//							player.setPlayerId(playerId);
//							player.setFirstName(firstName);
//							player.setLastName(lastName);
//							DAO.client.insert("insertPlayer",player);
//						}
//					}
//					String team = cols[1].getFirstChild().getText();
//					double sacks = 0.0;
//					if(cols[2].getFirstChild()!= null){
//						sacks = Double.valueOf(cols[2].getFirstChild().getText());
//					}
//					int index = 0;
//					for(int j=3;j<cols.length;j++){
//						if(j != 12 && j != 17){
//							if(cols[j].getFirstChild() != null){
//								defenseST[index] = Integer.valueOf(cols[j].getFirstChild().getText());
//							}else{
//								defenseST[index] = 0;
//							}
//							index++;
//						}
//					}
//					Defense defenseStat = new Defense();
//					defenseStat.setGameId(gameId);
//					defenseStat.setTeamCode(team);
//					defenseStat.setPlayerId(player.getPlayerId());
//					defenseStat.setSacks(sacks);
//					defenseStat.setInts(defenseST[0]);
//					defenseStat.setIntYds(defenseST[1]);
//					defenseStat.setIntTds(defenseST[2]);
//					defenseStat.setLongIntYds(defenseST[3]);
//					defenseStat.setFumbRec(defenseST[4]);
//					defenseStat.setFumbYds(defenseST[5]);
//					defenseStat.setFumbTds(defenseST[6]);
//					defenseStat.setKickRet(defenseST[7]);
//					defenseStat.setRetYds(defenseST[8]);
//					defenseStat.setRetTds(defenseST[9]);
//					defenseStat.setLongRet(defenseST[10]);
//					defenseStat.setPuntRet(defenseST[11]);
//					defenseStat.setPuntYds(defenseST[12]);
//					defenseStat.setPuntTds(defenseST[13]);
//					defenseStat.setLongPunt(defenseST[14]);
//
//					DAO.client.insert("insertDefense", defenseStat);
//				}else{
//					i++;
//				}
//			}
//			/*
//			Node[] node = pageContent.getChildren().toNodeArray();
//			for(int i=33;i<40;i++){
//				System.out.println(i + " " +node[i]);
//			}
//			*/
//
//			DAO.client.commitTransaction();
//		}catch(Exception e){
//			e.printStackTrace();
//			System.out.println("Error thrown in "+boxLink);
//		}finally{
//			DAO.client.endTransaction();
//		}
//	}
}

