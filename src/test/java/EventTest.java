import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import friday.task.Event;
import friday.task.EventArgsException;

public class EventTest {

    @Test
    public void eventCreationValid() {
        Event e = new Event("career fair /from 2025-09-01 /to 2025-09-03");
        assertEquals("[E] [ ] career fair (from: Sept 1 2025 to: Sept 3 2025)", e.toString());
    }

    @Test
    public void eventCreationInvalid() {
        try {
            new Event("career fair /from 2025-09-01");
            fail();
        } catch (EventArgsException e) {
            assertEquals("Event description must include '/to' part.", e.getMessage());
        }
    }
}
