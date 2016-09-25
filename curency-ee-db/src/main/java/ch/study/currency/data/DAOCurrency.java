package ch.study.currency.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import ch.study.currency.CurrencyData;
import ch.study.currency.Tool;

public class DAOCurrency {

	private static DAOCurrency instance;

	private DAOCurrency() {
	}

	public static DAOCurrency getInstance() {
		if (instance == null) {
			instance = new DAOCurrency();
		}
		return instance;
	}

	public void getDaylyCurses() {

	}

	public void insertDayCurrency() {

	}

	public boolean checkDayCurrency() throws ClassNotFoundException, SQLException{
		Date today = new Date();
		String query = "Select * from currencydata where date=?";
		String dayString = Tool.INSTANCE.createStringFromDate(today);
		Connection con = DBHelper.INSTANCE.getConnection();
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, dayString);
		ResultSet rs = ps.executeQuery();
		boolean hasValues = false;
	    while(rs.next() && rs.getInt(1)>0) {
	    	String currency = rs.getString("currency");
	    	double course = rs.getDouble("value"); 
	    	CurrencyData.INSTANCE.getCurrencyByShortName(currency).setCourse(course);
	    	hasValues = true;
	    } 
	    rs.close();
	    ps.close();
		return hasValues;
	}
}
