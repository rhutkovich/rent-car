/*
 * 22.09.2014 Roman Hutkovich
 */
package com.epam.rentcar.command;

import javax.servlet.http.HttpServletRequest;

import com.epam.rentcar.logic.exception.BusinessLogicException;

public interface ActionCommand {
    public String execute(HttpServletRequest request) throws BusinessLogicException;
}
