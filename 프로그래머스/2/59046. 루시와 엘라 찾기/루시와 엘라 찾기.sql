-- 2024-12-12 루시와 엘라 찾기
SELECT ANIMAL_ID, NAME, sex_upon_intake
FROM ANIMAL_INS
WHERE NAME IN ('Lucy', 'Ella', 'Pickle', 'Rogan', 'Sabrina', 'Mitty')
ORDER BY ANIMAL_ID;