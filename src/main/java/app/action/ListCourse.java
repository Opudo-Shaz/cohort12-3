package app.action;

import app.model.Course;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/course_lists")
public class ListCourse extends BaseListAction<Course> {
}
