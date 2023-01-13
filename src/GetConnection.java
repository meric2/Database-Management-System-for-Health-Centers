

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetConnect?on
 */
@WebServlet("/GetConnection")
public class GetConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static Connection c = null;
	
	private GetConnection()
	{
		
	}
	public static Connection getConnection(){
		if(c == null) {
			try {
				
				c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital","postgres","64");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return c;
		}else {
			return c;	
		}
	}

}
