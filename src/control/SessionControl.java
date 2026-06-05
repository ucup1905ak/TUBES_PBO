/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

/**
 *
 * @author farel
 */
import exception.database.DatabaseException;
import exception.validation.InvalidFormatException;
import model.Session;
import model.User;
import service.AuthService;

public class SessionControl {

    private Session currentSession = null;
    private final AuthService authService = new AuthService();

    public boolean login(String email, String password) throws DatabaseException, InvalidFormatException {
        if (email == null || email.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {
            return false;
        }

        try {
            Session session = authService.authenticate(email, password);
            
            if (session != null && session.isValid()) {
                this.currentSession = session;
                return true;
            }
        } catch (DatabaseException e) {
            throw e;
        }

        return false;
    }

    public void logout() throws DatabaseException {
        if (currentSession != null) {
            try {
                authService.invalidateSession(currentSession.getToken());
            } finally {
                this.currentSession = null;
            }
        }
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public User getCurrentUser() {
        if (currentSession != null) {
            return currentSession.getUser();
        }
        return null;
    }

    public boolean isSessionValid() throws DatabaseException {
        if (currentSession == null) {
            return false;
        }

        try {
            return authService.validateSession(currentSession.getToken());
        } catch (DatabaseException e) {
            throw e;
        }
    }

    public boolean isAuthenticated() {
        try {
            return currentSession != null && isSessionValid();
        } catch (DatabaseException e) {
            
            return false;
        }
    }

    public boolean refreshSession() throws DatabaseException {
        if (currentSession == null) {
            return false;
        }

        try {
            String newToken = authService.refreshToken(currentSession.getToken());
            if (newToken != null) {
                currentSession.setToken(newToken);
                return true;
            }
        } catch (DatabaseException e) {
            throw e;
        }

        return false;
    }

    public void clearSession() {
        this.currentSession = null;
    }


}
