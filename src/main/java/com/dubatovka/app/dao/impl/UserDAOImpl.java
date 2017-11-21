package com.dubatovka.app.dao.impl;

import com.dubatovka.app.dao.UserDAO;
import com.dubatovka.app.dao.exception.DAOException;
import com.dubatovka.app.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl extends UserDAO {
    private static final String SQL_AUTH = "SELECT id, email, role, registration_date " +
            "FROM user " +
            "WHERE email=? AND password_md5=?";
    
    public UserDAOImpl() {
    }
    
    @Override
    public User authorizeUser(String email, String password) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_AUTH)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return buildUser(resultSet);
        } catch (SQLException e) {
            throw new DAOException("Database connection error while authorizing user. " + e);
        }
    }
    
    private User buildUser(ResultSet resultSet) throws SQLException {
        User user = null;
        if (resultSet.next()) {
            user = new User();
            user.setId(resultSet.getInt(ID));
            user.setEmail(resultSet.getString(EMAIL));
            user.setRole(User.UserRole.valueOf(resultSet.getString(ROLE).toUpperCase()));
            user.setRegistrationDate(resultSet.getDate(REGISTRATION_DATE).toLocalDate());
        }
        return user;
    }
    
}