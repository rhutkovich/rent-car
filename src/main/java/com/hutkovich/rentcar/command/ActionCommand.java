/*
 * 22.09.2014 Roman Hutkovich
 */
package com.hutkovich.rentcar.command;

import com.hutkovich.rentcar.logic.exception.BusinessLogicException;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {
    String execute(HttpServletRequest request) throws BusinessLogicException;
}
