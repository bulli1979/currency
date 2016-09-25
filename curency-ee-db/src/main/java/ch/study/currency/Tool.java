package ch.study.currency;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public enum Tool {
	INSTANCE;
	
	private SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

	public Date createDateFromString(String dateString) throws ParseException{
		return formater.parse(dateString);
	}
	
	public String createStringFromDate(Date date){
		return formater.format(date);
	}
}
