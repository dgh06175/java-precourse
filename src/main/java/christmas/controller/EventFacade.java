package christmas.controller;

import christmas.domain.AppliedEvents;
import christmas.domain.Date;
import christmas.domain.OrderedMenu;
import christmas.exception.EventException;
import christmas.view.OutputView;

import java.util.Map;
import java.util.function.Supplier;

public class EventFacade {
    private final OutputView outputView;

    public <T> T executeWithRetry(Supplier<T> action) {
        while (true) {
            try {
                return action.get();
            } catch (EventException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    public EventFacade(OutputView outputView) {
        this.outputView = outputView;
    }

    public void displayMenuList() {
        outputView.printFormattedMenu();
    }

    public void displayOrderedMenu(Date visitdate, OrderedMenu orderedMenu) {
        outputView.printEventStartMessage(visitdate.value);
        displayOrder(orderedMenu);
        displayPriceBeforeDiscount(orderedMenu);
    }

    public void displayEventResult(Date visitdate, OrderedMenu orderedMenu) {
        AppliedEvents appliedEvents = AppliedEvents.of(visitdate, orderedMenu);
        displayGiveAway(appliedEvents);
        displayAppliedEvent(appliedEvents);
        displayTotalDiscount(appliedEvents);
        displayPrice(appliedEvents, orderedMenu);
        displayBadge(appliedEvents);
    }

    public void displayOrder(OrderedMenu orderedMenu) {
        Map<String, Integer> menuStringAndCount = orderedMenu.getMenuStringAndCount();
        outputView.printMenu(menuStringAndCount);
    }

    public void displayPriceBeforeDiscount(OrderedMenu orderedMenu) {
        int priceBeforeDiscount = orderedMenu.getTotalPrice();
        outputView.printPriceBeforeSale(priceBeforeDiscount);
    }

    public void displayGiveAway(AppliedEvents appliedEvents) {
        outputView.printGiveAway(appliedEvents.containsGiveawayEvent());
    }

    public void displayAppliedEvent(AppliedEvents appliedEvents) {
        outputView.printEventList(appliedEvents.getEventStringAndPrice());
    }

    public void displayTotalDiscount(AppliedEvents appliedEvents) {
        outputView.printTotalDiscount(appliedEvents.getTotalDiscount());

    }

    public void displayPrice(AppliedEvents appliedEvents, OrderedMenu orderedMenu) {
        int priceBeforeDiscount = orderedMenu.getTotalPrice();
        int priceAfterDiscount = appliedEvents.getPriceAfterDiscount(priceBeforeDiscount);
        outputView.printExpectedPrice(priceAfterDiscount);
    }

    public void displayBadge(AppliedEvents appliedEvents) {
        outputView.printEventBadge(appliedEvents.getBadge().displayName);
    }
}
