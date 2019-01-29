DB를 사용하면서 발생 가능한 문제점
---

대부분 Ap응답속도를 지연시키는 부분은 DB 쿼리 수행, 결과 처리하는 시간이다.  

대부분 spring, springboot를 쓰면서 DB connection, DataSource를 사용하기 때문에 별 탈은 없지만  
혹시 라도 Connection,Statement 관련 객체는 ResultSet을 close를 사용한다.  

Connection 객체를 얻는 부분이 가장 느리기 때문에  Db Connection Pool을 사용하고 있다. 

요즘은 Mybatis를 사용하고 있어 JDBC를 이용하여 커넥션을 맺고, 셀렉트문 날려서 리절트셋 받아오고, rs.next()등을 이용하여  
하나씩 받아오는 행위, SQL의 변경 등이 발생할 경우, 프로그램(java 파일)을 수정하기 때문에 그 유연성이 않좋았다.  
MyBatis에서는 SQL을 xml 파일에 작성하기 때문에, SQL의 변환이 자유롭고, 가독성이 좋다는 장점이 있다.


최근 MyBatis 대비 Spring JDBC로 넘어가는 추세라 간단하게 정리한다.  

* 초기 설정이 단순합니다.
* 쿼리 결과를 변환하는 구성요소(RowMapper)가 인터페이스로 정의되어 Java 컴파일러의 장점을 활용할 수 있습니다.
* SQL 쿼리를 Groovy 파일 안에 선언하면 Java 컴파일러의 장점을 활용할 수 있습니다.
* 간단한 ORMapping 기능을 제공합니다.
* Collection 파라미터를 더 편하게 사용할 수 있습니다.
* MyBatis의 일부 기능들은 단순히 쿼리를 실행하기만을 원하는 사람에게는 불필요하고 디버깅을 어렵게 합니다.
> * 세션관리, batch update 추상화 등

 
JDBC까 아직 비동기를 지원하지 않았지만 Ajdbc 프로젝트가 http://code.google.com/p/adbcj/, http://code.google.com/p/adbcj/

현재 mysql과 postgresql을 위해 기본적으로 2 개의 실험용 비동기 드라이버가 있습니다.

