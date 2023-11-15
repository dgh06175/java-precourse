# 크리스마스 프로모션

> 우테코 식당의 크리스마스 프로모션을 관리하는 프로그램입니다.
> 사용자에게 날짜와 메뉴를 입력받아 이벤트에서 어떤 혜택을 받을 수 있는지 알려줍니다.

# 기능 목록

### 입력

- [x] 방문할 날짜 입력
  - [x] 숫자가 아닐경우 예외 발생, 재입력
- [x] 주문할 메뉴와 개수 입력
  - [x] 빈 문자열일 경우 예외 발생, 재입력

### 유틸

- [x] 입력값 파싱 : InputParser
  - [x] 날짜 파싱
  - [x] 자연수가 아닐 경우 "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요." 출력하고 재입력
  - [x] 메뉴 파싱
  - [x] 메뉴 형식이 예시와 다를 경우 "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요." 출력하고 재입력

### 컨트롤러

- [x] 로직 호출 및 메세지 전달 도움 : EventController
- [x] 재입력 로직 메서드, 출력 메서드 모음 : EventFacade

### 도메인

- [x] 메뉴 카테고리 열거형 : MenuCategory
- [x] 메뉴 열거형 : Menu
  - 이름, 금액
- [x] 이벤트 추상클래스 : AbstractEvent
  - 공통 로직: 이벤트의 할인금액 반환, 이름 반환, 만원 이상인지 검사, 날짜가 이벤트 기간인지 검사
  - 각자 하위 클래스에서 가져야할 함수 : 할인 계산 함수
  - [x] 크리스마스 디데이 할인 : 크리스마스 다가올수록 할인금 증가
  - [x] 평일 할인 : 평일에 디저트 개당 2023원 할인
  - [x] 주말 할인 : 주말에 메인메뉴 개당 2023원 할인
  - [x] 특별 할인 : 총주문 금액에서 천원 할인
  - [x] 증정 이벤트 : 12만원 이상일 때 할인
- [x] 배지 열거형 : Badge
  - 금액으로 알맞은 배지 반환
- [x] 문자열 Enum 으로 변환 : MenuService
- [x] 주문한 메뉴 객체 : OrderedMenu
  - 저장된 메뉴의 총액 계산 기능
  - 메뉴 유효성 보증 책임
    - 예외 발생시 "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요." 출력하고 재입력
    - [x] 메뉴판에 없는 메뉴를 입력할 경우
    - [x] 메뉴 개수 1 이상의 숫자가 아닐 경우
    - [x] 중복 메뉴 입력할 경우
    - [x] 음료만 주문 불가능
    - [x] 메뉴 최대 20개까지 가능
- [x] 적용된 이벤트 객체 : AppliedEvents
  - 정적 팩터리 메서드
  - 어떤 Event의 할인이 얼마나 적용되었는지 한번에 저장하는 객체
  - 적용된 이벤트의 총액 계산 책임
  - 총액이 10000 이상일 때만 이벤트 적용

### 출력

- [x] 주문 메뉴 출력
- [x] 할인 전 총주문 금액 출력
- [x] 증정 메뉴 출력
- [x] 혜택 내역 출력
  - 모든 적용 이벤트 출력
- [x] 총혜택 금액 출력
- [x] 할인 후 예상 결제 금액 출력
- [x] 12월 이벤트 베지 출력

## 파일 구조
```text
├── Application.java
├── controller
│   ├── EventController.java
│   └── EventFacade.java
├── domain
│   ├── AppliedEvents.java : 적용된 이벤트
│   ├── MenuService.java
│   ├── OrderedMenu.java : 주문한 메뉴
│   ├── dto
│   │   ├── EventDiscountPrice.java
│   │   ├── MenuQuantity.java
│   │   └── StringIntPair.java
│   ├── enums
│   │   ├── Badge.java
│   │   ├── Menu.java
│   │   └── MenuCategory.java
│   └── events
│       ├── ChristmasDDayEvent.java
│       ├── Constant.java
│       ├── Event.java
│       ├── EventFactory.java
│       ├── GiveAwayEvent.java
│       ├── SpecialEvent.java
│       ├── WeekDayEvent.java
│       └── WeekEndEvent.java
├── exception
│   ├── DateException.java
│   ├── EventException.java
│   └── OrderException.java
├── util
│   └── InputParser.java
└── view
    ├── InputView.java
    └── OutputView.java
```

## 학습 목표

> 클래스(객체)를 분리하는 연습

1. 클래스의 역할과 책임을 생각해 보고
2. 도메인 로직에 집중하는 방향으로 구현
3. UI는 도메인 로직과 분리
   - View 에서 사용할 데이터는 getter로 데이터 전달
   - 연관성이 있는 상수는 static final 대신 enum 활용
   - 불면 값은 final 로 변경 막기
   - 데이터를 꺼내지 말고, 메세지를 던지도록 구조를 바꿔서 데이터를 가지는 객체가 일하도록 [참고 게시글](https://tecoble.techcourse.co.kr/post/2020-04-28-ask-instead-of-getter/)
   - 필드 (인스턴스 변수)가 많으면 복잡도가 높아 버그 발생 가능성이 높아지므로 줄이기 위해 노력하기.
   - 예외 상황 잘 찾아서 테스트 하기
   - 메소드 분리는 가독성 상승에 좋다. 성능이 떨어져도 된다

## 리팩토링

- [x] 정적 팩토리 메서드 써보기
- [x] 객체의 역할 다시 생각 해보기
  - [x] Event Enum -> 클래스 인터페이스 추상 클래스, 구현체로 바꾸기
  - [x] 인터페이스 삭제하고 추상 클래스로 통합하기
- [x] 객체가 데이터를 꺼내는 것이 아닌 메세지를 서로 주고 받고 있는지 체크
- [x] 테스트 코드 개선하기 (예외 상황 잘 찾아서)
- [x] 문자열 래핑
- [x] 12월 아니여도 적용 가능하게 확장성 개선
- [x] 메뉴판 출력
- [x] Facade 패턴 적용
- [x] Map 대신 record DTO 사용
