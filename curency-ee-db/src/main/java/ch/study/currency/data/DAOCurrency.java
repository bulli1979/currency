package ch.study.currency.data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.study.currency.Currency;
import ch.study.currency.CurrencyData;
import ch.study.currency.Tool;
import ch.study.currency.action.NumberCalculation;
import ch.study.currency.business.History;

import java.sql.DriverManager;

/**
 * @author Mirko Eberlein
 * class handles all connection to SQL Database Currency Table
 * Singelton
 */
public class DAOCurrency {

	private static DAOCurrency instance;
	//SQLite connection string
	private final static String DBPATH = "jdbc:sqlite::resource:currency.db";
	private Logger logger = (Logger) LoggerFactory.getLogger("ch.study.currency.data.DAOCurrency");
	
	private DAOCurrency() {}
	
	
	private Connection connect() throws ClassNotFoundException {
       
        Connection conn = null;
        try {
			Class.forName("org.sqlite.JDBC");
        	conn = DriverManager.getConnection(DBPATH);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
	

	/** Returns instance allow Construct as Singletonf
	 * @return DAOCurrency instance
	 */
	public static DAOCurrency getInstance() {
		if (instance == null) {
			instance = new DAOCurrency();
		}
		return instance;
	}

	/**
	 * @return true if there values for the current day in database existing
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean checkDayCurrency() throws ClassNotFoundException, SQLException{
		Date today = new Date();
		String query = "Select * from currencydata where date=?";
		String dayString = Tool.INSTANCE.createStringFromDate(today);
		Connection connection = this.connect();
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, dayString);
		ResultSet resultSet = ps.executeQuery();
		boolean hasValues = false;
	    while(resultSet.next() && resultSet.getInt(1)>0) {
	    	String currency = resultSet.getString("currency");
	    	double course = resultSet.getDouble("currencyvalue"); 
	    	CurrencyData.INSTANCE.getCurrencyByShortName(currency).setCourse(course);
	    	hasValues = true;
	    } 
	    
	    resultSet.close();
	    ps.close();
	    connection.close();
		return hasValues;
	}
	
	/** Allows to insert an currency for one day in the database
	 * @param currency
	 */
	public void instertCurrency(Currency currency){
		try{
			Connection con = this.connect();
			String sql = "INSERT INTO currencydata (`currency`,`currencyvalue`,`date`) 	VALUES(?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, currency.getShortName());
			ps.setDouble(2, currency.getCourse());
			ps.setString(3, Tool.INSTANCE.createStringFromDate(currency.getDate()));
			ps.execute();
			ps.close();
			con.close();
		}catch(Exception e){
			logger.error("InsertCurrency error",e);
		}
	}


	public List<History> getHistory(String from, String to) throws ClassNotFoundException, SQLException, ParseException {
		Connection con = this.connect();
		List<History> historyList = new ArrayList<>();
		try{
			String sql = "SELECT * FROM currencydata where currency=? or currency=? order by date";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, from);
			ps.setString(2, to);
			ResultSet resultSet = ps.executeQuery();
			handleResultSet(historyList, resultSet, from, to);
			resultSet.close();
			ps.close();
			con.close();
		}catch(Exception e){
			System.out.println(e);
		}
		return historyList;
	}
	
	private void handleResultSet(List<History> historyList , ResultSet resultSet,String from, String to) throws SQLException{
		History history = null;
		double fromCourse = 0;
		double toCourse = 0;
		double actValue;
		String date = null;
		while(resultSet.next()){
			String dataDate =resultSet.getString("date");
			if(date == null || !date.equals(dataDate)){
				date = dataDate;
				history = new History();
				toCourse = setCourse(to);
				fromCourse = setCourse(from);
				history.setDate(date);
			}
			String currency = resultSet.getString("currency");
			actValue = resultSet.getDouble("currencyvalue");
			if(currency.equals(from)){
				fromCourse = actValue;
			}else if(currency.equals(to)){
				toCourse = actValue;
			}
			if(fromCourse!=0 && toCourse != 0){
				history.setCourse(Tool.round(NumberCalculation.change(fromCourse, toCourse,1), 2));
				historyList.add(history);
			}
		}
	}
	private double setCourse(String currency){
		//Euro is all time 1
		if(currency.equals("EUR")){
			return 1;
		}else{
			return 0;
		}
	}
		
}
