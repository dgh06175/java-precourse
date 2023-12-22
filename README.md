# java_precourse_6
우아한테크코스 프리코스 6기 참여당시 작성한 자바 코드 저장소입니다.

## 1주차 숫자 야구

https://github.com/woowacourse-precourse/java-baseball-6/pull/398

<details>
  <summary>피드백</summary>
  
  - 다음 프로젝트를 할 때에는 처음부터 domain, controller, view를 분리하기
  - 예외 처리 할때 클래스 상속받아서 하기
    
    ```jsx
    package lotto.exception;
    
    public class InvalidLottoInputFormatException extends IllegalArgumentException {
        private static final String ERROR_MESSAGE = "[ERROR] 당첨 번호의 입력 형식이 맞지 않습니다.";
    
        public InvalidLottoInputFormatException() {
            super(ERROR_MESSAGE);
        }
    }
    ```
    
  - 정규표현식은 유지보수가 힘들수 있으니 숫자 검사 등 간단하게 할 수 있는 것은 코드 활용하자.
  - 성능이 조금 떨어지더라도 메소드의 역할을 명확히 구분하는것이 더 좋다.
    
</details>

## 2주차 자동차 경주

https://github.com/woowacourse-precourse/java-racingcar-6/pull/8

<details>
  <summary>피드백</summary>
  
### 개발 이외

1. 과제 요구사항이나 적용할 법칙들 정리하고 시작해도 좋다.
2. 커밋 메세지에 scope 추가해보자.
3. 단어 일관성 유지 해보자. ex) Game이라는 단어 한번 썼으면 Race 쓰지말고 쭉 increase 쓰기

### 테스트 관련

1. 테스트 코드 작성시 @Nested 어노테이션으로 테스트 클래스에 계층 구조를 만들수 있다.
2. 테스트 코드에서만 사용되는 객체는 tests/utils 에 작성하자.
3. 테스트 코드에서 CsvSource, ValueSource, MethodSource, ArgumentsSource 등 써보기
4. ParameterizedTest로 중복 코드 제거 가능 [Guide to JUnit 5 Parameterized Tests | Baeldung](https://www.baeldung.com/parameterized-tests-junit-5)
5. 테스트는 작은 단위부터 만들고 빠르게 피드백을 받는것이 중요하다.
6. 우테코에서 만들어준 메소드 사용 https://github.com/ChoiWonYu/java-racingcar-6/blob/ChoiWonYu/src/test/java/model/CompareNumberRefereeTest.java

### 예외 관련

1. 에러메세지는 enum에 적절하다.
https://github.com/woowacourse-precourse/java-racingcar-6/pull/211/files 참고!
2. 테스트 할떄 에러 메세지를 확인하자. (hasMessage)

### 객체 설계 관련

1. 한 메소드가 진짜 한가지 일만 하고있는지 다시 생각해보기
2. 원시값 포장했는데 getPosition밖에 안하면 그냥 원시값으로 놔두는게 나을수도 있다.
3. 문자열 같은거 다국어 지원까지 생각하면 별도로 관리하는게 맞음. 물론 간단한 경우에는 그냥 View 에서 관리.
4. 게임 진행에 대한 책임은 컨트롤러가 아닌 모델이다. 컨트롤러는 입력값 전달과 메세지 전송, 결과값 수신만 하면 됨
5. 랜덤값 같은 테스트 어려운 것은, 인터페이스를 만들어서 구현체를 전달하는 방식으로 의존성 주입을 하면 테스트가 편해진다.
6. 사용자 입력에 대한 기본적인 검증(문자열, 정수, 자연수 등)과 정제는 Util 패키지를 만들어서 거기에 작성하고, readLine 메소드를 Util에서 불러와서 사용해도 좋다.
7. boolean 메서드와 validate 메서드는 구분하라. 여러가지 비교를 하는 로직을 메소드로 따로 분리해서 그것을 validate 메소드의 if문에서 호출해라.
8. 객체 생성 과정에서 유효성 검사를 하고, 변환은 dto를 이용하면 된다

### 프로그래밍 관련

1. static 메서드는 프로그램 종료될 때 까지 메모리가 해제되지 않아서 비효율적이다. 전역에서 접근 가능하니 객체지향에도 방해됨. 꼭 필요한 경우에만 일반 함수로 만들기
2. 컬렉션은 안전성(불변성 보장)과 역할분리를 위해 일급 컬렉션으로 사용.
3. 스트림은 컬렉션이 매우 클 때 아니면 for문 보다 성능이 떨어지지만 가독성 측면에서 장점이 있기 떄문에 많이 써도 된다.
4. HashMap의 원 목적은 Key로 밸류값을 찾는것. 밸류에 아이디를 붙혀줄때 활용
5. 출력문자 포맷 (” : “ 등) 은 ”%s : %s” 이런 느낌으로 포매팅하는게 훨씬 괜찮은듯. 출력문자 문자열에는 \n, : 같은 문자 지양하자.
6. StringUtils.isBlank(” “) → isEmpty는 빈거만 잡지만 Blank는 추가로 공백도 같이 잡아줌
7. 클래스는 상수(또는 클래스 변수), 멤버(인스턴스) 변수, 생성자, 메서드 순으로 작성한다.
8. 정규식을 사용할 땐 String.matches 보다는 Pattern.matcher가 재사용 관점에서 더 좋다.

### 기타

README.md는 소스코드 이전에 어떤 프로젝트인지 소개하는 문서이다.

기능목록은 너무 상세하게(이름,메서드,반환) 적지 말고 차라리 예외적인 상황을 적어라. (계속 업데이트 해야한다)

---
</details>

## 3주차 로또

https://github.com/woowacourse-precourse/java-lotto-6/pull/39

<details>
  <summary>피드백</summary>

### 공통 피드백

- View 에서 사용할 데이터는 getter로 데이터 전달
- 연관성이 있는 상수는 static final 대신 enum 활용
- 불면 값은 final 로 변경 막기
- 데이터를 꺼내지 말고, 메세지를 던지도록 구조를 바꿔서 데이터를 가지는 객체가 일하도록
[참고](https://tecoble.techcourse.co.kr/post/2020-04-28-ask-instead-of-getter/)
- 필드 (인스턴스 변수)가 많으면 복잡도가 높아 버그 발생 가능성이 높아지므로 줄이기 위해 노력하기.
- 예외 상황 잘 찾아서 테스트 하기
- 메소드 분리는 가독성 상승에 좋다. 성능이 떨어져도 된다

### 코드 리뷰

- **객체를 객체답게**
    
    ex) 필요없는 상태 저장 x, **데이터 보다는 메세지 전달**
    
- 변수 이름의 모든 기준은 보는사람이 안헷갈리게
- 리팩토링 할 땐 객체답게 되고있는지 체크
- final 이면 굳이 private으로 접근 막을필요 없다.
- 생성자로 List<> 받을 때 얕은복사 주의
- 도메인에서 뷰로 데이터 보낼때 복잡하다면 DTO 적극 고려해보기

---

</details>

## 4주차 크리스마스 프로모션

https://github.com/dgh06175/java-precourse-6/tree/main/java-christmas-6-dgh06175

<details>
  <summary>피드백</summary>

- 놓친 것들 무조건 있으니 꼼꼼히 살피기 (접근지정자, 필요없는 메소드 등)
- Map 으로 어떤 데이터를 관리하는 것 보단 객체 새로 만드는게 좋음 (가독성, 코드 응집도), Map 은 순회하는데 적절한 컬렉션 타입이 아니다. 리스트로 하기
- Java 는 날짜 관련 API 제공한다.
- 형식이 쓸데없이 복잡할때 var 사용도 고려. ex) for문 안에서
- 인터페이스 추상화 등 잘 생각하고 가독성 좋게 짜기 (쓸데없이 많아도 안좋다)

- 테스트는 개념당 하나.
- 테스트코드도 가독성, 읽는사람 생각해서 작성하기.
- 추상화 되어있는 클래스만 의존성 주입 받기
- 추상클래스 같은거 잘 알고 쓰기
- 리팩토링으로 새로운 객체 만들었을 시 역할이 맞다면 기능 옮기기 고려
- static method만 있으면 생성자 private로 인스턴스 생성 막기
- 연관된 코드끼리 근처에 두기
- 변수 줄이기

---

</details>

# 다시풀기

## 1주차 숫자 야구

https://github.com/dgh06175/java-precourse-6/tree/main/java-baseball-6-study

<details>
  <summary>피드백</summary>

1번 단계에서 패드에 객체 그림 그리면서 진행하기

게임 진행은 컨트롤러의 책임이 아님. 메세지 전달만 하자

정적메서드 클래스는 생성자 접근막기

예외메세지 명확하게 쓰기

---

</details>

## 2주차 자동차 경주

https://github.com/dgh06175/java-precourse-6/tree/main/java-racingcar-6-study

## 3주차 로또

(링크 없음)

## 5기 4주차 다리 건너기

https://github.com/leegwichan/java-bridge-6-prac/pull/2

<details>
  <summary>피드백</summary>

시간이 정말 부족해서 테스트코드는 통과했지만 요구사항 못 지킴 (메소드 라인 제한 등)

람다 형식 안에 {} 안에 2 줄 이상일 경우 메소드 분리해서 람다함수 깔끔하게 사용하기

이 정도 프로그램에서는 검증과정 간단하게 하기.

클래스 분할 너무 하지 말고 기본 요구사항만 지키기.

요구사항에만 충실하도록 하기

README 정말 꼼꼼하게 읽기.

---

</details>

## 5기 5주차 점심 메뉴 추천

https://github.com/leegwichan/java-menu-6-prac/pull/3

<details>
  <summary>피드백</summary>

Map.of 로 맵 만들면 진짜 상수라서 상수 네이밍 가능

Collector.joining 하면 출력 형식 만들 때 좋다.

디폴트 생성자 사용하기. 같은 패키지에서만 쓸꺼면!

기능 목록 작성 30분정도 쓴다.

클래스 설계 까지는 안하고 코딩 좀 하다가 생각을 한다.

기본 요구사항만 딱 지키자. 더 하지도 덜 하지도 말고

README 무조건 꼼꼼히 읽자

---

</details>
