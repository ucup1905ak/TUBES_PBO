# **Tugas Besar Pemrograman Berorientasi Objek**

**Date:** May 20, 2026  
**Institution:** Universitas Atma Jaya Yogyakarta  
**Department:** Program Studi Informatika  
**Author:** [Farel AK](mailto:alexanderkimf@gmail.com)

# **Business Requirement Document** **MagerNoMore** 

| Created by | Farelino Alexander Kim |
| :---- | :---- |
| **On** | May 20, 2026 |
| **version** | v1.1 |
| **status** | Approved |

**MagerNoMore** is a desktop-based Java application developed to help users organize projects, manage tasks, collaborate with team members, and handle scheduling activities efficiently. The system is intended as a final project submission for the Object-Oriented Programming (OOP) course at Universitas Atma Jaya Yogyakarta.

The application aims to improve productivity and collaboration through centralized project tracking, task assignment, and visual project monitoring.

**Objectives**

The system is expected to achieve the following objectives:

* Improve task and project organization.  
* Simplify collaboration between users.  
* Provide structured task tracking and scheduling.  
* Support academic demonstration of OOP concepts.  
* Deliver an intuitive and responsive desktop application.

**Stakeholders**

| Stakeholder | Role |
| ----- | ----- |
| **End User** | A user who participates in projects created by another user or project owner. Team members collaborate with other users to complete assigned tasks and participate in project activities.  |
| **Project Owner** | A user who creates and manages projects within the system. The project owner has full control over project settings, members, tasks, and events.  |
| **Lecturer** | Acts as the academic evaluator for the final project implementation. Lecturers review whether the application satisfies functional and technical requirements.  |
| **Developer** | The individual or team responsible for designing, implementing, testing, and maintaining the system. |

# **In Scope**

* User authentication and account management  
* Project creation and collaboration  
* Task and event management  
* Task visualization dashboards  
* File attachment support  
* MySQL database integration

# **Out Of Scope**

* Mobile application support  
* Cloud deployment  
* Third-party integrations  
* Real-time messaging/chat system  
* AI-powered recommendations  
* Notifications for deadlines

# **Business Requirement**

## **5.1 User Management**

* Users must be able to register accounts.  
* Users must securely log in and log out.  
* Users must be able to reset and change passwords.

## **5.2 Project Collaboration**

* Users can create and manage projects.  
* Users can add members into projects.  
* Users can remove members from projects.  
* Projects can be shared among multiple users.

## **5.3 Task Management**

* Users can create, edit, search, and delete tasks.  
* Tasks can be assigned to users.  
* Tasks must support progress tracking.  
* Tasks can contain attachments and tags.

## **5.4 Event Management**

* Users can create and manage events.  
* Events include scheduling and participant management.  
* Events support location information and tagging.

## **5.5 Visualization**

The system must support:

1. Table View  
2. Board View  
3. Gantt Chart  
4. Calendar

# **6\. Success Criteria**

The project is considered successful if:

* All CRUD features operate correctly.  
* Response time remains under 3 seconds (using preemptive loading method).  
* GUI is user-friendly and consistent.  
* Database integrity is maintained.  
* OOP principles are clearly implemented.

# 

# **Product Requirement Document**

| Created by | Farelino Alexander Kim |
| :---- | :---- |
| **On** | May 19, 2026 |
| **version** | v1.5 |
| **status** | Approved |

# **1\. Project Overview**

This document outlines the technical and functional specifications for the Project Management System, a Java-based application designed to facilitate task organization, project collaboration, and event management. The system is developed as a final project for the Object-Oriented Programming (OOP) course at Universitas Atma Jaya Yogyakarta.

## **1.1 Project Goals**

The Project Management System aims to provide an efficient and user-friendly platform for managing projects, tasks, and events within a collaborative environment. The goals of this project include:

* Improve organization of projects and tasks.  
* Simplify collaboration between multiple users within a project.  
* Provide structured task tracking using status monitoring and visualization tools.  
* Support scheduling and event management activities.  
* Demonstrate the implementation of Object-Oriented Programming (OOP) principles in a real-world application.  
* Deliver a responsive and intuitive desktop-based user interface for academic and personal project management.

  # **Stakeholder**

The primary target users of the Project Management System are students, academic project teams, and small collaborative groups who require a centralized platform for organizing tasks and schedules.

| User Type | Description |
| ----- | ----- |
| **End User** | A user who participates in projects created by another user or project owner. Team members collaborate with other users to complete assigned tasks and participate in project activities.  |
| **Project Owner** | A user who creates and manages projects within the system. The project owner has full control over project settings, members, tasks, and events.  |
| **Lecturer** | Acts as the academic evaluator for the final project implementation. Lecturers review whether the application satisfies functional and technical requirements.  |
| **Developer** | The individual or team responsible for designing, implementing, testing, and maintaining the system. |

## 

# **2\. System Actors**

* **User** : The main actor of the system who can register accounts, log in, create and manage projects, create and update tasks, manage events, upload attachments, and use visualization features. A User can act as a **Team Member** or as a **Project Owner**  
  * **Team Member:** A user who participates in projects created by a Project Owner. Team members can access shared projects, update assigned tasks, manage task progress, and participate in events.   
  * **Project Owner**: A user with additional permissions to create projects, invite or remove members, assign tasks, manage project information, and monitor overall project progress.

  ## **2.1. User Access Control (UAC)**

* **Project Owner**: Update nama deskripsi project, add/remove member, create/delete/edit project.  
* **Project Member**: CRUD task event.

  # **3\. Functional Requirements**

## **3.1 User Management**

* Create an account (Sign-up).  
* Secure Login and Logout functionality.  
* Password management (Reset and Change password).

## **3.2 Project Management**

* Perform CRUD operations (Create, Read, Update, Delete) on projects.  
* Organize tasks within specific projects.  
* Share projects with other users.  
* Manage project membership (Invite or Remove members).

## **3.3 Task and Event Management**

* Manage tasks: Create, edit, search, and remove tasks.  
* Assign tasks to specific users.  
* Track task progress via status updates (Pending, In Progress, Done).  
* Add attachments and tags to tasks for better organization.  
* Manage events including participants, locations, and schedules.

## **3.4 Data Visualization**

The system must provide the following views for tasks:

* **Table View:** Detailed grid representation.  
* **Board View:** Kanban-style status tracking.  
* **Gantt View:** Timeline-based visualization.  
* **Calendar View:** Calendar based visualization.

  # **5\. Product Constraint**

## **5.1 Academic Constraints**

* The project must demonstrate the implementation of Object-Oriented Programming (OOP) principles including Encapsulation, Inheritance, Polymorphism, and Abstraction.  
* The project must fulfill the final project requirements of the OOP course at Universitas Atma Jaya Yogyakarta.  
* The source code and documentation must be original work created by the development team.  
* The project must implement basic UI/UX principal:  
  * predictability  
  * feedback  
  * familiarity  
  * generalizability  
  * consistency

## **5.3 Resource Constraints**

* The system will be developed without paid third-party services or enterprise tools.  
* Hardware and software resources are limited to personal computers and locally hosted servers.

## **5.4 Operational Constraints**

* Internet connectivity is not mandatory for local system operation.  
* The system is intended for desktop usage only and does not support mobile platforms.  
* Real-time synchronization and cloud deployment are outside the current project scope.

  # **6\. Acceptance Criteria (Minimum Viable Product)**

* The published application must be able to demonstrate all specific Use cases successfully.  
* The Application must have a good looking and easy to use interface.  
* The system must maintain data integrity and persistence over a period of time with continuous testing and real world use.  
* The system must comply with the technical requirements of TUGAS BESAR PBO.  
* The system must have good response time ensuring great user experience.

# **Technical Requirement Document**

| Created by | Farelino Alexander Kim |
| :---- | :---- |
| **On** | May 27, 2026 |
| **version** | v1.89 |
| **status** | In progress |

1. # **Technical Requirements**

   1. ## **Technology Stack**

      **Programming Language		:** Java (Java Runtime Environment).  
      **Development Environment		:** NetBeans 12.6 or higher.  
      **Database				:** MySQL (via XAMPP Package).  
      **Operating System			:** Windows.

   2. ## **Architecture and Design Patterns**

      The application must follow the **MVC-DAO** architecture:  
        
* **View:** Graphical User Interface (GUI).  
* **Control:** Business logic and event handling.  
* **Database Access Object (DAO):** Abstract interface to the database.  
* **Model:** Data structures and entities.  
* **Connection:** Database connectivity management.

  3. ## **OOP Core Principles**

     The implementation must demonstrate proficiency in:  
       
* **Encapsulation:** Protecting data using private fields and public accessors.  
* **Inheritance:** Establishing hierarchical relationships between classes.  
* **Polymorphism:** Utilizing method overriding and overloading.  
* **Abstraction:** Using Abstract Classes and Interfaces to define templates.  
* **Constructors/Destructors:** Proper object initialization and lifecycle management.  
* **Exception Handling:** Graceful management of runtime errors.


2. #  **Non-Functional Requirements**

* **Performance:** System response time must be under 3 seconds.  
* **Data Integrity:** MySQL database with at least 6 tables and 10 sample records per table.  
* **Security:** Password hashing and mandatory authentication for protected resources.  
* **Usability:** User-friendly GUI with consistent styling and appropriate component usage.  
* **Reliability:** Validation of all user inputs and handling of unexpected errors.


3. # **Runtime Exception & Exception Handling**

The system must implement an error handling system with good feedback.

1. # **Default** 

   System must handle these Exception correctly:  
* NumberFormatException & InputFormatException  
* SQLException  
* IOException  
* NullPointerException


  2. # **User Defined**

     System must handle these Exception correctly:  
* InvalidLoginCredentialException  
* EmptyFieldException  
* InvalidInputException  
* AccessDeniedException  
* QueryTypeMismatchException   
  


4. # **Views**

1. Login Page  
2. Register Page  
3. Dashboard / Select project  
   1. Table (tasks & events)  
   2. Board (tasks).  
   3. Gantt Chart (tasks & events)  
   4. Calendar (events)  
4. Create/Edit/Delete project panel  
5. Create/Edit task panel (include attachments)  
6. Create/Edit event panel (include attachments)  
7. Search & Create tags panel (simple as possible)  
8. Add/Remove user member  
9. Edit personal user profile 

   # **Entity Definitions**

| Entity | Description  |
| :---- | :---- |
| **User** | Represents a system user who can create accounts, manage projects, collaborate with other members, and perform task or event-related activities. Stores authentication and profile information.  |
| **Project** | Represents a workspace used to organize tasks, events, and collaboration between users. A project contains members, ownership information, descriptions, and customizable colors. |
| **ProjectItem** | An abstract entity representing a common base for Task and Event within a project. Holds shared attributes such as title, description, color, tags, attachments, assignees, and audit fields (created/updated by). Not instantiated directly. |
| **Task** | Represents a unit of work within a project. Tasks can be assigned to users, tracked by status and priority, attached with files and tags, and managed using deadlines and completion dates. |
| **Event** | Represents a scheduled activity or meeting within the system. Events contain scheduling details, locations, participants, tags, and optional attachments for coordination purposes. |
| **Tag** | Represents a label used to categorize and organize tasks or events. Tags improve filtering, searching, and visual organization within the application. |
| **Attachment** | Represents a file uploaded into the system and associated with tasks or events. Attachments store file metadata such as file type, size, upload time, and uploader information. |
| **Social**  | Represents a social media or external profile link belonging to a user. Stores the platform type (GITHUB, LINKEDIN, INSTAGRAM, etc.) and the corresponding URL. |

   # 

5. # **API/Interface Control Class Design**

### **Interfaces**

✅

| \<\<Interface\>\> ITaggable |
| ----- |
| \+ getTags() : List\<Tag\> \+ setTags(tags : List\<Tag\>) : void \+ addTag(tag : Tag) : void \+ removeTag(tag : Tag) : void |

✅

| \<\<Interface\>\> IAuditable |
| ----- |
| \+ getCreatedAt() : Timestamp \+ setCreatedAt(createdAt : Timestamp) : void \+ getUpdatedAt() : Timestamp \+ setUpdatedAt(updatedAt : Timestamp) : void |

✅

| \<\<Interface\>\> IAssignable |
| ----- |
| \+ getAssignee() : List\<User\> \+ addAssignee(assignee : User) : void \+ removeAssignee(assignee : User) : void |

✅

| \<\<Interface\>\> IGenericDAO\<T, ID\> |
| ----- |
| \+ add(entity : T) : int \+ get(id : ID) : T \+ fetchAll() : List\<T\> \+ update(entity : T) : int \+ delete(id : ID) : int |

✅

| \<\<Interface\>\> IGenericControl\<T, ID\> |
| ----- |
| \+ add(entity : T) : int \+ get(id : ID) : T \+ fetchAll() : List\<T\> \+ update(entity : T) : int \+ delete(id : ID) : int |

✅

| \<\<Interface\>\> IDatabaseConnection |
| ----- |
| \+ isConnected() : boolean \+ executeQuery( query : Query,mapper : IRowMapper\<T\>) : List\<T\>   throws QueryTypeMismatchException,          DatabaseConnectionFailedException,          SQLException \+ executeUpdate(query : Query) : int   throws QueryTypeMismatchException,          DatabaseConnectionFailedException,          SQLException |

✅

| \<\<Interface\>\> IRowMapper\<T\> |
| ----- |
| \+map(rs : ResultSet) : T |

| \<\<Interface\>\> IProjectItemDAO\<T\> extends IGenericDAO\<ProjectItem, Integer\> |
| ----- |
| fetchByProject(projectId : Integer) : List\<T\> fetchAsignee(projectItemId : Integer) : List\<User\> |

### **Control**

✅

| UserControl implements IGenericControl\<User, Integer\> |
| :---- |
| \-dao : UserDAO |
|  \+ add(entity : User) : int \+ get(id : Integer) : User \+ fetchAll() : List\<User\> \+ update(entity : User) : int \+ delete(id : Integer) : int  \+ search(username : keyword) : User \+ updateProfile(user : User) : boolean \+getByEmail(email : string) : User \+getByUsername(username : String) : User |

✅ 

| SocialControl implements IGenericControl\<Social, Integer\> |
| :---- |
| \- socialDAO : SocialDAO |
| \+ add(entity : Social) : boolean \+ get(id : Integer) : Social \+ fetchAll() : List\<Social\> \+ update(entity : Social) : boolean \+ delete(id : Integer) : boolean \+ findByUserId(userId : Integer) : List\<Social\> |

🧧

| \<\<interface\>\> ProjectItemControl implements IGenericControl\<ProjectItem, Integer\> |
| :---- |
| \- projectItemDAO : ProjectItemDAO |
| \+ add(entity : ProjectItem) : boolean \+ get(id : Integer) : ProjectItem \+ fetchAll() : List\<ProjectItem\> \+ update(entity : ProjectItem) : boolean \+ delete(id : Integer) : boolean \+ search(keyword : String) : List\<ProjectItem\> \+ addTag(itemId : Integer, tag : Tag) : boolean \+ removeTag(itemId : Integer, tagId : Integer) : boolean \+ addAttachment(itemId : Integer, attachment : Attachment) : boolean \+ removeAttachment(itemId : Integer, attachmentId : Integer) : boolean \+ assignUser(itemId : Integer, user : User) : boolean \+ removeAssignee(itemId : Integer, userId : Integer) : boolean |

🧧

| EventControl extends ProjectItemControl  |
| :---- |
| \-eventDAO : EventDAO |
| \+ add(entity : Event) : boolean \+ get(id : Integer) : Event \+ fetchAll() : List\<Event\> \+ update(entity : Event) : boolean \+ delete(id : Integer) : boolean \+ findByProject(projectId : Integer) : List\<Event\> \+ search(keyword : String) : List\<Event\> \+ getEventsByDate(date : Date) : List\<Event\> \+ getUpcomingEvents() : List\<Event\> \+ addParticipant(eventId : Integer, user : User) : boolean \+ removeParticipant(eventId : Integer, userId : Integer) : boolean |

🧧

| TaskControl extends ProjectItemControl  |
| :---- |
| \- taskDAO : TaskDAO |
| \+ add(entity : Task) : boolean \+ get(id : Integer) : Task \+ fetchAll() : List\<Task\> \+ update(entity : Task) : boolean \+ delete(id : Integer) : boolean \+ findByProject(projectId : Integer) : List\<Task\> \+ findByAssignee(userId : Integer) : List\<Task\> \+ search(keyword : String) : List\<Task\> \+ updateStatus(taskId : Integer, status : TaskStatus) : boolean \+ updatePriority(taskId : Integer, priority : TaskPriority) : boolean \+ markAsDone(taskId : Integer) : boolean \+ getTasksByStatus(status : TaskStatus) : List\<Task\> \+ getTasksDueToday() : List\<Task\> \+ getOverdueTasks() : List\<Task\> |

✅ 

| ProjectControl implements IGenericControl\<Project, Integer\> |
| :---- |
| \- projectDAO : ProjectDAO \- user : User \- selectedProject : Project |
| \+ ProjectControl(user : User) \+ add(entity : Project) : boolean \+ get(id : Integer) : Project \+ fetchAll() : List\<Project\> \+ update(entity : Project) : boolean \+ delete(id : Integer) : boolean \+ fetchUserProjects() : List\<Project\> \+ setProject(projectId : Integer) : boolean \+ getProject() : Project \+ editSelectedProject() : boolean \+ deleteSelectedProject() : boolean \+ addMember(user : User) : boolean \+ removeMember(user : User) : boolean \+ getMembers() : List\<User\> \+ getUserRole() : UserRole |

🧧

| TagControl implements IGenericControl\<Tag, Integer\> |
| :---- |
| –tagDAO: TagDAO |
| \+ add(entity : Task) : boolean \+ get(id : Integer) : Task \+ fetchAll() : List\<Task\> \+ update(entity : Task) : boolean \+ delete(id : Integer) : boolean \+ findByProject(projectId : Integer) : List\<Task\> \+ findByAssignee(userId : Integer) : List\<Task\> \+ search(keyword : String) : List\<Task\> \+ updateStatus(taskId : Integer, status : TaskStatus) : boolean \+ updatePriority(taskId : Integer, priority : TaskPriority) : boolean \+ markAsDone(taskId : Integer) : boolean \+ getTasksByStatus(status : TaskStatus) : List\<Task\> \+ getTasksDueToday() : List\<Task\> \+ getOverdueTasks() : List\<Task\> |

🧧

| AttachmentControl implements IGenericControl\<Attachment, Integer\> |
| :---- |
| \- attachmentDAO : AttachmentDAO |
| \+ add(entity : Attachment) : boolean \+ get(id : Integer) : Attachment \+ fetchAll() : List\<Attachment\> \+ update(entity : Attachment) : boolean \+ delete(id : Integer) : boolean \+ upload(file : File, projectItemId : Integer) : Attachment \+ download(id : Integer, destination : String) : boolean \+ findByProjectItem(itemId : Integer) : List\<Attachment\> |

✅ 

| SessionControl |
| :---- |
| \- currentSession : Session \- authService : AuthService  |
| \+ login(email : String, password : String) : boolean \+ logout() : void \+ getCurrentSession() : Session \+ getCurrentUser() : User \+ isAuthenticated() : boolean \+ isSessionValid() : boolean  \+refreshSession() : boolean \+ clearSession() : void |

### **Services**

✅ 

| AuthService  |
| :---- |
| \-token : String  \-user : User |
| \+authenticate(username : String, passwordHash : String) : Session | null \+getCurrentSession(token : String) : Session | null  |

✅ 

| DatabaseConnection implements IDatabaseConnection |
| :---- |
| \+SCHEME : String \+HOSTNAME : String \+DATABASE : String \+PORT : int \-USERNAME : String \-PASSWORD : String |
| \+getPath() : String \-connect() : void \-disconnect() : void \+ isConnected() : boolean \+ executeQuery(query : Query, mapper : IRowMapper\<T\>) : List\<T\> \+ executeUpdate(query : Query) : int |

### **Utility**

✅ 

| Query |
| :---- |
| \-sql : StringBuilder \-parameters : List\<Object\> \+Type : Enum (SELECT, INSERT, UPDATE, DELETE, UNKNOWN) \+queryType : Type |
| \+queryType : Type \+Type (enum): SELECT, INSERT, UPDATE, DELETE, UNKNOWN Methods: \+Query() \+select(columns : String...) : Query \+from(table : String) : Query \+where(condition : String, values : Object...) : Query \+and(condition : String, values : Object...) : Query \+or(condition : String, values : Object...) : Query \+join(table : String, condition : String) : Query \+leftJoin(table : String, condition : String) : Query \+rightJoin(table : String, condition : String) : Query \+insertInto(table : String, columns : String...) : Query \+values(values : Object...) : Query \+update(table : String) : Query \+set(column : String, value : Object) : Query \+deleteFrom(table : String) : Query \+getParameters() : List\<Object\> \+build() : String \+toString() : String |

✅ 

| Log |
| :---- |
| \-LOG\_FILE : String \-FORMATTER : DateTimeFormatter |
| \+create(String msg) : void \+err(String msg) : void |

### **DAO**

✅ 

| SocialDAO implements IGenericDAO\<Social, Integer\>, IRowMapper\<Social\> |
| :---- |
| \- db : DatabaseConnection |
| \+ add(entity : T) : int \+ get(id : ID) : T \+ fetchAll() : List\<T\> \+ update(entity : T) : int \+ delete(id : ID) : int \+ findByUserId(int userId) : List\<Social\> \+ map(rs : ResultSet) : Social |

✅ 

| UserDAO implements IGenericDAO\<User, Integer\> |
| :---- |
| \- db : DatabaseConnection |
| \+ add(entity : T) : int \+ get(id : ID) : T \+ fetchAll() : List\<T\> \+ update(entity : T) : int \+ delete(id : ID) : int \+ map(rs : ResultSet) : User |

✅ 

| SessionDAO implements IGenericDAO\<Task, Integer\>, IRowMapper\<Task\> |
| :---- |
| \-db : DatabaseConnection |
| \+ add(entity : T) : int \+ get(id : ID) : T \+ fetchAll() : List\<T\> \+ update(entity : T) : int \+ delete(id : ID) : int \+ map(rs : ResultSet) : Session \+getByToken(token : String) : Session \+getByUserId(userId : int) : List\<Session\> \+invalidate(token : String) : void \+invalidateAllUserSessions(int userId) : void |

✅ 

| ProjectDAO implements IGenericDAO\<Project, Integer\>, IRowMapper\<Project\> |
| :---- |
| \- db : DatabaseConnection |
| \+ add(entity : T) : int \+ get(id : ID) : T \+ fetchAll() : List\<T\> \+ update(entity : T) : int \+ delete(id : ID) : int \+ map(rs : ResultSet) : Project |

✅ 

| ProjectItemDAO implements IGenericDAO\<Task, Integer\>, IRowMapper\<Task\> |
| :---- |
| \-db : DatabaseConnection |
| \+ add(entity : T) : int \+ update(entity : T) : int \+ delete(id : ID) : int \+ map(rs : ResultSet) : ProjectItem |

🧧

| TagDAO implements IGenericDAO\<Task, Integer\>, IRowMapper\<Task\> |
| :---- |
| \-db : DatabaseConnection |
| \+ add(entity : T) : int \+ get(id : ID) : T \+ fetchAll() : List\<T\> \+ update(entity : T) : int \+ delete(id : ID) : int \+ map(rs : ResultSet) : Tag |

🧧

| TaskDAO implements IGenericDAO\<Task, Integer\>, IRowMapper\<Task\> |
| :---- |
| \-db : DatabaseConnection |
| \+ add(entity : T) : int \+ get(id : ID) : T \+ fetchAll() : List\<T\> \+ update(entity : T) : int \+ delete(id : ID) : int \+ map(rs : ResultSet) : Task |

🧧

| EventDAO implements IGenericDAO\<Task, Integer\>, IRowMapper\<Task\> |
| :---- |
| \-db : DatabaseConnection |
| \+ add(entity : T) : int \+ get(id : ID) : T \+ fetchAll() : List\<T\> \+ update(entity : T) : int \+ delete(id : ID) : int \+ map(rs : ResultSet) : Event |

🧧

| AttachmentDAO implements IGenericDAO\<Task, Integer\>, IRowMapper\<Task\> |
| :---- |
| \-db : DatabaseConnection |
| \+ add(entity : T) : int \+ get(id : ID) : T \+ fetchAll() : List\<T\> \+ update(entity : T) : int \+ delete(id : ID) : int \+ map(rs : ResultSet) : Attachment |

🧧

| ProjectMemberDAO implements IRowMapper\<Task\> |
| :---- |
| \-db : DatabaseConnection |
| \+ add(member : ProjectMember) : int \+ remove(projectId : Integer, userId : Integer) : int \+ getByProject(projectId : Integer) : List\<ProjectMember\> \+ getByUser(userId : Integer) : List\<ProjectMember\> \+ updateRole(projectId : Integer, userId : Integer, role : ProjectRole) : int \+ map(rs : ResultSet) : ProjectMember |

🟨

| ProjectItemAssigneeDAO |
| :---- |
| \-db : DatabaseConnection |
| \+ assignUser(projectItemId : Integer, userId : Integer) : int \+ removeAssignee(projectItemId : Integer, userId : Integer) : int \+ getAssignees(projectItemId : Integer) : List\<User\> \+ getAssignedItems(userId : Integer) : List\<ProjectItem\>  |

🧧

| ProjectItemTagDAO implements IRowMapper\<Task\> |
| :---- |
| \-db : DatabaseConnection |
| \+ assignTag(projectItemId : Integer, tagId : Integer) : int \+ removeTag(projectItemId : Integer, tagId : Integer) : int \+ getTags(projectItemId : Integer) : List\<Tag\> \+ getTaggedItems(tagId : Integer) : List\<ProjectItem\>  |

### **Exception**

✅ 

| \<\<exception\>\>  DatabaseException extends SQLException |
| :---- |
| \+ DatabaseException(message : String) \+ DatabaseException(message : String, cause : Throwable) |

✅ 

| \<\<exception\>\>  DatabaseConnectionFailedException extends DatabaseException  |
| :---- |
| \+ DatabaseConnectionFailedException(message : String) \+ DatabaseConnectionFailedException( message : String, cause : Throwable ) |

✅ 

| \<\<exception\>\>  QueryExecutionException extends DatabaseException  |
| :---- |
| \-query : String  |
| \+ QueryExecutionException(message : String) \+ QueryExecutionException(       message : String,       cause : Throwable   ) \+ QueryExecutionException(       query : String,       cause : Throwable   ) \+ getQuery() : String |

✅ 

| \<\<exception\>\>  QueryTypeMismatchException extends RuntimeException |
| :---- |
| \- actual : Query.Type \- expected : List\<Query.Type\>  |
| \+ QueryTypeMismatchException(       actual : Query.Type,       expected : Query.Type...   ) \+ getActual() : Query.Type \+ getExpected() : List\<Query.Type\> \+ getMessage() : String \+ toString() : String |

✅ 

| \<\<exception\>\>  AuthenticationException extends Exception |
| :---- |
| \+ AuthenticationException(message : String) \+ AuthenticationException(       message : String,       cause : Throwable   ) |

✅ 

| \<\<exception\>\>  InvalidLoginCredentialException extends AuthenticationException  |
| :---- |
| \+ InvalidLoginCredentialException(message : String) \+ InvalidLoginCredentialException(       message : String,       cause : Throwable   ) |

✅ 

| \<\<exception\>\>  SessionExpiredException extends AuthenticationException  |
| :---- |
| \+ SessionExpiredException(message : String) \+ SessionExpiredException(       message : String,       cause : Throwable   )  |

✅ 

| \<\<exception\>\>  AuthorizationException extends Exception |
| :---- |
| \+ AuthorizationException (message : String) \+ AuthorizationException (       message : String,       cause : Throwable   ) |

✅ 

| \<\<exception\>\>  AccessDeniedException extends AuthorizationException  |
| :---- |
| \+ AccessDeniedException(message : String) \+ AccessDeniedException(       message : String,       cause : Throwable   ) |

✅ 

| \<\<exception\>\>  ValidationException extends Exception  |
| :---- |
| \+ ValidationException(message : String) \+ ValidationException(       message : String,       cause : Throwable   ) |

✅ 

| \<\<exception\>\>  EmptyFieldException extends ValidationException |
| :---- |
| \-fieldName : String |
| \+ EmptyFieldException(message : String) \+ EmptyFieldException(fieldName : String) \+ getFieldName() : String  |

✅ 

| \<\<exception\>\>  InvalidInputException extends ValidationException |
| :---- |
| \+ InvalidInputException(message : String)  |

✅ 

| \<\<exception\>\>  InvalidFormatException extends ValidationException |
| :---- |
| \-fieldName : String \-expectedFormat : String  |
| \+ InvalidFormatException(message : String) \+ InvalidFormatException(       fieldName : String,       expectedFormat : String   ) \+ getFieldName() : String \+ getExpectedFormat() : String |

//BUSSINESS RELATED EXCEPTION   
✅ 

| \<\<exception\>\>  CommonException extends Exception  |
| :---- |
| \+ CommonException(message : String) \+ CommonException(       message : String,       cause : Throwable   ) |

✅ 

| \<\<exception\>\>  EntityNotFoundException extends CommonException |
| :---- |
| \+ EntityNotFoundException(message : String)  |

✅ 

| \<\<exception\>\>  UserAlreadyMemberException extends CommonException |
| :---- |
| \+ UserAlreadyMemberException(message : String)  |

✅ 

| \<\<exception\>\>  InvalidProjectStateException extends CommonException |
| :---- |
| \+ InvalidProjectStateException(message : String)  |

✅ 

| \<\<exception\>\>  InvalidTaskStateException extends CommonException |
| :---- |
| \+ InvalidTaskStateException(message : String)  |

✅ 

| \<\<exception\>\>  TaskAssignmentException extends CommonException |
| :---- |
| \+ TaskAssignmentException(message : String)  |

✅ 

| \<\<exception\>\>  EventSchedulingException extends CommonException |
| :---- |
| \+ EventSchedulingException(message : String)  |

### **Enum**

✅ 

| \<\<ENUM\>\> SocialPlatform |
| :---- |
| GITHUB LINKEDIN INSTAGRAM TWITTER FACEBOOK DISCORD YOUTUBE WEBSITE |

### ✅ 

| \<\<ENUM\>\> TaskPriority |
| :---- |
| LOW MEDIUM HIGH  |

### ✅ 

| \<\<ENUM\>\> TaskStatus |
| :---- |
| PENDING IN\_PROGRESS DONE  |

### ✅ 

| \<\<ENUM\>\> UserRole |
| :---- |
| PROJECT\_OWNER TEAM\_MEMBER  |

### ✅ 

| \<\<ENUM\>\> AttachmentType |
| :---- |
| IMAGE DOCUMENT VIDEO ARCHIVE OTHER  |

### **🧧**

| \<\<ENUM\>\> ProjectItemType |
| :---- |
| EVENT TASK |

### **Entity**

✅ 

| Social |
| :---- |
| \-id INT  \- user : User \-SocialPlatform : SocialPlatform \-url : String |
| \+Social(user: User, platform : SocialPlatform, url : String) \+Social(platform : SocialPlatform, url : String) \+getPlatform() : SocialPlatform \+setPlatform(platform : SocialPlatform) : void \+getUrl() : String \+setUrl(url : String) : void \+getUser() : User \+setUser() : void \+toString() : String  |

✅ 

| Session |
| :---- |
| \-id : int \-token : String \-user : User \-createdAt : Timestamp \-expiresAt : Timestamp \-isActive : boolean |
| \+Session() \+getId() : int \+setId(id : int) : void \+getToken() : String \+setToken(token : String) : void \+getUser() : User \+setUser(user : User) : void \+getCreatedAt() : Timestamp \+setCreatedAt(createdAt : Timestamp) : void \+getExpiresAt() : Timestamp \+setExpiresAt(expiresAt : Timestamp) : void \+isActive() : boolean \+setActive(isActive : boolean) : void \+isValid() : boolean \+logout() : void \+toString() : String |

✅ 

| User |
| :---- |
| \-id : int \-username : String \-email : String \-passwordHash : String  \-fullName : String \-bio : String \-profilePicture : String \-socials : List\<Social\> \-createdAt : Timestamp \-updatedAt : Timestamp  |
|  \+getId() : int \+setId(id : int) : void \+getUsername() : String \+setUsername(username : String) : void \+getEmail() : String \+setEmail(email : String) : void \+getPasswordHash() : String \+setPasswordHash(passwordHash : String) : void  \+getFullName() : String \+setFullName(fullName : String) : void \+getBio() : String \+setBio(bio : String) : void \+getProfilePicture() : String \+setProfilePicture(profilePicture : String) : void \+getSocials() : List\<Social\> \+setSocials(socials : List\<Social\>) : void \+addSocial(social : Social) : void \+removeSocial(social : Social) : void  \+getCreatedAt() : Timestamp \+setCreatedAt(createdAt : Timestamp) : void \+getUpdatedAt() : Timestamp \+setUpdatedAt(updatedAt : Timestamp) : void \+toString() : String  |

✅ 

| Project |
| :---- |
| \-id : int \-name : String \-description : String \-color : String \-members : List\<User\> \-createdAt : Timestamp \-updatedAt : Timestamp  |
| \+getId() : int \+setId(id : int) : void \+getName() : String \+setName(name : String) : void \+getDescription() : String \+setDescription(description : String) : void \+getColor() : String \+setColor(color : String) : void \+getMembers() : List\<User\> \+setMembers(members : List\<User\>) : void \+addMember(user : User) : void \+removeMember(user : User) : void \+getCreatedAt() : Timestamp \+setCreatedAt(createdAt : Timestamp) : void \+getUpdatedAt() : Timestamp \+setUpdatedAt(updatedAt : Timestamp) : void \+toString() : String |

✅ 

| ProjectItem implements IAssignable, IAuditable, ITaggable |
| :---- |
| \-id : int \-title : String \-description : String \-color : String \-project : Project \-tags : List\<Tag\> \-attachments : List\<Attachment\> \-createdAt : Timestamp \-createdBy: User \-updatedAt : Timestamp  \-updatedBy: User \-assignee : List\<User\> |
| \+getAssignee() : List\<User\> \+addAssignee(assignee : User) : void \+removeAssignee(assignee : User) : void \+getId() : int \+setId(id : int) : void \+getTitle() : String \+setTitle(title : String) : void \+getDescription() : String \+setDescription(description : String) : void \+getColor() : String \+setColor(color : String) : void \+getProject() : Project \+setProject(project : Project) : void \+getTags() : List\<Tag\> \+setTags(tags : List\<Tag\>) : void \+addTag(tag : Tag) : void \+removeTag(tag : Tag) : void \+getAttachments() : List\<Attachment\> \+setAttachments(attachments : List\<Attachment\>) : void \+addAttachment(attachment : Attachment) : void \+removeAttachment(attachment : Attachment) : void \+getCreatedAt() : Timestamp \+setCreatedAt(createdAt : Timestamp) : void \+getUpdatedAt() : Timestamp \+setUpdatedAt(updatedAt : Timestamp) : void \+toString() : String  |

✅ 

| Task extends ProjectItem |
| :---- |
| \-priority : TaskPriority \-status : TaskStatus \-startDate : Date \-dueDate : Date \-completedAt : Date  |
| \+getPriority() : TaskPriority \+setPriority(priority : TaskPriority) : void \+getStatus() : TaskStatus \+setStatus(status : TaskStatus) : void \+getStartDate() : Date \+setStartDate(startDate : Date) : void \+getDueDate() : Date \+setDueDate(dueDate : Date) : void \+getCompletedAt() : Date \+setCompletedAt(completedAt : Date) : void \+markAsDone() : void \+toString() : String  |

✅ 

| Event extends ProjectItem |
| :---- |
| \-location : String \-isAllDay : boolean \-startAt : Timestamp \-endAt : Timestamp |
| \+getLocation() : String \+setLocation(location : String) : void \+isAllDay() : boolean \+setAllDay(isAllDay : boolean) : void \+getStartAt() : Timestamp \+setStartAt(startAt : Timestamp) : void \+getEndAt() : Timestamp \+setEndAt(endAt : Timestamp) : void \+toString() : String  |

✅ 

| Tag |
| :---- |
| \-id : int \-name : String \-color : String \-createdAt : Timestamp  |
|  \+getId() : int \+setId(id : int) : void \+getName() : String \+setName(name : String) : void \+getColor() : String \+setColor(color : String) : void \+getCreatedAt() : Timestamp \+setCreatedAt(createdAt : Timestamp) : void \+toString() : String   |

✅ 

| Attachment |
| :---- |
| \-id : int \-projectItem : ProjectItem \-fileName : String \-filePath : String \-fileType : AttachmentType  \-fileSize : long \-uploadedAt : Timestamp \-uploadedBy : User  |
| \+getId() : int \+setId(id : int) : void \+getFileName() : String \+setFileName(fileName : String) : void \+getFilePath() : String \+setFilePath(filePath : String) : void \+getFileType() : AttachmentType \+setFileType(fileType : AttachmentType) : void  \+getFileSize() : long \+setFileSize(fileSize : long) : void \+getUploadedAt() : Timestamp \+setUploadedAt(uploadedAt : Timestamp) : void \+getUploadedBy() : User \+setUploadedBy(uploadedBy : User) : void \+toString() : String  |

6. # **Database Schema**

# ✅ 

| SessionTable |
| ----- |
| `id          INT  token       VARCHAR(255)  userId      INT  createdAt   DATETIME expiresAt   DATETIME isActive    BOOLEAN` |

✅ 

| UserTable |
| ----- |
| `id INT username VARCHAR(50) email VARCHAR(100) passwordHash VARCHAR(255) fullName VARCHAR(100) bio TEXT profilePicture VARCHAR(255) createdAt DATETIME updatedAt DATETIME` |

# ✅ 

| SocialTable |
| ----- |
| `id INT userId INT platform ENUM('GITHUB','LINKEDIN','INSTAGRAM','TWITTER','FACEBOOK','DISCORD','YOUTUBE','WEBSITE') url VARCHAR(255)` |

# ✅ 

| ProjectTable |
| ----- |
| `id INT name VARCHAR(100) description TEXT color VARCHAR(20) createdAt DATETIME updatedAt DATETIME`  |

# 

| ProjectMemberTable  |
| ----- |
| `projectId INT userId INT role ENUM('PROJECT_OWNER','TEAM_MEMBER') joinedAt DATETIME` |

# ✅ 

| ProjectItemTable  |
| ----- |
| `id INT title VARCHAR(100) description TEXT color VARCHAR(20) projectId INT createdBy INT updatedBy INT createdAt DATETIME updatedAt DATETIME` |

# 

| ProjectItemAssigneeTable |
| ----- |
| `projectItemId INT userId INT`  |

# ✅ 

| TaskTable  |
| ----- |
| `projectItemId INT priority ENUM('LOW','MEDIUM','HIGH') status ENUM('PENDING','IN_PROGRESS','DONE') startDate DATE dueDate DATE completedAt DATE`  |

# 

| EventTable  |
| ----- |
| `projectItemId INT location VARCHAR(150) AllDay BOOLEAN startAt DATETIME endAt DATETIME`  |

# ✅ 

| TagTable  |
| ----- |
| `id INT name VARCHAR(50) color VARCHAR(20) createdAt DATETIME`  |

# ✅ 

| ProjectItemTagTable  |
| ----- |
| `projectItemId INT tagId INT`  |

# ✅ 

| AttachmentTable  |
| ----- |
| `id INT projectItemId INT fileName VARCHAR(255) filePath VARCHAR(255) fileType ENUM('IMAGE','DOCUMENT','VIDEO','ARCHIVE','OTHER') fileSize BIGINT uploadedAt DATETIME uploadedBy INT`  |

# 

