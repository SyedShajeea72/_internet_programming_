import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HiddenFieldServlet2")
public class NewServlet1 extends HttpServlet {

protected void doGet(HttpServletRequest request,
                     HttpServletResponse response)
        throws ServletException, IOException {

    String name = request.getParameter("hf");
    String password = request.getParameter("hp");

    response.setContentType("text/html");

    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<body>");

    out.println("<h2>Hello, " + name + "</h2>");

    out.println("<p>Password received successfully.</p>");

    out.println("<p>This value was passed using Hidden Form Fields.</p>");

    out.println("</body>");
    out.println("</html>");
}

}
