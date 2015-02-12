package joejava.nfl.action;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.lexer.Lexer;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.nodes.TextNode;

public class GetNFLTeams {

//	public static void main (String[] args) {
//
//		try{
//			URLConnection link = new URL("http://www.pro-football-reference.com/years/2009/games.htm").openConnection();
//			//URLConnection link = new URL("http://www.pro-football-reference.com/teams/").openConnection();
//				link.connect();
//				Lexer lexer = new Lexer(link);
//				Node node = null;
//				NodeList list = new NodeList();
//				int j = 0;
//
//				//Parse entire web page - returns body as text
//					while( (node = lexer.nextNode()) != null){
//						list.add(node);
//
//						if(j == 34)
//							System.out.println(node.getText());
//
//						j++;
//					}
//
//
//				//Parse body as text into nodes, read scores
//					//Need to account for ties
//					j=0;
//					Node[] nodeArray = list.toNodeArray();
//					Lexer bodyLexer = new Lexer(nodeArray[34].getText());
//					NodeList textNodes = new NodeList();
//					boolean start = false;
//					System.out.println("START");
//					while((node = bodyLexer.nextNode()) != null){
//						if(start && node instanceof org.htmlparser.nodes.TextNode && isClean(node.getText()) == false){
//							textNodes.add(node);
//							System.out.println(j+ " " + node.getText());
//							j++;
//						}
//					}
//					/*
//					Node[] bodyArray = textNodes.toNodeArray();
//					for(int i=0;i<bodyArray.length;i=i+12){
//						boolean swap = false;
//						String week = bodyArray[i].getText();
//						String dayOfWeek = bodyArray[i+1].getText();
//						String date = bodyArray[i+2].getText();
//						String outcome = bodyArray[i+3].getText();
//						String team1 = bodyArray[i+4].getText();
//						if(bodyArray[i+5].getText().equals("@") || bodyArray[i+5].getText().equals("N")){
//							swap = true;
//							i++;
//						}
//						String team2 = bodyArray[i+5].getText();
//						String score1 = bodyArray[i+6].getText();
//						String score2 = bodyArray[i+7].getText();
//						System.out.println(week+" "+dayOfWeek+" "+date+" "+outcome+" "+team1+" "+score1+" "+team2+" "+score2);
//					}
//					*/
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}

	private static boolean isClean(String text) {
		String [] bad = {"\n","box","Week","Day","Date","Winner/tie","Loser/tie","PtsW","PtsL","YdsW","TOW","YdsL","TOL","Playoffs"};
		for(int i=0;i<bad.length;i++){
			if(text.contains(bad[i])){
				return true;
			}
		}
		return false;
	}
}

