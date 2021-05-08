/*
 * Filename: StudentCourses.java
* Author: Stefanski
* 02/25/2020 
 */
package jpa.entitymodels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author Harry
 *
 */
@Entity

@Table( name="StudentCourses")
@IdClass(StudentCoursesID.class)
@NamedQueries({
	@NamedQuery( name="CoursesByStudent", query="Select c from StudentCourses c where c.scEmail = :email")
})
public class StudentCourses {
	@Id
	@Column(name="student_cEmail")
	private String scEmail;
	
	public StudentCourses() {}
	
	/**
	 * @param scEmail
	 * Id from Student
	 * @param courseID
	 * Id from Course
	 */
	public StudentCourses(String scEmail, int courseID) {
		this.scEmail = scEmail;
		this.courseID = courseID;
	}

	@Id
	@Column(name="course_id")
	private int courseID;

	/**
	 * @return the cEmail
	 */
	public String getScEmail() {
		return scEmail;
	}

	/**
	 * @param cEmail the cEmail to set
	 */
	public void setcEmail(String cEmail) {
		this.scEmail = cEmail;
	}

	/**
	 * @return the courseID
	 */
	public int getCourseID() {
		return courseID;
	}

	/**
	 * @param courseID the courseID to set
	 */
	public void setCourseID(int courseID)	 {
		this.courseID = courseID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + courseID;
		result = prime * result + ((scEmail == null) ? 0 : scEmail.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentCourses other = (StudentCourses) obj;
		if (courseID != other.courseID)
			return false;
		if (scEmail == null) {
			if (other.scEmail != null)
				return false;
		} else if (!scEmail.equals(other.scEmail))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StudentCourses{" +
				"cEmail='" + scEmail + '\'' +
				", courseID=" + courseID +
				'}';
	}
}
