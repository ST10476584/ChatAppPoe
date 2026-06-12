import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public class Message {

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;

    private static int totalMessages = 0;
    private static ArrayList<Message> sentMessages = new ArrayList<>();

    public Message(int messageNumber, String recipient, String messageText) {
        this.messageID = generateMessageID();
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
    }

    private String generateMessageID() {
        Random rand = new Random();
        long num = (long)(rand.nextDouble() * 1_000_000_0000L);
        return String.valueOf(num);
    }

    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    public String checkRecipientCell() {
        if (recipient.matches("^\\+\\d{9,10}$")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    public String validateMessageLength() {
        if (messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = messageText.length() - 250;
            return "Message exceeds 250 characters by " + excess + ", please reduce the size.";
        }
    }

    public String createMessageHash() {

        if (messageText == null || messageText.isEmpty()) {
            return "INVALID";
        }

        String[] words = messageText.split(" ");
        String first = words[0];
        String last = words[words.length - 1];

        String idPart = messageID.length() >= 2 ? messageID.substring(0, 2) : messageID;

        return (idPart + ":" + messageNumber + ":" + first + last).toUpperCase();
    }

    public String sentMessage(int option) {

        switch (option) {
            case 1:
                sentMessages.add(this);
                totalMessages++;
                return "Message successfully sent.";
            case 2:
                return "Press 0 to delete the message.";
            case 3:
                storeMessage();
                return "Message successfully stored.";
            default:
                return "Invalid option.";
        }
    }

    public void storeMessage() {
        try {
            FileWriter writer = new FileWriter("messages.json", true);

            writer.write("{\n");
            writer.write("\"MessageID\": \"" + messageID + "\",\n");
            writer.write("\"Hash\": \"" + createMessageHash() + "\",\n");
            writer.write("\"Recipient\": \"" + recipient + "\",\n");
            writer.write("\"Message\": \"" + messageText + "\"\n");
            writer.write("}\n");

            writer.close();

        } catch (IOException e) {
            System.out.println("Error writing to JSON file.");
        }
    }

    public static String printMessages() {
        StringBuilder output = new StringBuilder();

        for (Message m : sentMessages) {
            output.append("Message ID: ").append(m.messageID).append("\n");
            output.append("Message Hash: ").append(m.createMessageHash()).append("\n");
            output.append("Recipient: ").append(m.recipient).append("\n");
            output.append("Message: ").append(m.messageText).append("\n\n");
        }

        return output.toString();
    }

    public static int returnTotalMessages() {
        return totalMessages;
    }

    // ✅ ADD THESE GETTERS (IMPORTANT FOR PART 3)

    public String getMessageID() {
        return messageID;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getMessageHash() {
        return createMessageHash();
    }
    
    public static ArrayList<String> loadStoredMessagesFromJSON() {
    ArrayList<String> messages = new ArrayList<>();

    try {
        java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader("messages.json"));
        String line;

        while ((line = reader.readLine()) != null) {
            if (line.contains("\"Message\"")) {
                String msg = line.split(":")[1].trim();
                msg = msg.replace("\"", "").replace(",", "");
                messages.add(msg);
            }
        }

        reader.close();

    } catch (Exception e) {
        System.out.println("Error reading JSON file.");
    }

    return messages;
}
}