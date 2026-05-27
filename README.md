# MagerNoMore

MagerNoMore is a **desktop-based Java Project Management System** designed to help users organize projects, manage tasks, collaborate with team members, and handle scheduling efficiently.

This project is developed as a **final assignment for the Object-Oriented Programming (OOP) course** at Universitas Atma Jaya Yogyakarta.

---

## 👥 Project Members (NPM)

* Farelino Alexander Kim — 240713000 (Productivity Apps: Project Management)
* Silvanus Febrianesha Widyatama — 240712992
* Aldiondra Ahira Saputra — 240712797
* Bagus Tito Wijoyo — 240712833
Project Online Workspace : https://drive.google.com/drive/folders/1kbJBbLC0XBNro1HSm-lNsFAglM-vFcQw?usp=drive_link
---

## 📌 Project Overview

MagerNoMore helps users:

* Manage multiple projects in one platform
* Assign and track tasks efficiently
* Collaborate with team members
* Schedule events and activities
* Visualize project progress using multiple views

The system is built to demonstrate **Object-Oriented Programming principles** such as encapsulation, inheritance, polymorphism, and abstraction.

---

## 🎯 Objectives

* Improve task and project organization
* Simplify collaboration between users
* Provide structured task tracking and scheduling
* Support academic OOP demonstration
* Deliver an intuitive desktop application

---

## 🧑‍💼 Stakeholders

* **End User**: Team member participating in projects
* **Project Owner**: Manages projects, tasks, and members
* **Lecturer**: Academic evaluator
* **Developer**: System designer and implementer

---

## ⚙️ Features (Functional Requirements)

### 👤 User Management

* Register account
* Login / Logout securely
* Reset & change password

### 📁 Project Management

* Create, update, delete projects
* Add / remove members
* Share projects among users

### ✅ Task Management

* CRUD tasks
* Assign tasks to users
* Track progress (Pending, In Progress, Done)
* Attach files and tags

### 📅 Event Management

* Create and manage events
* Schedule participants
* Add location & tags

### 📊 Visualization

* Table View
* Board (Kanban) View
* Gantt Chart
* Calendar View

---

## 🧱 System Architecture

The system uses **MVC + DAO architecture**:

* **Model** → Data structures (User, Project, Task, Event, etc.)
* **View** → Java GUI interface
* **Controller** → Business logic
* **DAO** → Database access layer
* **Connection** → MySQL connectivity

---

## 🛠️ Technology Stack

* Programming Language: Java
* IDE: NetBeans
* Database: MySQL
* Local Server: XAMPP
* OS: Windows

---

## 🧠 OOP Principles Used

* Encapsulation
* Inheritance
* Polymorphism
* Abstraction
* Exception Handling
* Proper object lifecycle management

---

## 🗄️ Database Structure

Main tables include:

* User, Social
* Project, ProjectMember
* Task, Event
* Tag, Attachment
* ProjectItem, Assignee relations

Supports:

* Relational integrity
* Multi-user collaboration
* Task/event tagging system

---

## 🚫 Out of Scope

* Mobile application
* Cloud deployment
* Real-time chat system
* AI recommendations
* Notifications
* Third-party integrations

---

## 📈 Success Criteria

The project is considered successful if:

* All CRUD operations work correctly
* Response time < 3 seconds
* GUI is consistent and user-friendly
* Database integrity is maintained
* OOP principles are clearly implemented

---

## 🧪 Exception Handling

System handles:

### Built-in Exceptions

* NumberFormatException
* SQLException
* IOException
* NullPointerException

### Custom Exceptions

* InvalidLoginCredentialException
* EmptyFieldException
* InvalidInputException
* AccessDeniedException

---

## 📌 Summary

MagerNoMore is a structured, scalable desktop project management system built for academic purposes, focusing on **clean architecture, OOP principles, and collaborative workflow management**.

---

If you want, I can also:

* convert this into a **GitHub-ready README with badges + screenshots section**
* or generate a **UML diagram section**
* or make a **professional portfolio version**
