package jpa.dao;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.entitymodels.StudentCourses;

import java.util.List;

public interface StudentDAO {
    //Create Student
    void createStudent(Student student);
    //Read Student
    List<Student> getAllStudents();
    //Update Student
    boolean updateStudent(String email);
    //Called by updateStudent
    void changeName(String email);
    void changeEmail(String email);
    //void changePassword(String email);
    Student getStudentByEmail(String email);
    boolean validateStudent(String email, String password);
    void registerStudentToCourse(String email, int cId);
    List<StudentCourses> getStudentCourse(String email);
}
