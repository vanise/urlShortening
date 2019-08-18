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
 * ROOT PAGE : http://localhost:8080/
```
