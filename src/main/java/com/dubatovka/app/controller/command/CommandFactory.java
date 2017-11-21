package com.dubatovka.app.controller.command;

import com.dubatovka.app.controller.command.impl.*;
import com.dubatovka.app.controller.command.impl.authorization.LoginCommand;
import com.dubatovka.app.controller.command.impl.authorization.LogoutCommand;
import com.dubatovka.app.controller.command.impl.authorization.RegisterCommand;
import com.dubatovka.app.controller.command.impl.ShowAllPlayersCommand;
import com.dubatovka.app.entity.User;
import com.dubatovka.app.service.ConfigConstant;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

import static com.dubatovka.app.service.ConfigConstant.ATTR_ROLE;

public class CommandFactory {
    private static final Logger logger = LogManager.getLogger(CommandFactory.class);
    private static HashMap<CommandType, Command> guestCommands = new HashMap<>();
    private static HashMap<CommandType, Command> playerCommands = new HashMap<>();
    private static HashMap<CommandType, Command> adminCommands = new HashMap<>();
    
    static {
        guestCommands.put(CommandType.GOTO_INDEX, new GotoIndexCommand());
        guestCommands.put(CommandType.REGISTER, new RegisterCommand());
        guestCommands.put(CommandType.LOGIN, new LoginCommand());
        guestCommands.put(CommandType.LOGOUT, new LogoutCommand());
        guestCommands.put(CommandType.CHANGE_LOCALE, new ChangeLocaleCommand());
        
        guestCommands.put(CommandType.SHOW_ALL_PLAYERS, new ShowAllPlayersCommand());

        playerCommands.putAll(guestCommands);
        playerCommands.put(CommandType.LOGOUT, new LogoutCommand());
        
        adminCommands.put(CommandType.GOTO_INDEX, new GotoIndexCommand());
        adminCommands.put(CommandType.CHANGE_LOCALE, new ChangeLocaleCommand());
    }
    
    private enum CommandType {
        GOTO_INDEX,
        REGISTER,
        LOGIN,
        LOGOUT,
        CHANGE_LOCALE,
        SHOW_ALL_PLAYERS,
        GOTO_PAGINATION;
    }
    
    
    private CommandFactory() {
    }
    
    
    public static Command defineCommand(HttpServletRequest request) {
        String commandTypeName = request.getParameter(ConfigConstant.PARAM_COMMAND_TYPE);
        User.UserRole role = (User.UserRole) request.getSession().getAttribute(ATTR_ROLE);
        boolean validCommandTypeName = validateCommandTypeName(commandTypeName);
        Command command;
        
        if (validCommandTypeName) {
            commandTypeName = commandTypeName.trim().toUpperCase();
            CommandType commandType = CommandType.valueOf(commandTypeName);
            switch (role) {
                case PLAYER:
                    command = playerCommands.get(commandType);
                    break;
                case ADMIN:
                    command = adminCommands.get(commandType);
                    break;
                default:
                    command = guestCommands.get(commandType);
            }
            
            if (command == null) {
                logger.log(Level.ERROR, "Command implementation is not defined for command type " + commandType + " Check class: " + CommandFactory.class.getName());
                
                command = new GotoIndexCommand();
            }
        } else {
            logger.log(Level.ERROR, "Request doesn't have command_type parameter or defined command_type parameter is invalid: " + commandTypeName + ". Check web page: " + request.getHeader("referer"));
            
            command = new GotoIndexCommand();
        }
        
        return command;
    }
    
    private static boolean validateCommandTypeName(String commandName) {
        if (commandName == null || commandName.trim().isEmpty()) {
            return false;
        }
        for (CommandType type : CommandType.values()) {
            if (type.toString().equalsIgnoreCase(commandName)) {
                return true;
            }
        }
        return false;
    }
}