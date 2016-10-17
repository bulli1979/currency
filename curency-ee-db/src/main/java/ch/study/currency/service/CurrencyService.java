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
import ch.study.currency.business.ChangeResponse;
@Path("/currency")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CurrencyService {
    private Logger logger = (Logger) LoggerFactory.getLogger("chapters.introduction.CurrencyService");
	
	@GET
	@Path("/change/{amount}/{from}/{to}/")
	public ChangeResponse changeMoneyCheck(@PathParam("from") String from,@PathParam("to") String to,@PathParam("amount") int amount){
		//http://localhost:8080/study/service/currency/change/200/CHF/USD/
		System.out.println(from + " " + to + " " + amount);
		// 1.2 0.9  1000 0.001
		return null;
	}
	
	@GET
	@Path("/getall")
	private void getCurrencys(){
		
		//List<Currency> currencyList = CurrencyData.INSTANCE.getCurrencyList();
	}
}
