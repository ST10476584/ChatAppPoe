import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

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

        System.out.println(user.registerUser());

        System.out.println("\n=== Login ===");

        System.out.print("Enter username: ");
        String loginUser = scanner.nextLine();

        System.out.print("Enter password: ");
        String loginPass = scanner.nextLine();

        boolean success = user.loginUser(loginUser, loginPass);

        System.out.println(user.returnLoginStatus(success, firstName, lastName));

        scanner.close();
    }
}