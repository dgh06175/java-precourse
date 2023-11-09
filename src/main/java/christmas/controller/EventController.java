package christmas.controller;

import christmas.domain.Date;
import christmas.exception.EventException;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventController {
    InputView inputView;
    OutputView outputView;
    public EventController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        // 방문 날짜 입력 받기
        Date visitdate = requestVisitDate();
        // 주문 메뉴 입력 받기

        // 주문 메뉴 및 금액 출력

        // 이벤트 혜택 출력
    }

    private Date requestVisitDate() {
        while (true) {
            try {
                return new Date(inputView.readDate());
            } catch (EventException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
