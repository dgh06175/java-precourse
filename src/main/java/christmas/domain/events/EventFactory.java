package christmas.domain.events;

import java.util.Arrays;
import java.util.List;

public class EventFactory {
    private static final List<Event> allEvents = Arrays.asList(
            new ChristmasDDay(),
            new WeekDay(),
            new WeekEnd(),
            new Special(),
            new GiveAway()
    );

    public static List<Event> getAllEvents() {
        return allEvents;
    }
}
