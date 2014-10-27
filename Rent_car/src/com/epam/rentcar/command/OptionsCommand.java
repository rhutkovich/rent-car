package com.epam.rentcar.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.epam.rentcar.entity.Car;
import com.epam.rentcar.logic.LoginLogic;
import com.epam.rentcar.logic.OptionsLogic;
import com.epam.rentcar.logic.exception.BusinessLogicException;
import com.epam.rentcar.logic.exception.LoginLogicException;
import com.epam.rentcar.resource.ConfigurationManager;

public class OptionsCommand implements ActionCommand {
    private static final String PARAM_NAME_SUITABLE_CARS = "freeCars";
    private static final String PARAM_NAME_OPTIONS = "options";
    
    @Override
    public String execute(HttpServletRequest request)
	    throws BusinessLogicException {
	String page = null;
	try {
	    List<Car> freeCars = LoginLogic.getFreeCars();
	    request.setAttribute(PARAM_NAME_SUITABLE_CARS, OptionsLogic.getSuitableCarsList(request, freeCars));	
	    request.setAttribute(PARAM_NAME_OPTIONS, OptionsLogic.getOptions(request, freeCars));
	    page = ConfigurationManager.getProperty("path.page.main");
	} catch (LoginLogicException e) {
	    throw new BusinessLogicException("Option command trouble",e);
	}
	return page;
    }

}
