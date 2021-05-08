package com.hutkovich.rentcar.resource;

public class MessageManager {
    //private final static ResourceBundle rb = ResourceBundle.getBundle("resources.config");
    private MessageManager() {}
    
    public static String getProperty(String key) {
	//return rb.getString(key);
	//TODO make nornal config file!
	switch(key) {
	case "message.loginerror": return "Incorrect login or password.";
	case "message.nullpage": return "Page not found. Business logic error.";
	case "message.wrongaction": return ": command not found or worng!";
	default : return null;
	}
    }
}
