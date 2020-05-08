package com.authentication;


import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionInactive implements HttpSessionListener {

   
    public SessionInactive() {
        // TODO Auto-generated constructor stub
    }

    public void sessionCreated(HttpSessionEvent createEvent)  { 
         // TODO Auto-generated method stub
    	createEvent.getSession().setMaxInactiveInterval(20*60);
    	/*HttpSession session=createEvent.getSession();
    	System.out.println(session.getCreationTime());*/
    	
    }

	
    public void sessionDestroyed(HttpSessionEvent distroyedEvent)  { 
         // TODO Auto-generated method stub
    	HttpSession session=distroyedEvent.getSession();
    	if(session!=null)
    	{
    		session.invalidate();
    		//System.out.println("distroyed");
    	}
    	
    }
	
}
