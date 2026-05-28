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
public class Events implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "project_item_id", nullable = false)
    private Integer projectItemId;
    @Column(length = 150)
    private String location;
    @Column(name = "is_all_day")
    private Boolean isAllDay;
    @Basic(optional = false)
    @Column(name = "start_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startAt;
    @Column(name = "end_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endAt;
    @JoinColumn(name = "project_item_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private ProjectItems projectItems;

    public Events() {
    }

    public Events(Integer projectItemId) {
        this.projectItemId = projectItemId;
    }

    public Events(Integer projectItemId, Date startAt) {
        this.projectItemId = projectItemId;
        this.startAt = startAt;
    }

    public Integer getProjectItemId() {
        return projectItemId;
    }

    public void setProjectItemId(Integer projectItemId) {
        this.projectItemId = projectItemId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getIsAllDay() {
        return isAllDay;
    }

    public void setIsAllDay(Boolean isAllDay) {
        this.isAllDay = isAllDay;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
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
        if (!(object instanceof Events)) {
            return false;
        }
        Events other = (Events) object;
        if ((this.projectItemId == null && other.projectItemId != null) || (this.projectItemId != null && !this.projectItemId.equals(other.projectItemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.Events[ projectItemId=" + projectItemId + " ]";
    }
    
}
