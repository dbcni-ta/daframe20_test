DAFrame 2.x Evaluation Pack 3.0 (UTF-8) 

=========== 설정시 유의점 ==========

1. 본 배포판은 이클립스의 'Java Project' 에 최적화 되어 있습니다.

2. DAFrame 2.x 버전부터는 JDK 1.4 를 지원하지 않습니다.

3. 본 배포판은 CVS로 형상관리함에 있어 .cvsignore 설정들도 포함하고 있으니 참고하십시요.

4. 본 배포판의 각종 프로그램 소스들은 기본적으로 UTF-8 로 인코딩되어 있으나
   프레임워크의 환경설정 파일 (/WEB-INF/conf/*) 부분의 파일들중 일부는 EUC-KR 형태입니다.

   org.eclipse.core.resources.prefs 파일에 
   아래와 같이 사전 세팅되어 있으니 별다른 처리는 해주시지 않으셔도됩니다.

	encoding//app.war/WEB-INF/conf/admin.conf=UTF-8
	encoding//app.war/WEB-INF/conf/prop/example_kor.properties=EUC-KR
	encoding//app.war/WEB-INF/conf/prop/pageNavi.properties=UTF-8
	encoding//app.war/WEB-INF/conf/prop/sso_federation.properties=EUC-KR
	encoding//app.war/WEB-INF/gen/template/AC-001.EUC-KR.src=EUC-KR
	encoding//app.war/WEB-INF/gen/template/AC-001.src=EUC-KR
	encoding//app.war/WEB-INF/gen/template/TSQL-001.EUC-KR.src=EUC-KR
	encoding//app.war/WEB-INF/gen/template/TSQL-001.src=EUC-KR
	encoding/<project>=UTF-8
