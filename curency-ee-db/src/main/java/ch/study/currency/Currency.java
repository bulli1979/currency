package ch.study.currency;

public interface Currency {
	public String getShortName();
	public String getName();
	public double getCourse();
	public void setCourse(double course);
	public boolean exist();
}
