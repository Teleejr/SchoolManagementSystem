/*
 * Filename: SMSRunner.java
* Author: Stefanski
* 02/25/2020 
 */
package jpa.mainrunner;

import static java.lang.System.out;

import java.util.List;
import java.util.Scanner;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.entitymodels.StudentCourses;
import jpa.service.CourseService;
import jpa.service.StudentCourseService;
import jpa.service.StudentService;
import jpa.util.SmsUtil;

import javax.persistence.EntityManagerFactory;

/**1
 * 
 * @author Harry
 *
 */
public class SMSRunner {
	public static EntityManagerFactory emf = SmsUtil.getEntityManagerFactory();

	private Scanner sin;
	private StringBuilder sb;

	private CourseService courseService;
	private StudentService studentService;
	private Student currentStudent;

	public SMSRunner() {
		sin = new Scanner(System.in);
		sb = new StringBuilder();
		courseService = new CourseService();
		studentService = new StudentService();
	}

	/**
	 * @param args
	 * main runner
	 */
	public static void main(String[] args) {

		SMSRunner sms = new SMSRunner();
		sms.run();
	}

	private void run() {
		// Login or quit
		switch (menu1()) {
		case 1:
			if (studentLogin()) {
				registerMenu();
			}
			break;
		case 2:
			displayStudents(studentService.getAllStudents());
			break;
		case 3:
			createStudentMenu();
			break;
		case 5:
			//doesn't work in switch statement
			updateStudentsMenu();
			break;
		case 4:
			out.println("Goodbye!");
			break;

		default:

		}
	}

	private int menu1() {
		sb.append("\n1.Student Login\n2.View all Students\n3.Create Student\n4.Quit Application\nPlease Enter Selection: ");
		out.print(sb.toString());
		sb.delete(0, sb.length());

		return sin.nextInt();
	}

	private boolean studentLogin() {
		boolean retValue = false;
		out.print("Enter your email address: ");
		String email = sin.next();
		out.print("Enter your password: ");
		String password = sin.next();

		Student students =  studentService.getStudentByEmail(email);
		if (students != null) {
			currentStudent = students;
		}

		if (currentStudent != null && currentStudent.getSPass().equals(password)) {
			List<StudentCourses> courses = studentService.getStudentCourse(email);
			out.println("MyClasses");

			Course c = new Course();
			CourseService cs = new CourseService();

			out.printf("%-5s %-35s %-25s\n", "ID", "Name", "Instructor");
			for (StudentCourses course : courses) {
				c.setCId(course.getCourseID());
				Course c1 = cs.getCourseById(c.getCId());
				out.printf("%-5s %-35s %-25s\n", c1.getCId(), c1.getCName(), c1.getCInstructorName());
			}
			retValue = true;
		} else {
			out.println("User Validation failed. GoodBye!");
		}
		return retValue;
	}

	//Added createStudentMenu
	private void createStudentMenu() {
		//Get name, email, and password for the new student
		out.print("Enter name: ");
		String name = sin.next();
		out.print("Enter email: ");
		String email = sin.next();
		out.print("Enter password: ");
		String password = sin.next();

		//Create temp student object
		Student temp = new Student(name, email, password);

		//Create student
		studentService.createStudent(temp);
		//Display new student
		out.printf("You entered: %5s %15s %15s\n", temp.getSName(), temp.getSEmail(), temp.getSPass());
		//Get student by email
		currentStudent = studentService.getStudentByEmail(temp.getSEmail());

		//Confirm currentStudent is not null, else say student was not added to the system
		if(currentStudent != null) {
			out.printf("System received: %5s %15s %15s\n", currentStudent.getSName(), currentStudent.getSEmail(), currentStudent.getSPass());
		}//end if
		else {
			out.println("Student was not added to the system");
		}//end else

	}//end createStudentMenu

	//Displays all students in the system
	private void displayStudents(List<Student> student) {

		for(Student st : student)
			out.println(st);

	}//end displayStudents

	private void updateStudentsMenu() {
		//Get email and password, then call updateStudent
		out.println("Enter email: ");
		String email = sin.next();
		out.println("Enter password: ");
		String password = sin.next();

		//Validate student. If true, call update method and print menu
		if(studentService.validateStudent(email, password)) {
			studentService.updateStudent(email);
		}//end if
		//If validateStudent returns false, display error with verification
		else {
			out.println("Verification failed: Incorrect email and/or password");
		}//end else
	}//updateStudentsMenu

	private void registerMenu() {
		sb.append("\n1.Register a class\n2. Logout\nPlease Enter Selection: ");
		out.print(sb.toString());
		sb.delete(0, sb.length());

		switch (sin.nextInt()) {
		case 1:
			List<Course> allCourses = courseService.getAllCourses();

			out.printf("%5s %15s %15s\n", "ID", "Course", "Instructor");

			for (Course course : allCourses) {
				out.printf("%-5s %-35s %-25s\n", course.getCId(), course.getCName(), course.getCInstructorName());

			}
			out.println();
			out.print("Enter Course Number: ");
			int number = sin.nextInt();
			Course newCourse = courseService.getCourseById(number);

			if (newCourse != null) {
				studentService.registerStudentToCourse(currentStudent.getSEmail(), newCourse.getCId());
				Student temp = studentService.getStudentByEmail(currentStudent.getSEmail());
				
				StudentCourseService scService = new StudentCourseService();
				List<StudentCourses> sCourses = scService.getAllStudentCourses(temp.getSEmail());

					Course c = new Course();
					CourseService cs = new CourseService();
					System.out.printf("%-5s %-35s %-25s\n", "ID", "Course Name", "Instructor Name");
					System.out.println("MyClasses");
					for (StudentCourses course : sCourses) {
						c.setCId(course.getCourseID());
						Course c1 = cs.getCourseById(c.getCId());

						System.out.printf("%-5s %-35s %-25s\n", c1.getCId(), c1.getCName(), c1.getCInstructorName());
					}
			}
			break;
		case 2:
		default:
			out.println("Goodbye!");
		}
	}
}
