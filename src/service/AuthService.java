package service;

import dao.SessionDAO;
import dao.UserDAO;
import exception.authentication.InvalidLoginCredentialException;
import exception.database.DatabaseException;
import java.util.List;
import model.Session;
import model.User;
import utility.PasswordHasher;

/**
 *
 * @author Farelino Alexander Kim / 240713000
 */

public class AuthService {
    public static final int EXPIRY =  3600;
    private final UserDAO userDAO = new UserDAO();
    private final SessionDAO sessionDAO = new SessionDAO();
    private final PasswordHasher passwordHasher = new PasswordHasher();


    public Session authenticate(String email, String password) throws DatabaseException, InvalidLoginCredentialException {
        if (email == null || password == null) {
            return null;
        }

        try {
            User user = userDAO.getByEmail(email);
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
}