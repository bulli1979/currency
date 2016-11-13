package ch.study.currency.service;

import java.util.List;

import javax.validation.constraints.Pattern;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.spi.validation.ValidateRequest;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.study.currency.Currency;
import ch.study.currency.CurrencyData;
import ch.study.currency.action.NumberCalculation;
import ch.study.currency.business.ChangeResponse;
@Path("/api")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.APPLICATION_JSON)
@ValidateRequest
public class CurrencyService {
    private Logger logger = (Logger) LoggerFactory.getLogger("chapters.introduction.CurrencyService");
	
	@GET
	@Path("/change/{amount}/{from}/{to}/")
	
	public Response changeMoneyCheck(
			@PathParam("from") String from,
			@PathParam("to") String to,
			@PathParam("amount") double amount){
		try{
		return Response.status(Status.OK).entity(new ChangeResponse.Builder(from, to, amount).
				addResult(NumberCalculation.change(CurrencyData.INSTANCE.getCurrencyByShortName(from).getCourse(), 
						CurrencyData.INSTANCE.getCurrencyByShortName(to).getCourse(), amount)).addStatus(200).build()).build();
		}catch(Exception e){
			logger.error("Im Restservice changeMoneyCheck ist ein Fehler aufgetreten." ,e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ChangeResponse.Builder(from, to, amount).addResult(0).addStatus(401).addError(e.toString()). build()).build();
		}
	}
	
	@GET
	@Path("/getall")
	public List<Currency> getCurrencys() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		return CurrencyData.INSTANCE.getCurrencyList();
	}
}
