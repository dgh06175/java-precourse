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
        outputView.printFormattedMenu();
        // 날짜, 주문 입력 받기
        Date visitdate = requestVisitDate();
        OrderedMenu orderedMenu = requestOrder();
        // 주문 내역 출력
        outputView.printEventStartMessage(visitdate.value);
        displayOrder(orderedMenu);
        displayPriceBeforeDiscount(orderedMenu);
        // 이벤트 혜택 출력
        AppliedEvents appliedEvents = AppliedEvents.of(visitdate, orderedMenu);
        displayResult(appliedEvents, orderedMenu);
    }

    private Date requestVisitDate() {
        while (true) {
            try {
                String inputDate = inputView.readDate();
                int parsedDate = inputParser.parseDate(inputDate);
                return new Date(parsedDate);
            } catch (EventException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private OrderedMenu requestOrder() {
        while (true) {
            try {
                String inputOrder = inputView.readOrder();
                Map<String, Integer> parsedStringOrder = inputParser.parseOrder(inputOrder);
                EnumMap<Menu, Integer> parsedOrder = MenuService.stringToMenu(parsedStringOrder);
                return new OrderedMenu(parsedOrder);
            } catch (EventException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private void displayOrder(OrderedMenu orderedMenu) {
        Map<String, Integer> menuStringAndCount = orderedMenu.getMenuStringAndCount();
        outputView.printMenu(menuStringAndCount);
    }

    private void displayPriceBeforeDiscount(OrderedMenu orderedMenu) {
        int priceBeforeDiscount = orderedMenu.getTotalPrice();
        outputView.printPriceBeforeSale(priceBeforeDiscount);
    }

    private void displayResult(AppliedEvents appliedEvents, OrderedMenu orderedMenu) {
        outputView.printGiveAway(appliedEvents.containsGiveawayEvent());
        outputView.printEventList(appliedEvents.getEventStringAndPrice());
        outputView.printTotalDiscount(appliedEvents.getTotalDiscount());
        int priceBeforeDiscount = orderedMenu.getTotalPrice();
        int priceAfterDiscount = appliedEvents.getPriceAfterDiscount(priceBeforeDiscount);
        outputView.printExpectedPrice(priceAfterDiscount);
        outputView.printEventBadge(appliedEvents.getBadge().name);
    }
}
