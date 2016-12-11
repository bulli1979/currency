package ch.study.currency;

import java.util.Date;

/**
 * @author Nicole Wallner
 * @version 0.1
 *
 */

public class CurrencyINR implements Currency {
	private static final String SHORTNAME = "INR";
	private static final String NAME = "Indische Rupie";
	private double course = 0;
	private boolean exist = true;
	private Date date;
	
	public CurrencyINR() {
		date = new Date();
	}
	
	@Override
	public String getShortName() {
		return SHORTNAME;
	}
	
	@Override
	public String getName() {
		return NAME;
	}
	
	@Override
	public Date getDate(){
		return date;
	}
	
	@Override
	public double getCourse() {
		return course;
	}

	@Override
	public void setCourse(double course){
		this.course = course;
	}
	@Override
	public boolean exist() {
		
		return exist;
	}
	/**
	 * @param exist the exist to set
	 */
	public void setExist(boolean exist) {
		this.exist = exist;
	}

}
