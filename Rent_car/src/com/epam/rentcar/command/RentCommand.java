package com.epam.rentcar.command;

import javax.servlet.http.HttpServletRequest;

import com.epam.rentcar.logic.RentLogic;
import com.epam.rentcar.logic.exception.BusinessLogicException;
import com.epam.rentcar.logic.exception.RentLogicException;
import com.epam.rentcar.resource.ConfigurationManager;

public class RentCommand implements ActionCommand {
    public static final String RENTED_CAR_ID = "rentedId";
    public static final String RENTED_CAR = "rentedCar";
    
    @Override
    public String execute(HttpServletRequest request)
	    throws BusinessLogicException {
	String page = null;
	try {
	    int rentedCarId = Integer.parseInt(request.getParameter(RENTED_CAR_ID));
	    request.getSession().setAttribute(RENTED_CAR_ID, rentedCarId);
	    request.setAttribute(RENTED_CAR, RentLogic.getRentedCar(rentedCarId));
	    page = ConfigurationManager.getProperty("path.page.rent");
	} catch (RentLogicException e) {
	    throw new BusinessLogicException("Troubles with getting rented car",e);
	}
	
	return page;
    }
}
