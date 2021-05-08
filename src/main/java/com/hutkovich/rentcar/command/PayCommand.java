package com.hutkovich.rentcar.command;

import com.hutkovich.rentcar.entity.Car;
import com.hutkovich.rentcar.logic.LoginLogic;
import com.hutkovich.rentcar.logic.OptionsTransfer;
import com.hutkovich.rentcar.logic.PayLogic;
import com.hutkovich.rentcar.logic.exception.BusinessLogicException;
import com.hutkovich.rentcar.logic.exception.LoginLogicException;
import com.hutkovich.rentcar.logic.exception.PayLogicException;
import com.hutkovich.rentcar.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
