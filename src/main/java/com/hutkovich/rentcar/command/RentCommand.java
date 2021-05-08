package com.hutkovich.rentcar.command;

import com.hutkovich.rentcar.logic.RentLogic;
import com.hutkovich.rentcar.logic.exception.BusinessLogicException;
import com.hutkovich.rentcar.logic.exception.RentLogicException;
import com.hutkovich.rentcar.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

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
            throw new BusinessLogicException("Troubles with getting rented car", e);
        }

        return page;
    }
}
