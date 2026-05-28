/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author farel
 */
@MappedSuperclass
@Table(catalog = "pbo_tubes", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"email"}),
    @UniqueConstraint(columnNames = {"username"})})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String username;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String email;
    @Basic(optional = false)
    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;
    @Basic(optional = false)
    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;
    @Lob
    @Column(length = 65535)
    private String bio;
    @Column(name = "profile_picture", length = 255)
    private String profilePicture;
    @Basic(optional = false)
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @ManyToMany(mappedBy = "usersCollection")
    private Collection<ProjectItems> projectItemsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<ProjectMembers> projectMembersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Sessions> sessionsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uploadedBy")
    private Collection<Attachments> attachmentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<SocialLinks> socialLinksCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy")
    private Collection<ProjectItems> projectItemsCollection1;
    @OneToMany(mappedBy = "updatedBy")
    private Collection<ProjectItems> projectItemsCollection2;

    public Users() {
    }

    public Users(Integer id) {
        this.id = id;
    }

    public Users(Integer id, String username, String email, String passwordHash, String fullName, Date createdAt, Date updatedAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Collection<ProjectItems> getProjectItemsCollection() {
        return projectItemsCollection;
    }

    public void setProjectItemsCollection(Collection<ProjectItems> projectItemsCollection) {
        this.projectItemsCollection = projectItemsCollection;
    }

    public Collection<ProjectMembers> getProjectMembersCollection() {
        return projectMembersCollection;
    }

    public void setProjectMembersCollection(Collection<ProjectMembers> projectMembersCollection) {
        this.projectMembersCollection = projectMembersCollection;
    }

    public Collection<Sessions> getSessionsCollection() {
        return sessionsCollection;
    }

    public void setSessionsCollection(Collection<Sessions> sessionsCollection) {
        this.sessionsCollection = sessionsCollection;
    }

    public Collection<Attachments> getAttachmentsCollection() {
        return attachmentsCollection;
    }

    public void setAttachmentsCollection(Collection<Attachments> attachmentsCollection) {
        this.attachmentsCollection = attachmentsCollection;
    }

    public Collection<SocialLinks> getSocialLinksCollection() {
        return socialLinksCollection;
    }

    public void setSocialLinksCollection(Collection<SocialLinks> socialLinksCollection) {
        this.socialLinksCollection = socialLinksCollection;
    }

    public Collection<ProjectItems> getProjectItemsCollection1() {
        return projectItemsCollection1;
    }

    public void setProjectItemsCollection1(Collection<ProjectItems> projectItemsCollection1) {
        this.projectItemsCollection1 = projectItemsCollection1;
    }

    public Collection<ProjectItems> getProjectItemsCollection2() {
        return projectItemsCollection2;
    }

    public void setProjectItemsCollection2(Collection<ProjectItems> projectItemsCollection2) {
        this.projectItemsCollection2 = projectItemsCollection2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.Users[ id=" + id + " ]";
    }
    
}
