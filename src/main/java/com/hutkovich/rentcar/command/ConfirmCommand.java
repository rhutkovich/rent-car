package com.hutkovich.rentcar.command;

import com.hutkovich.rentcar.logic.exception.BusinessLogicException;
import com.hutkovich.rentcar.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class ConfirmCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request)
            throws BusinessLogicException {
        String page = null;
        int useTerm = Integer.parseInt(request.getParameter("useTerm"));
        request.setAttribute("useTerm", useTerm);
        request.setAttribute("totalSum", Float.parseFloat(request.getParameter("carCost")) * useTerm);

        page = ConfigurationManager.getProperty("path.page.pay");
        return page;
    }

}
