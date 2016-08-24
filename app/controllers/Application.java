package controllers;

import play.mvc.*;
import views.html.*;
import com.fasterxml.jackson.databind.JsonNode;


public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
    public static Result scheduler() {
        return ok(scheduler.render());
    }
    public static Result getSchedulerDetails () throws Exception {
    	 JsonNode json = request().body().asJson();
    	 String from = json.findPath("from").textValue();
    	 String password = json.findPath("password").textValue();
    	 String subject = json.findPath("subject").textValue();
    	 String to = json.findPath("to").textValue();
    	 String message = json.findPath("message").textValue();
    	 String date = json.findPath("date").textValue();
    	 String time = json.findPath("time").textValue();
    	 CronScheduler obj = new CronScheduler(from,password,subject,to,message,date,time);
    	 String res = obj.ScheduleJob();  
    	 return ok(res);
    }    
}

