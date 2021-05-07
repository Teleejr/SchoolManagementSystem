package jpa.dao;

import jpa.entitymodels.StudentCourses;

import java.util.List;

public interface StudentCourseDAO {

    List<StudentCourses> getAllStudentCourses();
}
