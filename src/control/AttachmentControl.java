/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dao.AttachmentDAO;
import exception.database.DatabaseException;
import interfaces.IGenericControl;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.Attachment;
import model.ProjectItem;
import model.enums.AttachmentType;
import utility.security.Log;

/**
 *
 * @author Farelino Alexander Kim - 240713000
 */
public class AttachmentControl implements IGenericControl<Attachment, Integer> {

    private static final String STORAGE_DIR = "uploads/attachments";

    private final AttachmentDAO attachmentDAO;

    public AttachmentControl() {
        this.attachmentDAO = new AttachmentDAO();
        Log.create("[Control] : Init Attachment Control");
    }

    @Override
    public int add(Attachment entity) throws DatabaseException {
        Log.create("[Control] : Add Attachment");

        if (entity == null) {
            Log.err("[Control] : Add Attachment failed - entity is null");
            return 0;
        }

        if (entity.getProjectItem() == null || entity.getProjectItem().getId() <= 0) {
            Log.err("[Control] : Add Attachment failed - project item is invalid");
            return 0;
        }

        if (entity.getFileName() == null || entity.getFilePath() == null || entity.getFileType() == null) {
            Log.err("[Control] : Add Attachment failed - file metadata is incomplete");
            return 0;
        }

        try {
            int rows = attachmentDAO.add(entity);
            Log.create("[Control] : Add Attachment success (" + rows + " row(s))");
            return rows;
        } catch (DatabaseException e) {
            Log.err("[Control] : Add Attachment failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Attachment get(Integer id) throws DatabaseException {
        Log.create("[Control] : Get Attachment");

        if (id == null || id <= 0) {
            Log.err("[Control] : Get Attachment failed - id is null or invalid");
            return null;
        }

        try {
            Attachment attachment = attachmentDAO.get(id);
            Log.create("[Control] : Get Attachment success");
            return attachment;
        } catch (DatabaseException e) {
            Log.err("[Control] : Get Attachment failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Attachment> fetchAll() throws DatabaseException {
        Log.create("[Control] : Fetch All Attachment");

        try {
            List<Attachment> attachments = attachmentDAO.fetchAll();
            Log.create("[Control] : Fetch All Attachment success (" + attachments.size() + " row(s))");
            return attachments;
        } catch (DatabaseException e) {
            Log.err("[Control] : Fetch All Attachment failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public int update(Attachment entity) throws DatabaseException {
        Log.create("[Control] : Update Attachment");

        if (entity == null || entity.getId() <= 0) {
            Log.err("[Control] : Update Attachment failed - entity or id is invalid");
            return 0;
        }

        try {
            int rows = attachmentDAO.update(entity);
            Log.create("[Control] : Update Attachment success (" + rows + " row(s))");
            return rows;
        } catch (DatabaseException e) {
            Log.err("[Control] : Update Attachment failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public int delete(Integer id) throws DatabaseException {
        Log.create("[Control] : Delete Attachment");

        if (id == null || id <= 0) {
            Log.err("[Control] : Delete Attachment failed - id is null or invalid");
            return 0;
        }

        try {
            Attachment existing = get(id);
            int rows = attachmentDAO.delete(id);

            if (rows > 0 && existing != null) {
                deletePhysicalFile(existing.getFilePath());
            }

            Log.create("[Control] : Delete Attachment success (" + rows + " row(s))");
            return rows;
        } catch (DatabaseException e) {
            Log.err("[Control] : Delete Attachment failed: " + e.getMessage());
            throw e;
        }
    }

    public List<Attachment> findByProjectItem(Integer itemId) throws DatabaseException {
        Log.create("[Control] : Find Attachment By Project Item");

        if (itemId == null || itemId <= 0) {
            Log.err("[Control] : Find Attachment By Project Item failed - itemId is null or invalid");
            return new ArrayList<>();
        }

        try {
            List<Attachment> result = new ArrayList<>();

            for (Attachment attachment : fetchAll()) {
                if (attachment.getProjectItem() != null
                        && Objects.equals(attachment.getProjectItem().getId(), itemId)) {
                    result.add(attachment);
                }
            }

            Log.create("[Control] : Find Attachment By Project Item success (" + result.size() + " row(s))");
            return result;
        } catch (DatabaseException e) {
            Log.err("[Control] : Find Attachment By Project Item failed: " + e.getMessage());
            throw e;
        }
    }

    public Attachment upload(File file, Integer projectItemId) throws DatabaseException {
        Log.create("[Control] : Upload Attachment");

        if (file == null || projectItemId == null || projectItemId <= 0) {
            Log.err("[Control] : Upload Attachment failed - file or projectItemId is invalid");
            return null;
        }

        if (!file.exists() || !file.isFile()) {
            Log.err("[Control] : Upload Attachment failed - source file does not exist");
            return null;
        }

        Path sourcePath = file.toPath();
        Path storageDirectory = Paths.get(STORAGE_DIR);

        try {
            Files.createDirectories(storageDirectory);

            String originalFileName = file.getName();
            String storedFileName = System.currentTimeMillis() + "_" + originalFileName;
            Path destinationPath = storageDirectory.resolve(storedFileName);

            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

            Attachment attachment = new Attachment();
            attachment.setFileName(originalFileName);
            attachment.setFilePath(destinationPath.toAbsolutePath().toString());
            attachment.setFileSize(Files.size(sourcePath));
            attachment.setFileType(resolveAttachmentType(originalFileName));
            attachment.setUploadedAt(new Timestamp(System.currentTimeMillis()));

            ProjectItem projectItem = new ProjectItem() {};
            projectItem.setId(projectItemId);
            attachment.setProjectItem(projectItem);

            int rows = add(attachment);
            if (rows <= 0) {
                Files.deleteIfExists(destinationPath);
                Log.err("[Control] : Upload Attachment failed - database insert failed");
                return null;
            }

            Log.create("[Control] : Upload Attachment success");
            return attachment;
        } catch (IOException e) {
            Log.err("[Control] : Upload Attachment failed: " + e.getMessage());
            throw new DatabaseException("Failed to upload attachment file", e);
        }
    }

    public boolean download(Integer id, String destination) throws DatabaseException {
        Log.create("[Control] : Download Attachment");

        if (id == null || id <= 0 || destination == null || destination.isBlank()) {
            Log.err("[Control] : Download Attachment failed - invalid id or destination");
            return false;
        }

        Attachment attachment = get(id);
        if (attachment == null || attachment.getFilePath() == null || attachment.getFilePath().isBlank()) {
            Log.err("[Control] : Download Attachment failed - attachment not found or file path empty");
            return false;
        }

        Path sourcePath = Paths.get(attachment.getFilePath());
        if (!Files.exists(sourcePath)) {
            Log.err("[Control] : Download Attachment failed - source file missing");
            return false;
        }

        try {
            Path destinationPath = Paths.get(destination);

            if (Files.exists(destinationPath) && Files.isDirectory(destinationPath)) {
                destinationPath = destinationPath.resolve(attachment.getFileName());
            } else if (destination.endsWith(File.separator)) {
                Files.createDirectories(destinationPath);
                destinationPath = destinationPath.resolve(attachment.getFileName());
            } else if (destinationPath.getParent() != null) {
                Files.createDirectories(destinationPath.getParent());
            }

            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            Log.create("[Control] : Download Attachment success");
            return true;
        } catch (IOException e) {
            Log.err("[Control] : Download Attachment failed: " + e.getMessage());
            throw new DatabaseException("Failed to download attachment file", e);
        }
    }

    private AttachmentType resolveAttachmentType(String fileName) {
        if (fileName == null) {
            return AttachmentType.OTHER;
        }

        String lowerName = fileName.toLowerCase();

        if (lowerName.matches(".*\\.(png|jpg|jpeg|gif|bmp|webp)$")) {
            return AttachmentType.IMAGE;
        }
        if (lowerName.matches(".*\\.(mp4|avi|mkv|mov|wmv)$")) {
            return AttachmentType.VIDEO;
        }
        if (lowerName.matches(".*\\.(zip|rar|7z|tar|gz)$")) {
            return AttachmentType.ARCHIVE;
        }
        if (lowerName.matches(".*\\.(pdf|doc|docx|xls|xlsx|ppt|pptx|txt|csv)$")) {
            return AttachmentType.DOCUMENT;
        }

        return AttachmentType.OTHER;
    }

    private void deletePhysicalFile(String filePath) {
        if (filePath == null || filePath.isBlank()) {
            return;
        }

        try {
            Files.deleteIfExists(Paths.get(filePath));
        } catch (IOException e) {
            Log.err("[Control] : Failed to delete physical attachment file: " + e.getMessage());
        }
    }
}
