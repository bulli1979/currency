package ch.study.currency.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

import ch.study.currency.Currency;
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

	public boolean checkDayCurrency() throws ClassNotFoundException, SQLException{
		Date today = new Date();
		System.out.println("here");
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
	    	System.out.println("Course" + CurrencyData.INSTANCE.getCurrencyByShortName(currency).getCourse());
	    	hasValues = true;
	    } 
	    
	    rs.close();
	    ps.close();
	    System.out.println(hasValues);
		return hasValues;
	}
	
	public void instertCurrency(Currency currency) throws SQLException, ClassNotFoundException{
		Connection con = DBHelper.INSTANCE.getConnection();
		String id = UUID.randomUUID().toString();
		String sql = "Instert into currency ('id','currency','value','date') values(?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(0, id);
		ps.setString(1, currency.getShortName());
		ps.setDouble(2, currency.getCourse());
		ps.setString(3, Tool.INSTANCE.createStringFromDate(currency.getDate()));
		ps.execute();
		ps.close();
		
	}
}
