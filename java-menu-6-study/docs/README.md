# 점심 메뉴 추천

한 주의 점심 메뉴를 추천해 주는 서비스다.

## 기능 목록

### 서비스 시작 및 종료
- [x] 서비스 시작 문구 출력
- [x] 서비스 종료 문구 출력

### 코치 정보 입력
- [x] 코치의 이름 입력
- [x] 입력 값 검증 및 예외 처리 (IllegalArgumentException)
- [x] 코치별 못 먹는 메뉴 입력
- [x] 입력 값 검증 및 예외 처리 (IllegalArgumentException)

### 메뉴 추천 로직
- [x] 카테고리 무작위 선택
- [x] 코치별 메뉴 추천
- [x] 일주일 동안 같은 카테고리 최대 2회 제한 적용
- [x] 코치별 중복되지 않는 메뉴 추천
- [x] 못 먹는 메뉴 고려

### 결과 출력
- [x] 메뉴 추천 결과 테이블 형태로 출력

### 예외 처리
- [x] IllegalArgumentException 처리
- [x] IllegalStateException 처리
- [x] 사용자 정의 예외 처리 (MenuRecommendationException, ValidationException)
- [x] 에러 메시지 출력 시 "[ERROR]"로 시작

- [ ] ApplicationTest 통과하기

## 클래스 설계

### Model

- [ ] Category : 카테고리 enum

- [ ] MenuDate : 메뉴 저장한 클래스
  - Map<Category, List<String>> 메뉴들 멤버 보유

- [ ] SelectedMenu : 선택된 메뉴
  - Coach, List<String> 멤버 보유

### Service

- [ ] coaches : 코치들 일급 컬렉션
  - List<coach> items 멤버 보유
  - 코치는 최소 2명, 최대 5명까지 식사를 함께 한다.

- [ ] coach : 코치
  - String name , List<String> InvalidMenu 멤버 보유
  - 이름은 최소 2글자, 최대 4글자

- [ ] MenuSelector : 메뉴 고르는 역할
  - Coaches, MenuData, List<Category> 를 받아서 List<SelectedMenu> 반환
  - 랜덤 사용

- [ ] CategorySelector : 카테고리 고르는 역할
  - List<Category> 반환
  - 랜덤 사용

