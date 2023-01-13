

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreatePatientIllness
 */
@WebServlet("/CreatePatientIllness")
public class CreatePatientIllness extends HttpServlet {/**
     * @see HttpServlet#HttpServlet()
     */
     public CreatePatientIllness() {
         super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		PrintWriter out = response.getWriter();
		try {
			Connection c = GetConnection.getConnection();
			
			String  illnessname = request.getParameter("illnessname");
			String  ssn = request.getParameter("ssn");
			
			String sql = "insert into HEALTHCENTER_SYSTEM.PATIENT_ILLNESS(illnessname,ssn) values(?,?)";
	
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, illnessname);
			ps.setString(2, ssn);
			
			
			ps.addBatch();
			
			ps.executeBatch();
			ps.clearBatch();
			
				response.setContentType("text/html");  
				out.println("<br><br><br><h1 align=center><font color=\"green\">SUCCESSFUL<br></font></h1><script type=\"text/javascript\">");  
				out.println("redirectURL = \"welcome.html\";setTimeout(\"location.href = redirectURL;\",\"5000\");");  
				out.println("</script>");
			
		} catch (SQLException e) { 
			response.setContentType("text/html");  
			out.println("<br><br><br><h1 align=center><font color=\"red\">THERE IS SOME PROBLEM<br></font></h1><script type=\"text/javascript\">");  
			out.println("redirectURL = \"welcome.html\";setTimeout(\"location.href = redirectURL;\",\"5000\");");  
			out.println("</script>");
			e.printStackTrace();
		}
	}
}
