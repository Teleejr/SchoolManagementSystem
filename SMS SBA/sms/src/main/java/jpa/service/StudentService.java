package jpa.service;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.entitymodels.StudentCourses;
import jpa.mainrunner.SMSRunner;
import lombok.extern.log4j.Log4j;

import javax.persistence.*;
import java.util.List;
@Log4j

public class StudentService implements StudentDAO {


    @Override
    public List<Student> getAllStudents() {
        //Create entity manager
        EntityManager em = SMSRunner.emf.createEntityManager();
        //Begin a session
        em.getTransaction().begin();

        //Create a list of students and put query results inside of it
        List student = em.createQuery("From Student").getResultList();

        em.getTransaction().commit();
        em.close();
        return student;
    }

    @Override
    public Student getStudentByEmail(String email) {
        //Create entity manager
        EntityManager em = SMSRunner.emf.createEntityManager();

        //Create a Student object
        Student st = new Student();

        try{
            //Begin a session
            em.getTransaction().begin();

            //Create a query and set the parameter
            Query q = em.createQuery("FROM Student as s WHERE s.Email = : givenEmail");
            q.setParameter("givenEmail", email);

            //Set result to a Student and cast it
            st = (Student) q.getSingleResult();

            em.getTransaction().commit();
            em.close();

        }//end try
        catch (IllegalArgumentException | EntityNotFoundException e) {
            e.printStackTrace();
            log.error("Commit issue or no record found");
        }//end catch



        return st;
    }

    @Override
    public boolean validateStudent(String email, String password) {
        //Create entity manager
        EntityManager em = SMSRunner.emf.createEntityManager();

        try {
            //Begin a session
            em.getTransaction().begin();

            //Create a query
            Query q = em.createQuery("SELECT s.Password FROM Student as s WHERE s.Email = email");
            q.setParameter("email", email);

            //Assign result to a string
            String pw = (String) q.getSingleResult();

            //Test if result equals password
            if (pw.equals(password)) {
                //If true, commit the transaction, close it, and return true
                em.getTransaction().commit();
                em.close();
                return true;
            } else {
                //If false, commit the transaction, close it, and return false
                em.getTransaction().commit();
                em.close();
                return false;
            }
        }//end try
        catch(IllegalArgumentException | EntityNotFoundException e) {
            e.printStackTrace();
            log.error("Commit issue or no record found");
        }//end catch

        //if nothing else return false
        return false;

    }//end validateStudent

    @Override
    public void registerStudentToCourse(String email, int cId) {
        //Create entity manager
        EntityManager em = SMSRunner.emf.createEntityManager();
        //Get student by email
        Student st = getStudentByEmail(email);
        //Get student password validate the student
        validateStudent(email, st.getSPass());

        try {
            //Begin transaction
            em.getTransaction().begin();

            Query q = em.createNamedQuery("CoursesByStudent");
            q.setParameter("email", email);

            //Create a list of courses
            List<StudentCourses> sc = q.getResultList();
            String studentCourse = "StudentCourses{" +
                    "eMail='" + email + '\'' +
                    ", courseID=" + cId +
                    '}';

            for (int i = 0; i < sc.size(); i++) {
                //If the results match the given parameters, add student to the course
                if (!sc.get(i).toString().equals(studentCourse)) {
                    //Create a query to insert a student into a course
                    Query q2 = em.createQuery("INSERT FROM Student into StudentCourse sc WHERE email = sc.email && cId = sc.cId");
                    q2.setParameter("email", email);
                    q2.setParameter("cId", cId);
                }//end if

            }//end for

            //Commit transaction
        }//end try
        catch(IllegalArgumentException | EntityNotFoundException e) {
            e.printStackTrace();
            log.error("Commit issue or no record found");
        }//end catch
        finally {
            em.close();
        }//end finally

    }//end registerStudentToCourse

    @Override
    public List<Course> getStudentCourse(String email) {
        //Create entity manager
        EntityManager em = SMSRunner.emf.createEntityManager();

        List<Course> course = null;

        try {
            //Begin transaction
            em.getTransaction().begin();

            //Create a query and set parameters
            Query q = em.createNamedQuery("CoursesByStudent");
            q.setParameter("email", email);

            //Put results in a list
            course = q.getResultList();

            //Commit transaction
            em.getTransaction().commit();

        }//end try
        catch(IllegalStateException | EntityExistsException | IllegalArgumentException | TransactionRequiredException e) {
            e.printStackTrace();
        }//end catch
        finally {
            em.close();
        }//end finally
        return course;
    }//end method
}
