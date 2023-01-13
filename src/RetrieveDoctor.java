

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RetrieveDoctor
 */
@WebServlet("/RetrieveDoctor")
public class RetrieveDoctor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveDoctor() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		String did = request.getParameter("did");
			PrintWriter out = response.getWriter();
			try {
				
			Connection c = GetConnection.getConnection();
			String sql = "select * from HEALTHCENTER_SYSTEM.DOCTOR";
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.addBatch();
			
			ResultSet r = ps.executeQuery();
			ResultSetMetaData rms = r.getMetaData();
			ps.clearBatch();
			
			out.println("<table>");
			out.println("<td>");
			out.println("<th>"+rms.getColumnName(1)+"</th>");
			out.println("<th>"+rms.getColumnName(2)+"</th>");
			out.println("<th>"+rms.getColumnName(3)+"</th>");
			out.println("<th>"+rms.getColumnName(4)+"</th>");
			out.println("<th>"+rms.getColumnName(5)+"</th>");
			
			out.println("</td>");
			while(r.next()){
				out.println("<tr>");
				out.println("<td></td>");
				out.println("<td>"+r.getString(1)+"</td>");
				out.println("<td>"+ r.getString(2) +"</td>");
				out.println("<td>"+ r.getString(3) +"</td>");
				out.println("<td>"+ r.getString(4) +"</td>");
				out.println("<td>"+ r.getString(5) +"</td>");
				
				out.println("</tr>");
			}
			out.println("</table>");
		} catch (SQLException e) { 
			response.setContentType("text/html");  
			out.println("<br><br><br><h1 align=center><font color=\"red\">TRY AGAIN<br></font></h1>");  
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);		
	}

}
