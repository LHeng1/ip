package avalon;

import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = DateTimeParser.stringToDateTime(from);
        this.to = DateTimeParser.stringToDateTime(to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateTimeParser.printDateTimeToString(from)
                + " to: " + DateTimeParser.printDateTimeToString(to) + ")";
    }
}