package controllers;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
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
	public static String from;
	public static String password;
	public static String subject;
	public static String to;
	public static String message;
	public static String date;
	public static String time;
		
	public CronScheduler(String from, String password, String subject, String to, String message, String date, String time){
		CronScheduler.from = from;
		CronScheduler.password = password;
		CronScheduler.subject = subject;
		CronScheduler.to = to;
		CronScheduler.message = message;
		CronScheduler.date = date;
		CronScheduler.time = time;		
	}	
	public String ScheduleJob() throws Exception{
		try{ 
			//parse time and date into cron time format
			String[] time_parts = time.split(":");		
			String second = "0";
			String minute = time_parts[1];	
			if(minute.charAt(0) == '0'){ minute = minute.substring(1); }
			String hour = time_parts[0];	
			if(hour.charAt(0) == '0'){hour = hour.substring(1);}
			String[] date_parts = date.split("-");
			String day = date_parts[2];
			if(day.charAt(0) == '0'){day = day.substring(1);}
			String month = date_parts[1];
			if(month.charAt(0) == '0'){month = month.substring(1);}
			String year = date_parts[0];
			String ScheduleTime =second+" "+minute+" "+hour+" "+day+" "+month+" "+"?"+" "+year;
//			ScheduleTime = "0/5 * * * * ?";
		
			Random rand = new Random();;
			int randomNum = rand.nextInt();
			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
//			scheduler.clear(); 
			JobKey job1Key = JobKey.jobKey("JobName"+randomNum, "group1"+randomNum);
			if (scheduler.checkExists(job1Key))
				scheduler.deleteJob(job1Key);
			JobDetail job = JobBuilder.newJob(ScheduleMailJob.class)
				.withIdentity(job1Key).build();
			
			TriggerKey tk1 = TriggerKey.triggerKey("TriggerName"+randomNum, "group1"+randomNum);		
		    Trigger trigger = TriggerBuilder
		    	.newTrigger()
		    	.withIdentity(tk1)
				.withSchedule(
						CronScheduleBuilder.cronSchedule(ScheduleTime))
				.build();
		    	//schedule it
		    	scheduler.start();
		    	scheduler.scheduleJob(job, trigger);
		    	return "Job Scheduled Successfully";
			}catch(SchedulerException e){
				Logger.info(" Based on given time schedule, the given trigger will never fire.");
				return " Based on given time schedule, the given trigger will never fire.";
		}  		      			    			
	}	 
}
