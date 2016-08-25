package controllers;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import java.util.Random;
import play.Logger;

public class CronScheduler {
	private static Random rand = new Random();

	public static String ScheduleJob(final String jobName, final String SchedulerTimePattern,
			final JobDataMap jobDataMap) throws Exception {
		try {

			final String ScheduleTime = SchedulerTimePattern;
			final int randomNum = rand.nextInt();
			final Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			final JobKey jobKey = JobKey.jobKey(jobName + randomNum, jobName + "_group" + randomNum);
			if (scheduler.checkExists(jobKey))
				scheduler.deleteJob(jobKey);

			final JobDetail job = JobBuilder.newJob(ScheduleJob.class).usingJobData(jobDataMap).withIdentity(jobKey)
					.build();
			final TriggerKey tk = TriggerKey.triggerKey(jobName + randomNum, jobName + "_group" + randomNum);
			final Trigger trigger = TriggerBuilder.newTrigger().withIdentity(tk)
					.withSchedule(CronScheduleBuilder.cronSchedule(ScheduleTime)).build();

			scheduler.start();
			scheduler.scheduleJob(job, trigger);
			return "Job Scheduled Successfully";
		} catch (final SchedulerException e) {
			Logger.info(" Based on given time schedule, the given trigger will never fire.");
			return " Based on given time schedule, the given trigger will never fire.";
		}
		catch (final RuntimeException e) {
			Logger.info("Cron Expression Format is not Correct, Either Day or Week Should be ?.");
			return "Cron Expression Format is not Correct, Either Day or Week Should be ?.";
		}
	}	
}
