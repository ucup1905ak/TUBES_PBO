/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(catalog = "pbo_tubes", schema = "")
public class Attachments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "file_name", nullable = false, length = 255)
    private String fileName;
    @Basic(optional = false)
    @Column(name = "file_path", nullable = false, length = 255)
    private String filePath;
    @Basic(optional = false)
    @Column(name = "file_type", nullable = false, length = 8)
    private String fileType;
    @Basic(optional = false)
    @Column(name = "file_size", nullable = false)
    private long fileSize;
    @Basic(optional = false)
    @Column(name = "uploaded_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadedAt;
    @JoinColumn(name = "project_item_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private ProjectItems projectItemId;
    @JoinColumn(name = "uploaded_by", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Users uploadedBy;

    public Attachments() {
    }

    public Attachments(Integer id) {
        this.id = id;
    }

    public Attachments(Integer id, String fileName, String filePath, String fileType, long fileSize, Date uploadedAt) {
        this.id = id;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.uploadedAt = uploadedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public Date getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(Date uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public ProjectItems getProjectItemId() {
        return projectItemId;
    }

    public void setProjectItemId(ProjectItems projectItemId) {
        this.projectItemId = projectItemId;
    }

    public Users getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(Users uploadedBy) {
        this.uploadedBy = uploadedBy;
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
        if (!(object instanceof Attachments)) {
            return false;
        }
        Attachments other = (Attachments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.Attachments[ id=" + id + " ]";
    }
    
}
