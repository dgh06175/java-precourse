package christmas.controller;

import christmas.domain.MenuService;
import christmas.domain.OrderedMenu;
import christmas.domain.records.MenuQuantity;
import christmas.domain.records.StringIntPair;
import christmas.exception.DateException;
import christmas.util.InputParser;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class EventController {
    private final LocalDate eventDate;
    private final InputView inputView;
    private final InputParser inputParser;
    private final EventFacade eventFacade;

    public EventController(int year, Month eventMonth, InputView inputView, OutputView outputView, InputParser inputParser) {
        this.eventDate = LocalDate.of(year, eventMonth, 1);
        this.inputView = inputView;
        this.inputParser = inputParser;
        this.eventFacade = new EventFacade(outputView);
    }

    public void run() {
        eventFacade.displayMenuList();
        LocalDate visitDate = requestVisitDate();
        OrderedMenu orderedMenu = requestOrder();
        eventFacade.displayOrderedMenu(visitDate, orderedMenu);
        eventFacade.displayEventResult(visitDate, orderedMenu);
    }

    /**
     * 방문 날짜(일)를 입력받아 검증하고 LocalDate 객체로 만들어 반환한다.
     * @return 방문 날짜
     */
    private LocalDate requestVisitDate() {
        return eventFacade.executeWithRetry(() -> {
            String inputDate = inputView.readDate(eventDate);
            int parsedDay = inputParser.parseDay(inputDate);
            return initLocalDate(parsedDay);
        });
    }

    private LocalDate initLocalDate(int parsedDay) {
        try {
            return LocalDate.of(eventDate.getYear(), eventDate.getMonth(), parsedDay);
        } catch (DateTimeException e) {
            throw new DateException();
        }

    }

    /**
     * 주문을 입력받아 검증하고 OrderedMenu 객체로 만들어 반환한다.
     * @return 주문한 메뉴
     */
    private OrderedMenu requestOrder() {
        return eventFacade.executeWithRetry(() -> {
            String inputOrder = inputView.readOrder();
            List<StringIntPair> parsedStringOrder = inputParser.parseOrder(inputOrder);
            List<MenuQuantity> parsedOrder = MenuService.stringToMenu(parsedStringOrder);
            return new OrderedMenu(parsedOrder);
        });
    }
}
