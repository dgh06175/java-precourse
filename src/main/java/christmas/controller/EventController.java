package christmas.controller;

import christmas.domain.AppliedEvents;
import christmas.domain.Date;
import christmas.domain.MenuService;
import christmas.domain.OrderedMenu;
import christmas.domain.enums.Menu;
import christmas.util.InputParser;
import christmas.exception.EventException;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.EnumMap;
import java.util.Map;

public class EventController {
    InputView inputView;
    OutputView outputView;
    InputParser inputParser;

    public EventController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.inputParser = new InputParser();
    }

    public void run() {
        // 방문 날짜 입력 받기
        Date visitdate = requestVisitDate();
        // 주문 메뉴 입력 받기
        OrderedMenu orderedMenu = requestOrder();
        // 주문 메뉴 및 금액 출력
        displayOrder(orderedMenu, visitdate);
        displayPriceBeforeDiscount(orderedMenu);
        // 이벤트 혜택 출력
        AppliedEvents appliedEvents = AppliedEvents.of(orderedMenu, visitdate);
        displayResult(appliedEvents, orderedMenu);
    }

    private Date requestVisitDate() {
        while (true) {
            try {
                String inputDate = inputView.readDate();
                return new Date(inputParser.parseDate(inputDate));
            } catch (EventException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private OrderedMenu requestOrder() {
        while (true) {
            try {
                Map<String, Integer> parsedStringOrder = inputParser.parseOrder(inputView.readOrder());
                EnumMap<Menu, Integer> parsedOrder = MenuService.stringToMenu(parsedStringOrder);
                return new OrderedMenu(parsedOrder);
            } catch (EventException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private void displayOrder(OrderedMenu orderedMenu, Date date) {
        outputView.printMenu(orderedMenu.getMenuStringAndCount(), date);
    }

    private void displayPriceBeforeDiscount(OrderedMenu orderedMenu) {
        outputView.printPriceBeforeSale(orderedMenu.getTotalPrice());
    }

    private void displayResult(AppliedEvents appliedEvents, OrderedMenu orderedMenu) {
        outputView.printGiveAway(appliedEvents.containsGiveawayEvent());
        outputView.printEventList(appliedEvents.getEventStringAndPrice());
        outputView.printTotalDiscount(appliedEvents.getTotalDiscount());
        outputView.printExpectedPrice(orderedMenu.getTotalPrice() - appliedEvents.getTotalDiscountExceptGiveAway());
        outputView.printEventBadge(appliedEvents.getBadge().name);
    }
}
