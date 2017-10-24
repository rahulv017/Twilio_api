package com.twilio.sms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalTime;

import com.twilio.exception.ApiConnectionException;
import com.twilio.sms.utils.MessageSender;

/**
 * Servlet implementation class SendSms
 */
@WebServlet("/sms")
public class SendSms extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public SendSms() {
        MessageSender message=new MessageSender();
    }
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String name=request.getParameter("name");
		String phoneNO=request.getParameter("number");
		String message1=request.getParameter("message");
		String time_sleep=request.getParameter("time");
		String time_till=request.getParameter("time1");
		if(name==null||phoneNO==null||message1==null||time_sleep==null||time_till==null)
		{
			String write="Fill in the details fully";
			request.setAttribute("error", write);
			request.getRequestDispatcher("error.jsp").forward(request, response);
			
		}
		
		PrintWriter pr=response.getWriter();
		//pr.println("Thank YOU  Message");
		MessageSender message=new MessageSender();
		int calculate=0;
		int time=1000*60*60; //for Thread.sleep(time)
		int flag=0; //for stopping the application to run at night
		int count=0; //no of send messages
		DateFormat sdf = new SimpleDateFormat("hh:mm aa");
		LocalTime local1 = new LocalTime( time_sleep );
		LocalTime local = new LocalTime( time_till );
	
			

		 
		for(int i=0;i<24;i++)
		{
			int now = LocalTime.now().getHourOfDay(); //joda time api
			//int nightHour=21;
			
			if(now>=local1.getHourOfDay()&&now<=local.getHourOfDay())
			{
				flag=1;
				break;
			}
		
			else
			{
				
			
		 try{
			 count++;
			 Thread.sleep(time);
			 calculate=calculate+time;
			 message.sendMessage(message1+"\n total time the application running: "+calculate+"milliseconds ",phoneNO);
			 
			 
		 }catch(ApiConnectionException e)
		 {
			 System.out.println("Exception "+e);
			  //throw  new ApiConnectionException("error");
			 String message2=e.getMessage();
			 for(int j=0;j<5;j++)
			 {
				 try{
					 count++;
					 message.sendMessage(message2+" \n total time the application running: "+calculate+"milliseconds", phoneNO);
					 Thread.sleep(time);
					 calculate=calculate+time;
					 break;
				 }catch(Exception e1)
				 {
					 System.out.println("Exception "+e1);
				 }
			 }
		 } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}
		
		}
		request.setAttribute("time", calculate);
		request.setAttribute("number_of_messages", count);
		 if(flag==1)
		 {
			 request.getRequestDispatcher("error.jsp").forward(request, response); //if messages are sent  not successful
		 }
		 
		 else
		 {
			 request.getRequestDispatcher("thank.jsp").forward(request, response); //if messages are sent  successfully
			 
		 }
			 
		 
		 
		
	}

	

}
