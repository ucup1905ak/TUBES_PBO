/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author farel
 */
@Embeddable
public class ProjectMembersPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "project_id", nullable = false)
    private int projectId;
    @Basic(optional = false)
    @Column(name = "user_id", nullable = false)
    private int userId;

    public ProjectMembersPK() {
    }

    public ProjectMembersPK(int projectId, int userId) {
        this.projectId = projectId;
        this.userId = userId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) projectId;
        hash += (int) userId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectMembersPK)) {
            return false;
        }
        ProjectMembersPK other = (ProjectMembersPK) object;
        if (this.projectId != other.projectId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.ProjectMembersPK[ projectId=" + projectId + ", userId=" + userId + " ]";
    }
    
}
