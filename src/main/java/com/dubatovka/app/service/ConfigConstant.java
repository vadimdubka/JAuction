package com.dubatovka.app.service;

public class ConfigConstant {
    /**
     * Locale strings
     */
    public static final String DEFAULT_LOCALE = "default";
    public static final String LOCALE_RU = "ru_RU";
    public static final String LOCALE_EN = "en_US";
    
    /**
     * Additional string constants
     */
    public static final char MESSAGE_SEPARATOR = ' ';
    public static final String EMPTY_STRING = "";
    public static final String WHITESPACE = " ";
    
    
    /**
     * Attribute names.
     */
    public static final String ATTR_PLAYERS = "players";
    public static final String ATTR_PAGE_MODEL_BUILDER = "page_model_builder";
    public static final String ATTR_ROLE = "role";
    public static final String ATTR_USER = "user";
    public static final String ATTR_PLAYER = "player";
    public static final String ATTR_ADMIN = "admin";
    public static final String ATTR_LOCALE = "locale";
    public static final String ATTR_ERROR_MESSAGE = "errorMessage";
    public static final String ATTR_EMAIL_INPUT = "email_input";
    public static final String ATTR_FNAME_INPUT = "fname_input";
    public static final String ATTR_LNAME_INPUT = "lname_input";
    public static final String ATTR_MNAME_INPUT = "mname_input";
   
    /**
     * Navigation previous query constant
     */
    public static final String PREV_QUERY = "prevQuery";
    
    
    /**
     * Parameters names from requests.
     */
    public static final String PARAM_COMMAND_TYPE = "command_type";
    public static final String PARAM_PAGE_NUMBER = "page_number";
    public static final String PARAM_EMAIL = "email";
    public static final String PARAM_FNAME = "fname";
    public static final String PARAM_LNAME = "lname";
    public static final String PARAM_LOCALE = "locale";
    public static final String PARAM_MNAME = "mname";
    public static final String PARAM_PASSWORD = "password";
    public static final String PARAM_PASSWORD_AGAIN = "password_again";
    
    /**
     * Server message property keys
     */
    public static final String MESSAGE_INVALID_EMAIL = "invalid.email";
    public static final String MESSAGE_INVALID_NAME = "invalid.name";
    public static final String MESSAGE_INVALID_PASSWORD = "invalid.password";
    public static final String MESSAGE_LOGIN_MISMATCH = "login.mismatch";
    public static final String MESSAGE_PASSWORD_MISMATCH = "password.mismatch";
    
    /**
     * Navigation response types
     */
    public static final String FORWARD = "forward";
    public static final String REDIRECT = "redirect";
    
    /**
     * Common JSP pages paths
     */
    public static final String PAGE_INDEX = "/index.jsp";
    public static final String PAGE_PLAYERS = "/pages/players.jsp";
    public static final String PAGE_REGISTER = "/register.jsp";
    public static final String PAGE_MAIN = "/pages/main.jsp";
    
    /**
     * Common navigation queries
     */
    public static final String GOTO_INDEX = "/controller?command_type=goto_index";
    public static final String GOTO_PAGINATION = "/controller?command_type=goto_pagination";
    public static final String GOTO_REGISTER = "/controller?command_type=goto_register";
    public static final String GOTO_MAIN = "/controller?command_type=goto_main";
    
    private ConfigConstant() {
    }
}