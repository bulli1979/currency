package ch.study.currency.quartz;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import ch.study.currency.data.DAOCurrency;

/**Class check if there Data for today in the database if not the act courses will be importet from the ezb
 * @author Mirko Eberlein
 *
 */
public class CurrencyLoader implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		XMLImportService importService = new XMLImportService();
		try {
			System.out.println("here");
			if(!DAOCurrency.getInstance().checkDayCurrency()){
				System.out.println("inner");
				importService.importXML();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
