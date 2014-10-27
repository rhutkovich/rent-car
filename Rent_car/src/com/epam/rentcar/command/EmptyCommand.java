package com.epam.rentcar.command;

import javax.servlet.http.HttpServletRequest;

import com.epam.rentcar.resource.ConfigurationManager;

public class EmptyCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
	/* В случае ошибки или прямого обращения к контроллеру
	 * переадресация на страницу ввода логина
	 */
	String page = ConfigurationManager.getProperty("path.page.login");
	return page;
    }

}
