package ch.study.currency.business;

/**
 * @author Mirko Eberlein
 * @version 0.1
 *
 */

/**
 * A History class object keeps a date, and a historical course and the class provides the necessary getter and setter methods.
 * 
 */
public class History {
	private String date;
	private double course;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getCourse() {
		return course;
	}
	public void setCourse(double course) {
		this.course = course;
	}
	
}
