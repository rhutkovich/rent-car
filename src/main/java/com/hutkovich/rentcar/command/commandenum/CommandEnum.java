package com.hutkovich.rentcar.command.commandenum;

import com.hutkovich.rentcar.command.*;

/**
 * Перечесление, хранящее ссылки на объект каждой команды, которую способен
 * обработать сервлет.
 *
 * @author Roman Hutkovich
 * @version 1.0 20.09.2014
 */
public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    RENT {
        {
            this.command = new RentCommand();
        }
    },
    OPTIONS {
        {
            this.command = new OptionsCommand();
        }
    },
    CONFIRM {
        {
            this.command = new ConfirmCommand();
        }
    },
    PAY {
        {
            this.command = new PayCommand();
        }
    },
    UNRENT {
        {
            this.command = new UnrentCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
