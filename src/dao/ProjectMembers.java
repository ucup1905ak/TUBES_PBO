/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author farel
 */
@MappedSuperclass
@Table(name = "project_members", catalog = "pbo_tubes", schema = "")
public class ProjectMembers implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProjectMembersPK projectMembersPK;
    @Basic(optional = false)
    @Column(nullable = false, length = 14)
    private String role;
    @Basic(optional = false)
    @Column(name = "joined_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date joinedAt;
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Projects projects;
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public ProjectMembers() {
    }

    public ProjectMembers(ProjectMembersPK projectMembersPK) {
        this.projectMembersPK = projectMembersPK;
    }

    public ProjectMembers(ProjectMembersPK projectMembersPK, String role, Date joinedAt) {
        this.projectMembersPK = projectMembersPK;
        this.role = role;
        this.joinedAt = joinedAt;
    }

    public ProjectMembers(int projectId, int userId) {
        this.projectMembersPK = new ProjectMembersPK(projectId, userId);
    }

    public ProjectMembersPK getProjectMembersPK() {
        return projectMembersPK;
    }

    public void setProjectMembersPK(ProjectMembersPK projectMembersPK) {
        this.projectMembersPK = projectMembersPK;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(Date joinedAt) {
        this.joinedAt = joinedAt;
    }

    public Projects getProjects() {
        return projects;
    }

    public void setProjects(Projects projects) {
        this.projects = projects;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (projectMembersPK != null ? projectMembersPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectMembers)) {
            return false;
        }
        ProjectMembers other = (ProjectMembers) object;
        if ((this.projectMembersPK == null && other.projectMembersPK != null) || (this.projectMembersPK != null && !this.projectMembersPK.equals(other.projectMembersPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.ProjectMembers[ projectMembersPK=" + projectMembersPK + " ]";
    }
    
}
