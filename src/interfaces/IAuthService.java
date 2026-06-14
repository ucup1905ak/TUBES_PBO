/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import exception.authentication.InvalidLoginCredentialException;
import exception.database.DatabaseException;
import exception.validation.ValidationException;
import model.Session;
import model.User;

/**
 *
 * @author Farelino Alexander Kim / 240713000
 */
public interface IAuthService {

    public Session authenticate(String email, String password) throws DatabaseException, InvalidLoginCredentialException;

    public User register(String username, String fullname, String email, String password) throws DatabaseException, ValidationException;

    public boolean validateSession(String token) throws DatabaseException;

    public Session getSessionByToken(String token) throws DatabaseException;

    public void invalidateSession(String token) throws DatabaseException;

    public String refreshToken(String token) throws DatabaseException;

    public boolean changePassword(int userId, String oldPassword, String newPassword) throws DatabaseException;
}
