package joejava.games.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import joejava.util.LangUtility;

public class JumbleServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	      				throws ServletException, IOException {
	
		PrintWriter out = response.getWriter();
	    String input = request.getParameter("input");
	    ArrayList<String> matches = LangUtility.getMatches(input);
	    
	    out.println("You have "+matches.size()+" matches.");
	    
	    for(String s : matches){
	    	out.println(s);
	    }
	}
}
