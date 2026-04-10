package app;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class RegisterPage extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException{

        //if session exist use it, otherwise create a new one
        HttpSession session = req.getSession();
        System.out.println("session.getAttribute(\"SESSION_ID\"): " + session.getAttribute("SESSION_ID"));
        if(session.getAttribute("SESSION_ID") == null){
            req.getSession().invalidate();
            resp.sendRedirect("./login");
        }

        ServletConfig config = getServletConfig();

        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>");
        writer.println(config.getInitParameter("pageName"));
        writer.println("</title>");
        writer.println("<style>");
        writer.println("body { font-family: Arial; margin: 40px; background-color: #f4f6f8; }");
        writer.println("header { background-color: #2c3e50; color: white; padding: 15px; }");
        writer.println("section { margin-top: 20px; padding: 20px; background: white; border-radius: 5px; max-width: 400px; }");
        writer.println("input { width: 100%; padding: 8px; margin: 10px 0; }");
        writer.println("button { padding: 10px; background-color: #3498db; color: white; border: none; width: 100%; }");
        writer.println("a { display: inline-block; margin-top: 10px; color: #3498db; }");
        writer.println("</style>");
        writer.println("</head>");

        writer.println("<body>");

// Header
        writer.println("<header>");
        writer.println("<h1>");
        writer.println(config.getInitParameter("pageHeader"));
        writer.print("Logged In User: ");
        writer.println(session.getAttribute("UserActualName"));
        writer.println("</h1>");
        writer.println("</header>");

// Form
        writer.println("<section>");
        writer.println("<h2>Register for a Course</h2>");

        writer.println("<form method='post' action='./register'>");

// Name
        writer.println("<label>Full Name:</label>");
        writer.println("<input type='text' name='name' placeholder='Enter your full name' required />");

// National ID
        writer.println("<label>National ID:</label>");
        writer.println("<input type='text' name='nationalId' placeholder='Enter your ID number' required />");

// Submit button
        writer.println("<button type='submit'>Register</button>");

        writer.println("</form>");
        writer.println("</section>");

        RequestDispatcher dispatcher = req.getRequestDispatcher("footer");
        dispatcher.include(req, resp);

        writer.println("</body>");
        writer.println("</html>");

    }

    @SuppressWarnings("unchecked")
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //if session exist use it, otherwise create a new one
        HttpSession session = req.getSession();

        List<Person> personsRegister;
        if (session.getAttribute("PERSONS_DB") == null)
            personsRegister = new ArrayList<Person>();
        else
            personsRegister = (List<Person>) session.getAttribute("PERSONS_DB");

        personsRegister.add(new Person(req.getParameter("name"),
            req.getParameter("nationalId")));

        session.setAttribute("PERSONS_DB", personsRegister);

        resp.sendRedirect("./list_registered");
    }
}
