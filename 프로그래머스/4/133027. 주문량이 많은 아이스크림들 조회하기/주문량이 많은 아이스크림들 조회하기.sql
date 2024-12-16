-- 코드를 입력하세요
WITH COUNTS AS (
    SELECT FLAVOR, SUM(TOTAL_ORDER)
    FROM (
        SELECT H.SHIPMENT_ID, H.FLAVOR, (H.TOTAL_ORDER + J.TOTAL_ORDER) AS TOTAL_ORDER
        FROM FIRST_HALF H JOIN JULY J
        ON H.FLAVOR = J.FLAVOR
    )
    GROUP BY FLAVOR
    ORDER BY SUM(TOTAL_ORDER) DESC
)

SELECT FLAVOR
FROM COUNTS
WHERE ROWNUM <= 3
