package christmas.domain;

import christmas.exception.DateException;

public class Date {
    private static final int FIRST_DATE = 1;
    private static final int LAST_DATE = 31;
    int value;

    public Date (int value){
        validateDate(value);
        this.value = value;
    }

    private void validateDate(int value) {
        if (value < FIRST_DATE || value > LAST_DATE) {
            throw new DateException();
        }
    }
}
