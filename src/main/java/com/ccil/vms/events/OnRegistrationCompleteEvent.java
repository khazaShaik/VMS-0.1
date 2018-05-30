package com.ccil.vms.events;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import com.ccil.vms.model.UserIdentity;

public class OnRegistrationCompleteEvent extends ApplicationEvent {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String appUrl;
    private Locale locale;
    private UserIdentity userIdentity;
 
    public OnRegistrationCompleteEvent(
      UserIdentity userIdentity, Locale locale, String appUrl) {
        super(userIdentity);
         
        this.setUserIdentity(userIdentity);
        this.locale = locale;
        this.appUrl = appUrl;
    }

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public UserIdentity getUserIdentity() {
		return userIdentity;
	}

	public void setUserIdentity(UserIdentity userIdentity) {
		this.userIdentity = userIdentity;
	}

	
     
  
}
