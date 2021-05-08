package jpa.dao;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.entitymodels.StudentCourses;

import java.util.List;

public interface StudentDAO {
    List<Student> getAllStudents();
    Student getStudentByEmail(String email);
    boolean validateStudent(String email, String password);
    void registerStudentToCourse(String email, int cId);
    List<StudentCourses> getStudentCourse(String email);
}
