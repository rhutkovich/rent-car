package com.hutkovich.rentcar.command;

import com.hutkovich.rentcar.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
	String page = ConfigurationManager.getProperty("path.page.index");
	// уничтожение сессии
	request.getSession().invalidate();
	return page;
    }

}
