package ch.study.currency.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import ch.study.currency.Currency;
import ch.study.currency.CurrencyData;
import ch.study.currency.Tool;
import java.sql.DriverManager;



public class DAOCurrency {

	private static DAOCurrency instance;

	private DAOCurrency() {
	}
	
	
	private Connection connect() throws ClassNotFoundException {
        // SQLite connection string
        Connection conn = null;
        try {
			Class.forName("org.sqlite.JDBC");
        	conn = DriverManager.getConnection("jdbc:sqlite::resource:currency.db");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
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
		String query = "Select * from currencydata where date=?";
		String dayString = Tool.INSTANCE.createStringFromDate(today);
		Connection conn = this.connect();
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, dayString);
		ResultSet rs = ps.executeQuery();
		boolean hasValues = false;
	    while(rs.next() && rs.getInt(1)>0) {
	    	String currency = rs.getString("currency");
	    	double course = rs.getDouble("currencyvalue"); 
	    	CurrencyData.INSTANCE.getCurrencyByShortName(currency).setCourse(course);
	    	System.out.println("found");
	    	hasValues = true;
	    } 
	    
	    rs.close();
	    ps.close();
	    conn.close();
	    System.out.println(hasValues);
		return hasValues;
	}
	
	public void instertCurrency(Currency currency){
		try{
			Connection con = this.connect();
			String sql = "INSERT INTO currencydata (`currency`,`currencyvalue`,`date`) 	VALUES(?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, currency.getShortName());
			ps.setDouble(2, currency.getCourse());
			
			System.out.println("ro:" + con.isReadOnly());
			
			ps.setString(3, Tool.INSTANCE.createStringFromDate(currency.getDate()));
			System.out.println(ps.getMetaData());
			if(ps.execute()){
				System.out.println("insert complete" + currency.getCourse() + " " + currency.getShortName());
			}else{
				System.out.println("insert Failed" + currency.getCourse() + " " + currency.getShortName());
			}
			
			ps.close();
			con.close();
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
