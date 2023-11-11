package christmas.domain;

import christmas.exception.DateException;

public class Date {
    private static final int FIRST_DATE = 1;
    private static final int LAST_DATE = 31;
    public final int value;

    public Date (int value){
        validateDate(value);
        this.value = value;
    }

    private void validateDate(int value) {
        if (value < FIRST_DATE || value > LAST_DATE) {
            throw new DateException();
        }
    }

    public boolean isDateBetweenClosed(Date start, Date end) {
        return this.value >= start.value && this.value <= end.value;
    }

    public boolean isWeekend() {
        return value % 7 == 1 || value % 7 == 2;
    }
}
