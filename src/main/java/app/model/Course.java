package app.model;

import app.framework.*;

import java.io.Serializable;

@DbTable(name = "courses")
@Cohort12Form(label = "Course Register", actionUrl = "course/save")
@Cohort12Table(label = "Courses", addLink = "course/add", deleteLink = "course/delete")
public class Course implements Serializable {

    @DbColumn(name = "id", type = "INT", primaryKey = true, autoIncrement = true)
    private int id;

    @DbColumn(name = "name", type = "VARCHAR(255)")
    @Cohort12FormField(label = "Course Name",
        placeholder = "Please enter Course Name")
    @Cohort12TableCol(label = "Course Name")
    private String name;

    @DbColumn(name = "school_name", type = "VARCHAR(255)")
    @Cohort12FormField(label = "School Name",
        placeholder = "Please enter School Name", select = "school")
    private String schoolName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
