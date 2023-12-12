package bridge.view;

import bridge.domain.Bridge;
import java.util.List;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap(List<String> bridgeParts, int playerLocation, boolean moveSuccess) {
        StringBuilder upperRow = new StringBuilder("[");
        StringBuilder lowerRow = new StringBuilder("[");

        for (int i = 0; i <= playerLocation; i++) {
            String part = bridgeParts.get(i);
            if (i == playerLocation) {
                if (moveSuccess) {
                    if (part.equals("U")) {
                        upperRow.append(" O ");
                        lowerRow.append("   ");
                    }
                    if (part.equals("D")){
                        upperRow.append("   ");
                        lowerRow.append(" O ");
                    }
                }
                if (!moveSuccess) {
                    if (part.equals("U")) {
                        upperRow.append("   ");
                        lowerRow.append(" X ");
                    }
                    if (part.equals("D")){
                        upperRow.append(" X ");
                        lowerRow.append("   ");
                    }
                }
            }

            if (i < playerLocation) {
                if (part.equals("U")) {
                    upperRow.append(" O ");
                    lowerRow.append("   ");
                }

                if (part.equals("D")) {
                    upperRow.append("   ");
                    lowerRow.append(" O ");
                }
            }

            if (i <= playerLocation - 1) {
                upperRow.append("|");
                lowerRow.append("|");
            }
        }

        upperRow.append("]");
        lowerRow.append("]");

        System.out.println(upperRow);
        System.out.println(lowerRow);
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult(boolean isGameWin, int attemptCount) {
        String gameWin = getGameWinMessage(isGameWin);
        System.out.printf("게임 성공 여부: %s%n", gameWin);
        System.out.printf("총 시도한 횟수: %d", attemptCount);
    }

    private String getGameWinMessage(boolean isGameWin) {
        if (isGameWin) {
            return "성공";
        }
        return "실패";
    }

    public void printResultMessage() {
        System.out.println("최종 게임 결과");
    }

    public void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }
}
