package com.hutkovich.rentcar.resource;

public class ConfigurationManager {
    //private final static ResourceBundle rb = ResourceBundle.getBundle("resources.config");
    private ConfigurationManager() {}
    
    public static String getProperty(String key) {
	//return rb.getString(key);
	//TODO make a normal config file!
	switch(key) {
	case "path.page.index": return "/index.jsp";
	case "path.page.login": return "/jsp/login.jsp";
	case "path.page.admin": return "/jsp/admin.jsp";
	case "path.page.main": return "/jsp/main.jsp";
	case "path.page.rent": return "/jsp/rent.jsp";
	case "path.page.pay": return "/jsp/pay.jsp";
	case "path.page.error": return "/jsp/error/error.jsp";
	default : return null;
	}
    }
}
