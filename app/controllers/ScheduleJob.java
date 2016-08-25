package controllers;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ScheduleJob implements Job {

	public void execute(JobExecutionContext context) throws JobExecutionException {
		final JobDataMap map = context.getJobDetail().getJobDataMap();

		final Boolean isMailJob = map.getBoolean(MailJob.IS_MAIL_JOB);
		Command cmd = null;
		if (null != isMailJob && isMailJob) {
			cmd = getMailCommand(map);
		}		
		cmd.execute();
	}

	public Command getMailCommand(final JobDataMap map) {
		final String from = map.getString(MailJob.FROM);
		final String to = map.getString(MailJob.TO);
		final String msg = map.getString(MailJob.MSG);
		final String sub = map.getString(MailJob.SUB);
		final String pass = map.getString(MailJob.PASSWORD);
		return new MailJob(from, pass, sub, to, msg);
	}
}
