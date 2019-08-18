KAKAO-PAY ASSIGNMENT
=====================
> 2019 **KAKAOPAY** 서치펌 공개 채용
## 개발환경

* BackEnd
  * JAVA8
  * Spring 4.1.6.RELEASE
  * MyBatis 3.2.2
  * MAVEN
  * Tomcat 8
  * lombok 1.16.4
  * Java SE Development Kit 1.8
  
* FrontEnd
  * bootstrap-3.2.0
  * jquery-3.3.1
  * JSTL
  
* Database
  * MySql (MARIA DB)
  * NAS에 maria DB server를 상시 가동중 ( 평소에는 휴면모드라 처음에는 느릴수있습니다. )
  
* Package Structure
```
src - ../controller/UrlShorteningController.java ==> # Main Controller
      ../repository/UrlshorteningDAO.java =========> # Database Transaction 
      ../repository/UrlShorteningMapper.xml =======> # SQL Code
      ../service/UrlShorteningService.java ========> # Service Interface
      ../service/UrlShorteningServiceImpl.java ====> # Service Main
      ../util/messageUtil.java ====================> # Common Message
      ../util/UrlShorteningUtil.java ==============> # ShorteningUrl Util
      ../vo/ShortUrlVO.java =======================> # Value Object
      
webapp - css/style.css ============================> # Common css
         resources/js/shortUrl.js =================> # Shortening Main javascript
         WEB-INF/views/index.jsp ==================> # Main Page
         WEB-INF/views/error/error.jsp ============> # Error Page
```

* Bulid & Excute
```
 * Maven Project( Exisiting Maven Prohects ) Import
 * server Tomcat 8 > Web Modules > path Edit > /  
 * http://localhost:8080/ 가 아닌 http://localhost/ 를 ROOT로 설정 하려면 port 80으로 변경
 * Tomcat server Excute
 * ROOT Page : http://localhost:8080/
```
=====================


요구사항
---------------------
* webapp으로 개발하고 URL 입력폼 제공 및 결과 출력.
* URL Shortening Key는 8 Character 이내로 생서되어야 합니다.
* 동일한 URL에 대한 요청은 동일한 Shortening Key로 응답해야 합니다.
* Shortening 된 URL을 요청받으면 원래 URL로 Redirect 합니다.
* Shortening Key 생성 알고리즘은 직접 구현해야 합니다. (라이브러리 사용 불가)
* Unit Test Code 작성.
* Database 사용은 필수 아님.(선택)
* readme.md 파일에 프로젝트 소개 및 문제해결 전략 그리고 프로젝트 빌드와 실행방법 명시.
* 언어, 프레임워크의 제한 없음.
=====================


문제해결 전략
---------------------
1. 해결방안
<pre>
* 입력받은 URL을 hashCode로 변환
</pre>



