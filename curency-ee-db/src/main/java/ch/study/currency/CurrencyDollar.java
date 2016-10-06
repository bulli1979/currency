package ch.study.currency;

public class CurrencyDollar implements Currency {
	private static final String SHORTNAME = "USD";
	private static final String NAME = "Dollar";
	private double course = 0;
	private boolean exist = true;
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
	 * @param exist the exist to set
	 */
	public void setExist(boolean exist) {
		this.exist = exist;
	}

}
