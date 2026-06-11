package model;

import model.enums.SocialPlatform;

/**
 *
 * @author Farelino Alexander Kim / 240713000ino Alexander Kim - 240713000
 */
public class Social {

    private int id;
    private User user;
    private SocialPlatform platform;
    private String url;

    public Social(User user, SocialPlatform platform, String url) {
        this.user = user;
        this.platform = platform;
        this.url = url;
    }

    public Social(SocialPlatform platform, String url) {
    
        this.platform = platform;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SocialPlatform getPlatform() {
        return platform;
    }

    public void setPlatform(SocialPlatform platform) {
        this.platform = platform;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "SocialLink{"
                + "id=" + id
                + ", platform=" + platform
                + ", url='" + url + '\''
                + '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
