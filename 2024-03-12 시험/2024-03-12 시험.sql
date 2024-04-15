create table MY_EMP(
	EMP_NUM INT AUTO_INCREMENT PRIMARY KEY ,
   -- 사원번호
	EMP_NAME VARCHAR(20) NOT NULL ,
   -- 사원명
	EMP_DEPT_NUM INT NOT NULL ,
   -- 부서번호
	EMP_DEPT_NAME VARCHAR(20) NOT NULL ,
   -- 부서명
	EMP_JON VARCHAR(20) NOT NULL
   -- 직급
); 


DELETE FROM MY_EMP ;

INSERT INTO MY_EMP(
	EMP_NAME ,
	EMP_DEPT_NUM ,
	EMP_DEPT_NAME ,
	EMP_JON )
	VALUES (
	'오철민'
   , 1
   , '개발'
   , '사원'
	);
   
UPDATE MY_EMP
SET 
	EMP_NAME = '김자바' ,
	EMP_JON = '대리'
WHERE
	EMP_NUM = 1;
   
SELECT * FROM my_emp;

SELECT * FROM emp;

SELECT * from dept;

SELECT 
   EMPNO ,
   ENAME ,
   SAL ,
   COMM
FROM
   EMP
WHERE
   (SAL <= 500 OR 1500 <= SAL) AND comm IS NOT NULL;
   
SELECT 
   EMPNO ,
   ENAME ,
   HIREDATE
FROM
   EMP
WHERE
   ENAME LIKE CONCAT('이', '%')
ORDER BY EMPNO ASC;

SELECT
   EMPNO ,
   ENAME ,
   emp.DEPTNO ,
   CASE
      WHEN emp.DEPTNO = 10 THEN '인사부'
      WHEN emp.DEPTNO = 20 THEN '영업부'
      ELSE '생산부'
   END AS DNAME
FROM
   emp INNER JOIN dept
   ON emp.DEPTNO = dept.DEPTNO;
   
SELECT 
   EMPNO ,
   ENAME , 
   HIREDATE ,
   COMM
FROM
   EMP
WHERE
   DATE_FORMAT(HIREDATE, '%Y') = 2007
ORDER BY HIREDATE asc;

SELECT 
   job ,
   SUM(SAL) , 
   AVG(SAL) ,
   case
      when AVG(COMM) IS NULL then 0.0
      ELSE AVG(COMM)
   END AS 'AVG(COMM)1' ,
   IFNULL(AVG(comm), 0.0) AS 'AVG(COMM)2'
FROM
   EMP
GROUP BY JOB
ORDER BY job ASC;

SELECT -- 3
   CONCAT(CAST(DATE_FORMAT(hiredate, '%m')AS INT),'월') AS 입사월,
   COUNT(hiredate),
   SUBSTRING(hiredate,6, 2) ,
   SUM(sal)
FROM -- 1
   emp
where SUBSTRING(hiredate,6, 2) <> 01   -- 2
GROUP BY DATE_FORMAT(hiredate, '%m') -- 4 
ORDER BY 입사월 ; -- 5


-- 월별 입사자 수를 조회
-- 월별 입사자 수가 2명 이상인 데이터만 조회

SELECT  -- 3
   CONCAT(CAST(DATE_FORMAT(hiredate, '%m')AS INT),'월') AS '입사월' ,
   COUNT(empno) AS 입사인원
FROM
   emp -- 1
WHERE DATE_FORMAT(hiredate, '%m') <> 10
GROUP BY DATE_FORMAT(hiredate, '%m')
HAVING 입사인원 >= 2
ORDER BY 입사인원 desc;

SELECT
   ename,
   hiredate, 
   DATE_FORMAT(hiredate, '%m')
FROM
   emp;

SELECT * FROM emp;
SELECT * FROM dept;

-- ELETE FROM emp;


-- 부서 테이블 생성하기
CREATE TABLE DEPT(
	 DEPTNO INT PRIMARY KEY,
	 DNAME VARCHAR(14),
	 LOC   VARCHAR(13) ) ;

-- 사원 테이블 생성하기
CREATE TABLE EMP( 
  	 EMPNO INT PRIMARY KEY,
	 ENAME VARCHAR(10),
 	 JOB   VARCHAR(9),
	 MGR  INT,
	 HIREDATE DATETIME,
	 SAL INT,
	 COMM INT,
	 DEPTNO INT CONSTRAINT FK_DEPTNO REFERENCES DEPT);

 -- 급여 테이블 생성하기
CREATE TABLE SALGRADE(
	 GRADE INT,
	 LOSAL INT,
	 HISAL INT );

-- 사원 테이블에 샘플 데이터 추가하기
INSERT INTO DEPT VALUES(10, '경리부', '서울');
INSERT INTO DEPT VALUES(20, '인사부', '인천');
INSERT INTO DEPT VALUES(30, '영업부', '용인'); 
INSERT INTO DEPT VALUES(40, '전산부', '수원');

-- 부서 테이블에 샘플 데이터 추가하기
INSERT INTO EMP VALUES(1001, '김사랑', '사원', 1013, DATE_FORMAT('2007-03-01','%Y-%m-%d %H"%i%s'), 300, NULL, 20);
INSERT INTO EMP VALUES(1002, '한예슬', '대리', 1005, DATE_FORMAT('2007-04-02','%Y-%m-%d %H"%i%s'), 250,   80, 30);
INSERT INTO EMP VALUES(1003, '오지호', '과장', 1005, DATE_FORMAT('2005-02-10','%Y-%m-%d %H"%i%s'), 500,  100, 30);
INSERT INTO EMP VALUES(1004, '이병헌', '부장', 1008, DATE_FORMAT('2003-09-02','%Y-%m-%d %H"%i%s'), 600, NULL, 20);
INSERT INTO EMP VALUES(1005, '신동협', '과장', 1005, DATE_FORMAT('2005-04-07','%Y-%m-%d %H"%i%s'), 450,  200, 30);
INSERT INTO EMP VALUES(1006, '장동건', '부장', 1008, DATE_FORMAT('2003-10-09','%Y-%m-%d %H"%i%s'), 480, NULL, 30);
INSERT INTO EMP VALUES(1007, '이문세', '부장', 1008, DATE_FORMAT('2004-01-08','%Y-%m-%d %H"%i%s'), 520, NULL, 10);
INSERT INTO EMP VALUES(1008, '감우성', '차장', 1003, DATE_FORMAT('2004-03-08','%Y-%m-%d %H"%i%s'), 500,    0, 30);
INSERT INTO EMP VALUES(1009, '안성기', '사장', NULL, DATE_FORMAT('1996-10-04','%Y-%m-%d %H"%i%s'),1000, NULL, 20);
INSERT INTO EMP VALUES(1010, '이병헌', '과장', 1003, DATE_FORMAT('2005-04-07','%Y-%m-%d %H"%i%s'), 500, NULL, 10);
INSERT INTO EMP VALUES(1011, '조향기', '사원', 1007, DATE_FORMAT('2007-03-01','%Y-%m-%d %H"%i%s'), 280, NULL, 30);
INSERT INTO EMP VALUES(1012, '강혜정', '사원', 1006, DATE_FORMAT('2007-08-09','%Y-%m-%d %H"%i%s'), 300, NULL, 20);
INSERT INTO EMP VALUES(1013, '박중훈', '부장', 1003, DATE_FORMAT('2002-10-09','%Y-%m-%d %H"%i%s'), 560, NULL, 20);
INSERT INTO EMP VALUES(1014, '조인성', '사원', 1006, DATE_FORMAT('2007-11-09','%Y-%m-%d %H"%i%s'), 250, NULL, 10);

-- 급여 테이블에 샘플 데이터 추가하기
INSERT INTO SALGRADE VALUES (1, 700,1200);
INSERT INTO SALGRADE VALUES (2, 1201,1400);
INSERT INTO SALGRADE VALUES (3, 1401,2000);
INSERT INTO SALGRADE VALUES (4, 2001,3000);
INSERT INTO SALGRADE VALUES (5, 3001,9999);
COMMIT;


SELECT 
   EMPNO , 
   ENAME ,
   HIREDATE ,
   SAL ,
   DEPTNO ,
   (SELECT loc
   FROM dept
   WHERE emp.DEPTNO = dept.deptno) AS loc
FROM
   emp
WHERE
   deptno = (
   SELECT deptno
   FROM dept
   WHERE loc = '서울');
   
SELECT
   EMPNO , 
   ENAME ,
   HIREDATE ,
   SAL ,
   emp.DEPTNO ,
   dept.loc
FROM 
   emp INNER JOIN dept
   ON emp.DEPTNO = dept.DEPTNO
WHERE
   dept.LOC ='서울';
   
   
-- 각 직급별 급여의 합
SELECT job, SUM(sal)
FROM emp
GROUP BY job;

SELECT * FROM emp;
select * FROM dept;

-- 부서번호별 인원수 조회
SELECT 
   deptno ,
   case
      when deptno = 10 then '경리부'
      when deptno = 20 then '인사부'
      when deptno = 30 then '영업부'
      when deptno = 40 then '전산부'
      ELSE ''
   END AS dname ,
   COUNT(empno)
FROM 
   emp
GROUP BY deptno;


SELECT COUNT(deptno) FROM emp;

-- 다중행 함수 

-- 중복을 제거한 데이터 조회

SELECT DISTINCT job FROM emp;

CREATE TABLE MEMBER(
   MEMBER_ID VARCHAR(50) PRIMARY KEY ,
   MEMBER_NAME VARCHAR(10) NOT NULL ,
   MEMBER_PW VARCHAR(100) NOT NULL ,
   MEMBER_TEL VARCHAR(20));
   
SELECT * FROM MEMBER;

CREATE TABLE THEATER(
   THEATER_NUM INT PRIMARY KEY ,
   THEATER_NAME VARCHAR(50) NOT NULL ,
   THEATER_SEAT_COUNT INT NOT NULL);
SELECT * FROM theater;

CREATE TABLE SEAT(
   SEAT_NUM INT PRIMARY KEY ,
   SEAT_ROW INT NOT NULL ,
   SEAT_COL INT NOT NULL ,
   SEAT_STATUS VARCHAR(4) DEFAULT 'N' ,
   THEATER_NUM INT NOT NULL REFERENCES THEATER(THEATER_NUM));
   
SELECT * FROM seat;
SELECT * FROM ticketing;
DROP TABLE THEATER;
DROP TABLE SEAT;
DROP TABLE TICKETING;
CREATE TABLE TICKETING(
   TICKETING_NUM INT PRIMARY KEY ,
   MEMBER_ID VARCHAR(50) NOT NULL REFERENCES MEMBER(MEMBER_ID) ,
   THEATER_NUM INT NOT NULL REFERENCES THEATER(THEATER_NUM) ,
   SEAT_NUM INT NOT NULL REFERENCES SEAT(SEAT_NUM));
   
   
SELECT 
   TICKETING_NUM ,
   THEATER_NUM ,
   SEAT_NUM
FROM
   TICKETING
WHERE
   MEMBER_ID = 'java';

INSERT INTO theater
VALUES(
   1, 'vip',10);
   
INSERT INTO seat
VALUES
(1, 1, 1, 'N', 1),
(2, 1, 2, 'N', 1),
(3, 1, 3, 'N', 1),
(4, 1, 4, 'N', 1),
(5, 1, 5, 'N', 1),
(6, 1, 6, 'N', 1),
(7, 1, 7, 'N', 1),
(8, 1, 8, 'N', 1),
(9, 1, 9, 'N', 1),
(10, 1, 10, 'N', 1);

DELETE FROM seat;

UPDATE seat
SET SEAT_STATUS = 'Y';

UPDATE theater
SET theater_name = 'VIP';

SELECT * FROM theater;
SELECT * FROM seat where theater_num = 1;



   
SELECT
   CASE
      when COUNT(SEAT_NUM) = 0 then '예매할 수 있는 좌석이 없습니다.'
      ELSE '예매가능'
   END 
FROM
   THEATER INNER JOIN SEAT
   ON THEATER.THEATER_NUM = SEAT.THEATER_NUM
WHERE
   THEATER_NAME = 'VIP' AND SEAT_STATUS = 'N';
   
SELECT * from security_member;

CREATE TABLE PYTHON_BOARD(
   BOARD_NUM INT PRIMARY KEY ,
   TITLE VARCHAR(20),
   writer VARCHAR(20),
   read_cnt INT);
   
SELECT * FROM python_board;
board