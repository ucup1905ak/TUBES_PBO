package model;

/**
 *
 * @author Farelino Alexander Kim / 240713000ino Alexander Kim - 240713000
 */
import java.sql.Timestamp;

public class Session {

    private int id;
    private String token;
    private User user;
    private Timestamp createdAt;
    private Timestamp expiresAt;
    private boolean isActive;

    public Session(){}
    public Session(User user, String token) {
        this.user = user;
        this.token = token;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.isActive = true;
    }

    public Session(User user, String token, Timestamp expiresAt) {
        this.user = user;
        this.token = token;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.expiresAt = expiresAt;
        this.isActive = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Timestamp expiresAt) {
        this.expiresAt = expiresAt;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isValid() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        return isActive && expiresAt != null && expiresAt.after(now);
    }

    public void logout() {
        this.isActive = false;
    }

    @Override
    public String toString() {
        return "Session{"
                + "id=" + id
                + ", token='" + token + '\''
                + ", user=" + (user != null ? user.getUsername() : "null")
                + ", createdAt=" + createdAt
                + ", expiresAt=" + expiresAt
                + ", isActive=" + isActive
                + '}';
    }
}
