/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author farel
 */
@MappedSuperclass
@Table(catalog = "pbo_tubes", schema = "")
public class Tasks implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "project_item_id", nullable = false)
    private Integer projectItemId;
    @Basic(optional = false)
    @Column(nullable = false, length = 6)
    private String priority;
    @Basic(optional = false)
    @Column(nullable = false, length = 11)
    private String status;
    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "due_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;
    @Column(name = "completed_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date completedAt;
    @JoinColumn(name = "project_item_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private ProjectItems projectItems;

    public Tasks() {
    }

    public Tasks(Integer projectItemId) {
        this.projectItemId = projectItemId;
    }

    public Tasks(Integer projectItemId, String priority, String status) {
        this.projectItemId = projectItemId;
        this.priority = priority;
        this.status = status;
    }

    public Integer getProjectItemId() {
        return projectItemId;
    }

    public void setProjectItemId(Integer projectItemId) {
        this.projectItemId = projectItemId;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
    }

    public ProjectItems getProjectItems() {
        return projectItems;
    }

    public void setProjectItems(ProjectItems projectItems) {
        this.projectItems = projectItems;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (projectItemId != null ? projectItemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tasks)) {
            return false;
        }
        Tasks other = (Tasks) object;
        if ((this.projectItemId == null && other.projectItemId != null) || (this.projectItemId != null && !this.projectItemId.equals(other.projectItemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.Tasks[ projectItemId=" + projectItemId + " ]";
    }
    
}
