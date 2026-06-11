/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Farelino Alexander Kim / 240713000ino Alexander Kim - 240713000
 */
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class User {

    private int id;
    private String username;
    private String email;
    private String passwordHash;
    private String fullName;
    private String bio;
    private String profilePicture;
    private List<SocialLink> socials = new ArrayList<>();
    ;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    
    public User(){
        
    }
    
    public User(String username, String fullname, String email, String passwordHash) {
        this.username = username;
        this.email = email;
        this.fullName = fullname;
        this.passwordHash = passwordHash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public List<SocialLink> getSocials() {
        return socials;
    }

    public void setSocials(List<SocialLink> socials) {
        this.socials = socials;
    }

    public void addSocial(SocialLink social) {
        this.socials.add(social);
    }

    public void removeSocial(SocialLink social) {
        this.socials.remove(social);
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", username='" + username + '\''
                + ", email='" + email + '\''
                + ", fullName='" + fullName + '\''
                + ", bio='" + bio + '\''
                + ", profilePicture='" + profilePicture + '\''
                + ", socials=" + socials
                + ", createdAt=" + createdAt
                + ", updatedAt=" + updatedAt
                + '}';
    }
}
