
-- 메뉴 테이블
CREATE TABLE menu(
	MENU_NUM INT AUTO_INCREMENT PRIMARY KEY ,
	MENU_TYPE VARCHAR(20) NOT NULL,
	MENU_NAME VARCHAR(20) NOT NULL,
	PAGE_NAME VARCHAR(20) NOT NULL
	);
	
INSERT INTO menu(
	MENU_TYPE ,
	MENU_NAME ,
	PAGE_NAME ,
	MENU_INDEX)
	VALUES
	(
	'userSide',
	'테스트1',
	'test1',
	1) ,
	(
	'userSide',
	'테스트2',
	'test2',
	2),
	(
	'userSide',
	'테스트3',
	'test3',
	3);

-- 생각해보니깐 메뉴순서가 있어야 할거 같음
ALTER TABLE menu ADD MENU_INDEX INT NOT NULL;	
ALTER TABLE menu MODIFY MENU_INDEX INT;

-- DELETE from menu;
SELECT * FROM menu;

INSERT INTO menu(
	MENU_TYPE ,
	MENU_NAME ,
	PAGE_NAME , 
	MENU_INDEX)
	VALUES(
	'adminHeader',
	'상품추가',
	'addItem',
	1),
	(
	'adminHeader',
	'상품',
	'updateItem',
	2),
	(
	'adminHeader',
	'판매현황',
	'salesStatus',
	3
	);
	
INSERT INTO menu(
		MENU_TYPE ,
	MENU_NAME ,
	PAGE_NAME , 
	MENU_INDEX)
	VALUES
	(
	'adminSide' ,
	'상품추가',
	'addItem',
	1),
	(
	'admimSide' ,
	'상품수정',
	'updateItem',
	2),
	(
	'adminSide' ,
	'상품삭제',
	'deleteItem',
	3);
	

-- 메뉴 인덱스 변경 

UPDATE menu
SET MENU_INDEX = MENU_INDEX -1
WHERE 
	page_name ='salesStatus';

DELETE FROM menu
WHERE
menu_num IN (1,2,3,4,5,6);


-- 메뉴검색
SELECT * FROM menu;

select
            MENU_NUM ,
            MENU_TYPE ,
            MENU_NAME ,
            PAGE_NAME ,
            MENU_INDEX
        FROM
            MENU
        WHERE
            MENU_TYPE='adminSide';

select
        MENU_NUM ,
        MENU_TYPE ,
        MENU_NAME ,
        PAGE_NAME ,
        menu_index
        FROM
        MENU
        WHERE
        MENU_TYPE='adminHeader'
		  ORDER BY menu_index;

-- 메뉴 설정 변경		  
UPDATE menu
SET
menu_type = 'adminSide'
WHERE
menu_num = 14;

COMMIT;


SELECT *
FROM menu
WHERE
MENU_TYPE LIKE concat('admin','%')
ORDER BY menu_type, menu_index;

        
        
-- 상품 카테고리 정보 테이블
CREATE TABLE ITEM_CATEGORY(
	CATE_CODE INT AUTO_INCREMENT PRIMARY KEY
	, CATE_NAME VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO item_category(
	CATE_NAME ) 
	VALUES
	('테스트 카테고리1'),
	('테스트 카테고리2'),
	('테스트 카테고리3');

SELECT * FROM item_category;


-- 상품 정보 테이블
CREATE TABLE ITEM(
	ITEM_CODE INT AUTO_INCREMENT PRIMARY KEY
	, ITEM_NAME VARCHAR(50) NOT NULL UNIQUE
	, ITEM_PRICE INT NOT NULL
	, ITEM_STOCK INT DEFAULT 9999
	, REG_DATE DATETIME DEFAULT CURRENT_TIMESTAMP
	, CATE_CODE INT NOT NULL REFERENCES item_category (CATE_CODE)
);

CREATE TABLE item_img(
	IMG_CODE INT AUTO_INCREMENT PRIMARY KEY ,
	ORIGEN_FILE_NAME VARCHAR(100) NOT NULL ,
	ATTACHED_FILE_NAME_IMAGE VARCHAR(100) NOT NULL ,
	ITEM_CODE INT not null REFERENCES ITEM(ITEM_CODE)
	);
	
ALTER TABLE item_img drop ATTACHED_FILE_NAME_IMAGE;
ALTER TABLE item_img ADD ATTACHED_FILE_NAME VARCHAR(100) NOT NULL;

SELECT * FROM item_img;


