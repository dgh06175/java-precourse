package christmas.domain;

import static christmas.domain.Constant.CHRISTMAS_DAY;
import static christmas.domain.Constant.END_OF_DECEMBER_DAY;
import static christmas.domain.Constant.START_OF_MONTH_DAY;

import christmas.exception.DateException;

public class Date {
    private static final int WEEK_COUNT = 7;
    private static final int MON = 4;
    private static final int TUE = 5;
    private static final int WED = 6;
    private static final int THU = 0;
    private static final int FRI = 1;
    private static final int SAT = 2;
    private static final int SUN = 3;
    public final int value;

    public Date (int value){
        validateDate(value);
        this.value = value;
    }

    private void validateDate(int value) {
        if (value < START_OF_MONTH_DAY || value > END_OF_DECEMBER_DAY) {
            throw new DateException();
        }
    }

    public boolean isDateBetweenClosed(Date start, Date end) {
        return this.value >= start.value && this.value <= end.value;
    }

    public int getDaysToChristmas() {
        return value - START_OF_MONTH_DAY;
    }

    public boolean isChristmas() {
        return value == CHRISTMAS_DAY;
    }

    public boolean isWeekend() {
        return isFriday() || isSaturday();
    }

    public boolean isMonday() {
        return value % WEEK_COUNT == MON;
    }

    public boolean isTuesday() {
        return value % WEEK_COUNT == TUE;
    }

    public boolean isWednesday() {
        return value % WEEK_COUNT == WED;
    }

    public boolean isThursday() {
        return value % WEEK_COUNT == THU;
    }

    public boolean isFriday() {
        return value % WEEK_COUNT == FRI;
    }

    public boolean isSaturday() {
        return value % WEEK_COUNT == SAT;
    }

    public boolean isSunday() {
        return value % WEEK_COUNT == SUN;
    }
}
