package service;

import dao.SessionDAO;
import dao.UserDAO;
import exception.authentication.InvalidLoginCredentialException;
import exception.database.DatabaseException;
import java.util.List;
import model.Session;
import model.User;
import utility.PasswordHasher;
import exception.validation.ValidationException;
import exception.validation.EmptyFieldException;
import exception.validation.InvalidInputException;
import exception.validation.InvalidFormatException;
import interfaces.IAuthService;

/**
 *
 * @author Farelino Alexander Kim / 240713000
 */

public class AuthService implements IAuthService {
    public static final int EXPIRY =  3600;
    private static final UserDAO userDAO = new UserDAO();
    private static final SessionDAO sessionDAO = new SessionDAO();
    private static final PasswordHasher passwordHasher = new PasswordHasher();


    @Override
    public Session authenticate(String email, String password) throws DatabaseException, InvalidLoginCredentialException {
        if (email == null || password == null) {
            return null;
        }

        try {
            User user = userDAO.getByEmail(email);
            if (user == null) {
                user = userDAO.getByUsername(email);
            }
            if (user == null) {
                throw new InvalidLoginCredentialException("User not found");
            }
           

            if (!passwordHasher.verify(password, user.getPasswordHash())) {
                throw  new InvalidLoginCredentialException("Password incorrect");
                
            }

            String token = generateToken(user.getId());
            Session session = new Session(user, token);
            session.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
            session.setExpiresAt(calculateExpiry());

            sessionDAO.add(session);

            return session;

        } catch (DatabaseException e) {
            throw e;
        }
    }

    @Override
    public User register(String username, String fullname, String email, String password) throws DatabaseException, ValidationException {
        if (email == null || email.trim().isEmpty()) {
            throw new EmptyFieldException("Email");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new EmptyFieldException("Password");
        }
        if (username == null || username.trim().isEmpty()) {
            throw new EmptyFieldException("Username");
        }

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!email.matches(emailRegex)) {
            throw new InvalidFormatException("Email", "valid email address (e.g., example@domain.com)");
        }

        if (userDAO.getByEmail(email) != null) {
            throw new InvalidInputException("Email already in use");
        }
        if (userDAO.getByUsername(username) != null) {
            throw new InvalidInputException("Username already in use");
        }

        String hashedPassword = passwordHasher.hash(password);
        User newUser = new User(username, fullname, email, hashedPassword);
        
        userDAO.add(newUser);
        return userDAO.getByUsername(username);
    }

    @Override
    public boolean validateSession(String token) throws DatabaseException {
        if (token == null || token.trim().isEmpty()) {
            return false;
        }

        try {
            Session session = sessionDAO.getByToken(token);
            if (session == null) {
                return false;  // Session not found
            }

            return session.isValid();

        } catch (DatabaseException e) {
            throw e;
        }
    }

    @Override
    public Session getSessionByToken(String token) throws DatabaseException {
        if (token == null || token.trim().isEmpty()) {
            return null;
        }

        try {
            Session session = sessionDAO.getByToken(token);
            if (session != null && session.isValid()) {
                return session;
            }
        } catch (DatabaseException e) {
            throw e;
        }

        return null;
    }

    @Override
    public void invalidateSession(String token) throws DatabaseException {
        if (token == null || token.trim().isEmpty()) {
            return;
        }

        try {
            sessionDAO.invalidate(token);
        } catch (DatabaseException e) {
            throw e;
        }
    }

    @Override
    public String refreshToken(String token) throws DatabaseException {
        if (token == null || token.trim().isEmpty()) {
            return null;
        }

        try {
            Session session = sessionDAO.getByToken(token);
            if (session == null || !session.isValid()) {
                return null;  
            }

            String newToken = generateToken(session.getUser().getId());

            session.setToken(newToken);
            session.setExpiresAt(calculateExpiry());
            sessionDAO.update(session);

            return newToken;

        } catch (DatabaseException e) {
            throw e;
        }
    }

    private String generateToken(int userId) {
        long timestamp = System.currentTimeMillis();
        return java.util.UUID.randomUUID().toString() + "_" + userId + "_" + timestamp;
    }

    private java.sql.Timestamp calculateExpiry() {
        long expiryMillis = System.currentTimeMillis() + (EXPIRY); 
        return new java.sql.Timestamp(expiryMillis);
    }

    @Override
    public boolean changePassword(int userId, String oldPassword, String newPassword) throws DatabaseException {
        if (oldPassword == null || newPassword == null) {
            return false;
        }

        try {
            User user = userDAO.get(userId);
            if (user == null) {
                return false;
            }

            if (!passwordHasher.verify(oldPassword, user.getPasswordHash())) {
                return false;
            }

            String newHashedPassword = passwordHasher.hash(newPassword);
            user.setPasswordHash(newHashedPassword);
            
            int rowsUpdated = userDAO.update(user);
            return rowsUpdated > 0;
        } catch (DatabaseException e) {
            throw e;
        }
    }
}