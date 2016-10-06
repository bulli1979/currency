package ch.study.currency;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.reflections.Reflections;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Logger;

/**
 * Enum as Singleton to have all courses in one place stores the cursses of all
 * courses permantently
 */
public enum CurrencyData {
	INSTANCE;
    private Logger logger = (Logger) LoggerFactory.getLogger("chapters.introduction.CurrencyData");
	private List<Currency> currencyList;
	private Date date;

	/**
	 * Build Currency List once
	 */
	private CurrencyData() {}
	private void fillList() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		
		Reflections reflections = new Reflections("ch.study.currency");
		Set<Class<? extends Currency>> currencySet = reflections.getSubTypesOf(Currency.class);	
		currencyList = new ArrayList<>();
		for(Class<? extends Currency> clazz : currencySet){
			currencyList.add(clazz.newInstance());
		}
	}
	public List<Currency> getCurrencyList() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		logger.info("Huhu");
		if(currencyList == null){
			fillList();
		}
		return currencyList;
	}
	
	public Currency getCurrencyByShortName(String shortName){
		try{
			for(Currency currency : currencyList){
				if(currency.getShortName().equals(shortName)){
					return currency;
				}
			}	
		}catch(Exception e){
			logger.error("Error in getCurrencyByShortName: " + e.getMessage());
		}
		return null;
		
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
}
