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
 *
 * The Tool class provides basic operations to convert strings into Date objects and reverse.
 * An Enumerator with static helper functions. (Using Enum as Singelton see Effective Java.)
 *
 */
public enum Tool {
	INSTANCE;
	
	private SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Converts a string which contains a date to a Date object.
	 * @param dateString string to be converted to a Date object. Target format yyyy-MM-dd (required by the SQLite-database)
	 * @return a Date object created from the given String
	 * @throws ParseException	Signals that an error has been reached unexpectedly while parsing.
	 *
	 */
	public Date createDateFromString(String dateString) throws ParseException{
		return formater.parse(dateString);
	}
	
	
	/**
	 * Converts a Date object to a string.
	 * @param date Date object to be converted to String
	 * @return date as String in format yyyy-MM-dd
	 */
	public String createStringFromDate(Date date){
		return formater.format(date);
	}
	
	/**
	 * Round a given double value to given digit number places
	 * @param value double value to be rounded
	 * @param places Decimal places to be rounded
	 * @return rounded input value
	 */
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();
	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}

	/**
	 * Returns a list with the ...
	 * @param from 
	 * @param to 
	 * @return 
	 */
	public static List<History> getHistory(String from, String to) throws ClassNotFoundException, SQLException, ParseException {
		return DAOCurrency.getInstance().getHistory(from,to);
	}
	
}
