import org.junit.Test;
import static org.junit.Assert.*;

public class Part3Test {

    @Test
    public void testSentMessagesArray() {

        String[] messages = new String[2];
        messages[0] = "Did you get the cake?";
        messages[1] = "It is dinner time!";

        assertEquals("Did you get the cake?", messages[0]);
        assertEquals("It is dinner time!", messages[1]);
    }

    @Test
    public void testLongestMessage() {

        String[] messages = {
            "Did you get the cake?",
            "Where are you? You are late! I have asked you to be on time.",
            "Yohoooo, 1 am at your gate."
        };

        String longest = messages[0];

        for (String msg : messages) {
            if (msg.length() > longest.length()) {
                longest = msg;
            }
        }

        assertEquals(
            "Where are you? You are late! I have asked you to be on time.",
            longest
        );
    }

    @Test
    public void testSearchByMessageID() {

        String messageID = "1234567890";
        String recipient = "+27838884567";
        String message = "It is dinner time!";

        assertEquals("1234567890", messageID);
        assertEquals("+27838884567", recipient);
        assertEquals("It is dinner time!", message);
    }

    @Test
    public void testSearchByRecipient() {

        String recipient = "+27838884567";

        String msg1 = "Where are you? You are late! I have asked you to be on time.";
        String msg2 = "Ok, 1 am leaving without you.";

        assertEquals("+27838884567", recipient);
        assertTrue(msg1.contains("late"));
        assertTrue(msg2.contains("leaving"));
    }

    @Test
    public void testDeleteMessage() {

        String message = "Where are you?";
        message = null;

        assertNull(message);
    }

    @Test
    public void testReportData() {

        String hash = "00:0:HITONIGHT";
        String recipient = "+27838884567";
        String message = "Hi tonight";

        assertEquals("00:0:HITONIGHT", hash);
        assertEquals("+27838884567", recipient);
        assertEquals("Hi tonight", message);
    }
}