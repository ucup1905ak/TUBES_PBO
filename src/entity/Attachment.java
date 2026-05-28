/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Farelino Alexander Kim / 240713000ino Alexander Kim - 240713000
 */
import entity.enums.AttachmentType;
import java.sql.Timestamp;

public class Attachment {

    private int id;
    private ProjectItem projectItem;
    private String fileName;
    private String filePath;
    private AttachmentType fileType;
    private long fileSize;
    private Timestamp uploadedAt;
    private User uploadedBy;

    public Attachment() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProjectItem getProjectItem() {
        return projectItem;
    }

    public void setProjectItem(ProjectItem projectItem) {
        this.projectItem = projectItem;
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

    public AttachmentType getFileType() {
        return fileType;
    }

    public void setFileType(AttachmentType fileType) {
        this.fileType = fileType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public Timestamp getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(Timestamp uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public User getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(User uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileType=" + fileType +
                ", fileSize=" + fileSize +
                ", uploadedAt=" + uploadedAt +
                ", uploadedBy=" + (uploadedBy != null ? uploadedBy.getUsername() : "null") +
                '}';
    }
}