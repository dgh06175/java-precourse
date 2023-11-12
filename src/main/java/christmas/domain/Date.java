package christmas.domain;

import static christmas.domain.Constant.END_OF_DECEMBER_DAY;
import static christmas.domain.Constant.START_OF_MONTH_DAY;

import christmas.exception.DateException;

public class Date {
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

    public boolean isWeekend() {
        return value % 7 == 1 || value % 7 == 2;
    }
}
