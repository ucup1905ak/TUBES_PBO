# Class Descriptions

Attachment
Attributes:
- id : int
- projectItem : ProjectItem
- fileName : String
- filePath : String
- fileType : AttachmentType
- fileSize : long
- uploadedAt : Timestamp
- uploadedBy : User
Methods:
+ getId() : int
+ setId(id : int) : void
+ getProjectItem() : ProjectItem
+ setProjectItem(projectItem : ProjectItem) : void
+ getFileName() : String
+ setFileName(fileName : String) : void
+ getFilePath() : String
+ setFilePath(filePath : String) : void
+ getFileType() : AttachmentType
+ setFileType(fileType : AttachmentType) : void
+ getFileSize() : long
+ setFileSize(fileSize : long) : void
+ getUploadedAt() : Timestamp
+ setUploadedAt(uploadedAt : Timestamp) : void
+ getUploadedBy() : User
+ setUploadedBy(uploadedBy : User) : void
+ toString() : String

Event (extends ProjectItem)
Attributes:
- location : String
- isAllDay : boolean
- startAt : Timestamp
- endAt : Timestamp
Methods:
+ getLocation() : String
+ setLocation(location : String) : void
+ isAllDay() : boolean
+ setAllDay(isAllDay : boolean) : void
+ getStartAt() : Timestamp
+ setStartAt(startAt : Timestamp) : void
+ getEndAt() : Timestamp
+ setEndAt(endAt : Timestamp) : void
+ toString() : String

Project
Attributes:
- id : int
- name : String
- description : String
- color : String
- members : List&lt;User&gt;
- createdAt : Timestamp
- updatedAt : Timestamp
Methods:
+ getId() : int
+ setId(id : int) : void
+ getName() : String
+ setName(name : String) : void
+ getDescription() : String
+ setDescription(description : String) : void
+ getColor() : String
+ setColor(color : String) : void
+ getMembers() : List&lt;User&gt;
+ setMembers(members : List&lt;User&gt;) : void
+ addMember(user : User) : void
+ removeMember(user : User) : void
+ getCreatedAt() : Timestamp
+ setCreatedAt(createdAt : Timestamp) : void
+ getUpdatedAt() : Timestamp
+ setUpdatedAt(updatedAt : Timestamp) : void
+ toString() : String

ProjectItem (abstract)
Attributes:
- id : int
- title : String
- description : String
- color : String
- project : Project
- tags : List&lt;Tag&gt;
- attachments : List&lt;Attachment&gt;
- createdAt : Timestamp
- createdBy : User
- updatedAt : Timestamp
- updatedBy : User
- assignee : List&lt;User&gt;
Methods:
+ getId() : int
+ setId(id : int) : void
+ getTitle() : String
+ setTitle(title : String) : void
+ getDescription() : String
+ setDescription(description : String) : void
+ getColor() : String
+ setColor(color : String) : void
+ getProject() : Project
+ setProject(project : Project) : void
+ getTags() : List&lt;Tag&gt;
+ setTags(tags : List&lt;Tag&gt;) : void
+ addTag(tag : Tag) : void
+ removeTag(tag : Tag) : void
+ getAttachments() : List&lt;Attachment&gt;
+ setAttachments(attachments : List&lt;Attachment&gt;) : void
+ addAttachment(attachment : Attachment) : void
+ removeAttachment(attachment : Attachment) : void
+ getCreatedAt() : Timestamp
+ setCreatedAt(createdAt : Timestamp) : void
+ getCreatedBy() : User
+ setCreatedBy(createdBy : User) : void
+ getUpdatedAt() : Timestamp
+ setUpdatedAt(updatedAt : Timestamp) : void
+ getUpdatedBy() : User
+ setUpdatedBy(updatedBy : User) : void
+ getAssignee() : List&lt;User&gt;
+ setAssignee(assignee : List&lt;User&gt;) : void
+ addAssignee(assignee : User) : void
+ removeAssignee(assignee : User) : void
+ toString() : String

Session
Attributes:
- id : int
- token : String
- user : User
- createdAt : Timestamp
- expiresAt : Timestamp
- isActive : boolean
Methods:
+ getId() : int
+ setId(id : int) : void
+ getToken() : String
+ setToken(token : String) : void
+ getUser() : User
+ setUser(user : User) : void
+ getCreatedAt() : Timestamp
+ setCreatedAt(createdAt : Timestamp) : void
+ getExpiresAt() : Timestamp
+ setExpiresAt(expiresAt : Timestamp) : void
+ isActive() : boolean
+ setActive(isActive : boolean) : void
+ isValid() : boolean
+ logout() : void
+ toString() : String

SocialLink
Attributes:
- id : int
- user : User
- platform : SocialPlatform
- url : String
Methods:
+ SocialLink(user : User, platform : SocialPlatform, url : String)
+ SocialLink(platform : SocialPlatform, url : String)
+ getId() : int
+ setId(id : int) : void
+ getUser() : User
+ setUser(user : User) : void
+ getPlatform() : SocialPlatform
+ setPlatform(platform : SocialPlatform) : void
+ getUrl() : String
+ setUrl(url : String) : void
+ toString() : String

Tag
Attributes:
- id : int
- name : String
- color : String
- createdAt : Timestamp
Methods:
+ getId() : int
+ setId(id : int) : void
+ getName() : String
+ setName(name : String) : void
+ getColor() : String
+ setColor(color : String) : void
+ getCreatedAt() : Timestamp
+ setCreatedAt(createdAt : Timestamp) : void
+ toString() : String

Task (extends ProjectItem)
Attributes:
- priority : TaskPriority
- status : TaskStatus
- startDate : Date
- dueDate : Date
- completedAt : Date
Methods:
+ getPriority() : TaskPriority
+ setPriority(priority : TaskPriority) : void
+ getStatus() : TaskStatus
+ setStatus(status : TaskStatus) : void
+ getStartDate() : Date
+ setStartDate(startDate : Date) : void
+ getDueDate() : Date
+ setDueDate(dueDate : Date) : void
+ getCompletedAt() : Date
+ setCompletedAt(completedAt : Date) : void
+ markAsDone() : void
+ toString() : String

User
Attributes:
- id : int
- username : String
- email : String
- passwordHash : String
- fullName : String
- bio : String
- profilePicture : String
- socials : List&lt;SocialLink&gt;
- createdAt : Timestamp
- updatedAt : Timestamp
Methods:
+ getId() : int
+ setId(id : int) : void
+ getUsername() : String
+ setUsername(username : String) : void
+ getEmail() : String
+ setEmail(email : String) : void
+ getPasswordHash() : String
+ setPasswordHash(passwordHash : String) : void
+ getFullName() : String
+ setFullName(fullName : String) : void
+ getBio() : String
+ setBio(bio : String) : void
+ getProfilePicture() : String
+ setProfilePicture(profilePicture : String) : void
+ getSocials() : List&lt;SocialLink&gt;
+ setSocials(socials : List&lt;SocialLink&gt;) : void
+ addSocial(social : SocialLink) : void
+ removeSocial(social : SocialLink) : void
+ getCreatedAt() : Timestamp
+ setCreatedAt(createdAt : Timestamp) : void
+ getUpdatedAt() : Timestamp
+ setUpdatedAt(updatedAt : Timestamp) : void
+ toString() : String

AttachmentType (enum)
Attributes:
+ IMAGE
+ DOCUMENT
+ VIDEO
+ ARCHIVE
+ OTHER
Methods:
+ (no methods)

SocialPlatform (enum)
Attributes:
+ GITHUB
+ LINKEDIN
+ INSTAGRAM
+ TWITTER
+ FACEBOOK
+ DISCORD
+ YOUTUBE
+ WEBSITE
Methods:
+ (no methods)

TaskPriority (enum)
Attributes:
+ LOW
+ MEDIUM
+ HIGH
Methods:
+ (no methods)

TaskStatus (enum)
Attributes:
+ PENDING
+ IN_PROGRESS
+ DONE
Methods:
+ (no methods)

UserRole (enum)
Attributes:
+ PROJECT_OWNER
+ TEAM_MEMBER
Methods:
+ (no methods)

IAssignable (interface)
Attributes:
- (no attributes)
Methods:
+ getAssignee() : List&lt;User&gt;
+ addAssignee(assignee : User) : void
+ removeAssignee(assignee : User) : void

IAuditable (interface)
Attributes:
- (no attributes)
Methods:
+ getCreatedAt() : Timestamp
+ setCreatedAt(createdAt : Timestamp) : void
+ getUpdatedAt() : Timestamp
+ setUpdatedAt(updatedAt : Timestamp) : void

IAuthService (interface)
Attributes:
- (no attributes)
Methods:
+ authenticate(username : String, passwordHash : String) : Session
+ getCurrentSession(token : String) : Session

IDatabaseConnection (interface)
Attributes:
- (no attributes)
Methods:
+ isConnected() : boolean
+ executeQuery(sql : Query, mapper : IRowMapper&lt;T&gt;) : List&lt;T&gt;
+ executeUpdate(sql : Query) : int

IGenericDAO (interface)
Attributes:
- (no attributes)
Methods:
+ add(entity : T) : int
+ get(id : ID) : T
+ fetchAll() : List&lt;T&gt;
+ update(entity : T) : int
+ delete(id : ID) : int

IProjectControl (interface)
Attributes:
- (no attributes)
Methods:
+ fetchUserProjects() : List&lt;Project&gt;
+ setProject(projectId : int) : boolean
+ getSelectedProject() : Project
+ editSelectedProject() : boolean
+ add(project : Project) : boolean
+ deleteSelectedProject() : boolean
+ getUserRole() : UserRole

IRowMapper (interface)
Attributes:
- (no attributes)
Methods:
+ map(rs : ResultSet) : T

ITaggable (interface)
Attributes:
- (no attributes)
Methods:
+ getTags() : List&lt;Tag&gt;
+ setTags(tags : List&lt;Tag&gt;) : void
+ addTag(tag : Tag) : void
+ removeTag(tag : Tag) : void

IProjectDAO (interface)
Attributes:
- (no attributes)
Methods:
+ add(entity : Project) : int
+ get(id : Integer) : Project
+ fetchAll() : List&lt;Project&gt;
+ update(entity : Project) : int
+ delete(id : Integer) : int

DatabaseConnection
Attributes:
+ SCHEME : String
+ HOSTNAME : String
+ DATABASE : String
+ PORT : int
+ USERNAME : String
+ PASSWORD : String
- connection : Connection
Methods:
+ getPath() : String
+ isConnected() : boolean
+ executeQuery(sql : Query, mapper : IRowMapper&lt;T&gt;) : List&lt;T&gt;
+ executeUpdate(sql : Query) : int

GenericMapper
Attributes:
- (no attributes)
Methods:
+ (no methods)

UserDAO
Attributes:
- db : DatabaseConnection
Methods:
+ UserDAO(db : DatabaseConnection)
+ add(entity : User) : int
+ get(id : Integer) : User
+ fetchAll() : List&lt;User&gt;
+ update(entity : User) : int
+ delete(id : Integer) : int

SocialDAO
Attributes:
- db : DatabaseConnection
Methods:
+ SocialDAO(db : DatabaseConnection)
+ add(entity : SocialLink) : int
+ get(id : Integer) : SocialLink
+ fetchAll() : List&lt;SocialLink&gt;
+ update(entity : SocialLink) : int
+ delete(id : Integer) : int
+ findByUserId(userId : int) : List&lt;SocialLink&gt;
+ map(rs : ResultSet) : SocialLink

Log
Attributes:
+ LOG_FILE : String
+ FORMATTER : DateTimeFormatter
Methods:
+ create(msg : String) : void
+ err(msg : String) : void

Query
Attributes:
- sql : StringBuilder
- parameters : List&lt;Object&gt;
+ queryType : Type
+ Type (enum): SELECT, INSERT, UPDATE, DELETE, UNKNOWN
Methods:
+ Query()
+ select(columns : String...) : Query
+ from(table : String) : Query
+ where(condition : String, values : Object...) : Query
+ and(condition : String, values : Object...) : Query
+ or(condition : String, values : Object...) : Query
+ join(table : String, condition : String) : Query
+ leftJoin(table : String, condition : String) : Query
+ rightJoin(table : String, condition : String) : Query
+ insertInto(table : String, columns : String...) : Query
+ values(values : Object...) : Query
+ update(table : String) : Query
+ set(column : String, value : Object) : Query
+ deleteFrom(table : String) : Query
+ getParameters() : List&lt;Object&gt;
+ build() : String
+ toString() : String

DatabaseConnectionFailedException
Attributes:
- (no attributes)
Methods:
+ DatabaseConnectionFailedException(msg : String)

QueryTypeMismatchException
Attributes:
+ expected : List&lt;Query.Type&gt;
Methods:
+ QueryTypeMismatchException(types : Query.Type...)
+ toString() : String

TestDatabaseConnectino
Attributes:
- (no attributes)
Methods:
+ main(args : String[]) : void
