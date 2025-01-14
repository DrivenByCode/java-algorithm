-- 코드를 입력하세요
WITH REST_SCORE AS (
    SELECT 
        RI.REST_ID, 
        RI.REST_NAME, 
        RI.FOOD_TYPE, 
        RI.FAVORITES, 
        RI.ADDRESS, 
        ROUND(SUM(RR.REVIEW_SCORE) / COUNT(RR.REVIEW_SCORE), 2) AS SCORE
    FROM 
        (SELECT *
         FROM REST_INFO
         WHERE ADDRESS LIKE '서울%'
        ) RI 
            JOIN 
        REST_REVIEW RR
    ON RI.REST_ID = RR.REST_ID 
    GROUP BY RI.REST_ID, RI.REST_NAME, RI.FOOD_TYPE, RI.FAVORITES, RI.ADDRESS
)

SELECT *
FROM REST_SCORE
ORDER BY SCORE DESC, FAVORITES DESC