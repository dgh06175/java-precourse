package baseball.view;

import static baseball.domain.GameConstant.NOT_RETRY;
import static baseball.domain.GameConstant.RETRY;

public class ViewConstant {
    public static final String START_MESSAGE = "숫자 야구 게임을 시작합니다.";
    public static final String BASEBALL_NUMBER_INPUT_MESSAGE = "숫자를 입력해주세요 : ";
    public static final String WIN_MESSAGE = "3개의 숫자를 모두 맞히셨습니다! 게임 종료";
    public static final String RESTART_MESSAGE = "게임을 새로 시작하려면 " + RETRY + ", 종료하려면 " + NOT_RETRY + "를 입력하세요.";

    private ViewConstant() {}
}
