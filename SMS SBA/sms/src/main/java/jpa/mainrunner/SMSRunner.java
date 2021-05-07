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
			out.println("Goodbye!");
			break;

		default:

		}
	}

	private int menu1() {
		sb.append("\n1.Student Login\n2. Quit Application\nPlease Enter Selection: ");
		out.print(sb); //sb.toString()->sb. In case it doesn't work
		sb.delete(0, sb.length());

		return sin.nextInt();
	}

	private boolean studentLogin() {
		boolean retValue = false;
		out.print("Enter your email address: ");
		String email = sin.next();
		out.print("Enter your password: ");
		String password = sin.next();

		List<Student> students = (List<Student>) studentService.getStudentByEmail(email);
		if (students != null) {
			currentStudent = students.get(0);
		}

		if (currentStudent != null & currentStudent.getSPass().equals(password)) {
			List<Course> courses = studentService.getStudentCourse(email);
			out.println("MyClasses");
			for (Course course : courses) {
				out.println(course);
			}
			retValue = true;
		} else {
			out.println("User Validation failed. GoodBye!");
		}
		return retValue;
	}

	private void registerMenu() {
		sb.append("\n1.Register a class\n2. Logout\nPlease Enter Selection: ");
		out.print(sb); //sb.toString()->sb. In case it doesn't work
		sb.delete(0, sb.length());

		switch (sin.nextInt()) {
		case 1:
			List<Course> allCourses = courseService.getAllCourses();
			List<Course> studentCourses = studentService.getStudentCourse(currentStudent.getSEmail());
			allCourses.removeAll(studentCourses);
			out.printf("%5s%15S%15s\n", "ID", "Course", "Instructor");
			for (Course course : allCourses) {
				out.println(course);
			}
			out.println();
			out.print("Enter Course Number: ");
			int number = sin.nextInt();
			Course newCourse = courseService.getCourseById(number);//removed .get(0) from end

			if (newCourse != null) {
				studentService.registerStudentToCourse(currentStudent.getSEmail(), newCourse.getCId());
				Student temp = studentService.getStudentByEmail(currentStudent.getSEmail());//removed .get(0) from end
				
				StudentCourseService scService = new StudentCourseService();
				List<Course> sCourses = scService.getAllStudentCourses(temp.getSEmail());
				

				out.println("MyClasses");
				for (Course course : sCourses) {
					out.println(course);
				}
			}
			break;
		case 2:
		default:
			out.println("Goodbye!");
		}
	}
}
