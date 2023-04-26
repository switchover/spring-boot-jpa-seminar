# Spring Boot JPA Best Practices

## 예제 목록

### Entity 및 Associations
- 단방향 @OneToMany 연관관계 : UnidirectionalOneToMany
- 단방향 @ManyToOne 연관관계 : UnidirectionalManyToOne
- 양방향 @OneToMany 연관관계 : BidirectionalOneToMany
- @ManyToMany 연관관계 : BidirectionalManyToMany, ListVsSet
- @OneToMany 효율적 구성 : HelperMethod

### Fetch 및 Query
- N + 1 문제 : NPlus1Problem
- Direct Fetching : DirectFetching
- Read-Only 모드 : ReadOnlyQueries
- 지연 로딩 vs 즉시 로딩 : PopulatingChildViaProxy
- 속성 지연 로딩 : AttributeLazyLoadingBasic
- Open Session In View(OSIV) anti-pattern : JacksonHibernate5Module
- SELECT DISTINCT 최적화 : HintPassDistinctThrough

### Identifier
- Generator : AutoGeneratorType
- Hi/Lo 및 Pooled (-lo) algorithm 최적화 : HiLo, Pooled, PooledLo
- Composite Key : CompositeKeyEmbeddable, CompositeKeyIdClass

## 참고사항
- 예제 실행을 위해서는 MySQL 및 PostgreSQL 필요 (계정 정보 등은 각 예제 `application.properties` 파일 참조)
- 각 예제 `MainApplication` 상에 정리된 예제 실행에 대한 내용 참조
- DDL 자동 생성(`spring.jpa.hibernate.ddl-auto=create`)의 경우 `WARN`으로 `CommandAcceptanceException` 등의 예외 로그가 기록되나, 내부적으로 발생되는 자연스러운 로그로 무시하면 됨
- 일부 예제의 경우, 기존에 생성된 테이블(`author`, `book` 등) 삭제 후 실행 필요
  - 사용된 테이블 스키마가 달라 발생되는 문제로, Exception 확인 후 처리
