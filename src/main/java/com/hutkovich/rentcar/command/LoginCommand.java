package com.hutkovich.rentcar.command;

import com.hutkovich.rentcar.entity.Account;
import com.hutkovich.rentcar.entity.Administrator;
import com.hutkovich.rentcar.entity.Car;
import com.hutkovich.rentcar.entity.Client;
import com.hutkovich.rentcar.logic.LoginLogic;
import com.hutkovich.rentcar.logic.OptionsTransfer;
import com.hutkovich.rentcar.logic.exception.BusinessLogicException;
import com.hutkovich.rentcar.logic.exception.LoginLogicException;
import com.hutkovich.rentcar.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/** 
 * @version 1.0 20.09.2014
 * @author Roman Hutkovich
 */
public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_ADMIN = "admin";
    private static final String PARAM_NAME_ADMIN_ID = "adminId";
    private static final String PARAM_NAME_USER = "user";
    private static final String PARAM_NAME_USER_ID = "userId";
    private static final String PARAM_NAME_FREE_CARS = "freeCars";
    private static final String PARAM_NAME_OPTIONS = "options";
    private static final String PARAM_NAME_RENTED_CARS = "rentedCars";
    
    @Override
    public String execute(HttpServletRequest request) throws BusinessLogicException {
	String page = null;
	// извлечение из запроса логина и пароля
	String login = request.getParameter(PARAM_NAME_LOGIN);
	String pass = request.getParameter(PARAM_NAME_PASSWORD);
	
	try {
	    Account account = LoginLogic.getAccount(login, pass);
	    if (account != null) {
		Administrator admin = LoginLogic.getAdmin(account);
		if (admin != null) {
		    request.getSession().setAttribute(PARAM_NAME_ADMIN, admin);
		    request.getSession().setAttribute(PARAM_NAME_ADMIN_ID, admin.getId());
		    request.setAttribute(PARAM_NAME_RENTED_CARS, LoginLogic.getRentedCars());
		    page = ConfigurationManager.getProperty("path.page.admin");
		} else {
		    Client client = LoginLogic.getClient(account);
		    if (client != null) {
			request.getSession().setAttribute(PARAM_NAME_USER, client);
			request.getSession().setAttribute(PARAM_NAME_USER_ID, client.getId());
			List<Car> freeCars = LoginLogic.getFreeCars();
			request.setAttribute(PARAM_NAME_FREE_CARS, freeCars);
			request.setAttribute(PARAM_NAME_OPTIONS, new OptionsTransfer(freeCars)/*LoginLogic.getOptions(freeCars)*/);
			page = ConfigurationManager.getProperty("path.page.main");
		    }
		    // TODO что делать если null?
		}
	    } else {
		request.setAttribute("errorLoginPassMessage",
		 	   "login.error"); // MessageManager.getProperty("message.loginerror")
		page = ConfigurationManager.getProperty("path.page.login");
	    }
	} catch (LoginLogicException e) {
	    throw new BusinessLogicException("Login exception",e);
	}
	
	return page;
    }
}
