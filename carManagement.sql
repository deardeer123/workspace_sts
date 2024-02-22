CREATE TABLE CAR_INFO(
	MODEL_NUM INT AUTO_INCREMENT PRIMARY KEY , -- 모델 번호
	MODEL_NAME VARCHAR(20) NOT NULL , -- 모델명
	MODEL_PRICE INT NOT NULL , -- 금액
	MODEL_MANUFACTURER VARCHAR(20) NOT NULL -- 제조사
	);
	
CREATE TABLE SALES_INFO(
	SALES_NUM INT AUTO_INCREMENT PRIMARY KEY , -- 판매번호
	CAR_BUYER VARCHAR(20) NOT NULL , -- 구매자명
	BUYER_TEL VARCHAR(20) , -- 구매자 연락처
	MODEL_COLOR VARCHAR(20) NOT NULL , -- 색상
	SALES_DATE DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	MODEL_NUM INT NOT NULL REFERENCES car_info(MODEL_NUM)
	);
	
-- DROP TABLE SALES_INFO;

SELECT * FROM car_info;
SELECT * FROM sales_info;
-- DELETE FROM sales_info;

INSERT INTO car_info(
	MODEL_NAME ,
	MODEL_PRICE ,
	MODEL_MANUFACTURER)
values(
	'아반데' ,
	10000,
	'현대'
	);

 SELECT
            MODEL_NUM ,
            MODEL_NAME,
            MODEL_MANUFACTURER
        FROM
            car_info;
            
-- DELETE FROM car_info;
ALTER TABLE car_info AUTO_INCREMENT = 1;

SELECT 
	CAR_BUYER,
	BUYER_TEL,
	SALES_DATE,
	MODEL_COLOR,
	MODEL_NAME,
	MODEL_PRICE
FROM
	sales_info INNER JOIN car_info
	ON sales_info.MODEL_NUM = car_info.MODEL_NUM;






