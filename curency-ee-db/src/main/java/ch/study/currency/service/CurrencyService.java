package ch.study.currency.service;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.jboss.resteasy.spi.validation.ValidateRequest;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Logger;
import ch.study.currency.Currency;
import ch.study.currency.CurrencyData;
import ch.study.currency.Tool;
import ch.study.currency.action.NumberCalculation;
import ch.study.currency.business.ChangeResponse;
/**
 * @author Mirko Eberlein
 * Jax RS Rest Service Mapped auf den api pfad. 
 * Alle Anfragen auf API gehen hier ein und werden auf die entsprechenden Funktionen weitergeleitet.
 *
 */
@Path("/api")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.APPLICATION_JSON)
@ValidateRequest
public class CurrencyService {
   
	private Logger logger = (Logger) LoggerFactory.getLogger("chapters.introduction.CurrencyService");
	
	/**
	 * @param from from which currency will be changed
	 * @param to in which currency will be changed
	 * @param amount amount of money
	 * @return Response with currency data
	 */
	@GET
	@Path("/change/{amount}/{from}/{to}/")	
	public Response changeMoneyCheck(
			@PathParam("from") String from,
			@PathParam("to") String to,
			@PathParam("amount") double amount){
		try{
		return Response.status(Status.OK).entity(new ChangeResponse.Builder(from, to, amount).
				addResult(Tool.round(NumberCalculation.change(CurrencyData.INSTANCE.getCurrencyByShortName(from).getCourse(), 
						CurrencyData.INSTANCE.getCurrencyByShortName(to).getCourse(), amount),2)).addStatus(200).build()).build();
		}catch(Exception e){
			logger.error("Im Restservice changeMoneyCheck ist ein Fehler aufgetreten." ,e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ChangeResponse.Builder(from, to, amount).addResult(0).addStatus(401).addError(e.toString()). build()).build();
		}
	}
	
	
	@GET
	@Path("/history/{from}/{to}/")
	public Response getHistory(
			@PathParam("from") String from,
			@PathParam("to") String to){
		try{
			return Response.status(Status.OK).entity(Tool.getHistory(from,to)).build();
			}catch(Exception e){
				logger.error("Im Restservice changeMoneyCheck ist ein Fehler aufgetreten." ,e);
				return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Error in History " + e).build();
			}
	}
	
	
	/**
	 * @return list of all currencys who are aviable in the system
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@GET
	@Path("/getall")
	public List<Currency> getCurrencys() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		return CurrencyData.INSTANCE.getCurrencyList();
	}
}
