import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    // ✅ Message length success
    @Test
    public void testMessageLengthSuccess() {
        Message msg = new Message(0, "+27838968976", "Hello");
        assertEquals("Message ready to send.", msg.validateMessageLength());
    }

    // ✅ Message length failure
    @Test
    public void testMessageLengthFail() {

        StringBuilder longMsg = new StringBuilder();
        for (int i = 0; i < 260; i++) {
            longMsg.append("a");
        }

        Message msg = new Message(0, "+27838968976", longMsg.toString());

        assertTrue(msg.validateMessageLength().contains("Message exceeds"));
    }

    // ✅ Recipient valid
    @Test
    public void testRecipientValid() {
        Message msg = new Message(0, "+27838968976", "Hi");
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    // ✅ Recipient invalid
    @Test
    public void testRecipientInvalid() {
        Message msg = new Message(0, "08965", "Hi");
        assertTrue(msg.checkRecipientCell().contains("incorrectly formatted"));
    }

    // ✅ Message hash format
    @Test
    public void testMessageHashFormat() {
        Message msg = new Message(0, "+27838968976", "Hi tonight");
        String hash = msg.createMessageHash();

        assertTrue(hash.contains(":0:"));
        assertEquals(hash, hash.toUpperCase()); // must be uppercase
    }

    // ✅ Send message
    @Test
    public void testSendMessageOption() {
        Message msg = new Message(0, "+27838968976", "Hello");
        assertEquals("Message successfully sent.", msg.sentMessage(1));
    }

    // ✅ Discard message
    @Test
    public void testDiscardMessageOption() {
        Message msg = new Message(0, "+27838968976", "Hello");
        assertEquals("Press 0 to delete the message.", msg.sentMessage(2));
    }

    // ✅ Store message
    @Test
    public void testStoreMessageOption() {
        Message msg = new Message(0, "+27838968976", "Hello");
        assertEquals("Message successfully stored.", msg.sentMessage(3));
    }

    // ✅ Message ID generated
    @Test
    public void testMessageIDGenerated() {
        Message msg = new Message(0, "+27838968976", "Hello");
        String id = msg.getMessageID();

        assertNotNull(id);
        assertTrue(id.length() <= 10);
    }
}