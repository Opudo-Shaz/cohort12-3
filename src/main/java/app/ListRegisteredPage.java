package app;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/list_registered")
public class ListRegisteredPage extends HttpServlet {

    @SuppressWarnings("unchecked")
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //if session exist use it, otherwise create a new one
        HttpSession session = req.getSession();

        List<Person> personsRegister;
        if (session.getAttribute("PERSONS_DB") == null)
            personsRegister = new ArrayList<>();
        else
            personsRegister = (List<Person>) session.getAttribute("PERSONS_DB");

        PrintWriter writer = resp.getWriter();

        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>About Us - Training Academy</title>");
        writer.println("<style>");
        writer.println("body { font-family: Arial; margin: 40px; background-color: #f4f6f8; }");
        writer.println("header { background-color: #2c3e50; color: white; padding: 15px; }");
        writer.println("section { margin-top: 20px; padding: 15px; background: white; border-radius: 5px; }");
        writer.println("a { color: #3498db; text-decoration: none; font-weight: bold; }");
        writer.println("</style>");
        writer.println("</head>");

        writer.println("<body>");

// Header
        writer.println("<header>");
        writer.println("<h1>About COHORT 12 Training PORTA</h1>");
        writer.println("</header>");

// Who we are
        writer.println("<section>");
        writer.println("<h2>Student Registered</h2>");
        writer.println("<p>");

        writer.println("<table style='border-collapse: collapse; width: 50%; font-family: Arial, sans-serif;'>");

        Class personClazz = Person.class;

        List<String> fieldNames = new ArrayList<>();
        for (Field field : personClazz.getDeclaredFields()) {
            fieldNames.add(field.getName());
        }

        writer.println("<tr>");
        for (String fieldName : fieldNames) {
            // Header row
            writer.println("<th style='border: 1px solid #000; padding: 8px; background-color: #f2f2f2;'>" + fieldName + "</th>");
        }
        writer.println("</tr>");


        for (Person person : personsRegister) {
            writer.println("<tr>");
            for (String fieldName : fieldNames) {
                try {
                    Field field = person.getClass().getDeclaredField(fieldName);
                    field.setAccessible(true);
                    writer.println("<td style='border: 1px solid #000; padding: 8px;'>"
                            + field.get(person) + "</td>");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
            writer.println("</tr>");
        }

        writer.println("</table>");

        writer.println("</p>");
        writer.println("</section>");

// Navigation
        writer.println("<section>");
        writer.println("<a href=\"./register\">&larr; Register Student </a>");
        writer.println("</section>");

        RequestDispatcher dispatcher = req.getRequestDispatcher("footer");
        dispatcher.include(req, resp);

        writer.println("</body>");
        writer.println("</html>");

    }
}
