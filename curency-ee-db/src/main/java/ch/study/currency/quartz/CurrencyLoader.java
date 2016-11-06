package ch.study.currency.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.study.currency.data.DAOCurrency;

/**
 * Class check if there Data for today in the database if not the act courses
 * will be importet from the ezb
 * 
 * @author Mirko Eberlein
 *
 */
public class CurrencyLoader implements Job {
	private Thread thread;
    private Logger logger = (Logger) LoggerFactory.getLogger("chapters.introduction.CurrencyService");

	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 * Function is run on time event defined in QuartzForCurrencyData
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		XMLImportService importService = new XMLImportService();
		logger.info("Job Daten von Zentralbank holen startet.");
		try {
			if (!DAOCurrency.getInstance().checkDayCurrency()) {
				thread = Thread.currentThread();
				importService.importXML();
			}
		} catch (Exception e) {
			logger.error("An error in Quarz Job execute ",e);
		}
	}

	
	/** Function to handle server shut downs to get no memory leak
	 * @throws UnableToInterruptJobException
	 */
	public void interrupt() throws UnableToInterruptJobException {
		thread.interrupt();
		try {
			thread.join();
		} catch (InterruptedException e) {
			logger.error("UnableToInterruptJobException ",e);
			throw new UnableToInterruptJobException(e);
		} finally {
			// ... do cleanup
		}
	}

}
