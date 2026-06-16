/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import exception.database.DatabaseException;
import exception.database.ResultSetParsingException;
import interfaces.IGenericDAO;
import interfaces.IRowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.*;
import model.enums.AttachmentType;
import utility.security.Log;
import utility.db.Query;

/**
 *
 * @author Silvanus
 */
public class AttachmentDAO implements IGenericDAO<Attachment, Integer>, IRowMapper<Attachment>{

    @Override
    public int add(Attachment entity) throws DatabaseException {
        try {
            Query sql = new Query()
                .insertInto(
                    "attachments",
                    "project_item_id",
                    "file_name",
                    "file_path",
                    "file_type",
                    "file_size",
                    "uploaded_at",
                    "uploaded_by"
                )
                .values(
                    entity.getProjectItem() != null ? entity.getProjectItem().getId() : null,
                    entity.getFileName(),
                    entity.getFilePath(),
                    entity.getFileType(),
                    entity.getFileSize(),
                    entity.getUploadedAt(),
                    entity.getUploadedBy() != null ? entity.getUploadedBy().getId() : null
                );

            int rows = DB.executeUpdate(sql);
            Log.create("AttachmentDAO.add updated " + rows + " row(s).");
            return rows;
        } catch (DatabaseException e) {
            Log.err("AttachmentDAO.add failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Attachment get(Integer id) throws DatabaseException {
        try {
            Query sql = new Query()
                .select("*")
                .from("attachments")
                .where("id = ?", id);

            List<Attachment> listAttachment = DB.executeQuery(sql, this::map);
            Log.create("AttachmentDAO.get queried " + listAttachment.size() + " row(s).");
            if (listAttachment.isEmpty()) {
                return null;
            }
            return listAttachment.get(0);
        } catch (DatabaseException e) {
            Log.err("AttachmentDAO.get failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Attachment> fetchAll() throws DatabaseException {
        Query sql = new Query()
                .select("*")
                .from("attachments");

        try {
            List<Attachment> attachments = DB.executeQuery(sql, this::map);
            Log.create("AttachmentDAO.fetchAll queried " + attachments.size() + " row(s).");
            return attachments;
        } catch (DatabaseException e) {
            Log.err("AttachmentDAO.fetchAll failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public int update(Attachment entity) throws DatabaseException {
        try {
            Query sql = new Query()
                    .update("attachments")
                    .set("project_item_id", entity.getProjectItem() != null ? entity.getProjectItem().getId() : null)
                    .set("file_name", entity.getFileName())
                    .set("file_path", entity.getFilePath())
                    .set("file_type", entity.getFileType())
                    .set("file_size", entity.getFileSize())
                    .set("uploaded_at", entity.getUploadedAt())
                    .set("uploaded_by", entity.getUploadedBy() != null ? entity.getUploadedBy().getId() : null)
                    .where("id = ?", entity.getId());

            int rows = DB.executeUpdate(sql);
            Log.create("AttachmentDAO.update updated " + rows + " row(s).");
            return rows;
        } catch (DatabaseException e) {
            Log.err("AttachmentDAO.update failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public int delete(Integer id) throws DatabaseException {
        try {
            Query sql = new Query()
                    .deleteFrom("attachments")
                    .where("id = ?", id);

            int rows = DB.executeUpdate(sql);
            Log.create("AttachmentDAO.delete updated " + rows + " row(s).");
            return rows;
        } catch (DatabaseException e) {
            Log.err("AttachmentDAO.delete failed: " + e.getMessage());
            throw e;
        }
    }
    
    @Override
    public Attachment map(ResultSet rs) throws DatabaseException{
        try {
            Attachment attachment = new Attachment();
            attachment.setId(rs.getInt("id"));
            attachment.setFileName(rs.getString("file_name"));
            attachment.setFilePath(rs.getString("file_path"));

            String fileType = rs.getString("file_type");
            if (fileType != null) {
                attachment.setFileType(AttachmentType.valueOf(fileType));
            }

            attachment.setFileSize(rs.getLong("file_size"));
            attachment.setUploadedAt(rs.getTimestamp("uploaded_at"));

            ProjectItem projectItem = new ProjectItem() {};
            projectItem.setId(rs.getInt("project_item_id"));
            attachment.setProjectItem(projectItem);

            User uploadedBy = new User();
            uploadedBy.setId(rs.getInt("uploaded_by"));
            attachment.setUploadedBy(uploadedBy);

            return attachment;
        } catch (SQLException e) {
            Log.err("AttachmentDAO.map failed: " + e.getMessage());
            throw new ResultSetParsingException(
                    "Failed to parse Attachment from ResultSet",
                    e
            );
        }
    }
}
