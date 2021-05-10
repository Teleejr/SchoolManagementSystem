/*
 * Filename: StudentCourseId.java
* Author: Stefanski
* 02/25/2020 
 */
package jpa.entitymodels;

import java.io.Serializable;

import javax.persistence.Column;

/**
* Key class for composite key in Student Courses table
 * @author Harry
 *
 */
public class StudentCoursesID implements Serializable {

	private static final long serialVersionUID = 1L;

	private String scEmail;
	private int courseID;

	public StudentCoursesID() {
	}

	public StudentCoursesID(String email, String courseId) {
		this.setScEmail(email);
		this.setCourseID(courseID);
	}

	/**
	 * @return the scEmail
	 */
	public String getScEmail() {
		return scEmail;
	}

	/**
	 * @param scEmail
	 *            the scEmail to set
	 */
	public void setScEmail(String scEmail) {
		this.scEmail = scEmail;
	}

	/**
	 * @return the courseID
	 */
	public int getCourseID() {
		return courseID;
	}

	/**
	 * @param courseID
	 *            the courseID to set
	 */
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	/*
	 * (non-Javadoc)
	 * 
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

	/*
	 * (non-Javadoc)
	 * 
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
		StudentCoursesID other = (StudentCoursesID) obj;
		if (courseID != other.courseID)
			return false;
		if (scEmail == null) {
			if (other.scEmail != null)
				return false;
		} else if (!scEmail.equals(other.scEmail))
			return false;
		return true;
	}
}
