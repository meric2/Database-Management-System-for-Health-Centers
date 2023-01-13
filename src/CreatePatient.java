import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreatePatient
 */
@WebServlet("/CreatePatient")
public class CreatePatient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreatePatient() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		PrintWriter out = response.getWriter();
		Connection c = GetConnection.getConnection();
		String ssn = request.getParameter("ssn");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String centerid = request.getParameter("centerid");
		String docssn = request.getParameter("docssn");
		String sex = request.getParameter("gender");
		String visited = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

		try {
			String sql = "insert into HEALTHCENTER_SYSTEM.patient(ssn,fname,lname,centerid,sex,docssn,visitdate) values(?,?,?,?,?,?,?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, ssn);
			ps.setString(2, fname);
			ps.setString(3, lname);
			ps.setInt(4, Integer.valueOf(centerid));
			ps.setString(5, sex);
			ps.setString(6, docssn);
			ps.setDate(7, java.sql.Date.valueOf(visited));
			ps.addBatch();
			ps.executeBatch();
			ps.clearBatch();

			response.setContentType("text/html");
			out.println(
					"<br><br><br><h1 align=center><font color=\"green\">SUCCESSFUL<br></font></h1><script type=\"text/javascript\">");
			out.println("redirectURL = \"welcome.html\";setTimeout(\"location.href = redirectURL;\",\"5000\");");
			out.println("</script>");

		} catch (SQLException e) {
			e.printStackTrace();
			response.setContentType("text/html");
			out.println(
					"<br><br><br><h1 align=center><font color=\"red\">TRY AGAIN<br></font></h1><script type=\"text/javascript\">");
			out.println("redirectURL = \"welcome.html\";setTimeout(\"location.href = redirectURL;\",\"5000\");");
			out.println("</script>");
		}
	}

}
