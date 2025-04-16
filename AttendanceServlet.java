import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AttendanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String studentId = request.getParameter("studentId");
        String subject = request.getParameter("subject");
        String attendance = request.getParameter("attendance");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/school", "root", "password");

            PreparedStatement ps = conn.prepareStatement("INSERT INTO attendance (student_id, subject, attendance) VALUES (?, ?, ?)");
            ps.setString(1, studentId);
            ps.setString(2, subject);
            ps.setString(3, attendance);

            int status = ps.executeUpdate();
            if (status > 0) {
                out.println("<h2>Attendance recorded successfully!</h2>");
            } else {
                out.println("<h2>Failed to record attendance.</h2>");
            }

            conn.close();
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}
