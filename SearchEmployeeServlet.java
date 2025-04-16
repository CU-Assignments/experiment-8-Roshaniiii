import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SearchEmployeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int empId = Integer.parseInt(request.getParameter("empId"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/company", "root", "password");

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM employees WHERE id = ?");
            ps.setInt(1, empId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                out.println("<h2>Employee Found:</h2>");
                out.println("ID: " + rs.getInt("id") + "<br>Name: " + rs.getString("name"));
            } else {
                out.println("<h2>No employee found with ID " + empId + "</h2>");
            }

            conn.close();
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}
