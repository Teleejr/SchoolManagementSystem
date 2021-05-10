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
        //Create a list of courses
        List<Course> ac = null;


        try {
            //Begin transaction
            em.getTransaction().begin();

            //Query database for all courses
            Query q = em.createQuery("From Course c");
            //Put the results in the list
            ac = q.getResultList();

            //Commit and save
            em.getTransaction().commit();
        }
        //Catch exceptions
        catch(IllegalArgumentException | EntityNotFoundException | NullPointerException e) {
            e.printStackTrace();
        }
        //Close the entity manager
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
        //Catch exceptions
        catch (IllegalArgumentException | EntityNotFoundException e) {
            e.printStackTrace();
        }//end catch
        //Close entity manager
        finally {
            em.close();
        }//end finally

        return c;
    }//end getCourseById

}//end class
