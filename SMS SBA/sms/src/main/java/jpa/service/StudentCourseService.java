package jpa.service;

import jpa.entitymodels.Course;
import jpa.entitymodels.StudentCourses;
import jpa.mainrunner.SMSRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import java.util.List;

public class StudentCourseService {


    public List<Course> getAllStudentCourses(String sEmail) {
        //Create entity manager
        EntityManager em = SMSRunner.emf.createEntityManager();
        List<Course> sc = null;

        try {

            //Begin transaction
            em.getTransaction().begin();
            //Create a list of students and put query results inside of it
            Query q = em.createQuery("From StudentCourses c WHERE c.scEmail = : email");
            q.setParameter("email", sEmail);
            sc = q.getResultList();

            //Commit and save
            em.getTransaction().commit();
        }
        catch(IllegalArgumentException | EntityNotFoundException | NullPointerException e) {
            e.printStackTrace();
        }
        finally {
            em.close();
        }

        return sc;
    }
}
