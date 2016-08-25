Java Play Cron Job Scheduler API
=====================================
# Email Scheduler Application

##Problem Statement:

  Develop a pseudo Advanced Java/Scala web application (By building backend and writing APIs) which would
   	let you to schedule your email/sms on regular interval.
	    
  *Make a general purpose scheduler which takes parameters similar to the cron daemon in UNIX. One of the parameter
   	will be the time string and another will be the actual command to be executed.
  *For Example :- We would like to schedule an automatics email every third	Sunday of each month.
	. /my program 0 , 2 , * , * , * // path/ to / email "Hello , welcome to greedy world . . ! "
 
##Technology Used:

 **Front end:** Html, Bootstrap, Javascript

 **Backend:** Java 1.8, Play Framework 2.2.6, Quartz 2.2, JavaMail 1.5


##Application Overview:

  * An application that schedule the email on regular interval.
  * Using HTML input form get the value of schedule time, from email, to email, email subject and email body.
  * On submit input form value schedule the email based on the given user input information.
  * Concurrent job schedule & multiple job at a time is possible. 

##Implementation Overview:

  * Create an application using the java play framework.
  * Design simple HTML input form that takes input value of time, from email, to email, email subject and email body.
  * Convert the input time value into cron time expression.
  * Used ajax for client server communication, quartz for schedule cron job and java mail for send mail.
  * On ajax call send all value to  "getSchedulerDetails" API.
  * Now after getting all the details, trigger the cron job.
  * Used Random function to make jobkey unique

##Limitation:
  
  * Currently some time expression(2015-2018, 0/5, 6#3) is not possible to take input using UI form.

 