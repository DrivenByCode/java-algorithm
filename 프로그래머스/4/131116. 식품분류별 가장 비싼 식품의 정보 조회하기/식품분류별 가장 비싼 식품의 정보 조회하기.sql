-- 코드를 입력하세요
SELECT M.CATEGORY, M.MAX_PRICE, PRODUCT_NAME
FROM FOOD_PRODUCT FP
    JOIN (SELECT CATEGORY, MAX(PRICE) AS MAX_PRICE
            FROM FOOD_PRODUCT
            WHERE CATEGORY IN ('과자', '국', '김치', '식용유')
            GROUP BY CATEGORY) M
ON FP.CATEGORY = M.CATEGORY AND FP.PRICE = M.MAX_PRICE
ORDER BY M.MAX_PRICE DESC;