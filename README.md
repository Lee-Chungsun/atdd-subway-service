<p align="center">
    <img width="200px;" src="https://raw.githubusercontent.com/woowacourse/atdd-subway-admin-frontend/master/images/main_logo.png"/>
</p>
<p align="center">
  <img alt="npm" src="https://img.shields.io/badge/npm-%3E%3D%205.5.0-blue">
  <img alt="node" src="https://img.shields.io/badge/node-%3E%3D%209.3.0-blue">
  <a href="https://edu.nextstep.camp/c/R89PYi5H" alt="nextstep atdd">
    <img alt="Website" src="https://img.shields.io/website?url=https%3A%2F%2Fedu.nextstep.camp%2Fc%2FR89PYi5H">
  </a>
  <img alt="GitHub" src="https://img.shields.io/github/license/next-step/atdd-subway-service">
</p>

<br>

# 지하철 노선도 미션
[ATDD 강의](https://edu.nextstep.camp/c/R89PYi5H) 실습을 위한 지하철 노선도 애플리케이션

<br>

## 🚀 Getting Started

### Install
#### npm 설치
```
cd frontend
npm install
```
> `frontend` 디렉토리에서 수행해야 합니다.

### Usage
#### webpack server 구동
```
npm run dev
```
#### application 구동
```
./gradlew bootRun
```
<br>

## ✏️ Code Review Process
[텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

<br>

## 🐞 Bug Report

버그를 발견한다면, [Issues](https://github.com/next-step/atdd-subway-service/issues) 에 등록해주세요 :)

<br>

## 📝 License

This project is [MIT](https://github.com/next-step/atdd-subway-service/blob/master/LICENSE.md) licensed.

---
<details>
<summary style="font-Weight:bold; font-size:25px;"> 1단계 - 1단계 - 인수 테스트 기반 리팩터링 </summary>

### 요구사항
* LineSectionAcceptanceTest 리팩터링
* LineService 리팩터링

### 인수 테스트 기반 리팩터링
LineService의 비즈니스 로직을 도메인으로 옮기기  
한번에 많은 부분을 고치려 하지 말고 나눠서 부분부분 리팩터링하기  
전체 기능은 인수 테스트로 보호한 뒤 세부 기능을 TDD로 리팩터링하기
1. Domain으로 옮길 로직을 찾기
   스프링 빈을 사용하는 객체와 의존하는 로직을 제외하고는 도메인으로 옮길 예정
   객체지향 생활체조를 참고
2. Domain의 단위 테스트를 작성하기
   서비스 레이어에서 옮겨 올 로직의 기능을 테스트
   SectionsTest나 LineTest 클래스가 생성될 수 있음
3. 로직을 옮기기
   기존 로직을 지우지 말고 새로운 로직을 만들어 수행
   정상 동작 확인 후 기존 로직 제거

### 구현 리스트
*  시나리오, 흐름을 검증할 수 있도록 테스트 코드 리펙터링
* service의 busniess 로직을 domain으로 옮기기
    * domain 으로 옮기면서 각 domain의 TDD 구현해보기

* LineService 도메인이 할 일 옮기기
    - [ ] addLineStationNew
        - [ ] 리팩토링 후 commit
        - [ ] 레거시 코드 제거
    - [ ] exception Runtime 보다는 어떤 예외 인지 직관적으로 보여주기
    - [ ] removeLineStationNew
        - [ ] 리팩토 링 후 commit
        - [ ] 레거시 코드 제거
    - [ ] getStation Refactoring
    - [ ] Distance 원시값 포장
    - [ ] List<Section>을 상태로 갖는 Sections 일급 컬렉션 구현
    - [ ] Sections이 할 일들을 Line에서 하고 있기 때문에 메서드 이전
</details>

---
<details>
<summary style="font-weight: bold; font-size: 25px;">2단계 - 경로 조회 기능</summary>

### 요구사항
* 최단 경로 조회 인수 테스트 만들기
* 최단 경로 조회 기능 구현하기

### 구현 리스트
* jgrapt를 이용하여 최단 경로 찾기 인수 테스트
    -[ ] PathAcceptanceSevice 구현하여 인수테스트
        -[ ] 출발역과 도착역이 같은 경우 예외발생
        -[ ] 출발역과 도착역이 연결이 되어 있지 않은 경우 예외발생
        -[ ] 존재하지 않은 출발역이나 도착역을 조회 할 경우 예외발생
        -[ ] 예외처리 후 최단 경로 찾기

* 구현 순서 Outside - in 방식으로 구현
    * controller 생성
    * service 구현 시 mock과 dto를 이용하여 인수테스트 및 기능 구현
    * ___외부라이브러리는 가급적 실제 객체를 이용___
    
</details>

---
<details>
<summary style="font-wight: bold; font-size:25px;">3단계 - 인증을 통한 기능 구현</summary>

### 구현 리스트
- [x] 토큰 발급 기능 (로그인) 인수 테스트 만들기
- [x] 인증 - 내 정보 조회 기능 완성하기
- [ ] 인증 - 즐겨 찾기 기능 완성하기


#### 토큰 발급 테스트
* 토큰 발급(로그인)을 검증하는 인수 테스트 만들기

* ``AuthAcceptanceTest`` 인수 테스트 만들기

인수 조건


```html
Feature: 로그인 기능

    Scenario: 로그인을 시도한다.
    Given 회원 등록되어 있음
    When 로그인 요청
    Then 로그인 됨
```

* 이메일과 패스워드를 이용하여 요청 시 access token을 응답하는 기능을 구현하기
* ``AuthAcceptanceTest``을 만족하도록 구현하면 됨
* ``AuthAcceptanceTest``에서 제시하는 예외 케이스도 함께 고려하여 구현하기
* Bearer Auth 유효하지 않은 토큰 인수 테스트
    * 유효하지 않은 토큰으로 /members/me 요청을 보낼 경우에 대한 예외 처리

#### 즐겨 찾기 기능 완성하기

```
Feature: 즐겨찾기를 관리한다.

  Background 
    Given 지하철역 등록되어 있음
    And 지하철 노선 등록되어 있음
    And 지하철 노선에 지하철역 등록되어 있음
    And 회원 등록되어 있음
    And 로그인 되어있음

  Scenario: 즐겨찾기를 관리
    When 즐겨찾기 생성을 요청
    Then 즐겨찾기 생성됨
    When 즐겨찾기 목록 조회 요청
    Then 즐겨찾기 목록 조회됨
    When 즐겨찾기 삭제 요청
    Then 즐겨찾기 삭제됨
```

### 구현 목록
- [x] 즐겨찾기 기능 구현을 위한 인수 테스트 작성
- [x] service 도메인 단위 테스트 코드
- [x] 도메인 로직 구현 

</details>

---

## 4단계 - 요금 조회

### 요구사항
- [x] 경로 조회 시 거리 기준 요금 정보 포함하기 
- [x] 노선별 추가 요금 정책 추가
- [x] 연령별 할인 정책 추가

### 거리별 요금 정책
* 기본운임(10㎞ 이내) : 기본운임 1,250원
* 이용 거리초과 시 추가운임 부과
    * 10km초과∼50km까지(5km마다 100원)
    * 50km초과 시 (8km마다 100원)
    
> 참고 링크: http://www.seoulmetro.co.kr/kr/page.do?menuIdx=354

```
Feature: 지하철 경로 검색

  Scenario: 두 역의 최단 거리 경로를 조회
    Given 지하철역이 등록되어있음
    And 지하철 노선이 등록되어있음
    And 지하철 노선에 지하철역이 등록되어있음
    When 출발역에서 도착역까지의 최단 거리 경로 조회를 요청
    Then 최단 거리 경로를 응답
    And 총 거리도 함께 응답함
    And ** 지하철 이용 요금도 함께 응답함 **
```

#### 구현 리스트
- [x] 요금이 포함되어 있는 지하철 경로 검색 인수테스트 코드 작성
    - [x] PathServiceTest Mock Test 추가
    - [x] Path domain 수정
    - [x] Path response 수정

- [x] 노선별 추가 요금 정책 추가
    - [x] Line에 추가 요금 필드 추가
    - [x] 추가 요금이 있는 노선을 이용 할 경우 측정된 요금에 추가
    - [x] 경로 중 추가요금이 있는 노선을 환승 하여 이용 할 경우 가장 높은 금액의 추가 요금만 적용
    
- [x] 로그인 사용자별 요금 정책 추가
    - [x] 유아용, 청소년용, 비회원(일반) 요금 관리를 위한 class 추가
    - [x] 각 member 정책 enum 추가
    - [x] 로그인 없이 조회 시 일반 사용자로 조회
        - [x] 내 정보 조회 시에 로그인이 필요하다는 exception thow
    - [x] 요금 정책 적용
