import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import friday.task.Deadline;
import friday.task.DeadlineArgsException;

class DeadlineTest {
    @Test
    public void testDeadlineCreationValid() {
        Deadline deadline = new Deadline("submit report /by 2023-10-15");
        System.out.println(deadline.getFullDesc());
        assertEquals("[D] [ ] submit report (by: Oct 15 2023)", deadline.toString());
    }

    @Test
    public void testDeadlineCreationInvalid() {
        try {
            new Deadline("submit report");
            fail();
        } catch (DeadlineArgsException e) {
            assertEquals("The deadline cannot be empty... it must have the 'by' part.", e.getMessage());
        }
    }

    @Test
    public void testDeadlineCreationValidNoDate() {
        Deadline deadline = new Deadline("submit report /by tomorrow");
        assertEquals("[D] [ ] submit report (by: tomorrow)", deadline.toString());
    }
}
