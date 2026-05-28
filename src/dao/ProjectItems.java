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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author farel
 */
@MappedSuperclass
@Table(name = "project_items", catalog = "pbo_tubes", schema = "")
public class ProjectItems implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String title;
    @Lob
    @Column(length = 65535)
    private String description;
    @Column(length = 20)
    private String color;
    @Basic(optional = false)
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinTable(name = "project_item_assignees", joinColumns = {
        @JoinColumn(name = "project_item_id", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)})
    @ManyToMany
    private Collection<Users> usersCollection;
    @JoinTable(name = "project_item_tags", joinColumns = {
        @JoinColumn(name = "project_item_id", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "tag_id", referencedColumnName = "id", nullable = false)})
    @ManyToMany
    private Collection<Tags> tagsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectItemId")
    private Collection<Attachments> attachmentsCollection;
    @JoinColumn(name = "created_by", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Users createdBy;
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Projects projectId;
    @JoinColumn(name = "updated_by", referencedColumnName = "id")
    @ManyToOne
    private Users updatedBy;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "projectItems")
    private Events events;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "projectItems")
    private Tasks tasks;

    public ProjectItems() {
    }

    public ProjectItems(Integer id) {
        this.id = id;
    }

    public ProjectItems(Integer id, String title, Date createdAt, Date updatedAt) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    public Collection<Tags> getTagsCollection() {
        return tagsCollection;
    }

    public void setTagsCollection(Collection<Tags> tagsCollection) {
        this.tagsCollection = tagsCollection;
    }

    public Collection<Attachments> getAttachmentsCollection() {
        return attachmentsCollection;
    }

    public void setAttachmentsCollection(Collection<Attachments> attachmentsCollection) {
        this.attachmentsCollection = attachmentsCollection;
    }

    public Users getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Users createdBy) {
        this.createdBy = createdBy;
    }

    public Projects getProjectId() {
        return projectId;
    }

    public void setProjectId(Projects projectId) {
        this.projectId = projectId;
    }

    public Users getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Users updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Events getEvents() {
        return events;
    }

    public void setEvents(Events events) {
        this.events = events;
    }

    public Tasks getTasks() {
        return tasks;
    }

    public void setTasks(Tasks tasks) {
        this.tasks = tasks;
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
        if (!(object instanceof ProjectItems)) {
            return false;
        }
        ProjectItems other = (ProjectItems) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.ProjectItems[ id=" + id + " ]";
    }
    
}
