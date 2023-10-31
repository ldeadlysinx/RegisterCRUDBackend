# RegisterCRUDBackend

회원관리 및 게시판 시스템 

**이예빈** 

스프링부트 DB 시스템 JPA 설정 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.001.png)

스프링 부트 build     gradle을 통해  

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.002.png)

마리아 DB를 사용할수 있게 라이브러리를 가져옵니다. 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.003.png)

또한 JPA 기능을 사용하기 위해 JPA 기능을 넣어줍니다 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.004.png)

마리아 DB 시스템 설정 

스프링 부트에서 로컬에서 실행되고 있는 DB의 주소를 설정합니다. 그리고 해당DB에 접근할 수 있게 해당 DB의 username과 password 를 입력해줍니다. 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.005.png)

**spring.jpa.open-in-view=false:**  HTTP 요청 처리가 끝날 때 영속성 컨텍스트를 닫아 리소스를 릴리즈합니다. 이것은 지연로딩이 필요할 때 추가 쿼리를 실행하여 데이터를 가져오게 됩니다. 

**spring.jpa.hibernate.ddl-auto=update:**  Hibernate 에서  데이터베이스  스키마  생성  및 업데이트를 제어하는 설정입니다. 

**spring.jpa.properties.hibernate.show\_sql=true:**  Hibernate 에서  실행되는  SQL  쿼리를 로깅할지 여부를 제어하는 설정입니다. 

**spring.jpa.properties.hibernate.format\_sql=true:**  Hibernate 에서  출력되는  SQL  쿼리를 포맷팅하여 가독성을 높이는 설정입니다.

Entity 작업 

Entity는 데이터베이스 테이블과 1:1 매핑되는 자바 클래스라고 정의합니다.  Entity 기본 구조: 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.006.png)

**@Entity**: 해당 클래스가 Entity인 것을 선언합니다 

**@NoArgsConsTructor**: 접근 제한을 하여 기본 생성자의 무분별한 생성을 막아서 의도하지 않은 엔티티를 만드는 것을 막을 수 있습니다. 

**@Getter,Setter**: 이 메서드를 사용하면 따로  Entity 별로  get 메서드나  set 메서드를 사용할 필요 가 없습니다. 

Nullable = false로 지정된 Entity들은 null값이 들어올 수 없습니다. 

User Entity:  가입된 회원의 정보를 저장하는 Entity 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.007.png)

**Id**: 유저의 DB의 각 튜플을 식별하기 위해 설정된 프라이머리 키이다. 해당 유저정보가 DB에 들 어올 경우 순차적으로 값이 증가합니다 

**Username**: 해당 유저가 설정한 id값으로 unique = true를 통해 중복된 값을 가질 수가 없습니다.  **Password** :  유저가 직접 설정하는 비밀번호 값으로 해시화 과정을 거쳐 저장됩니다 

**Name**:  유저가 직접 설정하는 웹 서비스에서 직접 사용되는 이름이다. 중복된 값을 가질 수가 없습니다. 

Board Entity: 작성되는 게시글을 저장하는 Entity 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.008.jpeg)

**Id**: 게시글 DB의 각 튜플을 식별하기 위해 설정된 프라이머리 키이다. 해당 게시글이 DB에 들어 올 경우 순차적으로 값이 증가합니다 

**writer**: 게시글을 작성한 유저의 닉네임을 해당 게시글 DB에 저장합니다. **Title**: 게시글 제목을 담은 Entity 

**Content**: 게시글 내용을 담는 Entity TEXT 형식으로 저장됩니다. 

Comment Entity: 게시글에 작성된 댓글을 저장하는 Entity 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.009.jpeg)

**Id**: 댓글 DB의 각 튜플을 식별하기 위해 설정된 프라이머리 키이다. 해당 댓글이 DB에 들어올 경 우 순차적으로 값이 증가합니다 

**commentWriter**: 댓글을 작성한 유저의 닉네임을 해당 게시글 DB에 저장합니다. **commentContents**: 댓글 내용을 담은 Entity 

**ManyToOne(fetch = FetchType.LAZY)**: 이 어노테이션은 댓글 Entity 가 게시글 Entity 에 1 대 다수의  관계가  되게  설정해줍니다.  **FetchType.LAZY**를  사용하면  연관  엔티티가  실제로  필요할 ![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.010.png)때만 데이터베이스에서 가져옵니다. 

**@JsonBackReference:**  다대일  관계를  가진  엔티티끼리  서로  참고할  때  순환  참조  문제가 발생한데  이렇게  될  경우  서로  계속  참조하려고만  하여서  무한  루프가  발생합니다,  이를 방지하기 위해 사용합니다. 

Like Entity: 게시글의 추천을 나타내는 Entity 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.011.png)

**Id**: 좋아요 B의 각 튜플을 식별하기 위해 설정된 프라이머리 키입니다. 해당 댓글이 DB에 들어올 경우 순차적으로 값이 증가합니다 

**User,boardEntity**: 유저와 게시판과  좋아요 간의 1대 다수의 관계를 설정해줍니다. 이 같은 연결 된 DB를 통해 해당 게시글의 추천을 한 유저를 판별할 수 있습니다. 

**Dto 작업** 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.012.png)

DTO:  DTO 는  프로세스  간에  데이터를  전달하는  용도의  객체이다. 비즈니스  로직을  포함하지 않는 **데이터를  전달하기  위한  단순한  객체**를  뜻합니다.  예를  들어  회원가입  등에서  유저  정보의 입력을  Entity 를  통해  직접  입력  받을  경우  유출되면  안되는  민감한  정보들을  포함시키고  있기 때문에 Dto를 사용합니다. 

**JPA 레포지터리**  

엔티티만으로는  데이터베이스에  데이터를  저장하거나  조회  할  수  없습니다.  데이터  처리를  위해 서는 실제 데이터베이스와 연동하는 JPA 리포지터리가 필요합니다 

리포지터리란**?** 

리포지터리는  엔티티에  의해  생성된  데이터베이스  테이블에  접근하는  메서드들(예: findAll, save 등)을  사용하기  위한  인터페이스입니다.  데이터  처리를  위해서는  테이블에  어떤  값을  넣거나  값을 조회하는  등의 CRUD(Create, Read, Update, Delete)가  필요합니다.  이  때  이러한 CRUD 를 

어떻게  처리할지  정의하는  계층이  바로  리포지터리입니다. 

유저 리포지토리 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.013.png)

**findByUsername**: User Entity 와 연동되어 있는 DB 에서 username 을 가지고 있는 User 정보를 조회할 수 있는 메서드입니다. 

**findByName**: User Entity 와 연동되어 있는 DB 에서 name 값을 가지고 있는 User 정보를 조회할 수 있는 메서드입니다. 

게시판 리포지토리 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.014.png)

**findAll**: 모든 게시글을 조회할 수 있는 메서드입니다 **findByWriter**: 작성자를 통해 게시글을 조회할 수 있습니다 

**Custom Query** 

**@Query**: 데이터베이스 쿼리의 성능을 향상시키기 위해 사용되는 커스텀 쿼리 이러한 쿼리를 통 해 불필요한 쿼리를 최소화하고 효율적인 데이터 검색을 수행할 수 있습니다. 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.015.jpeg)

위의  코드에서  @Query  어노테이션을  사용하여  커스텀  쿼리를  사용하였으며,  이를  통해 “findByTitlteContaining” 메서드보다 성능을 향상시킬 수 있습니다. “LIKE %: keyword%” 와 같은 

사용자  지정  조건을  쿼리에  추가하여  특정  키워드를  포함하는  타이틀을  검색할  수  있습니다. 이를 통해 데이터베이스 쿼리를 더 효율적으로 최적화하고 필요한 데이터만을 검색할 수 있으며 성능을 향상시킬 수 있습니다. 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.016.png)

Test라는 키워드를 제목으로 가지고 있는 게시판 검색 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.017.jpeg)

JSON 형식으로 출력된 게시물  

**Controller  클래스** 

**Controller  시스템**: MVC(Model View Controller)의 디자인 패턴에서 모델 뷰를 분리하기 위한 양측 사이에 배치된 인터페이스 구현입니다. 

**@RestController**는  Spring Framework에서 사용되는 어노테이션 중 하나로, 웹 애플리케이션에서 ![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.018.png)RESTful 웹 서비스를 개발할 때 사용되고, 데이터(API 응답)를 생성하는 데 중점을 둡니다. 

회원 정보 저장을 위한 Controller 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.019.jpeg)

1\.  회원가입 

http://localhost:8080/test/auth/register 을 통해 회원가입 실행 

**@ResponseStatus(HttpStatus.CREATED)**를  사용하여  해당  컨트롤러  메서드에서  생성된 ![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.020.png)리소스에 대한 응답 상태 코드를  HTTP 201 Created로 지정합니다. 이것은 주로  POST 요 청이나  다른  리소스  생성  작업에  사용됩니다.  이렇게  상태  코드를  설정하면  클라이언트 와 서버 간의 의사 소통에 대한 정확성과 일관성을 유지됩니다. 

회원 정보 서비스 기술 설명 

1. 회원정보 저장 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.021.png)

등록하는  유저들의 정보를 Entity 객체로 그대로 받지 말고 안전을 위해 필요한 정보만을 위해 Dto로 받습니다. 

Dto의 값을 User Entity 값에 넣어주고 User값을 userRepository를 통해 저장합니다. 

또한 비밀번호는 스프링부트에서 제공하는 bCtryptoPasswordEncoder 메서드를 통해 암호 해시화 과정을 거쳐서 저장한다. 또한 이미 가입된 유저가 있을경우 똑같은 아이디를 입력해도 중복 가 입되지 않습니다. 

회원가입 데이터 전송전 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.022.png)

http://localhost:8080/test/auth/register 회원가입 데이터 전송 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.023.jpeg)

전송 이후 생겨난 유저 정보 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.024.png)

비밀번호 또한 해시화 과정을 거쳐 저장됨 

2. 해당 유저의 이름 가져오기 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.025.png)

제공받은 JWT 토큰을 스프링부트 내부에 저장된 JWT 시크릿키를 통해 해석을 시도합니다. 만약 해석을 성공할 시 해당 유저의 이름을 반환할 수 있습니다. 

게시판 Controller 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.026.jpeg)

게시판 기술 설명 

1. 게시판 글 목록 가져오기 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.027.png)

boardRepository 를 통해 모든 게시글들을 List에다 저장해서 JSON형식으로 반환합니다. 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.028.png)

[http://localhost:8080/api/board ](http://localhost:8080/api/board)를 통해 게시판 가져오기 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.029.jpeg)

JSON 형식으로 반환된 게시글

2. 해당 글의 id 별로 글 상세내용 출력 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.030.png)

링크에서 파라미터를 받아와 해당 게시글의 Dto를 받아와 ResponseEntity를 통해 해당 게시글의 상세 정보를 JSON형식으로 반환 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.031.jpeg)

3. 게시글 유저 확인 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.032.png)

각 게시글 id 별로 현재 유저가 작성한 유저인지 JWT Token을 통해 확인 가능 확인을 통 해 현재 글을 수정 또는 삭제가 가능하게 합니다 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.033.jpeg)

로그인을 한 유저의 JWT Token을 통해 해당 유저가 작성한 건지 확인해줍니다. 현재 글 은 해당 유저가 작성한 것이 맞으므로 true를 반환해줍니다. 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.034.jpeg)

해당글은 현재유저가 작성한글이 아니므로 false를 반환해줍니다. 4.CRUD 시스템  

**CREATE** 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.035.png)

입력 받은 게시글 Entity등과 JWT Token을 통해 받은 유저정보를 boardRepository를 통 해 게시글 DB 에 저장합니다. 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.036.jpeg)

만들기전 게시판 데이터베이스  

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.037.jpeg)

게시글 저장 전송 

![ref1]

데이터베이스에 저장된 글 

**Update** 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.039.jpeg)

파라미터를 통해 받은 게시글 id를 이용해서 해당 게시글을 가져온다 그리고 JWT Token 을 이용해 유저정보와 게시글을 작성한 유저와 일치할 경우 해당 게시글을 수정할 수 있 습니다. 

![ref1]

업데이트 되기 전 글 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.040.png)

업데이트 값 전송 ![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.041.png) 업데이트 이후 글 

**DELETE** 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.042.jpeg)

삭제 요청 전송 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.043.png)

해당 글 삭제 

현재 글을 작성한 유저와 현재 유저가 다를 경우 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.044.png)

해당 안내문이 뜨고 글은 삭제되지 않습니다. 

5\.  해당 유저의 작성 글 출력 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.045.png)

해당유저의 JWT Token을 받아 boardRepository를 통해 유저가 작성한 글을 List에 저장 후 JSON 형식으로 반환합니다. 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.046.jpeg)

Comment Controller 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.047.jpeg)

Comment 서비스 기능 

1. 게시글 별로 댓글 출력 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.048.png)

링크를 통해 게시글 파라미터 id 를 받아와 해당 게시글의 댓글들을 List 에 저장후 JSON 형식으로 반환해줍니다. 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.049.jpeg)

1번 게시글의 저장된 댓글 목록을 출력한 상태입니다. 

2. 현재 유저가 작성한 댓글인지 확인 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.050.png)

JWT Token 을 통해 유저 정보를 받아 해당 유저가 작성한 댓글일 경우 댓글을 삭제할 수 있습니다. 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.051.jpeg)

해당 유저 작성 글인지 확인 요구 결과 맞을 경우 true를 반환해주었습니다. 

3. 내가 작성한 댓글 목록 출력 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.052.png)

JWT Token 을 통해 받은 유저의 이름을 commentRepository 를 통해 찾아주고 List 에 저장 후 JSON 형식으로 반환해주었습니다. 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.053.jpeg)

해당 유저의 댓글 목록의 출력을 확인 

4. 댓글 삭제 

해당 유저(painkiller)가 해당 댓글을 작성한 유저일시 삭제할 수 있는 권한이 생깁니다. 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.054.png)

삭제 전 데이터베이스 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.055.png)

삭제 요청 전송 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.056.png)

삭제 이후 데이터베이스 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.057.png)

댓글 단 유저와 일치하지 않는 JWT Token을 넣을 경우 삭제되지 않습니다 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.058.png)

Like Controller 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.059.jpeg)

Like 서비스 기능 

1. 현재 게시물의 좋아요 기능이 내가 누른 좋아요 기능인지 확인 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.060.png)

JWT Token을 통해 발급받은 유저의 Id 값과 해당 게시글의 “좋아요”를 누른 유저의 Id 값과 일치하는지 일치할 경우 해당 “좋아요”를 다시 취소할 수 있습니다. 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.061.jpeg)

일치할 경우 true값 반환 

2. 좋아요 누르기 및 삭제 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.062.jpeg)

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.063.png)

좋아요 누르기 전 현재 좋아요 목록 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.064.png)

좋아요 전송 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.065.png)

누른 후 15번 게시글의 “좋아요” 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.066.png)

좋아요 삭제 전송 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.067.png)

삭제 후 좋아요 DB 테이블 

3. 내가 “좋아요”를 누른 게시글 id 출력 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.068.png)

JWT Token을 통해 얻은 현재유저의 id를 통해 현재 유저가 “좋아요”를 누른 게시글 을 LikeRepository를 이용해서 찾을 수 있습니다. 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.069.jpeg)

내가 “좋아요”를 누른 게시글 id를 JSON 형식으로 반환 

**Database Cache** 

**@Cacheable**: 스프링에서 제공하는 캐싱 기능을 활용하여 메서드의 결과를 캐시에 저장하고, 이 후 동일한 메서드 호출에 대한 결과를 캐시에 반환하는데 사용되는 어노테이션입니다 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.070.png). 

이러한 캐시를 사용함으로써 얻는 효과 

1. @Cacheable  어노테이션을  사용하면  메서드의  결과를  캐시에  저장합니다.  동일한 메서드가  반복적으로  호출될  때  매번  데이터베이스  조회를  수행하지  않고,  이전에 저장한 캐시에서 결과를 바로 반환합니다.  
1. 캐시를 사용함으로써 데이터의 무결성을 보다 쉽게 유지할 수 있습니다. 

2\. 

**스프링부트 보안 설정** 

**스프링 시큐리티 설정** 

**Spring Security**: 스프링 시큐리티는 인증(Authentication), 권한(Authorize) 등을 부여 및 보호 

기능을 제공하는 프레임워크입니다. 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.071.png)

build gradle를 통해 스프링 시큐리티 프레임워크를 가져옵니다. 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.072.png)

이와 같이 가져오고 [http://localhost:8080/login ](http://localhost:8080/login)화면이 출력되면 시큐리티가 작동하는 것을 확인 

**스프링 시큐리티 필터 클래스** 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.073.jpeg)

**@EnableWebSecurity**: 해당 클래스를 스프링 시큐리티의 기본 시큐리티 체인으로 만들어줍니다. 이 서버로 접근 시 이 필터를 통과해야 데이터에 접근할 수 있습니다. 

**Csrf().disable()**: Spring Security의 Cross-Site Request Forgery (CSRF) 공격 방어 기능을 비활성화하 는 설정입니다. 

CSRF 공격은 사용자가 의도하지 않았지만 공격자의 의도에 따라 공격 대상 서버를 공격하게 되는 공격  유형 중  하나입니다.  주로  쿠키  기반의  인증 방식을  사용할  때  발생할  수  있으며, Spring Security는 기본적으로 이러한 CSRF 방어 기능을 활성화하고 있습니다. 

그러나 REST API와 같이 세션과 쿠키를 사용하지 않는 경우에는 CSRF 방어를 사용할 필요가 없을 수 있습니다. 따라서 이 설정을 사용하여 CSRF 방어를 비활성화하면 REST API와 같은 상황에서 불 필요한 CSRF 토큰을 요구하지 않게 됩니다. 

**필터 1단계: Corsfilter** 

**corsFilter  설정** 

CORS Policy: 출처가 서로 다른 자원들을 공유하 자원들을 공유한다는 뜻으로 브라우저가 리소스 ![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.074.png)로드를 허용해야 하는 자체 출처 이외의 모든 출처  (도메인, 스키마 또는 포트)를 서버가 표시할 수 있도록 하는 정책. 

스프링부트를 통해서 프런트엔드 서버와의 통신을 자동으로 설정할 경우 충돌이 일어나 통신의 장애가 생겨서 통신을 제대로 할 수가 없습니다. 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.075.jpeg)

이러한 통신을 원활하게 하기 위해 직접 CORS Filter 클래스를 작성해주었습니다. 

**Config.addAllowedOriginPattern(‘http://localhost:3000’)**: 로컬에서 사용되고 있는 프런트  서버 (**http://localhost:3000’**)에 대한 응답만 허용입니다. (특정 프런트 ip 도메인만 허용하는 이유는 다 른 ip가 민감한 데이터에 접근해 탈취당할 수 있기 때문에 보안 문제가 발생한다.) 

**Config.addAllowedHeader(“\*”)**: 모든 header에 응답을 허용(현재 테스트 과정이기 때문에 모든 ip에 대한 응답을 허용하였다.) 

**Config.addAllowedMethod (“\*”)**: 모든 post, get, put, delete, patch 요청을 허용(현재 테스트 과정 이기 때문에 모든 IP에 대한 응답을 허용하였다.) 

**Config,addExposedHeader(JWTProperties.HEADER\_STRING**):  브라우저에서  JavaScript로  작성된 코드가  JWT를 읽을 수 있게 됩니다. ![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.076.png)

**필터 2단계: JWTAutheticationFilter** 

[http://localhost:8080/login ](http://localhost:8080/login)을 통해 로그인시 UsernamePasswordAuthenticationFilter 인터페이스의 attempAuthentication함수와 successfulAuthentication 함수가 실행 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.077.jpeg)

해당 attemptAuthentication 인터페이스의 함수를 @Override를 통해 재정의  

Post를 통해 받은 username과 password를 통해 authenticationToken 객체에 인자값으로 전달 이러한 객체를 authenticationManager에 전달할경우 principalDetailsService의 함수가 실행됨  

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.078.png)

principalDetailsService 실행시 userRepository를 통해 해당 유저가 존재여부를 확인. 존재가 성공 적으로 확인 시 successfulAuthentication함수에 authentication 객체를 전달 만약 유저가 존재 안 할 경우 전달을 하지 않습니다 

**JWT TOKEN  생성** 

JWT:JWT는 유저를 인증하고 식별하기 위한 토큰(Token) 기반 인증이다. 토큰 자체에 사용자의 권 한 정보나 서비스를 사용하기 위한 정보가 포함됩니다. 

successfulAuthentication에 authentication의 객체 전달이 성공하면 JWTToken을 생성합니다.  

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.079.png)

authentication 객체를 PrincipalDetails 객체로 형변환 해줍니다. 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.080.jpeg)

해당 객체를 형변환 할 경우 로그인 한 유저의 id와 username값을 토큰에 넣어줍니다.  

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.081.png)

SECRET: JWT토큰은 서명을 생성하고 검증하는데 사용되는 시크릿 키 

Bearer: HTTP 요청 헤더에 JWT를 전달하고 서버는 이를 사용하여 클라이언트의 인증을 수행합니 다. 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.082.png)

로그인 정보 Username과 password[ http://localhost:8080/login ](http://localhost:8080/login)으로 전달 시  

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.083.png)

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.084.png)

JWT Token 값이 생성되는 것을 확인할 수 있습니다. 

**필터 3단계: JWTAuthorizationFilter** 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.085.png)

JWT 토큰으로 서비스 시스템 인증 시 해당 필터가 실행된다. (게시글, 댓글, 좋아요 등록 수정 삭 제 기능을 위해 필요) 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.086.png)

JWTToken은 헤더의 Authorization을 통해 백엔드 서버로 전달할 수가 있다. 전송받은 JWT Token 값을 시크릿키를 활용해 username을 JWT Token으로부터 추출합니다. 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.087.png)

해당 username이 null 값이 아닐 시 해당 유저를 userRepository에서 찾은 후 authentication 객 체에 값을 전달 후 해당 유저의 존재여부 및 권한 등을 시큐리티의 세션에 접근하여 저장한다. 

**OAuth2.0** 

OAuth2.0:OAuth 2.0(Open Authorization 2.0, OAuth2)란 표준 인증 프로토콜이다. 다른 웹사이트 에서 제공하는  API를 이용하여 웹사이트나 애플리케이션의 접근 권한을 부여 할 수 있는 공통적 인 수단으로써 사용됩니다. 

구글 클라우드에서  OAuth2.0 api를 가져온다. 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.088.jpeg)

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.089.jpeg)

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.090.png)

Build gradle을 통해 OAuth 라이브러리를 가져온다. 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.091.png)

구글 OAuth api 로부터 받은 클라이언트 id와 클라이언트 시크릿키를 application.properties에 정 확히 적어줍니다.  

Redirect-uri: OAuth를 통해 로그인 후 스프링부트 로그인 컨트롤러로 이동할수 있게 하는 url 을 나타냅니다. 

OAuth 로그인 서비스 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.092.jpeg)

로그인  후  구글로부터  AccessToken을  발급받는  기능이다.  OAuth로그인을  성공할  경우 ApplicationProperties에 적은 url에 접근할 수 있습니다.  

해당 url에 접근 후 user에 email id 닉넴임등 각종 개인정보를 가져올수 있습니다. 해당 정보로 회원가입이나 로그인이 가능하게 합니다. 

OAuth 회원가입 및 로그인 Controller 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.093.jpeg)

OAuth 로그인 후 [http://localhost:8080/check/oauth2/code/google ](http://localhost:8080/check/oauth2/code/google)로 리디렉션 됩니다. 

리디렉션 이후 만약 유저가 DB테이블에 존재 하지 않을 경우 유저의 정보를 받아 회원정보에 등 록해줍니다. 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.094.png)

비밀번호는 의미 없는 비밀번호를 직접 설정해 입력해주었습니다. (1차적으로 구글 OAuth를 통해 로그인 하기 때문에 따로 비밀번호가 필요 없지만 null값이 들어가면 안되므로 사용하였다.) 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.095.jpeg)

로그인 이후 username과 password을 authentication 객체의 인자로 전달합니다.  

이후 authentication 객체를 PrincipalDetails로 형변환 한 후 해당 객체를 이용해 JWT Token을 발 급합니다. 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.096.png)

로그인 전 유저 DB 테이블 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.097.png)

구글 OAuth 로그인을 실행합니다. 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.098.png)

로그인 이후 생겨난 새로운 유저 DB테이블 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.099.png)

구글로부터 받은 Access Token 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.100.png)

Access Token으로부터 유저 정보를 가져온다. 

해당 username과 가비지 password를 가져와서 로그인을 시도한다. 

![](Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.101.png)

로그인 이후 생겨난 백엔드에서 직접 만든 JWT Token 

코드: https://github.com/ldeadlysinx/RegisterCRUDBackend/tree/master 

[ref1]: Aspose.Words.30982a8b-42ba-4de1-b411-94865d62f75e.038.png
