package christmas.controller;

import christmas.domain.AppliedEvents;
import christmas.domain.OrderedMenu;
import christmas.domain.records.StringIntPair;
import christmas.exception.EventException;
import christmas.view.OutputView;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Supplier;

public class EventFacade {
    private final OutputView outputView;

    public EventFacade(OutputView outputView) {
        this.outputView = outputView;
    }

    /**
     * action 을 실행하고 결과를 반환한다.
     * 도중에 예외가 발생하면 메세지를 출력하고 다시 실행한다.
     * @param action 실행할 행동
     */
    public <T> T executeWithRetry(Supplier<T> action) {
        while (true) {
            try {
                return action.get();
            } catch (EventException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    public void displayMenuList() {
        outputView.printFormattedMenu();
    }

    /**
     * 사용자가 주문한 메뉴를 출력한다.
     * @param visitDate 방문 날짜
     * @param orderedMenu 주문 메뉴
     */
    public void displayOrderedMenu(LocalDate visitDate, OrderedMenu orderedMenu) {
        outputView.printEventStartMessage(visitDate);
        displayOrder(orderedMenu);
        displayPriceBeforeDiscount(orderedMenu);
    }

    /**
     * 할인 이벤트 적용 결과를 모두 출력한다.
     * @param visitDate 방문 날짜
     * @param orderedMenu 주문 메뉴
     */
    public void displayEventResult(LocalDate visitDate, OrderedMenu orderedMenu) {
        AppliedEvents appliedEvents = AppliedEvents.of(visitDate, orderedMenu);
        displayGiveAway(appliedEvents);
        displayAppliedEvent(appliedEvents);
        displayTotalDiscount(appliedEvents);
        displayPrice(appliedEvents, orderedMenu);
        displayBadge(appliedEvents, visitDate);
    }

    private void displayOrder(OrderedMenu orderedMenu) {
        List<StringIntPair> menuStringQuantities = orderedMenu.getMenuStringQuantity();
        outputView.printMenu(menuStringQuantities);
    }

    private void displayPriceBeforeDiscount(OrderedMenu orderedMenu) {
        int priceBeforeDiscount = orderedMenu.getTotalPrice();
        outputView.printPriceBeforeSale(priceBeforeDiscount);
    }

    private void displayGiveAway(AppliedEvents appliedEvents) {
        outputView.printGiveAway(appliedEvents.containsGiveawayEvent());
    }

    private void displayAppliedEvent(AppliedEvents appliedEvents) {
        outputView.printEventList(appliedEvents.getEventStringAndPrice());
    }

    private void displayTotalDiscount(AppliedEvents appliedEvents) {
        outputView.printTotalDiscount(appliedEvents.getTotalDiscount());
    }

    private void displayPrice(AppliedEvents appliedEvents, OrderedMenu orderedMenu) {
        int priceBeforeDiscount = orderedMenu.getTotalPrice();
        int priceAfterDiscount = appliedEvents.getPriceAfterDiscount(priceBeforeDiscount);
        outputView.printExpectedPrice(priceAfterDiscount);
    }

    private void displayBadge(AppliedEvents appliedEvents, LocalDate visitDate) {
        outputView.printEventBadge(appliedEvents.getBadge().displayName, visitDate);
    }
}
