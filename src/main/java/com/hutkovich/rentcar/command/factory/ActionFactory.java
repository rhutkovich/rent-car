package com.hutkovich.rentcar.command.factory;

import com.hutkovich.rentcar.command.ActionCommand;
import com.hutkovich.rentcar.command.EmptyCommand;
import com.hutkovich.rentcar.command.commandenum.CommandEnum;
import com.hutkovich.rentcar.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Фабрика комманд. Производит команду в зависимости от значение
 * параметра запроса "command".
 * Если приходит неверный параметр, то выдается "пустая комманда".
 *
 * @author Roman Hutkovich
 * @version 1.0 21.09.2014
 */
public class ActionFactory {
    private static final String PARAM_NAME_COMMAND = "command";

    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = null;
        String action = request.getParameter(PARAM_NAME_COMMAND);

        if (action == null || action.isEmpty()) {
            current = new EmptyCommand();

            return current;
        }

        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
        }

        return current;
    }
}
