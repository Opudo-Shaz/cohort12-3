package app.bean;

import app.dao.CourseDao;
import app.model.AuditTrail;
import app.model.Course;
import app.utility.validation.Validate;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Stateless
public class CourseBean {

    @Inject
    private Event<AuditTrail> auditTrailEvent;

    @Inject
    private CourseDao courseDao;

    @Inject
    @Named("ValidCourse")
    public Validate<Course> validate;

    private int numberOfTimesCalled = 1;

    public boolean save(Course course){
        System.out.println("Saving course through EJB save");
        if (validate.process(course)) {
            auditTrailEvent.fire(new AuditTrail("Creating course: "
                + course.getName()));
            courseDao.save(course);
            return true;
        }

        System.out.println("********* " + numberOfTimesCalled);

        numberOfTimesCalled++;

        return false;
    }

    public List<Course> list(Course filter){
        System.out.println("Fetching course through EJB list");


        System.out.println("********* " + numberOfTimesCalled);

        numberOfTimesCalled++;

        return courseDao.findAll();
    }

}
