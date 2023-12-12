package menu.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import menu.exception.InvalidInputException;

public class InputView {
    private static final String INPUT_REGEX = "[가-힣\\w]+(,[가-힣\\w]+)*";

    public List<String> readCoachNames() {
        printReadNameMessage();
        String input = readString();

        validateInputRegex(input);
        return inputParser(input);
    }

    public List<String> readMenu(String name) {
        printReadMenuMessage(name);
        String input = readString();

        validateInputRegex(input);
        return inputParser(input);
    }

    private List<String> inputParser(String input) {
        return Arrays.asList(input.split(","));
    }

    private void printReadNameMessage() {
        System.out.println();
        System.out.println("코치의 이름을 입력해 주세요. (, 로 구분)");
    }

    private void printReadMenuMessage(String name) {
        System.out.println();
        System.out.printf("%s(이)가 못 먹는 메뉴를 입력해 주세요.%n", name);
    }

    private void validateInputRegex(String input) {
        if (!Pattern.matches(INPUT_REGEX, input)) {
            throw new InvalidInputException("입력 형식이 맞지 않습니다.");
        }
    }

    private String readString() {
        String input = Console.readLine().trim();
        validateString(input);
        return input;
    }

    private void validateString(String input) {
        if (input.isBlank()) {
            throw new InvalidInputException("빈 입력입니다.");
        }
    }
}
