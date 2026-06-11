/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import exception.database.DatabaseException;
import interfaces.IGenericDAO;
import interfaces.IRowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.*;
import service.DatabaseConnection;
import utility.Query;

/**
 *
 * @author Silvanus
 */
public abstract class ProjectItemDAO {

    protected final DatabaseConnection db = new DatabaseConnection();

    protected int addProjectItem(ProjectItem item) throws DatabaseException {
        Query sql = new Query();

        sql.insertInto(
                "project_items",
                "title",
                "description",
                "color",
                "project_id",
                "created_by",
                "updated_by"
        ).values(
                item.getTitle(),
                item.getDescription(),
                item.getColor(),
                item.getProject().getId(),
                item.getCreatedBy().getId(),
                item.getUpdatedBy() != null
                        ? item.getUpdatedBy().getId()
                        : null
        );

        return db.executeInsert(sql);
    }

    protected int updateProjectItem(ProjectItem item) throws DatabaseException {
        Query sql = new Query();

        sql.update("project_items")
                .set("title", item.getTitle())
                .set("description", item.getDescription())
                .set("color", item.getColor())
                .set("project_id", item.getProject().getId())
                .set(
                        "updated_by",
                        item.getUpdatedBy() != null ? item.getUpdatedBy().getId() : null
                )
                .where("id = " + item.getId());

        return db.executeUpdate(sql);
    }

    protected int deleteProjectItem(Integer id) throws DatabaseException {
        Query sql = new Query();

        sql.deleteFrom("project_items")
                .where("id = " + id);

        return db.executeUpdate(sql);
    }
}