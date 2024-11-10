package store.exception;

public enum StoreError {
    STOCK_NAME_EMPTY("상품 이름은 빈 문자열일 수 없습니다."),
    STOCK_PRICE_INVALID("상품 가격은 0 이상이어야 합니다."),
    STOCK_QUANTITY_INVALID("상품 수량은 0 이상이어야 합니다."),
    STOCK_PROMOTION_EMPTY("상품 할인 정보는 빈 문자열일 수 없습니다."),
    PROMOTION_NAME_EMPTY("프로모션 이름은 빈 문자열일 수 없습니다."),
    PROMOTION_BUY_NEGATIVE("프로모션 구매 조건은 0 이상이어야 합니다."),
    PROMOTION_GET_NEGATIVE("프로모션 증정 조건은 0 이상이어야 합니다."),
    PROMOTION_START_DATE_NULL("프로모션 시작 날짜는 null일 수 없습니다."),
    PROMOTION_END_DATE_NULL("프로모션 종료 날짜는 null일 수 없습니다."),
    PROMOTION_START_DATE_AFTER_END_DATE("프로모션 시작 날짜가 종료 날짜보다 늦을 수 없습니다."),
    ;

    public final String message;

    StoreError(String message) {
        this.message = message;
    }
}
