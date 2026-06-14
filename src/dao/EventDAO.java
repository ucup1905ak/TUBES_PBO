/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import exception.database.DatabaseException;
import exception.database.ResultSetParsingException;
import interfaces.IProjectItemDAO;
import interfaces.IRowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Event;
import model.Project;
import model.User;
import utility.Log;
import utility.Query;

/**
 *
 * @author Farelino Alexander Kim 
 */
public class EventDAO implements IProjectItemDAO<Event>, IRowMapper<Event> {

	@Override
	public List<Event> fetchByProject(Project id) {
		if (id == null) {
			return new ArrayList<>();
		}

		Query sql = new Query()
				.select(
						"pi.id as id",
						"pi.title as title",
						"pi.description as description",
						"pi.color as color",
						"pi.project_id as project_id",
						"pi.created_by as created_by",
						"pi.updated_by as updated_by",
						"pi.created_at as created_at",
						"pi.updated_at as updated_at",
						"e.location as location",
						"e.is_all_day as is_all_day",
						"e.start_at as start_at",
						"e.end_at as end_at"
				)
				.from("events e")
				.join("project_items pi", "pi.id = e.project_item_id")
				.where("pi.project_id = ?", id.getId());

		try {
			List<Event> events = DB.executeQuery(sql, this::map);
			Log.create("EventDAO.fetchByProject queried " + events.size() + " row(s).");
			return events;
		} catch (DatabaseException e) {
			Log.err("EventDAO.fetchByProject failed: " + e.getMessage());
			throw new IllegalStateException("Failed to fetch events by project", e);
		}
	}

	@Override
	public List<User> fetchAsignee(Event id) {
		if (id == null) {
			return new ArrayList<>();
		}

		Query sql = new Query()
				.select("u.*")
				.from("users u")
				.join("project_item_assignees pia", "pia.user_id = u.id")
				.where("pia.project_item_id = ?", id.getId());

		try {
			List<User> assignees = DB.executeQuery(sql, rs -> {
				try {
					User user = new User(
							rs.getString("username"),
							rs.getString("full_name"),
							rs.getString("email"),
							rs.getString("password_hash")
					);
					user.setId(rs.getInt("id"));
					user.setBio(rs.getString("bio"));
					user.setProfilePicture(rs.getString("profile_picture"));
					user.setCreatedAt(rs.getTimestamp("created_at"));
					user.setUpdatedAt(rs.getTimestamp("updated_at"));
					return user;
				} catch (SQLException e) {
					throw new ResultSetParsingException(
							"Failed to parse User from ResultSet",
							e
					);
				}
			});
			Log.create("EventDAO.fetchAsignee queried " + assignees.size() + " row(s).");
			return assignees;
		} catch (DatabaseException e) {
			Log.err("EventDAO.fetchAsignee failed: " + e.getMessage());
			throw new IllegalStateException("Failed to fetch event assignees", e);
		}
	}

	@Override
	public int add(Event entity) throws DatabaseException {
		try {
			Query sql = new Query();
			sql.insertInto(
					"events",
					"project_item_id",
					"location",
					"is_all_day",
					"start_at",
					"end_at"
			).values(
					entity.getId(),
					entity.getLocation(),
					entity.isAllDay(),
					entity.getStartAt(),
					entity.getEndAt()
			);

			int rows = DB.executeUpdate(sql);
			Log.create("EventDAO.add updated " + rows + " row(s).");
			return rows;
		} catch (DatabaseException e) {
			Log.err("EventDAO.add failed: " + e.getMessage());
			throw e;
		}
	}

	@Override
	public Event get(Integer id) throws DatabaseException {
		if (id == null) {
			return null;
		}

		Query sql = new Query()
				.select(
						"pi.id as id",
						"pi.title as title",
						"pi.description as description",
						"pi.color as color",
						"pi.project_id as project_id",
						"pi.created_by as created_by",
						"pi.updated_by as updated_by",
						"pi.created_at as created_at",
						"pi.updated_at as updated_at",
						"e.location as location",
						"e.is_all_day as is_all_day",
						"e.start_at as start_at",
						"e.end_at as end_at"
				)
				.from("events e")
				.join("project_items pi", "pi.id = e.project_item_id")
				.where("pi.id = ?", id);

		try {
			List<Event> events = DB.executeQuery(sql, this::map);
			Log.create("EventDAO.get queried " + events.size() + " row(s).");
			return events.isEmpty() ? null : events.get(0);
		} catch (DatabaseException e) {
			Log.err("EventDAO.get failed: " + e.getMessage());
			throw e;
		}
	}

	@Override
	public List<Event> fetchAll() throws DatabaseException {
		Query sql = new Query()
				.select(
						"pi.id as id",
						"pi.title as title",
						"pi.description as description",
						"pi.color as color",
						"pi.project_id as project_id",
						"pi.created_by as created_by",
						"pi.updated_by as updated_by",
						"pi.created_at as created_at",
						"pi.updated_at as updated_at",
						"e.location as location",
						"e.is_all_day as is_all_day",
						"e.start_at as start_at",
						"e.end_at as end_at"
				)
				.from("events e")
				.join("project_items pi", "pi.id = e.project_item_id");

		try {
			List<Event> events = DB.executeQuery(sql, this::map);
			Log.create("EventDAO.fetchAll queried " + events.size() + " row(s).");
			return events;
		} catch (DatabaseException e) {
			Log.err("EventDAO.fetchAll failed: " + e.getMessage());
			throw e;
		}
	}

	@Override
	public int update(Event entity) throws DatabaseException {
		try {
			Query sql = new Query()
					.update("events")
					.set("location", entity.getLocation())
					.set("is_all_day", entity.isAllDay())
					.set("start_at", entity.getStartAt())
					.set("end_at", entity.getEndAt())
					.where("project_item_id = ?", entity.getId());

			int rows = DB.executeUpdate(sql);
			Log.create("EventDAO.update updated " + rows + " row(s).");
			return rows;
		} catch (DatabaseException e) {
			Log.err("EventDAO.update failed: " + e.getMessage());
			throw e;
		}
	}

	@Override
	public int delete(Integer id) throws DatabaseException {
		try {
			Query sql = new Query()
					.deleteFrom("events")
					.where("project_item_id = ?", id);

			int rows = DB.executeUpdate(sql);
			Log.create("EventDAO.delete updated " + rows + " row(s).");
			return rows;
		} catch (DatabaseException e) {
			Log.err("EventDAO.delete failed: " + e.getMessage());
			throw e;
		}
	}

	@Override
	public Event map(ResultSet rs) throws DatabaseException {
		try {
			Event event = new Event();
			event.setId(rs.getInt("id"));
			event.setTitle(rs.getString("title"));
			event.setDescription(rs.getString("description"));
			event.setColor(rs.getString("color"));

			Project project = new Project();
			project.setId(rs.getInt("project_id"));
			event.setProject(project);

			event.setCreatedAt(rs.getTimestamp("created_at"));
			event.setUpdatedAt(rs.getTimestamp("updated_at"));

			int createdById = rs.getInt("created_by");
			if (!rs.wasNull()) {
				User createdBy = new User();
				createdBy.setId(createdById);
				event.setCreatedBy(createdBy);
			}

			int updatedById = rs.getInt("updated_by");
			if (!rs.wasNull()) {
				User updatedBy = new User();
				updatedBy.setId(updatedById);
				event.setUpdatedBy(updatedBy);
			}

			event.setLocation(rs.getString("location"));
			event.setAllDay(rs.getBoolean("is_all_day"));
			event.setStartAt(rs.getTimestamp("start_at"));
			event.setEndAt(rs.getTimestamp("end_at"));

			return event;
		} catch (SQLException e) {
			Log.err("EventDAO.map failed: " + e.getMessage());
			throw new ResultSetParsingException(
					"Failed to parse Event from ResultSet",
					e
			);
		}
	}
    
}
