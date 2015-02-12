package joejava.nfl;

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

public class GetUrl {

	public static void main (String[] args) {
	  
		try{
			URLConnection link = new URL("http://www.nfl.com/scores/2009/REG8").openConnection();
			link.connect();
			Lexer lexer = new Lexer(link);
			Node node = null;
			NodeList list = new NodeList();
			int j = 0;

			//Get html from nfl.com
			while( (node = lexer.nextNode()) != null){
				String text = node.getText();
				if(node instanceof TextNode && !text.contains("\n") && !text.contains("\t")){
					list.add(node);
					System.out.println(j + " "+node);
					j++;
				}
			}
			
			//look for 
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

