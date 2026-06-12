import java.util.Scanner;

public class Main {

    // ===== PART 3 ARRAYS =====
    static String[] sentMessages = new String[100];
    static String[] storedMessages = new String[100];
    static String[] disregardedMessages = new String[100];
    static String[] messageHashes = new String[100];
    static String[] messageIDs = new String[100];
    static String[] recipients = new String[100];

    static int sentCount = 0;
    static int storedCount = 0;
    static int discardCount = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // ===== REGISTRATION =====
        System.out.println("=== Registration ===");

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter SA phone (+27...): ");
        String phone = scanner.nextLine();

        Login user = new Login(username, password, phone);

        String registrationMessage = user.registerUser();
        System.out.println(registrationMessage);

        if (!registrationMessage.equals("User successfully registered.")) {
            System.out.println("Fix registration errors before logging in.");
            return;
        }

        // ===== LOGIN =====
        System.out.println("\n=== Login ===");

        System.out.print("Enter username: ");
        String loginUser = scanner.nextLine();

        System.out.print("Enter password: ");
        String loginPass = scanner.nextLine();

        boolean success = user.loginUser(loginUser, loginPass);

        System.out.println(user.returnLoginStatus(success, firstName, lastName));

        if (!success) return;

        // ===== PART 2 + 3 =====
        System.out.println("\nWelcome to QuickChat.");

        System.out.print("How many messages do you want to send? ");
        int numMessages = scanner.nextInt();
        scanner.nextLine();

        while (true) {

            System.out.println("\nChoose an option:");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.println("4) Stored Messages");

            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {

                for (int i = 0; i < numMessages; i++) {

                    System.out.println("\n--- Message " + (i + 1) + " ---");

                    System.out.print("Enter recipient (+code): ");
                    String recipient = scanner.nextLine();

                    System.out.print("Enter message: ");
                    String text = scanner.nextLine();

                    Message msg = new Message(i, recipient, text);

                    System.out.println("Message ID generated: " + msg.getMessageID());
                    System.out.println(msg.checkRecipientCell());
                    System.out.println(msg.validateMessageLength());

                    System.out.println("\nChoose option:");
                    System.out.println("1) Send Message");
                    System.out.println("2) Disregard Message");
                    System.out.println("3) Store Message");

                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    String result = msg.sentMessage(choice);
                    System.out.println(result);

                    // ===== STORE INTO ARRAYS =====
                    if (choice == 1) {
                        sentMessages[sentCount] = text;
                        messageHashes[sentCount] = msg.createMessageHash();
                        messageIDs[sentCount] = msg.getMessageID();
                        recipients[sentCount] = recipient;
                        sentCount++;

                        System.out.println("\nMessage Details:");
                        System.out.println(Message.printMessages());

                    } else if (choice == 2) {
                        disregardedMessages[discardCount] = text;
                        discardCount++;

                    } else if (choice == 3) {
                        storedMessages[storedCount] = text;
                        storedCount++;
                    }
                }

            } else if (option == 2) {
                System.out.println("Coming Soon.");

            } else if (option == 3) {
                break;

            } else if (option == 4) {
                storedMenu(scanner);

            } else {
                System.out.println("Invalid option.");
            }
        }

        System.out.println("\nTotal messages sent: " + Message.returnTotalMessages());
        scanner.close();
    }

    // ===== STORED MENU =====
    public static void storedMenu(Scanner scanner) {

        System.out.println("\n=== STORED MESSAGES MENU ===");
        System.out.println("1. Display stored messages");
        System.out.println("2. Longest stored message");
        System.out.println("3. Search by Message ID");
        System.out.println("4. Search by recipient");
        System.out.println("5. Delete by hash");
        System.out.println("6. Display report");

        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {

     
              case 1:
    System.out.println("Stored Messages from JSON:");
    for (String msg : Message.loadStoredMessagesFromJSON()) {
        System.out.println(msg);
    }
    break;
            case 2:
                String longest = storedMessages[0];
                for (int i = 1; i < storedCount; i++) {
                    if (storedMessages[i].length() > longest.length()) {
                        longest = storedMessages[i];
                    }
                }
                System.out.println("Longest: " + longest);
                break;

            case 3:
                System.out.print("Enter Message ID: ");
                String id = scanner.nextLine();
                for (int i = 0; i < sentCount; i++) {
                    if (messageIDs[i].equals(id)) {
                        System.out.println("Recipient: " + recipients[i]);
                        System.out.println("Message: " + sentMessages[i]);
                    }
                }
                break;

            case 4:
                System.out.print("Enter recipient: ");
                String rec = scanner.nextLine();
                for (int i = 0; i < sentCount; i++) {
                    if (recipients[i].equals(rec)) {
                        System.out.println(sentMessages[i]);
                    }
                }
                break;

            case 5:
                System.out.print("Enter hash: ");
                String hash = scanner.nextLine();
                for (int i = 0; i < sentCount; i++) {
                    if (messageHashes[i].equals(hash)) {
                        System.out.println("Message: " + sentMessages[i] + " successfully deleted.");
                        sentMessages[i] = null;
                    }
                }
                break;

            case 6:
                System.out.println("\n=== REPORT ===");
                for (int i = 0; i < sentCount; i++) {
                    if (sentMessages[i] != null) {
                        System.out.println("Hash: " + messageHashes[i]);
                        System.out.println("Recipient: " + recipients[i]);
                        System.out.println("Message: " + sentMessages[i]);
                        System.out.println("-------------------");
                    }
                }
                break;
        }
    }
}