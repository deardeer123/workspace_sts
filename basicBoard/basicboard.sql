SELECT * FROM basic_board;


-- user, manager, admin
CREATE TABLE security_member(
   MEMBER_ID VARCHAR(50) PRIMARY KEY ,
   MEMBER_NAME VARCHAR(50) NOT NULL ,
   MEMBER_PW VARCHAR(100) NOT NULL ,
   MEMBER_ROLL VARCHAR(50) DEFAULT 'USER'
);


DROP TABLE security_member;
SELECT * FROM security_member;

UPDATE security_member
SET member_roll = 'ADMIN'
WHERE member_id = 'admin';