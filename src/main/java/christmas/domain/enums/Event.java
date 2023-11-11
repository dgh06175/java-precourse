package christmas.domain.enums;

import static christmas.constant.*;

import christmas.domain.Date;
import christmas.domain.Order;

public enum Event {
    CHRISTMAS_D_DAY("크리스마스 디데이 할인",1, CHRISTMAS_DAY, 1000) {
        @Override
        public boolean isEventValid(Date date, Order order) {
            return isDateValid(date) && isTotalPriceValid(order);
        }

        @Override
        protected int calculateEventSpecificDiscount(Date date, Order order) {
            // 1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가
            int daysToChristmas = date.value - START_OF_MONTH_DAY;
            return this.saleAmount + daysToChristmas * 100;
        }
    },

    WEEKDAY("평일 할인", 1, END_OF_DECEMBER_DAY, 2_023) {
        @Override
        public boolean isEventValid(Date date, Order order) {
            return isDateValid(date) && isTotalPriceValid(order) && !date.isWeekend();
        }

        @Override
        protected int calculateEventSpecificDiscount(Date date, Order order) {
            // 디저트 메뉴 개수 * 2023 반환
            int dessertCount = order.getCountOfMenuCategory(MenuCategory.DESSERT);
            return dessertCount * saleAmount;
        }
    },

    WEEKEND("주말 할인", 1, END_OF_DECEMBER_DAY, 2_023) {
        @Override
        public boolean isEventValid(Date date, Order order) {
            return isDateValid(date) && isTotalPriceValid(order) && date.isWeekend();
        }

        @Override
        protected int calculateEventSpecificDiscount(Date date, Order order) {
            // 메인 메뉴 개수 * 2023 반환
            int mainCount = order.getCountOfMenuCategory(MenuCategory.MAIN);
            return mainCount * saleAmount;
        }
    },

    SPECIAL("특별 할인", 1, END_OF_DECEMBER_DAY, 1_000) {
        @Override
        public boolean isEventValid(Date date, Order order) {
            return isDateValid(date) && isTotalPriceValid(order) && isSpecial(date);
        }

        @Override
        protected int calculateEventSpecificDiscount(Date date, Order order) {
            // 총주문 금액에서 1000원 할인
            return saleAmount;
        }
    },

    GIVEAWAY("증정 이벤트", 1, END_OF_DECEMBER_DAY, 25_000) {

        @Override
        public boolean isEventValid(Date date, Order order) {
            return isDateValid(date) && isTotalPriceValid(order) && order.getTotalPrice() >= 120_000;
        }

        @Override
        protected int calculateEventSpecificDiscount(Date date, Order order) {
            // 총 주문 금액 12만원 이상일떄 샴페인 증정
            return Menu.샴페인.price;
        }
    };

    final String name;
    final Date startDay;
    final Date endDay;
    final int saleAmount;

    Event(String name, int startDay, int endDay, int saleAmount) {
        this.name = name;
        this.startDay = new Date(startDay);
        this.endDay = new Date(endDay);
        this.saleAmount = saleAmount;
    }

    public int calculateDiscount(Date date, Order order) {
        if (!isEventValid(date, order)) {
            return 0;
        }
        return calculateEventSpecificDiscount(date, order);
    }

    public abstract boolean isEventValid(Date date, Order order);
    protected abstract int calculateEventSpecificDiscount(Date date, Order order);
    protected boolean isDateValid(Date date) {
        return date.isDateBetweenClosed(this.startDay, this.endDay);
    }
    protected boolean isTotalPriceValid(Order order) {
        return order.getTotalPrice() >= 10_000;
    }
    protected boolean isSpecial (Date date) {
        return date.value % 7 == 3 || date.value == 25;
    }
}
