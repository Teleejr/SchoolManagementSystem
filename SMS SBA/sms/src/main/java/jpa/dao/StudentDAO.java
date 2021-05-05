package jpa.dao;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

public interface StudentDAO {
    Student getAllStudents();
    Student getStudentByEmail(String email);
    void validateStudent();
    void registerStudentToCourse(Course course);
    Course getStudentCourse(Student student);
}
