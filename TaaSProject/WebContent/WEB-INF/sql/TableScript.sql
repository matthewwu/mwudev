
DROP DATABASE IF EXISTS TaaSTestLabDb;
CREATE DATABASE TaaSTestLabDb;


DROP TABLE IF EXISTs TaaSTestLabDb.Request;
DROP TABLE IF EXISTS TaaSTestLabDb.EC2Instance;
DROP TABLE IF EXISTS TaaSTestLabDb.TestLabInstanceMap;
DROP TABLE IF EXISTS TaaSTestLabDb.VirtualLabInstanceMap;
DROP TABLE IF EXISTS TaaSTestLabDb.TestProjectUserMap;
DROP TABLE IF EXISTS TaaSTestLabDb.TestProject;
DROP TABLE IF EXISTS TaaSTestLabDb.VirtualTestLab;
DROP TABLE IF EXISTS TaaSTestLabDb.TestLab;
DROP TABLE IF EXISTS TaaSTestLabDb.User;
DROP TABLE IF EXISTs TaaSTestLabDb.UserType;


CREATE TABLE TaaSTestLabDb.EC2Instance(
    Id int NOT NULL auto_increment primary key,
    Name varchar(30) NOT NULL,
    Status varchar(50) NOT NULL,
    InstanceId varchar(50) NOT NULL,    
    DNS varchar(100) DEFAULT '',
    Type varchar(20)     
);

CREATE TABLE TaaSTestLabDb.UserType (
  TypeId int NOT NULL auto_increment primary key,
  TypeName varchar(20) NOT NULL  
) ;


CREATE TABLE TaaSTestLabDb.User (
  UserId int NOT NULL auto_increment primary key,
  UserName varchar(20) NOT NULL,
  Password varchar(20) NOT NULL,
  FullName varchar(20) NOT NULL,
  UserTypeId int NOT NULL,  
  FOREIGN KEY (UserTypeId) REFERENCES TaaSTestLabDb.UserType(TypeId)
                      ON DELETE CASCADE
) ;

CREATE TABLE TaaSTestLabDb.TestLab(
    LabId int NOT NULL auto_increment primary key,
    LabName varchar(30) NOT NULL,
    AdminUserId int NOT NULL,
    LUTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (AdminUserId) REFERENCES TaaSTestLabDb.User(UserId)
);

CREATE TABLE TaaSTestLabDb.VirtualTestLab(
    VLabId INT NOT NULL auto_increment primary key,    
    VLabName varchar(30) NOT NULL,
    ParentTestLabId INT NOT NULL,
    PMId INT NOT NULL,
    InstanceId varchar(40) default '',
    LUTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ParentTestLabId) REFERENCES TaaSTestLabDb.TestLab(LabId),
    FOREIGN KEY (PMId) REFERENCES TaaSTestLabDb.User(UserId)    
);

CREATE TABLE TaaSTestLabDb.TestProject(
    ProjectId INT NOT NULL auto_increment primary key,
    Name varchar(30) NOT NULL,
    ParentVLabId INT,
    Priority INT DEFAULT 3,
    ProjectUserId INT,
    InstanceId varchar(40) DEFAULT '',
    RequestStatus INT DEFAULT 0
);

CREATE TABLE TaaSTestLabDb.TestProjectUserMap(
    Id INT NOT NULL auto_increment primary key,
    ProjectId INT NOT NULL,
    UserId INT NOT NULL,
    FOREIGN KEY (ProjectId) REFERENCES TaaSTestLabDb.TestProject(ProjectId),
    FOREIGN KEY (UserId) REFERENCES TaaSTestLabDb.User(UserId)
);

CREATE TABLE TaaSTestLabDb.TestLabInstanceMap(
    MapId INT NOT NULL auto_increment primary key,
    TestLabId INT NOT NULL,
    InstanceId INT NOT NULL,
    FOREIGN KEY (TestLabId) REFERENCES TaaSTestLabDb.TestLab(LabId),
    FOREIGN KEY (InstanceId) REFERENCES TaaSTestLabDb.EC2Instance(Id)    
);

CREATE TABLE TaaSTestLabDb.VirtualLabInstanceMap(
    MapId INT NOT NULL auto_increment primary key,
    VTestLabId INT NOT NULL,
    InstanceId INT NOT NULL,
    FOREIGN KEY (VTestLabId) REFERENCES TaaSTestLabDb.VirtualTestLab(VLabId),
    FOREIGN KEY (InstanceId) REFERENCES TaaSTestLabDb.EC2Instance(Id)    
);

CREATE TABLE TaaSTestLabDb.Request(
    RequestId INT NOT NULL auto_increment primary key,    
    ProjectId INT NOT NULL,
    Status INT NOT NULL DEFAULT 0,
    EC2InstanceId varchar(50) DEFAULT '',
    LUTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ProjectId) REFERENCES TaaSTestLabDb.TestProject(ProjectId)    
);