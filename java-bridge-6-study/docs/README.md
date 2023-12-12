# 다리 건너기

위 아래 둘 중 하나의 칸만 건널수 있는 다리를 끝까지 건너가는 게임입니다.

# 기능 목록

### 게임 초기화 및 시작
    - [x] 게임 시작 문구 출력
    - [x] 다리 길이 입력받기
    - [x] 다리 생성

### 사용자 입력 처리
    - [x] 이동할 칸 입력받기 (U/D)
    - [x] 게임 재시작/종료 입력받기 (R/Q)
    - [x] 입력 값 검증 및 예외 처리

### 게임 로직 처리
    - [x] 플레이어의 이동 처리
    - [x] 이동 결과 확인 및 표시
    - [x] 다리 건너기 성공 또는 실패 판단
    - [x] 게임 종료 시 최종 결과 및 시도 횟수 출력

### 예외 상황 처리
    - [x] 올바르지 않은 다리 길이 입력 시 예외 처리
    - [x] 올바르지 않은 이동 입력 시 예외 처리
    - [x] 올바르지 않은 재시작/종료 입력 시 예외 처리


# 클래스 목록

```text
├── Application.java
├── controller
│   └── GameController.java
├── domain
│   ├── Bridge.java
│   ├── BridgeGame.java
│   ├── BridgeMaker.java
│   └── Player.java
├── exception
│   ├── ErrorMessage.java
│   └── InvalidInputException.java
├── util
│   ├── BridgeNumberGenerator.java
│   └── BridgeRandomNumberGenerator.java
└── view
    ├── InputView.java
    └── OutputView.java

```

