# 자동차 경주

> 초간단 자동차 경주 게임입니다.
> 여러 대의 자동차가 경주를 하여 우승자를 가립니다.

# 기능 목록

## 초기 설정
### 자동차 이름 입력 및 검증
- [x] 사용자로부터 자동차 이름 입력 요청
- [x] 쉼표로 구분된 여러 이름이 올바르게 입력되었는지 확인
- [x] 입력 받은 자동차 이름 저장
- [x] 유효하지 않은 입력 시 `IllegalArgumentException` 발생

### 경주 횟수 입력 및 검증
- [x] 사용자로부터 경주 횟수 입력 요청
- [x] 입력된 경주 횟수가 정수인지 확인
- [x] 입력 받은 경주 횟수로 저장
- [x] 유효하지 않은 입력 시 `IllegalArgumentException` 발생

## 게임 진행
### 자동차 객체 생성 및 초기화
- [x] 입력받은 이름을 기반으로 각 자동차 객체 생성
  - [x] 입력된 이름이 5자 이하인지 확인
- [x] 각 자동차의 초기 위치 설정

### 경주 진행
- [x] 입력된 경주 횟수를 기반으로 경주 객체 생성
- [x] 지정된 횟수만큼 경주 반복
- [x] 각 반복마다 모든 자동차에 대해 전진 여부 결정
- [x] 0에서 9 사이의 무작위 값 생성
- [x] 생성된 값이 4 이상인 경우 해당 자동차 전진
- [x] 각 자동차의 현재 위치를 출력 형식에 맞게 표시
- [x] 각 경주 회차가 끝날 때마다 결과 출력

## 결과 처리
### 우승자 결정 및 출력
- [x] 가장 멀리 이동한 자동차(들) 찾기
- [x] 우승자(들)의 이름 결정
- [x] 단독 우승자의 경우, 해당 우승자 이름 출력
- [x] 공동 우승자의 경우, 쉼표로 구분하여 출력


## 클래스 설계

```text
├── Application.java
├── controller
│   └── RaceController.java
├── domain
│   ├── Car.java
│   ├── Race.java
│   ├── AttemptResult.java
│   └── GameResult.java
├── view
│   ├── InputView.java
│   └── OutputView.java
├── exception
│   ├── ErrorMessage.java
│   └── InvalidInputException.java
└── util
    ├── NumberGenerator.java
    └── RandomNumberGenerator.java
```

1. Domain
   - Car: 자동차 객체, 이름과 위치 정보를 가짐.
   - Race: 자동차 경주를 진행하는 클래스, 자동차들과 경주 회수를 관리.
   - AttemptResult: 한 번의 경주 결과를 나타내는 클래스.
   - GameResult: 게임 결과를 나타내는 클래스.
2. View
   - InputView: 사용자 입력을 받는 클래스.
   - OutputView: 결과를 출력하는 클래스.
3. Controller
   - RaceController: 게임의 전반적인 진행을 관리.
4. Exception
   - ErrorMessage: 에러 메세지 열거형
   - InvalidInputException: 사용자 입력이 잘못되었을 때 발생하는 예외.
5. Utility
   - NumberGenerator: 값 생성 인터페이스
   - RandomNumberGenerator: 무작위 값을 생성하는 유틸리티 클래스.

## 리팩터링
- [x] 테스트 코드 추가 및 개선
- [x] 일급 컬렉션으로 자동차 다룸
- [x] 원시값 포장 : 단순히 길이 검증과 숫자 증가 기능만 있는 원시값 포장은 오버엔지니어링이라는 판단 하에 진행하지 않음 
- [x] 객체 역할 정리 (결과 객체) : View 에서 출력값 만드는게 맞다고 판단 하에 그냥 놔둠
