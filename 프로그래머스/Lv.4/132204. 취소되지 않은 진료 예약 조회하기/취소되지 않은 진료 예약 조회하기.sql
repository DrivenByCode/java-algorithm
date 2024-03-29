-- 코드를 입력하세요
-- DR_ID: 의사 아이디 <- DOCTOR 테이블
-- DR_NAME: 의사이름
-- PT_NAME: 환자이름
-- PT_NO: 환자번호
WITH DATES AS (
    SELECT APNT_NO, APNT_YMD, PT_NO, MDDR_ID, MCDP_CD
    FROM APPOINTMENT
    WHERE MCDP_CD ='CS' AND TO_CHAR(APNT_YMD, 'YYYY-MM-DD') = '2022-04-13' AND APNT_CNCL_YN = 'N'
),
PT AS(
    SELECT APNT_NO, APNT_YMD, PT_NO, PT_NAME, MDDR_ID, MCDP_CD
    FROM PATIENT JOIN DATES
    USING(PT_NO)
),
DT AS(
    SELECT APNT_NO, PT_NAME, PT_NO, P.MCDP_CD, DR_NAME, APNT_YMD
    FROM PT P JOIN DOCTOR D
    ON P.MDDR_ID = D.DR_ID
)
SELECT *
FROM DT
ORDER BY APNT_YMD;