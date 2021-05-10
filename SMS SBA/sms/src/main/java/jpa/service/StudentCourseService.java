package jpa.service;


import jpa.entitymodels.StudentCourses;
import jpa.mainrunner.SMSRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import java.util.List;

public class StudentCourseService {


    public List<StudentCourses> getAllStudentCourses(String sEmail) {
        //Create entity manager
        EntityManager em = SMSRunner.emf.createEntityManager();
        //Create a list to hold the data
        List<StudentCourses> sc = null;

        try {

            //Begin transaction
            em.getTransaction().begin();
            //Query to find all student courses by email
            Query q = em.createQuery("From StudentCourses c WHERE c.scEmail = : email");
            //Set the parameter that references email in the query
            q.setParameter("email", sEmail);
            //Get the results of the query and put them in the list
            sc = q.getResultList();

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

        return sc;
    }//end getAllStudentCourses

}
