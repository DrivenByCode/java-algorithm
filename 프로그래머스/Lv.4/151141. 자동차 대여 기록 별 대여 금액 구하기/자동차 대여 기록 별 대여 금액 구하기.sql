-- 코드를 입력하세요
-- SELECT T.HISTORY_ID, DAILY_FEE * DAYS * (100 - NVL(D.DISCOUNT_RATE, 0)) / 100 AS FEE
-- FROM TRUCKS T LEFT JOIN DISCOUNT D
-- ON T.DURATION_TYPE = D.DURATION_TYPE
-- WHERE DAILY_FEE * DAYS * (100 - NVL(D.DISCOUNT_RATE, 0)) / 10 > 0
-- ORDER BY FEE DESC, HISTORY_ID DESC

WITH TRUCKS AS (
    SELECT 
        H.HISTORY_ID, (H.END_DATE - H.START_DATE) + 1 AS DAYS, T.DAILY_FEE, 
        CASE
            WHEN 7 <= (H.END_DATE - H.START_DATE) + 1 AND (H.END_DATE - H.START_DATE) + 1 < 30 THEN '7일 이상'
            WHEN 30 <= (H.END_DATE - H.START_DATE) + 1 AND (H.END_DATE - H.START_DATE) + 1 < 90 THEN '30일 이상'
            WHEN 90 <= (H.END_DATE - H.START_DATE) + 1 THEN '90일 이상'
        END AS DURATION_TYPE
    FROM 
        (SELECT CAR_ID, DAILY_FEE
        FROM CAR_RENTAL_COMPANY_CAR
        WHERE CAR_TYPE = '트럭') T 
    RIGHT JOIN 
        CAR_RENTAL_COMPANY_RENTAL_HISTORY H
    ON T.CAR_ID = H.CAR_ID
    WHERE T.DAILY_FEE IS NOT NULL
),
DISCOUNT AS (
    SELECT DURATION_TYPE, REPLACE(DISCOUNT_RATE, '%', '') AS DISCOUNT_RATE
    FROM CAR_RENTAL_COMPANY_DISCOUNT_PLAN
    WHERE CAR_TYPE = '트럭'
)

SELECT T.HISTORY_ID, DAILY_FEE * DAYS * (100 - NVL(D.DISCOUNT_RATE, 0)) / 100 AS FEE
FROM TRUCKS T LEFT JOIN DISCOUNT D
ON T.DURATION_TYPE = D.DURATION_TYPE
WHERE DAILY_FEE * DAYS * (100 - NVL(D.DISCOUNT_RATE, 0)) / 10 > 0
ORDER BY FEE DESC, HISTORY_ID DESC
