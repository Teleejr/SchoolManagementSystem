package jpa.service;

import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;
import jpa.mainrunner.SMSRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import java.util.List;

public class CourseService implements CourseDAO {

    @Override
    public List<Course> getAllCourses() {
        //Create entity manager
        EntityManager em = SMSRunner.emf.createEntityManager();
        List<Course> ac = null;


        try {
            //Begin transaction
            em.getTransaction().begin();

            //Create a list of students and put query results inside of it
            Query q = em.createQuery("From Course c");
            ac = q.getResultList();

            //Commit and save
            em.getTransaction().commit();
        }
        catch(IllegalArgumentException | EntityNotFoundException | NullPointerException e) {
            e.printStackTrace();
        }
        finally {
            em.close();
        }

        return ac;

    }

    @Override
    public Course getCourseById(int cId) {
        //Create entity manager
        EntityManager em = SMSRunner.emf.createEntityManager();

        //Create a Student object
        Course c = null;

        try{
            //Begin a session
            em.getTransaction().begin();

            //Find course
            c = em.find(Course.class, cId);

            //Commit
            em.getTransaction().commit();

        }//end try
        catch (IllegalArgumentException | EntityNotFoundException e) {
            e.printStackTrace();
        }//end catch
        finally {
            em.close();
        }//end finally

        return c;
    }//end getCourseById

}
