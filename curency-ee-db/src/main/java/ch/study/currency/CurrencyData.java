package ch.study.currency;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.reflections.Reflections;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Logger;

/**
 * @author Mirko Eberlein
 * @version 0.1
 *
 */

/**
 * Enumerator as Singleton to have all courses in a singular place stored. Stores all the courses 
 * permanently.
 */
public enum CurrencyData {
	INSTANCE;
    private Logger logger = (Logger) LoggerFactory.getLogger("chapters.introduction.CurrencyData");
	private List<Currency> currencyList;
	private Date date;

	/**
	 * Builds a Currency List once.
	 */
	private CurrencyData() {
		
	}
	
	/**
	 * Creates a Reflections object in which all available Currency classes are stored. A new created ArrayList
	 * is filled with the available Currency classes.
	 * 
	 * @throws ClassNotFoundException	Thrown when an application tries to load in a class through its string name, but no definition for the class with the specified name could be found.
	 * @throws InstantiationException	Thrown when an application tries to create an instance of a class using the newInstance method in class Class, but the specified class object cannot be instantiated.
	 * @throws IllegalAccessException	Thrown when an application tries to reflectively create an instance (other than an array), set or get a field, or invoke a method, but the currently executing method does not have access to the definition of the specified class, field, method or constructor.
	 *
	 */
	private void fillList() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		
		Reflections reflections = new Reflections("ch.study.currency");
		Set<Class<? extends Currency>> currencySet = reflections.getSubTypesOf(Currency.class);	
		currencyList = new ArrayList<>();
		for(Class<? extends Currency> clazz : currencySet){
			currencyList.add(clazz.newInstance());
		}
	}
	
	/**
	 * Returns a List with all available currencies.
	 * 
	 * @return list with all available currencies
	 * 
	 * @throws ClassNotFoundException	Thrown when an application tries to load in a class through its string name, but no definition for the class with the specified name could be found.
	 * @throws InstantiationException	Thrown when an application tries to create an instance of a class using the newInstance method in class Class, but the specified class object cannot be instantiated.
	 * @throws IllegalAccessException	Thrown when an application tries to reflectively create an instance (other than an array), set or get a field, or invoke a method, but the currently executing method does not have access to the definition of the specified class, field, method or constructor.
	 *
	 */
	public List<Currency> getCurrencyList() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		if(currencyList == null){
			fillList();
		}
		return currencyList;
	}
	
	/**
	 * Returns a specific Currency object for which was searched by an acronym.
	 * If the list with the available currencies is empty, the list will be refreshed
	 * by a fillList().
	 * 
	 * @param shortName 	currency acronym
	 * 
	 * @return a Currency object
	 *
	 */
	public Currency getCurrencyByShortName(String shortName){
		try{
			if(currencyList == null){
				fillList();
			}
			for(Currency currency : currencyList){
				if(currency.getShortName().equals(shortName)){
					return currency;
				}
			}	
		}catch(Exception e){
			System.out.println(e);
			logger.error("Error in getCurrencyByShortName: " + e.getMessage());
		}
		return null;
		
	}

	/**
	 * Returns the value in the date variable.
	 * 
	 * @return a Date Object
	 *
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the value in the date variable.
	 * 
	 * @param a Date Object
	 *
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
}
