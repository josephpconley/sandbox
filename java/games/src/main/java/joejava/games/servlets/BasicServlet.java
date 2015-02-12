package joejava.games.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import joejava.util.LangUtility;

public class BasicServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	      				throws ServletException, IOException {
	
		PrintWriter out = response.getWriter();
	    String house = request.getParameter("house");
	    String player1 = request.getParameter("player1");
	    String player2 = request.getParameter("player2");
	    
	    //String action = PokerUtility.getBasic(player, house, false);
	    //out.println("You should " + action);
	}
}
