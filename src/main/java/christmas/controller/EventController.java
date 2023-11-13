package christmas.controller;

import christmas.domain.Date;
import christmas.domain.MenuService;
import christmas.domain.OrderedMenu;
import christmas.domain.enums.Menu;
import christmas.util.InputParser;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.EnumMap;
import java.util.Map;

public class EventController {
    private final InputView inputView;
    private final InputParser inputParser;
    EventFacade eventFacade;

    public EventController(InputView inputView, OutputView outputView, InputParser inputParser) {
        this.inputView = inputView;
        this.inputParser = inputParser;
        this.eventFacade = new EventFacade(outputView);
    }

    public void run() {
        eventFacade.displayMenuList();
        // 날짜, 주문 입력 받기
        Date visitdate = requestVisitDate();
        OrderedMenu orderedMenu = requestOrder();
        // 주문 내역 출력
        eventFacade.displayOrderedMenu(visitdate, orderedMenu);
        // 이벤트 혜택 출력
        eventFacade.displayEventResult(visitdate, orderedMenu);
    }

    private Date requestVisitDate() {
        return eventFacade.executeWithRetry(() -> {
            String inputDate = inputView.readDate();
            int parsedDate = inputParser.parseDate(inputDate);
            return new Date(parsedDate);
        });
    }

    private OrderedMenu requestOrder() {
        return eventFacade.executeWithRetry(() -> {
            String inputOrder = inputView.readOrder();
            Map<String, Integer> parsedStringOrder = inputParser.parseOrder(inputOrder);
            EnumMap<Menu, Integer> parsedOrder = MenuService.stringToMenu(parsedStringOrder);
            return new OrderedMenu(parsedOrder);
        });
    }
}
