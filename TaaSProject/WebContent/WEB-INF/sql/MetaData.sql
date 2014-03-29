#User Type Metadata
INSERT INTO TaaSTestLabDb.UserType
(TypeName)
VALUES ('SystemAdmin');

INSERT INTO TaaSTestLabDb.UserType
(TypeName)
VALUES ('ProjectManager');

INSERT INTO TaaSTestLabDb.UserType
(TypeName)
VALUES ('User');

#User Metadata
INSERT INTO TaaSTestLabDb.User 
(UserName,Password,FullName,UserTypeId)
VALUES
('TaasAdmin','taas','TaaS Administrator',1);

INSERT INTO TaaSTestLabDb.User 
(UserName,Password,FullName,UserTypeId)
VALUES
('PM1','taas','Project Manager 1',2);

INSERT INTO TaaSTestLabDb.User 
(UserName,Password,FullName,UserTypeId)
VALUES
('PM2','taas','Project Manager 2',2);

INSERT INTO TaaSTestLabDb.User 
(UserName,Password,FullName,UserTypeId)
VALUES
('PM13','taas','Project Manager 3',2);

INSERT INTO TaaSTestLabDb.User 
(UserName,Password,FullName,UserTypeId)
VALUES
('User-1','taas','User 1',3);

INSERT INTO TaaSTestLabDb.User 
(UserName,Password,FullName,UserTypeId)
VALUES
('User-2','taas','User 2',3);

INSERT INTO TaaSTestLabDb.User 
(UserName,Password,FullName,UserTypeId)
VALUES
('User-3','taas','User 3',3);

INSERT INTO TaaSTestLabDb.User 
(UserName,Password,FullName,UserTypeId)
VALUES
('User-4','taas','User 4',3);

INSERT INTO TaaSTestLabDb.User 
(UserName,Password,FullName,UserTypeId)
VALUES
('User-5','taas','User 5',3);

#Test Lab test data
INSERT INTO TaaSTestLabDb.TestLab
(LabName,AdminUserId)
VALUES
('Test Lab 1',1);

INSERT INTO TaaSTestLabDb.VirtualTestLab
(VLabName,ParentTestLabId,PMId)
VALUES
('Virtual Test Lab 1',1,2);
INSERT INTO TaaSTestLabDb.VirtualTestLab
(VLabName,ParentTestLabId,PMId)
VALUES
('Virtual Test Lab 2',1,3);

INSERT INTO TaaSTestLabDb.TestProject
(Name,ParentVLabId,Priority,ProjectUserId)
VALUES
('Test Project 1',1,2,5);

INSERT INTO TaaSTestLabDb.TestProject
(Name,ParentVLabId,Priority,ProjectUserId)
VALUES
('Test Project 2',1,1,6);
