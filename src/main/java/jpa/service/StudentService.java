package jpa.service;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Student;
import jpa.entitymodels.StudentCourses;
import jpa.mainrunner.SMSRunner;
import lombok.extern.log4j.Log4j;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

@Log4j

public class StudentService implements StudentDAO {


    @Override
    public void createStudent(Student student) {
        //Create entity manager
        EntityManager em = SMSRunner.emf.createEntityManager();
        //Begin transaction
        em.getTransaction().begin();

    try {
        //Persist to the database and commit
        em.persist(student);
        em.getTransaction().commit();
    }//end try
    catch(EntityExistsException e) {
        log.error("This student already exists");
        e.printStackTrace();
        em.getTransaction().rollback();
        }//end catch
        finally {
            em.close();
        }//end finally
    }//end createStudent

    @Override
    public List<Student> getAllStudents() {
        //Create entity manager
        EntityManager em = SMSRunner.emf.createEntityManager();
        //Begin a session
        em.getTransaction().begin();
        List<Student> student = null;

        try {
            //Query the db to get all students
            Query q = em.createQuery("From Student s");
            //Put the results in the student list
            student = q.getResultList();

            //Commit and save
            em.getTransaction().commit();
        }
        //Catch exceptions, print stack, and roll back
        catch(IllegalArgumentException | EntityNotFoundException | NullPointerException e) {
            e.printStackTrace();
            log.error("Unable to complete transaction with database");
            em.getTransaction().rollback();
        }
        finally {
            em.close();
        }
        return student;
    }//end method

    public boolean updateStudent(String email) {
        //Create entity manager
        EntityManager em = SMSRunner.emf.createEntityManager();
        //Create Scanner
        Scanner update = null;
        //Create temp Student
        Student temp = null;


        try {
            //Begin transaction
            em.getTransaction().begin();

            //Initialize Scanner
            update = new Scanner(System.in);

            System.out.println("1.Change name\n2.Change email\n3.Change password");
            int choice = update.nextInt();
        //Update name, email, or password based on given number
            switch (choice){
                case 1:
                    changeName(email);
                    return true;
                case 2:
                    changeEmail(email);
                    return true;
                case 3:
                    //changePassword(email);
                    return true;
            }//end switch
        }//end try
        catch(IllegalArgumentException | EntityNotFoundException e) {
            log.error("Email and password do not match");
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        }//end catch
        finally {
            em.close();
        }//end finally

        return true;
    }//end updateStudent

    public void changeName(String email) {
        //Create entity manager
        EntityManager em = SMSRunner.emf.createEntityManager();
        //Create a student using email
        Student student = getStudentByEmail(email);
        //Create a scanner
        Scanner update = new Scanner(System.in);

        //Get first and last name from the user, concat it and set it
        System.out.println("Enter first name: ");
        String fName = update.nextLine();
        System.out.println("Enter last name: ");
        String lName = update.nextLine();
        String fullName = fName.concat(" " + lName);

        try {
            //Begin session
            em.getTransaction().begin();

            //Display current student info
            System.out.println("Before update: " + student.toString());
            //Set student name
            student.setSName(fullName);
            //Save to db
            //em.persist(student);
            em.getTransaction().commit();
            //Display updated student info
            System.out.println("Before update: " + student);

        }
        catch(IllegalArgumentException | EntityExistsException e) {
            log.error("Name change failed");
            e.printStackTrace();
            em.getTransaction().rollback();
        }//end catch
        finally {
            em.close();
        }//end finally
    }//end changeName

    public void changeEmail(String email) {
        //Create entity manager
        EntityManager em = SMSRunner.emf.createEntityManager();
        //Create a student using email
        Student student = getStudentByEmail(email);
        //Create a scanner
        Scanner update = new Scanner(System.in);

        //Get first and last name from the user, concat it and set it
        System.out.println("Enter new email: ");
        String newEmail = update.nextLine();


        try {
            //Begin session
            em.getTransaction().begin();

            //Display current student info
            System.out.println("Before update: " + student.toString());
            //Set student email
            student.setSEmail(newEmail);
            //Save to db
            em.persist(student);
            em.getTransaction().commit();
            //Display updated student info
            System.out.println("Before update: " + student);

        }
        catch(IllegalArgumentException | EntityExistsException e) {
            log.error("Email change failed");
            e.printStackTrace();
            em.getTransaction().rollback();
        }//end catch
        finally {
            em.close();
        }//end finally
    }//end changeName

    @Override
    public Student getStudentByEmail(String email) {
        //Create entity manager
        EntityManager em = SMSRunner.emf.createEntityManager();

        //Create a Student object
        Student st = new Student();

        try{
            //Begin a session
            em.getTransaction().begin();

            //Save
            st = em.find(Student.class, email);

            //Commit
            em.getTransaction().commit();

        }//end try
        catch (IllegalArgumentException | EntityNotFoundException  e) {
            e.printStackTrace();
            //log.error("Commit issue or no record found");
        }//end catch
        finally {
            em.close();
        }

        return st;
    }

    @Override
    public boolean validateStudent(String email, String password) {

            //Create a new Student and getStudentByEmail
            Student st = getStudentByEmail(email);

            //Test if result equals password
            if (st != null) {
                return st.getSPass().equals(password);
            }
            else {
                System.out.println("Validation failed");
                return false;
            }

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
                    "cEmail='" + email + '\'' +
                    ", courseID=" + cId +
                    '}';
            StudentCourses sc2 = new StudentCourses();

            if(sc.size() == 0) {
                sc2.setcEmail(email);
                sc2.setCourseID(cId);
                em.persist(sc2);
            }//end if
            else {

                for (StudentCourses studentCourses : sc) {
                    //If the results match the given parameters, add student to the course
                    if (!studentCourses.toString().equals(studentCourse)) {
                        sc2.setcEmail(email);
                        sc2.setCourseID(cId);
                        em.persist(sc2);
                    }//end if
                }//end for
            }//end else

            //Commit transaction
            em.getTransaction().commit();
        }//end try
        catch(IllegalArgumentException | EntityNotFoundException e) {
            e.printStackTrace();
            //log.error("Commit issue or no record found");
        }//end catch
        finally {
            em.close();
        }//end finally

    }//end registerStudentToCourse

    @Override
    public List<StudentCourses> getStudentCourse(String email) {
        //Create entity manager
        EntityManager em = SMSRunner.emf.createEntityManager();

        List<StudentCourses> course = null;

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
