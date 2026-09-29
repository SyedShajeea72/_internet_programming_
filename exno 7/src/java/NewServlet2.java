import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/URLRewriteServlet1")
public class NewServlet2 extends HttpServlet {
protected void doGet(HttpServletRequest request,
                     HttpServletResponse response)
        throws ServletException, IOException {

    String name = request.getParameter("uname");
    String password = request.getParameter("password");

    response.setContentType("text/html");

    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<body>");

    out.println("<h2>Welcome, " + name + "</h2>");

    out.println("<a href='URLRewriteServlet2?uname=" + name
            + "&password=" + password + "'>");

    out.println("Visit Next Page");

    out.println("</a>");

    out.println("</body>");
    out.println("</html>");
}

}
