package ch.study.currency;

import java.util.Date;

public class CurrencyEuro implements Currency {
	private static final String SHORTNAME = "EU";
	private static final String NAME = "Euro";
	private double course = 0;
	private boolean exist = true;
	private Date date;
	
	public CurrencyEuro() {
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
	 * @param exist the exist to set if export from the ezb is empt there is no return
	 */
	public void setExist(boolean exist) {
		this.exist = exist;
	}

	@Override
	public Date getDate() {
		return date;
	}

	
}
