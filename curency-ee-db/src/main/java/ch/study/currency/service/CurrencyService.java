package ch.study.currency.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.study.currency.Currency;
import ch.study.currency.CurrencyData;
import ch.study.currency.action.ChangeMoneyClass;
import ch.study.currency.business.ChangeResponse;
@Path("/api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CurrencyService {
    private Logger logger = (Logger) LoggerFactory.getLogger("chapters.introduction.CurrencyService");
	
	@GET
	@Path("/change/{amount}/{from}/{to}/")
	public ChangeResponse changeMoneyCheck(@PathParam("from") String from,@PathParam("to") String to,@PathParam("amount") double amount){
		
		return new ChangeResponse.Builder(from, to, amount).
				addResult(ChangeMoneyClass.change(CurrencyData.INSTANCE.getCurrencyByShortName(from).getCourse(), 
						CurrencyData.INSTANCE.getCurrencyByShortName(to).getCourse(), amount)).build();
		
	}
	
	@GET
	@Path("/getall")
	public List<Currency> getCurrencys() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		return CurrencyData.INSTANCE.getCurrencyList();
		//List<Currency> currencyList = CurrencyData.INSTANCE.getCurrencyList();
	}
}
