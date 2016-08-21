package ch.study.currency.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.study.currency.business.ChangeResponse;
@Path("/currency")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CurrencyService {
    private Logger logger = (Logger) LoggerFactory.getLogger("chapters.introduction.CurrencyService");

	@GET
	@Path("/hw")
	public String helloWorld(){
		logger.info("Ich bin eine information");
		logger.error("Ich bin ein Error");
		logger.debug("ich bin der Debug");
		return "{Hello World}";
	}
	
	@GET
	@Path("/change/amount/{amount}/from/{from}/to/{to}/")
	public ChangeResponse changeMoneyCheck(@PathParam("from") String from,@PathParam("to") String to,@PathParam("amount") int amount){
		
		return null;
	}
	
	
	
}
