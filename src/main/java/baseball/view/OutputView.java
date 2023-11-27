package baseball.view;

import java.util.Map;

public class OutputView {
    public void printStartMessage() {
        System.out.println("숫자 야구 게임을 시작합니다.");
    }

    public void printBaseballNumberInputMessage() {
        System.out.println("숫자를 입력해주세요 : ");
    }

    public void printNumberCompareResult(Map<String, Integer> result) {
        int strike = result.get("Strike");
        int ball = result.get("Ball");
        if (strike == 0 && ball == 0) {
            System.out.println("낫싱");
            return;
        }
        if (ball > 0) {
            System.out.printf("%d볼", ball);
        }
        if (ball > 0 && strike > 0) {
            System.out.print(" ");
        }
        if (strike > 0) {
            System.out.printf("%d스트라이크", ball);
        }
        System.out.println();
    }

    public void printWinMessage() {
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
    }

    public void printRestartMessage() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
    }
}
