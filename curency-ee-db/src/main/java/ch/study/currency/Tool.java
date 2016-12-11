package ch.study.currency;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ch.study.currency.business.History;
import ch.study.currency.data.DAOCurrency;

/**
 * @author Mirko Eberlein
 * Enum with static helper functions
 * Using Enum as Singelton see Effective Java 
 *
 */
public enum Tool {
	INSTANCE;
	
	private SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * @param dateString need format yyyy-MM-dd (sqllite)
	 * @return a Date created from the given String
	 * @throws ParseException
	 */
	public Date createDateFromString(String dateString) throws ParseException{
		return formater.parse(dateString);
	}
	
	
	/**Create String from date
	 * @param date date who will convert to String
	 * @return date as String in format yyyy-MM-dd
	 */
	public String createStringFromDate(Date date){
		return formater.format(date);
	}
	
	/**Round a given double value to given digit number places
	 * @param value double value to round
	 * @param places Decimal places to round
	 * @return
	 */
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();
	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}


	public static List<History> getHistory(String from, String to) throws ClassNotFoundException, SQLException, ParseException {
		return DAOCurrency.getInstance().getHistory(from,to);
	}
	
}
