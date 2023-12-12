package menu.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import menu.exception.InvalidInputException;

public class Coaches {
    private static final int MIN_COACH_SIZE = 2;
    private static final int MAX_COACH_SIZE = 5;
    private final List<Coach> coachList;

    public Coaches(List<Coach> coachList) {
        validateCoaches(coachList);
        this.coachList = new ArrayList<>(coachList);
    }

    public List<Coach> getCoachList() {
        return Collections.unmodifiableList(coachList);
    }

    private void validateCoaches(List<Coach> coachList) {
        if (coachList.size() < MIN_COACH_SIZE || coachList.size() > MAX_COACH_SIZE) {
            throw new InvalidInputException("코치는 최소 %d명, 최대 %d명까지 식사를 함께 한다.".formatted(MIN_COACH_SIZE, MAX_COACH_SIZE));
        }
    }
}
