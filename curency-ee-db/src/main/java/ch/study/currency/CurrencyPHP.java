package ch.study.currency;

import java.util.Date;

public class CurrencyPHP implements Currency {
	private static final String SHORTNAME = "PHP";
	private static final String NAME = "Phillipinischer Peso";
	private double course = 0;
	private boolean exist = true;
	private Date date;
	
	public CurrencyPHP() {
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
