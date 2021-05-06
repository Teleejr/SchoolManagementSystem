package jpa.service;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

import java.util.List;

public class StudentService implements StudentDAO {
    public static

    @Override
    public List<Student> getAllStudents() {
        //Create entity
        return null;
    }

    @Override
    public Student getStudentByEmail(String email) {
        return null;
    }

    @Override
    public boolean validateStudent(String email, String password) {
        return false;
    }

    @Override
    public void registerStudentToCourse(String email, int cId) {

    }

    @Override
    public List<Course> getStudentCourse(String email) {
        return null;
    }
}
