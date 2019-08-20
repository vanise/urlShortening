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
 * Maven Project( Exisiting Maven Projects ) Import
 * Text Encoding 설정 > Preferences > General > Workspace > Text File encoding > UTF-8로 변경
 * server Tomcat 8 > Web Modules > path 변경 > /  (Tomcat8 zip파일 Git Repository에 Upload)
 * http://localhost:8080/ 가 아닌 http://localhost/ 를 ROOT로 설정 하려면 port 80으로 변경
 * Tomcat server Excute
 * ROOT Page : http://localhost:8080/
```
---------------------------------------------------------------------------------------------------------------------


요구사항
---------------------
* webapp으로 개발하고 URL 입력폼 제공 및 결과 출력.
* URL Shortening Key는 8 Character 이내로 생되어야 합니다.
* 동일한 URL에 대한 요청은 동일한 Shortening Key로 응답해야 합니다.
* Shortening 된 URL을 요청받으면 원래 URL로 Redirect 합니다.
* Shortening Key 생성 알고리즘은 직접 구현해야 합니다. (라이브러리 사용 불가)
* Unit Test Code 작성.
* Database 사용은 필수 아님.(선택)
* readme.md 파일에 프로젝트 소개 및 문제해결 전략 그리고 프로젝트 빌드와 실행방법 명시.
* 언어, 프레임워크의 제한 없음.
---------------------------------------------------------------------------------------------------------------------


문제해결 전략
---------------------
### 1. 해결방안
```
1. 입력받은 URL을 hashCode로 변환.
2. 어떻게 ? String Class의 hashCode() 또는 Message-Digest를 이용한 MD5(128bit),SHA-256(160bit)를 이용.
3. String Class의 hashCode는 int형태로 반환. [a-zA-Z]로 만드려면 int가 좋아보여 hashCode로 채택.
4. [a-zA-Z]의 갯수의 총합은 52라, 52진수를 사용하기로함.
5. URL을 String hashCode로 변환된 int를 52진수를 사용하여 [a-zA-Z]로 조합된 URL 생성.
```

### 2. 문제발생
```
1. hashCode의 returnType은 int다. int의 최대값은 2147483647(10자리)이다. 그후에는 음수로 반환됨.
2. 좋아. 음수로 반환되면 양수로 바꿔주자.
3. Shortening된 URL이 생성된다! abCDEf...문자열의 최대값이 6자리다.
```

### 3. 문제해결
```
1. Random Class를 사용할까 했으나, 처음에는 괜찮으나 버킷 사이즈가 모두 찼을때 무한루프 가능성이 있어 제외했다.
2. 그럼 문자열 2자리를 더 채우려면 52 * 52, 2704만큼의 정수가 더 필요하다. 2704 가 넘으면 3자리수 생기기 
   때문에 2704를 넘으면 안됨.
3. 안전하게 처음 변환된 hashCode의 앞자리 3자리만 가져오자.
4. 처음 변환된 6자리 이내의 문자열 + 처음 변환된 hashCode의 앞자리 3자리의 52진수값 2자리 이내의 문자열을 합쳐 
   8자리 [a-zA-Z]로 조합된 URL을 완성.
5. 해시충돌회피를 위해 해시충돌기법중 가장 먼저 생각난 선형탐색기법을 이용하기로함.
6. 52진수의 8자리 문자열의 버킷사이즈가 너무큼. 임의의 버킷사이즈를 만들기로함. 임의의 버킷사이즈는 처음 생성된 
   hashCode로 채택.
7. 결론. hashCode의 탐색후 hashcode의 앞의 3자리수만큼 한번 더 탐색해 8자리 문자열로 된 Shortening URL 생성.
```

### 4. review
* 더 효율적인 hashCode 생성 방안을 고려
* 사용자의 편의성을 더 고려하여 프로그래밍
* hashCode에 대해 더 공부할 필요를 느낌
* ProgressBar 필요를 느낌 (응답이 늦을때, 요청이 실행된건지 알수가없음)
---------------------------------------------------------------------------------------------------------------------

Controller Url 정리
--------------------- 
#### 1. Url To ShorteningUrl
 * URL : /urlConvert
 * Method Name : urlShortening
 * ParameterType : VO
 * ReturnType : map
 
#### 2. ShorteningUrl To Url
 * URL : /shorteningUrlConvert
 * Method Name : urlShortening
 * ParameterType : VO
 * ReturnType : map
 
#### 3. ShorteningUrl To Url
 * URL : /{shortUrl} ( @PathVariable 사용 )
 * Method Name : redirectUrl
 * ParameterType : String
 * ReturnType : redirect





