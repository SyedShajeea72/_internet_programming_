import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HiddenFieldServlet1")
public class NewServlet extends HttpServlet {

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

    out.println("<form action='HiddenFieldServlet2' method='get'>");

    out.println("<input type='hidden' name='hf' value='" + name + "'>");

    out.println("<input type='hidden' name='hp' value='" + password + "'>");

    out.println("<input type='submit' value='Go'>");

    out.println("</form>");

    out.println("</body>");
    out.println("</html>");
}

}
