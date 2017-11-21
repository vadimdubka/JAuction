package com.dubatovka.app.service;

import com.dubatovka.app.dao.UserDAO;
import com.dubatovka.app.dao.exception.DAOException;
import com.dubatovka.app.entity.Admin;
import com.dubatovka.app.entity.User;
import com.dubatovka.app.entity.Player;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class UserService extends AbstractService {
    private static final Logger logger = LogManager.getLogger(UserService.class);
    private UserDAO userDAO = daoFactory.getUserDAO();
    
    public UserService() {
    }
    
    public User authorizeUser(String email, String password) {
        User user = null;
        email = email.toLowerCase().trim();
        password = Encryptor.encryptMD5(password);
        try {
            user = userDAO.authorizeUser(email, password);
        } catch (DAOException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        if (user != null) {
            if (user.getRole() == User.UserRole.PLAYER) {
                user = initPlayer(user);
            } else {
                user = initAdmin(user);
            }
        }
        return user;
    }
    
    Player initPlayer(User user) {
        if (user == null) {
            return null;
        }
        Player player = new Player();
        player.setId(user.getId());
        player.setEmail(user.getEmail());
        player.setRole(User.UserRole.PLAYER);
        player.setRegistrationDate(user.getRegistrationDate());
        PlayerService playerService = new PlayerService();
        playerService.initPlayerInfo(player);
        return player;
    }
    
    
    private Admin initAdmin(User user) {
        if (user == null) {
            return null;
        }
        Admin admin = new Admin();
        admin.setId(user.getId());
        admin.setEmail(user.getEmail());
        admin.setRole(User.UserRole.ADMIN);
        admin.setRegistrationDate(user.getRegistrationDate());
        return admin;
    }
}