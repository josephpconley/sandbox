package joejava.games.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QueryServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String query = request.getParameter("query");
		
		runQuery(query,out);
	}
	
	public static void runQuery(String sql, PrintWriter out){
		try{
			Class.forName("org.postgresql.Driver").newInstance();
			try{
				Connection con = DriverManager.getConnection("jdbc:postgresql://joetomcat.no-ip.org:5432/postgres","postgres","arvydas11");
				try{
					PreparedStatement st1 = con.prepareStatement(sql);
					ResultSet rs1 = st1.executeQuery();
					ResultSetMetaData meta = rs1.getMetaData();
					for(int i=0;i<meta.getColumnCount();i++){
						out.print(meta.getColumnName(i+1) + "1");
					}	
					out.println("2");
					while(rs1.next()){
						for(int i=0;i<meta.getColumnCount();i++){
							out.print(rs1.getObject(i+1) + "3");
						}
						out.println("4");
					}
						
					rs1.close();
					st1.close();
	            }catch (SQLException e){
	            	out.println( "JDBC error: " + e );
	            }finally{
	            	con.close();
	            }
	         }catch(SQLException e){
	        	 out.println( "could not get JDBC connection: " + e );
	         }
		}catch(Exception e){
			out.println( "could not load JDBC driver: " + e );
		}		
	}
}
