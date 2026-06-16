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
import model.Project;
import service.DatabaseConnection;
import utility.Log;
import utility.Query;

/**
 *
 * @author Silvanus
 */
public class ProjectDAO implements IGenericDAO<Project, Integer>, IRowMapper<Project> {

    private final DatabaseConnection db = new DatabaseConnection();

    /**
     * (28/5)
     *
     * Semua method di sini konsepnya sama kayak UserDAO Dengan meninggikan nama
     * Yesus, semoga bekerja - Widi
     *
     * Farel : Return ID Project yang baru dibuat, lalu langsung set selected ke project tersebut
     * 
     * God Bless You All, Semoga Lancar - Farel
     */
    @Override
    public int add(Project entity) throws DatabaseException {
        try {
            Query sql = new Query();

            sql.insertInto("projects",
                    "name",
                    "description",
                    "color"
            )
                    .values(
                            entity.getName(),
                            entity.getDescription(),
                            entity.getColor()
                    );
            int id = db.executeInsert(sql);
            Log.create("ProjectDAO.add inserted project id " + id + ".");
            return id;
        } catch (DatabaseException e) {
            Log.err("ProjectDAO.add failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Project get(Integer id) throws DatabaseException {
        Query sql = new Query()
                .select("*")
                .from("projects")
                .where("id = ?", id);

        try {
            List<Project> listProject = db.executeQuery(sql, this::map);
            Log.create("ProjectDAO.get queried " + listProject.size() + " row(s).");
            if (listProject.isEmpty()) {
                return null;
            }
            return listProject.get(0);
        } catch (DatabaseException e) {
            Log.err("ProjectDAO.get failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Project> fetchAll() throws DatabaseException {
        Query sql = new Query()
                .select("*")
                .from("projects");
        try {
            List<Project> projects = db.executeQuery(sql, this::map);
            Log.create("ProjectDAO.fetchAll queried " + projects.size() + " row(s).");
            return projects;
        } catch (DatabaseException e) {
            Log.err("ProjectDAO.fetchAll failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public int update(Project entity) throws DatabaseException {
        try {
            Query sql = new Query()
                    .update("projects")
                    .set("name", entity.getName())
                    .set("description", entity.getDescription())
                    .set("color", entity.getColor())
                    .set("updated_at", entity.getUpdatedAt())
                    .where("id = ?", entity.getId());

            int rows = db.executeUpdate(sql);
            Log.create("ProjectDAO.update updated " + rows + " row(s).");
            return rows;
        } catch (DatabaseException e) {
            Log.err("ProjectDAO.update failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public int delete(Integer id) throws DatabaseException {
        try {
            Query sql = new Query()
                    .deleteFrom("projects")
                    .where("id  = ?", id);

            int rows = db.executeUpdate(sql);
            Log.create("ProjectDAO.delete updated " + rows + " row(s).");
            return rows;
        } catch (DatabaseException e) {
            Log.err("ProjectDAO.delete failed: " + e.getMessage());
            throw e;
        }
    }

    public Project getByName(String name) throws DatabaseException {
        Query sql = new Query()
                .select("*")
                .from("projects")
                .where("name = ?", name);

        try {
            List<Project> listProject = db.executeQuery(sql, this::map);
            Log.create("ProjectDAO.get queried " + listProject.size() + " row(s).");
            if (listProject.isEmpty()) {
                return null;
            }
            return listProject.get(0);
        } catch (DatabaseException e) {
            Log.err("ProjectDAO.get failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Project map(ResultSet rs) throws DatabaseException {
        Project p = new Project();
        try {
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setDescription(rs.getString("description"));
            p.setColor(rs.getString("color"));
            p.setCreatedAt(rs.getTimestamp("created_at"));
            p.setUpdatedAt(rs.getTimestamp("updated_at"));
        } catch (SQLException e) {
            Log.err("ProjectDAO.map failed: " + e.getMessage());
            throw new ResultSetParsingException(
                    "Failed to parse Project from ResultSet",
                    e
            );

        }
        return p;
    }

}
