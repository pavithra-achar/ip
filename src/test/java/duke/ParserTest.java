package duke;

import static org.junit.jupiter.api.Assertions.*;

import duke.exception.MissingParameterException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class ParserTest {

    private Parser parser = new Parser();

    @Test
    void parseDateTime_fullDateTime_success() throws MissingParameterException {
        LocalDateTime dt = parser.parseDateTime("12-03-2025 14:30");

        assertEquals(2025, dt.getYear());
        assertEquals(3, dt.getMonthValue());
        assertEquals(12, dt.getDayOfMonth());
        assertEquals(14, dt.getHour());
        assertEquals(30, dt.getMinute());
    }

    @Test
    void parseDateTime_dateOnly_defaultsTo2359() throws MissingParameterException {
        LocalDateTime dt = parser.parseDateTime("12-03-2025");

        assertEquals(23, dt.getHour());
        assertEquals(59, dt.getMinute());
    }

    @Test
    void parseDateTime_invalidFormat_exceptionThrown() {
        try {
            parser.parseDateTime("2025/03/12");
        } catch (MissingParameterException e) {
            assertEquals("Thy request is incomplete. Please provide all the required information. " +
                    "Thy time must follow dd-mm-yyyy or dd-mm-yyyy HH:mm format.", e.getMessage());
        }
    }
}
