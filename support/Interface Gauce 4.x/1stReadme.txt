1. 가우스 클라이언트 설치 (별도 파일)
2. 가우스 서버 설치 (별도파일) 
  - 단 설치경로는 DAFrame의 WEB-INF 가 아닌 별도의 (가우스 자체적인) WEB-INF에 설치
3. gauce.xml 및 gauce40.properties 파일을 app.war/WEB-INF 밑으로 복사.
4. gauce40.properties 항목을 수정 (2번의 가우스 서버 설치 경로)
5. web.xml 파일을 app.war/WEB-INF 밑으로 복사 (덮어씀)
6. gauce4018.jar app.war/WEB-INF/lib 밑으로 복사