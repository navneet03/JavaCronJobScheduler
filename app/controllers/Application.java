package controllers;

import play.mvc.*;
import views.html.*;

import org.quartz.JobDataMap;

import com.fasterxml.jackson.databind.JsonNode;

public class Application extends Controller {

	public static Result index() {
		return ok(index.render());
	}

	public static Result scheduler() {
		return ok(scheduler.render());
	}

	public static Result getSchedulerDetails() throws Exception {
		final JsonNode json = request().body().asJson();
		final String SchedulerTimePattern = json.findPath("SchedulerTimePattern").textValue();				
		final JobDataMap mailJobDataMap = getMailJobDataMap(json);
		return ok(CronScheduler.ScheduleJob("mail", SchedulerTimePattern, mailJobDataMap));
	}

	public static JobDataMap getMailJobDataMap(final JsonNode jsonNode) {
		final JobDataMap mailJobDataMap = new JobDataMap();
		mailJobDataMap.put(MailJob.FROM, jsonNode.findPath(MailJob.FROM).textValue());
		mailJobDataMap.put(MailJob.TO, jsonNode.findPath(MailJob.TO).textValue());
		mailJobDataMap.put(MailJob.SUB, jsonNode.findPath(MailJob.SUB).textValue());
		mailJobDataMap.put(MailJob.MSG, jsonNode.findPath(MailJob.MSG).textValue());
		mailJobDataMap.put(MailJob.PASSWORD, jsonNode.findPath(MailJob.PASSWORD).textValue());
		mailJobDataMap.put(MailJob.IS_MAIL_JOB, true);
		return mailJobDataMap;
	}
}
