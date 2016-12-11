package ch.study.currency.quartz;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;


/**
 * 
 * @author Mirko Eberlein
 * @version 0.1
 *
 */

/**
 * The QuartzForCurrencyData class creates a new job for the Quartz Job Scheduler. The created job will be
 * executed in a 24 hour interval. 
 *
 */
@WebListener
public class QuartzForCurrencyData extends QuartzInitializerListener{
	private final int HOURINTERVAL = 24;
	private final String IDENTITY = "simple";
    private Logger logger = (Logger) LoggerFactory.getLogger("chapters.introduction.QuartzForCurrencyData");

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		super.contextInitialized(sce);
		ServletContext ctx = sce.getServletContext();
		StdSchedulerFactory factory = (StdSchedulerFactory) ctx.getAttribute(QUARTZ_FACTORY_KEY);
		try {
			Scheduler scheduler = factory.getScheduler();
			JobDetail jobDetail = JobBuilder.newJob(CurrencyLoader.class).build();
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity(IDENTITY)
					.withSchedule( SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(HOURINTERVAL).repeatForever()).build();
			scheduler.scheduleJob(jobDetail, trigger);
			scheduler.start();
		} catch (Exception e) {
			logger.error("Error in QuartzForCurrencyData " + e.getMessage());
		}
	}
}
