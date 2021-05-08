package com.hutkovich.rentcar.command;

import com.hutkovich.rentcar.entity.Car;
import com.hutkovich.rentcar.logic.LoginLogic;
import com.hutkovich.rentcar.logic.OptionsLogic;
import com.hutkovich.rentcar.logic.exception.BusinessLogicException;
import com.hutkovich.rentcar.logic.exception.LoginLogicException;
import com.hutkovich.rentcar.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
