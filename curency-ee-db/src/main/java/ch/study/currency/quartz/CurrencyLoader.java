package ch.study.currency.quartz;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class CurrencyLoader implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		XMLImportService importService = new XMLImportService();
		try {
			importService.importXML();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
