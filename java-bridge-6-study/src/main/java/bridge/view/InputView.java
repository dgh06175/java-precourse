package bridge.view;

import static bridge.exception.ErrorMessage.INVALID_INPUT;

import bridge.exception.InvalidInputException;
import camp.nextstep.edu.missionutils.Console;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {

    /**
     * 다리의 길이를 입력받는다.
     */
    public int readBridgeSize() {
        printReadBridgeSizeMessage();
        return parseInt(readString());
    }

    private void printReadBridgeSizeMessage() {
        System.out.println("다리의 길이를 입력해주세요.");
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public String readMoving() {
        printReadMovingMessage();
        String input = readString();

        validateString(input);
        return input;
    }

    private void printReadMovingMessage() {
        System.out.println("이동할 칸을 선택해주세요. (위: U, 아래: D)");
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public String readGameCommand() {
        printReadGameCommandMessage();
        String input = readString();

        validateString(input);
        return input;
    }

    private void printReadGameCommandMessage() {
        System.out.println("게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)");
    }


    private String readString() {
        return Console.readLine().trim();
    }

    private void validateString(String input) {
        if (input.isBlank()) {
            throw new InvalidInputException(INVALID_INPUT.message);
        }
    }

    private int parseInt(String input) {
        validateString(input);
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException(INVALID_INPUT.message);
        }
    }
}
