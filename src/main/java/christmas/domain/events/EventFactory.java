package christmas.domain.events;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class EventFactory {
    private static List<Event> allEvents (LocalDate eventDate) {
        return Arrays.asList(
                new ChristmasDDayEvent(eventDate),
                new WeekDayEvent(eventDate),
                new WeekEndEvent(eventDate),
                new SpecialEvent(eventDate),
                new GiveAwayEvent(eventDate)
        );
    }

    public static List<Event> getAllEvents(LocalDate eventDate) {
        return allEvents(eventDate);
    }
}
