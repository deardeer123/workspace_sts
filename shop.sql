-- 쇼핑몰 프로젝트 테이블

-- 회원 정보 테이블
CREATE TABLE SHOP_MEMBER (
	MEMBER_ID VARCHAR(20) PRIMARY KEY
	,MEMBER_PW VARCHAR(20) NOT NULL
	,MEMBER_NAME VARCHAR(20) NOT NULL
	,GENDER VARCHAR(10) NOT NULL -- male , female 
	,MEMBER_EMAIL VARCHAR(50) NOT NULL UNIQUE
	,MEMBER_TEL VARCHAR(20) -- 010-1111-2222
	,MEMBER_ADDR VARCHAR(50)
	,ADDR_DETAIL VARCHAR(50)
	,POST_CODE VARCHAR(10) -- 40212
	,JOIN_DATE DATETIME DEFAULT CURRENT_TIMESTAMP
	,MEMBER_ROLL VARCHAR(20) DEFAULT 'USER' -- 권한 USER, ADMIN
);

-- 상품 카테고리 정보 테이블
CREATE TABLE ITEM_CATEGORY(
	CATE_CODE INT AUTO_INCREMENT PRIMARY KEY
	, CATE_NAME VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO item_category() VALUES(1, 'IT/인터넷');
INSERT INTO item_category() VALUES(2, '소설/에세이');
INSERT INTO item_category() VALUES(3, '문화/여행');
COMMIT;

SELECT 
	*
FROM item_category;



-- 상품 정보 테이블
CREATE TABLE SHOP_ITEM(
	ITEM_CODE INT AUTO_INCREMENT PRIMARY KEY
	, ITEM_NAME VARCHAR(50) NOT NULL UNIQUE
	, ITEM_PRICE INT NOT NULL
	, ITEM_STOCK INT DEFAULT 10
	, ITEM_INTRO VARCHAR(100)
	, REG_DATE DATETIME DEFAULT CURRENT_TIMESTAMP
	, CATE_CODE INT NOT NULL REFERENCES item_category (CATE_CODE)
);
COMMIT;

INSERT into shop_item(
	ITEM_CODE ,
	ITEM_NAME ,
	ITEM_PRICE ,
	CATE_CODE )
VALUE(
	0 ,
	'테스트' , 
	1 ,
	1 
	);
	
DELETE FROM shop_item
WHERE item_intro IS NULL;
	
	


SELECT * FROM shop_member;

SELECT * FROM shop_item;

UPDATE shop_member
SET MEMBER_ROLL = 'ADMIN'
WHERE MEMBER_ID = '남지훈';

UPDATE SHOP_ITEM
SET ITEM_PRICE = 21600
WHERE ITEM_CODE = '3';

SELECT * FROM shop_member;

UPDATE shop_member
SET MEMBER_ROLL = 'ADMIN'
WHERE MEMBER_ID = 'admin';

DELETE FROM shop_item;

SELECT * FROM shop_item;

CREATE TABLE ITEM_IMAGE(
	IMAGE_CODE INT AUTO_INCREMENT PRIMARY KEY ,
	ORIGEN_FILE_NAME VARCHAR(100) NOT NULL ,
	ATTACHED_FILE_NAME_IMAGE VARCHAR(100) , 
	IS_MAIN VARCHAR(2) NOT NULL , -- 'Y' , "N '
	ITEM_CODE INT NOT NULL REFERENCES shop_item(ITEM_CODE)
);

SELECT * FROM item_image;
SELECT * FROM shop_item;
COMMIT;

SELECT *
FROM shop_item;

SELECT *
FROM item_image;

DROP TABLE shop_item;

SELECT * from Iitem_listTEM_LIST;

CREATE VIEW ITEM_LIST(
	ITEM_CODE,
	ITEM_NAEM,
	ITEM_PRICE,
	ATTACHED_FILE_NAME_IMAGE) AS
SELECT ITEM_IMAGE.ITEM_CODE
	, ITEM_NAME
	, ITEM_PRICE
	, ATTACHED_FILE_NAME_IMAGE
FROM shop_item INNER JOIN item_image
ON shop_item.ITEM_CODE = item_image.ITEM_CODE
WHERE item_image.IS_MAIN='Y' ;

SELECT image_code
FROM item_image
WHERE IS_MAIN = 'Y';


SELECT MAX(item_code)
FROM shop_item;


SELECT 
	CASE WHEN
 		MAX(image_code)IS NULL
	THEN 0
		ELSE MAX(image_code) 
	END 
FROM item_image;

SELECT * FROM item_image;

SELECT 
	IMAGE_CODE ,
	ORIGEN_FILE_NAME ,
	ATTACHED_FILE_NAME_IMAGE ,
	IS_MAIN ,
	ITEM_IMAGE.ITEM_CODE
FROM 
	item_image INNER JOIN shop_item
ON
	item_image.item_code = shop_item.ITEM_CODE
WHERE 
	IS_MAIN = 'Y';
	
SELECT * FROM shop_item;

SELECT
            SHOP_ITEM.ITEM_CODE ,
            ITEM_NAME ,
            ITEM_PRICE ,
            ITEM_STOCK ,
            ITEM_INTRO ,
            REG_DATE ,
            CATE_CODE ,
            ATTACHED_FILE_NAME_IMAGE
        FROM
            SHOP_ITEM INNER JOIN item_image
        ON
            SHOP_ITEM.ITEM_CODE = item_image.ITEM_CODE
        WHERE
            SHOP_ITEM.ITEM_CODE = 1;
	
SELECT
            SHOP_ITEM.ITEM_CODE ,
            ITEM_NAME ,
            ITEM_PRICE ,
            ITEM_STOCK ,
            ITEM_INTRO ,
            REG_DATE ,
            CATE_CODE ,
            ATTACHED_FILE_NAME_IMAGE
        FROM
            SHOP_ITEM INNER JOIN item_image
        ON
            SHOP_ITEM.ITEM_CODE = item_image.ITEM_CODE
        WHERE
            SHOP_ITEM.ITEM_CODE = 1;
            
SELECT * FROM shop_item;
SELECT * FROM item_category;

SELECT 
	ITEM_CODE ,
	ITEM_NAME ,
	ITEM_PRICE ,
	ITEM_INTRO ,
	REG_DATE ,
	item_category.CATE_CODE ,
	CATE_NAME

FROM
	shop_item INNER JOIN ITEM_CATEGORY
ON
	shop_item.CATE_CODE = item_category.CATE_CODE;
	
SELECT
            SHOP_ITEM.ITEM_CODE ,
            ITEM_NAME ,
            ITEM_PRICE ,
            ITEM_STOCK ,
            ITEM_INTRO ,
            REG_DATE ,
            item_category . CATE_CODE ,
            CATE_NAME
        FROM
            SHOP_ITEM INNER Jitem_imageOIN ITEM_CATEGORY
        ON
            SHOP_ITEM.CATE_CODE = ITEM_CATEGORY.CATE_CODE;

-- 장바구니 
-- 상품 , 소유자 d
CREATE TABLE SHOP_CART (
	CART_CODE INT AUTO_INCREMENT PRIMARY KEY ,
	ITEM_CODE int not null REFERENCES shop_item(item_code) ,
	MEMBER_ID VARCHAR(20) NOT NULL REFERENCES shop_member (member_id) ,
	CART_CNT INT NOT NULL ,
	CART_DATE DATETIME default CURRENT_TIMESTAMP
);

SELECT * FROM shop_cart;

-- 상품명, 가격, 개수, 총가격
SELECT 
	shop_item.ITEM_NAME ,
	shop_item.ITEM_PRICE ,
	SHOP_CART.CART_CNT ,
	shop_item.ITEM_PRICE * SHOP_CART.CART_CNT AS '총가격'
FROM
	SHOP_CART INNER JOIN shop_item
ON
	SHOP_CART.ITEM_CODE = shop_item.ITEM_CODE;
	
-- 회원 아이디가 java인 회원의
-- 장바구니에 담긴 상품 목록
-- 장바구니코드 , 대표이미지명(첨부파일명)
-- 상품명, 가격, 개수, 총 가격

SELECT 
	SHOP_CART.MEMBER_ID ,
	SHOP_CART.CART_CODE ,
	item_image.ATTACHED_FILE_NAME_IMAGE ,
	shop_item.ITEM_NAME ,
	shop_item.ITEM_PRICE ,
	SHOP_CART.CART_CNT ,
	shop_item.ITEM_PRICE * SHOP_CART.CART_CNT AS '총가격'
FROM
	SHOP_CART INNER JOIN shop_item
		ON SHOP_CART.ITEM_CODE = shop_item.ITEM_CODE
	INNER JOIN item_image
		ON shop_item.ITEM_CODE = item_image.ITEM_CODE
WHERE
	SHOP_CART.MEMBER_ID = 'java' AND item_image.IS_MAIN = 'Y';
-- 15514 울산 남구 그린 아파트 111동
SELECT POST_CODE , MEMBER_ADDR, ADDR_DETAIL, CONCAT(POST_CODE, ' ' ,MEMBER_ADDR, ' ' ,ADDR_DETAIL)
FROM shop_member;


-- 장바구니와 관련된 모든 정보를 조회할 수 있는 VIEW 생성
CREATE OR REPLACE VIEW CART_VIEW
AS
SELECT 
	shop_cart.CART_CODE ,
	shop_cart.ITEM_CODE ,
	shop_cart.MEMBER_ID ,
	shop_cart.CART_CNT ,
	shop_cart.CART_DATE ,
	
	shop_item.ITEM_NAME ,
	shop_item.ITEM_PRICE ,
	shop_item.ITEM_INTRO ,
	ITEM_PRICE * CART_CNT AS TOTAL_PRICE ,
	
	shop_member.MEMBER_NAME ,
	shop_member.MEMBER_TEL ,
	CONCAT(shop_member.POST_CODE, ' ' ,shop_member.MEMBER_ADDR, ' ' ,shop_member.ADDR_DETAIL) AS ADDRESS ,
	
	item_image.ATTACHED_FILE_NAME_IMAGE ,
	item_image.ORIGEN_FILE_NAME ,
	item_image.IS_MAIN ,
	item_image.IMAGE_CODE,
	
	item_category.CATE_CODE ,
	item_category.CATE_NAME 

FROM 
	shop_cart INNER JOIN shop_item
	ON shop_cart.ITEM_CODE = shop_item.ITEM_CODE
	INNER JOIN shop_member
	ON shop_member.MEMBER_ID = shop_cart.MEMBER_ID
	INNER JOIN item_image
	ON item_image.ITEM_CODE = shop_cart.ITEM_CODE
	INNER	JOIN item_category
	ON item_category.CATE_CODE = shop_item.CATE_cart_viewCODE
WHERE
	item_image.IS_MAIN = 'Y';
	
SELECT * FROM cart_view
WHERE 
	MEMBER_ID = 'aaaa'
 ORDER BY cart_code;

SELECT * FROM shop_cart;
SELECT * FROM shop_member;

UPDATE shop_cart
SET CART_CNT = CART_CNT + 1
WHERE CART_CODE = 1;

DELETE FROM shop_cart;

SELECT count(*)
        FROM cart_view
        WHERE MEMBER_ID = 'aaaa' AND item_code = 8;
        
SELECT *
FROM cart_view;

select
            total_price
        FROM 
            cart_view
        WHERE
            cart_code = 15;
            
SELECT * FROM shop_cart;            

DELETE FROM shop_cart
WHERE 
CART_CODE IN (
50, 51
);

SELECT * FROM shop_cart;

DELETE FROM shop_cart
WHERE cart_code = 48 OR cart_code = 49;

-- 구매 정보 테이블
CREATE TABLE SHOP_BUY(
	BUY_CODE INT AUTO_INCREMENT PRIMARY KEY ,
	MEMBER_ID VARCHAR(20) NOT NULL REFERENCES shop_member(MEMBER_ID) ,
	BUY_PRICE INT NOT NULL ,
	BUY_DATE DATETIME DEFAULT CURRENT_TIMESTAMP
);
-- 구매 정보 상세 테이블
CREATE TABLE BUY_DETAIL(
	BUY_DETAIL_CODE INT AUTO_INCREMENT PRIMARY KEY ,
	ITEM_CODE INT NOT NULL REFERENCES shop_item(item_code) ,
	BUY_CNT INT NOT NULL ,
	TOTAL_PRICE INT NOT NULL ,
	BUY_CODE INT NOT NULL REFERENCES SHOP_BUY(BUY_CODE)
	
);

SELECT * FROM shop_buy;
SELECT * FROM buy_detail
WHERE buy_code = (
	SELECT buy_code
	FROM shop_buy
	WHERE member_id = 'bbbb'
);

select IFNULL(MAX(buy_code),0)+1
        from shop_buy;
        
        
select *
        from cart_view
        where cart_code = 87;
        
SELECT shop_cart.ITEM_CODE
	, CART_CNT AS BUY_CNT
	, ITEM_PRICE * CART_CNT AS TOTAL_PRICE
FROM shop_cart INNER JOIN shop_item
ON shop_cart.item_code = shop_item.ITEM_CODE

	
DELETE FROM buy_detail
WHERE buy_code = 4;

DELETE FROM shop_buy
WHERE member_id = 'bbbb';

SELECT * FROM emp;
SELECT * FROM dept;

SELECT empno, ename, deptno
FROM emp 
WHERE deptno = (
	SELECT deptno
	FROM emp
	WHERE ename = '김사랑'
	);
	
	
SELECT ename, job, sal
FROM emp
WHERE sal = (
	SELECT sal
	FROM emp
	WHERE ename='한예슬'
	);
	
SELECT empno, ename, emp.deptno, dname
FROM emp, dept
WHERE emp.DEPTNO = dept.DEPTNO;

SELECT empno, ename, deptno , (
	SELECT dname 
	FROM dept 
	WHERE deptno = emp.deptno) AS dname
FROM emp;


SELECT *
FROM emp
WHERE sal > (
	SELECT AVG(sal)
	FROM emp 
	);


SELECT *
FROM emp;

SELECT *
FROM dept;

SELECT * FROM shop_buy;

SELECT * FROM buy_detail;

SELECT * from shop_item;

SELECT 
	*
FROM
	shop_buy INNER JOIN buy_detail
ON
	shop_buy.buy_code = buy_detail.buy_code
WHERE 
	shop_buy.buy_code IN (
		SELECT buy_code
		FROM shop_buy
		WHERE member_id ='aaaa'
	);

SELECT *
FROM item_image;


UPDATE emp
SET sal = sal + 100
WHERE deptno = (
	SELECT deptno
	FROM dept
	WHERE LOC = '인천'
	);
	
UPDATE emp
SET sal = sal + 100
WHERE deptno = 20;


-- dept 안에 근무직 있음

SELECT * FROM shop_cart;
SELECT * FROM shop_item;

SELECT * FROM shop_member;


-- 장바구니에 있는
-- 상품명 shop_item.item_name , 상품가격 shop_item.item_Price,
-- 장바구니 소유자 ID shop_cart.mamber_id , 장바구니 소유자 shop_mamber.member_name

SELECT 
	item_code,
	(
		SELECT item_name
		FROM shop_item
		WHERE shop_item.ITEM_CODE = shop_cart.ITEM_CODE
	) AS item_name ,
	(
		SELECT ITEM_PRICE
		FROM SHOP_ITEM
		WHERE shop_item.ITEM_CODE = shop_cart.item_code
	) AS item_price ,
	member_id ,
	(
		SELECT member_name
		FROM shop_member
		WHERE shop_member.member_id = shop_cart.member_id
	) AS member_name
	
FROM 
	shop_cart;
	
SELECT 
	CASE WHEN
 		MAX(image_code)IS NULL
	THEN 0
		ELSE MAX(image_code) 
	END 
FROM item_image;

SELECT
	case when
		MAX(buy_code) IS NULL
	THEN 1
		ELSE MAX(buy_code)+1
	END AS '끝에 있는 buy_code + 1'
FROM shop_buy;

SELECT * FROM shop_buy;

DELETE FROM buy_detail;
DELETE FROM shop_buy;

SELECT * FROM emp;

SELECT ename
FROM emp
WHERE sal IN(200, 250, 300, 400, 500);

SELECT ename
FROM emp
WHERE sal = 200;

SELECT ename
FROM emp
WHERE sal = 250;

SELECT * FROM shop_cart;

SELECT * FROM shop_buy;


SELECT 
	*
FROM
	shop_buy INNER JOIN buy_detail
	ON
		shop_buy.buy_code = buy_detail.buy_code
	INNER JOIN shop_item
	ON buy_detail.ITEM_CODE = shop_item.ITEM_CODE
WHERE 
	shop_buy.buy_code IN (
		SELECT buy_code
		FROM shop_buy
		WHERE member_id ='aaaa'
	);

SELECT * FROM buy_detail;
SELECT * FROM shop_item;
SELECT * FROM item_image;
SELECT * FROM shop_buy;

-- 상품코드, 상품명, 대표 이미지명 수매수량, 구매 가격
SELECT
	shop_item.ITEM_CODE ,
	shop_item.ITEM_NAME ,
	item_image.ORIGEN_FILE_NAME ,
	buy_detail.BUY_CNT ,
	buy_detail.BUY_CNT * shop_item.ITEM_PRICE as TOTAL_PRICE
FROM
	shop_item INNER JOIN item_image
	ON shop_item.item_code = item_image.ITEM_CODE
	INNER JOIN buy_detail
	ON shop_item.ITEM_CODE = buy_detail.ITEM_CODE
WHERE 
	IS_MAIN ='Y';
	
item_image.ATTACHED_FILE_NAME_IMAGE


SELECT 
	item_code ,
	buy_cnt ,
	total_price,
	(SELECT ITEM_NAME
	 FROM SHOP_ITEM
	 WHERE shop_item.ITEM_CODE = buy_detail.ITEM_CODE) AS ITEM_NAME ,
	(SELECT ORIGEN_FILE_NAME
	 FROM ITEM_IMAGE
	 WHERE ITEM_CODE = buy_detail.ITEM_CODE AND IS_MAIN = 'y') AS ORIGEN_FILE_NAME
FROM buy_detail;

SELECT Detail.item_code
	, buy_cnt
	, total_price
	, item_name
	, ORIGEN_FILE_NAME
	, buy_date
FROM buy_detail detail INNER JOIN shop_item item
ON detail.ITEM_CODE = item.ITEM_CODE
INNER JOIN item_image img
ON img.ITEM_CODE = item.ITEM_CODE
INNER join shop_buy buy
ON buy.buy_code = detail.BUY_CODE
WHERE is_main = 'Y' AND buy.MEMBER_ID='admin'
ORDER BY item_code;

SELECT
	item_code,
	buy_cnt ,
	total_price ,
	(SELECT item_name
	 FROM shop_item
	 WHERE shop_item.ITEM_CODE = buy_detail.ITEM_CODE) AS item_name ,
	(SELECT ORIGEN_FILE_NAME
	 FROM item_image
	 WHERE item_code = buy_detail.ITEM_CODE AND is_main = 'y') AS ORIGEN_FILE_NAME ,
	(SELECT buy_code
	 FROM shop_buy
	 WHERE shop_buy.buy_code = buy_detail.ITEM_CODE) AS buy_code ,
	(SELECT buy_price
	 FROM shop_buy
	 WHERE buy_code = buy_detail.buy_code) AS buy_price
FROM 
	buy_detail;
	
SELECT *
FROM cart_view;

SELECT * FROM buy_detail;
SELECT * FROM shop_buy;
DELETE FROM shop_buy
WHERE buy_code = 1;

SELECT
            detail.buy_code
            , buy.buy_price
            , Detail.item_code
            , buy_cnt
            , total_price
            , item_name
            , ATTACHED_FILE_NAME_IMAGE
            , buy_date
            , buy.member_id
        FROM
            buy_detail as detail INNER JOIN shop_item as item
            ON detail.ITEM_CODE = item.ITEM_CODE
            INNER JOIN item_image as img
            ON img.ITEM_CODE = item.ITEM_CODE
            INNER join shop_buy as buy
            ON buy.buy_code = detail.BUY_CODE
        WHERE
            is_main = 'Y'
        ORDER BY buy_date DESC;

SELECT 
	BUY.BUY_CODE ,
	BUY.MEMBER_ID ,
	BUY.BUY_PRICE ,
	BUY.BUY_DATE ,
	DETAIL.BUY_CNT ,
	DETAIL.TOTAL_PRICE ,
	ITEM.ITEM_NAME ,
	IMG.ATTACHED_FILE_NAME_IMAGE
FROM
	shop_buy AS BUY INNER JOIN buy_detail AS DETAIL
	ON BUY.BUY_CODE = DETAIL.BUY_CODE
   INNER JOIN shop_item AS ITEM     
	ON DETAIL.ITEM_CODE = ITEM.ITEM_CODE
	INNER JOIN item_image AS IMG
	ON ITEM.ITEM_CODE = IMG.ITEM_CODE
WHERE
	IMG.IS_MAIN = 'Y' AND BUY.BUY_CODE = 5;
	
Select

            SHOP_ITEM.ITEM_CODE ,
            ITEM_NAME ,
            ITEM_PRICE ,
            ITEM_STOCK ,
            item_category.cate_name,
            item_category.cate_code,
            item_image.ORIGEN_FILE_NAME,
            item_image.is_main
        from
            SHOP_ITEM INNER JOIN item_category
            ON shop_item.CATE_CODE = item_category.CATE_CODE
            INNER JOIN item_image
            ON shop_item.ITEM_CODE = item_image.ITEM_CODE
        where
            SHOP_ITEM.ITEM_CODE = 1;

-- 컬럼 추가 준비중 : 1 판매중 2 매진:3
ALTER TABLE shop_item ADD COLUMN ITEM_STATUS INT DEFAULT 1; 

ALTER TABLE shop_item DROP COLUMN item_status;
ALTER TABLE shop_item MODIFY ITEM_STATUS INT DEFAULT 1;

UPDATE shop_item
SET item_status = 2
WHERE item_status = 1;

SELECT * FROM shop_item;


SELECT BUY_CODE ,
            MEMBER_ID ,
            BUY_PRICE ,
            BUY_DATE
        FROM SHOP_BUY
        WHERE 
		  buy_date > '2024-02-05 15:39:00'
        AND buy_date < '2024-02-07'
       
      	AND MEMBER_ID like CONCAT('%','ad','%')
        
        ORDER BY BUY_DATE DESC;
        
SELECT * FROM shop_buy;

SELECT buy_date
, DATE_FORMAT(buy_date, '%Y-%m-%d')
, DATE_FORMAT(buy_date, '%y/%m/%d %H:%i:%s')
, DATE_FORMAT(buy_date, '%y')
, DATE_FORMAT(buy_date, '%y-%m-%d %H:%i') AS buy_date2
FROM shop_buy;


SELECT BUY_CODE ,
            MEMBER_ID ,
            BUY_PRICE ,
            DATE_FORMAT(buy_date, '%Y-%m-%d') AS BUY_DATE
        FROM SHOP_BUY
        WHERE 
        DATE_FORMAT(buy_date, '%Y-%m-%d') >= '2024-02-02'
        AND DATE_FORMAT(buy_date, '%Y-%m-%d') <= '2024-05-04'
      
        ORDER BY BUY_DATE DESC;
SELECT * FROM shop_buy;

SELECT *
        FROM item_image
        WHERE IMAGE_CODE = 1;
        
        
        
SELECT ITEM_CODE
	, ITEM_NAME
	, ITEM_STOCK
	, ITEM_STATUS
	, IF(ITEM_STATUS = 1 , '준비중' , IF(ITEM_STATUS = 2, '판매중', '매진')) AS '상태1'
	, 
	CASE
		WHEN ITEM_STATUS = 1 THEN '준비 중'
		WHEN ITEM_STATUS = 2 THEN '판매 중'
		ELSE '매진'
	END AS STR_STATUS
FROM
	shop_item;

SELECT * FROM shop_item;
	
SELECT CATE_NAME
	, CATE.CATE_CODE
	, ITEM_STOCK
	, ITEM_STATUS
	, item.ITEM_CODE
	, img.ORIGEN_FILE_NAME
	, img.ATTACHED_FILE_NAME_IMAGE
	, img.IMAGE_CODE
FROM shop_item ITEM
INNER JOIN item_category CATE
ON ITEM.CATE_CODE = CATE.CATE_CODE
INNER JOIN item_image img
ON item.item_code = img.ITEM_CODE
WHERE item.item_code = 1;