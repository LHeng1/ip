package avalon;

/**
 * Utility class for parsing and serializing tasks.
 */
public class TaskParser {

    /**
     * Parses a string representation of a task and returns the corresponding Task object.
     *
     * @param line The string representation of the task to be parsed.
     * @return The Task object parsed from the input string.
     */
    public static Task parse(String line) {
        String[] parts = line.split(" \\| ");

        assert parts.length <= 5 : "Task string should have no more than 5 parts";

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        Task task = null;

        switch (type) {
        case "T":
            task = new ToDo(description);
            break;
        case "D":
            task = new Deadline(description, parts[3]);
            break;
        case "E":
            task = new Event(description, parts[3], parts[4]);
            break;
        }

        if (isDone) {
            assert task != null;
            task.markDone();
        }

        return task;
    }

    /**
     * Serializes a Task object to a string for saving to a file.
     *
     * @param task The Task object to be serialized.
     * @return The string representation of the serialized task.
     */
    public static String serialize(Task task) {
        String doneStatus = task.isDone ? "1" : "0";

        if (task instanceof ToDo) {
            return "T | " + doneStatus + " | " + task.description;
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "D | " + doneStatus + " | " + task.description + " | "
                    + DateTimeParser.dateTimeToString(deadline.by);
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return "E | " + doneStatus + " | " + task.description + " | "
                    + DateTimeParser.dateTimeToString(event.from) + " | "
                    + DateTimeParser.dateTimeToString(event.to);
        } else {
            return "Wrong formatting";
        }
    }
}
