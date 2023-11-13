package christmas.domain.events;

import java.util.Arrays;
import java.util.List;

public class EventFactory {
    private static final List<Event> allEvents = Arrays.asList(
            new ChristmasDDayEvent(),
            new WeekDayEvent(),
            new WeekEndEvent(),
            new SpecialEvent(),
            new GiveAwayEvent()
    );

    public static List<Event> getAllEvents() {
        return allEvents;
    }
}
