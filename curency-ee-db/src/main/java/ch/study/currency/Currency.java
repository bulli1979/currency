package ch.study.currency;

import java.util.Date;

/**
 * @author Mirko Eberlein
 * @version 0.1
 *
 */

/**
 * Interface for all currencies.
 * 
 */
public interface Currency {
	public String getShortName();
	public String getName();
	public Date getDate();
	public double getCourse();
	public void setCourse(double course);
	public boolean exist();
}
