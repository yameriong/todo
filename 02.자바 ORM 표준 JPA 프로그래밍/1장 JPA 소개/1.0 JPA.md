# JPA

객체 모델링의 장점을 이용하지 않고 테이블 구조에 객체를 맞춰 데이터 통신하는 Mapper만 만들어서 사용하고 있는 것 이로 인하여
많은 crud를 다시 만들면서 개발에 많은 시간이 들지만

ORM(Object Relational Mapping) 중 표준 중 하나인 JPA로 객체 모델링 관계형 데이터베이스 사이의 차이도 해결 가능함.
1. CRUD의 sql을 작성 할 필요가 없다.
2. 조회결과를 객체 매핑하는 작업도 처리 가능하다.
3. 네이티브 sql 사용으로 직접 작성이 가능하다.
4. DB의 변경시 별도 sql 처리 없이 개발 가능.

JPA를 사용하면서 객체 중심으로 개발하여 생산성, 유지보수가 좋아졌고 테스트 작성하기도 편리해졌습니다.

개발 중 객체의 구조가 변경시 

```{.java}
@Entity(name = "Member")
@Data
class Member{    
    @Id         // 주키 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 숫자 증가
    private Long id;
    private String name;
}

- > 
@Entity(name = "Member")
@Data
class Member{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Team team;
}

@Entity(name = "Team")
@Data
class Team{        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String teamName;
}
```
각 sql 질의문이 쓰여지는 모든곳에 수정하여야 합니다.

데이터 접근 계층을 사용해서 sql을 숨겨도 DAO를 열어서 어떤 sql이 실행되는지 확인해야 하는 점.

비지니스 요구사항을 모델링한 객체를 엔티티라고 하는데 db종속적인 상황에서 엔티티를 신뢰할 수 없다.

DAO에 쿼리문을 열어 어떤 Join으로 나온 결과인지 확인해야 하는데 ***물리적으로는 SQL과 JDBC API데이터 접근 계층에
숨기는 것에 성공했을지 몰라도 논리적으로는 엔티티와 아주 강한 의존관계이다.***

이런 의존관계는 객체에 필드 추가 할 때도 퍼시스턴트레이어를 모두 변경해야 합니다.

SQL에 종속적인 개발자에서 탈피해보자!.

예제

Spring Data를 사용한 도매인 + 레파지토리도 있지만

사용하고 있는 컨트롤러, 서비스, 모델 순으로 합니다.

Repository

```{.java}
// JpaRepository에서 기본적인 crud를 제공한다
// JpaRepository 매개변수로 도메인 클래스의 키타입을 기술한다

public interface MemberRepository extends JpaRepository<Member, Long>{};

서비스 인터페이스

public interface MemberService{
    Member save(Member m);
    void   delete(Long id);
    Member getMember(Long id);
    List<Member> getAllMember();
}

서비스 구현체

@Service
public class MemberServiceImpl Implements MemberService {
    @Autowierd
    MemberRepository memberRepository;

    public Member save(Member m){
        return memberRepository.save(m);
    }

    void   delete(Long id){
        memberRepository.delete(id);
    }

    Member getMember(Long id){
        memberRepository.findOne(id);
    }
    List<Member> getAllMember(){
        memberRepository.findAll();
    }

}
컨트롤러

@RestController
@RequestMapping("/member")
publeic class MemberController{
    @Autowierd
    MemberService memberService;

    @GetMapping(/{id})
    public @ResponseBody Object getMember(@Pathvariable(value="id") Long id) throws Exception {
        return memberService.getMember(id);
}
```

## 패러다임의 불일치

점점 커지는 서비스 애플리케이션의 핵심은 결국 복잡성의 제어입니다.

비지니스의 요구사항을 정의한 도메인 모델돋 객체로 모델링하면 객체지향 언어가 가진 장점을 활용할 수 있다.
문제는 이렇게 정의한 도메인 모델을 저장할 때 발생한다.

Member를 DB를 이용하여 저장시 회원 객체가 팀 객체를 참조하고 있다면 회원 객체를 저장할 때 팀 객체도 함께 저장해야 합니다.
회원 객체만 저장하면 팀객체를 잃어버려 에러가 발생합니다.

많은 방법이 있지만 가장 현실적인 방법은 RDB에 객체를 집어 넣는 것입니다. 
RDB는 데이터 집합적이여서 상속, 추상화, 다형성은 없습니다.

서로 다른 부분을 개발자가 중간에서 해결해 주어야 합니다.

```{.java}
Member member1 = memberDAO.getMember(100);
Member member2 = memberDAO.getMember(100);

member1 == member2; // false
```

같은 데이터베이스 로우에서 조회지만 객체 측면에서는 둘다 다른 인스턴스입니다. getmember할때마다 new member()로 인스턴스 생성된다.

JPA는 같은 트렌젝션일 때 같은 객체가 조회되는 것을 보장한다

```{.java}
Member member1 = jpa.find(Member.class,100);
Member member2 = jpa.find(Member.class,100);

member1 == member2; // true
```
## JPA란 무엇인가?

왜 JPA를 사용해야 한느가?

* 생산성
>> 자바 컬렉션에 객체를 저장 하듯 JPA에게 저장할 객체를 전할다면 된다.
>> 지루하고 반복적인 코드와 CRUD용 SQL을 개발자가 직접 작성하지 않아도 된다.

* 유지보수
>>SQL에 의존적인 개발에서도 엔티티에 필드를 하나만 추가해도 등록, 수정, 조회할 모든 sql를 맵핑 할
>>API를 모두 변경해여야 한다.

* ORM Q&A
1. JPA를 잘 이해하지 못하면 N+1 같은 문제로 인한 심각한 성능 저하 발생가능(sql1번으로 100명조회 하는게 각 회원
마다 주문한 상품을 추가로 조회하기 위해 100번의 추가 sql이 실행하는것)

2. 통계 쿼리처럼 복잡한 SQL은 네이티브 SQL이나 JdbcTemplate 같은 sql 매퍼 형태의 프레임워크 혼용

3. Mybatis, 스프링 JdbcTemplate은 보통 SQL맵퍼라고 합니다.


