package com.epam.rentcar.command;

import javax.servlet.http.HttpServletRequest;

import com.epam.rentcar.logic.LoginLogic;
import com.epam.rentcar.logic.UnrentLogic;
import com.epam.rentcar.logic.exception.BusinessLogicException;
import com.epam.rentcar.logic.exception.UnrentLogicException;
import com.epam.rentcar.resource.ConfigurationManager;

public class UnrentCommand implements ActionCommand {
    private static final String PARAM_NAME_RENTED_CARS = "rentedCars";

    @Override
    public String execute(HttpServletRequest request)
	    throws BusinessLogicException {
	String page = null;
	try {
	    UnrentLogic.unrentCar(Integer.parseInt(request.getParameter("carId")));
	} catch (NumberFormatException e) {
	    throw new BusinessLogicException("Wrong car ID!",e);
	} catch (UnrentLogicException e) {
	    throw new BusinessLogicException("Impossible unrent car",e);
	}
	
	request.setAttribute(PARAM_NAME_RENTED_CARS, LoginLogic.getRentedCars());
	page = ConfigurationManager.getProperty("path.page.admin");
	return page;
    }

}
