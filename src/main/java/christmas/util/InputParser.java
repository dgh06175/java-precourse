package christmas.util;

import christmas.domain.dto.StringIntPair;
import christmas.exception.DateException;
import christmas.exception.OrderException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputParser {
    private static final String DIVIDER = ",";
    private static final String MENU_COUNT_DIVIDER = "-";
    private static final String MENU_REGEX = String.format("[가-힣a-zA-Z]+%s\\d+", MENU_COUNT_DIVIDER);
    private static final String INPUT_MENU_REGEX = String.format("(%s)+(%s ?%s)*", MENU_REGEX, DIVIDER, MENU_REGEX);

    public int parseDay(String inputDate) {
        validateInputDay(inputDate);
        return Integer.parseInt(inputDate);
    }

    /**
     * 입력 문자열을 메뉴 이름 문자열과 개수 정수 dto로 만들어서 반환
     * @param inputOrder 주문 입력
     * @return 문자열, 정수 객체
     */
    public List<StringIntPair> parseOrder(String inputOrder) {
        validateInputOrderRegex(inputOrder);
        List<StringIntPair> parsedOrder = inputOrderToMenuStringQuantities(inputOrder);
        validateDuplicatedMenu(parsedOrder);
        return parsedOrder;
    }

    private List<StringIntPair> inputOrderToMenuStringQuantities(String inputOrder) {
        return Arrays.stream(inputOrder.split(DIVIDER))
                .map(item -> item.trim().split(MENU_COUNT_DIVIDER))
                .map(i -> new StringIntPair(i[0], Integer.parseInt(i[1])))
                .collect(Collectors.toList());
    }

    private void validateInputDay(String inputDate) {
        if (Integer.parseInt(inputDate) <= 0) {
            throw new DateException();
        }
    }

    private void validateInputOrderRegex(String inputOrder) {
        if(!Pattern.matches(INPUT_MENU_REGEX, inputOrder)) {
            throw new OrderException();
        }
    }

    private void validateDuplicatedMenu(List<StringIntPair> parsedOrder) {
        Set<String> seenMenus = new HashSet<>();

        for (StringIntPair item : parsedOrder) {
            if (!seenMenus.add(item.string())) {
                throw new OrderException();
            }
        }
    }
}
