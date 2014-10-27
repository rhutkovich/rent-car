package com.epam.rentcar.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.epam.rentcar.entity.Car;
import com.epam.rentcar.logic.LoginLogic;
import com.epam.rentcar.logic.OptionsTransfer;
import com.epam.rentcar.logic.PayLogic;
import com.epam.rentcar.logic.exception.BusinessLogicException;
import com.epam.rentcar.logic.exception.LoginLogicException;
import com.epam.rentcar.logic.exception.PayLogicException;
import com.epam.rentcar.resource.ConfigurationManager;

public class PayCommand implements ActionCommand {

    private static final String PARAM_NAME_FREE_CARS = "freeCars";
    private static final String PARAM_NAME_OPTIONS = "options";

    @Override
    public String execute(HttpServletRequest request)
	    throws BusinessLogicException {
	String page  = null;
	try {
	    int userId = (Integer) request.getSession().getAttribute("userId");
	    int carId = (Integer) request.getSession().getAttribute("rentedId");
	    int term = Integer.parseInt(request.getParameter("useTerm"));
	    
	    if (PayLogic.rentCar(userId,carId,term)) {
		page = ConfigurationManager.getProperty("path.page.main");
		List<Car> freeCars = LoginLogic.getFreeCars();
		request.setAttribute(PARAM_NAME_FREE_CARS, freeCars);
		request.setAttribute(PARAM_NAME_OPTIONS, new OptionsTransfer(freeCars));
	    }
	} catch (PayLogicException e) {
	    throw new BusinessLogicException("Pay logic troubles",e);
	} catch (LoginLogicException e) {
	    throw new BusinessLogicException("Login logic troubles in pay command",e);
	}
	
	return page;
    }

}
