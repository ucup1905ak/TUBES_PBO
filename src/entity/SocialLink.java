package entity;

import entity.enums.SocialPlatform;


/**
 *
 * @author Farelino Alexander Kim - 240713000
 */
public class SocialLink {
 
    private int id;
    private SocialPlatform platform;
    private String url;
 
    public SocialLink(SocialPlatform platform, String url) {
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
        return "SocialLink{" +
                "id=" + id +
                ", platform=" + platform +
                ", url='" + url + '\'' +
                '}';
    }
}