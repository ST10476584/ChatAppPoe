import org.junit.Test;
import static org.junit.Assert.*;

public class LoginTest {

    // ===== USERNAME TESTS =====
    @Test
    public void testValidUsername() {
        Login login = new Login("Kyl_1", "Password1!", "+27838968976");
        assertTrue(login.checkUserName());
    }

    @Test
    public void testInvalidUsername() {
        Login login = new Login("Kyle!!!!", "Password1!", "+27838968976");
        assertFalse(login.checkUserName());
    }

    // ===== PASSWORD TESTS =====
    @Test
    public void testValidPassword() {
        Login login = new Login("Kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.checkPasswordComplexity());
    }

    @Test
    public void testInvalidPassword() {
        Login login = new Login("Kyl_1", "password", "+27838968976");
        assertFalse(login.checkPasswordComplexity());
    }

    // ===== CELL NUMBER TESTS =====
    @Test
    public void testValidCellNumber() {
        Login login = new Login("Kyl_1", "Password1!", "+27838968976");
        assertTrue(login.checkCellPhoneNumber());
    }

    @Test
    public void testInvalidCellNumber() {
        Login login = new Login("Kyl_1", "Password1!", "08966553");
        assertFalse(login.checkCellPhoneNumber());
    }

    // ===== LOGIN TESTS =====
    @Test
    public void testLoginSuccess() {
        Login login = new Login("Kyl_1", "Password1!", "+27838968976");
        assertTrue(login.loginUser("Kyl_1", "Password1!"));
    }

    @Test
    public void testLoginFail() {
        Login login = new Login("Kyl_1", "Password1!", "+27838968976");
        assertFalse(login.loginUser("wrong", "wrong"));
    }

    // ===== MESSAGE TESTS (assertEquals - IMPORTANT) =====
    @Test
    public void testLoginMessageSuccess() {
        Login login = new Login("Kyl_1", "Password1!", "+27838968976");
        String message = login.returnLoginStatus(true, "John", "Doe");
        assertEquals("Welcome John, Doe it is great to see you again.", message);
    }

    @Test
    public void testLoginMessageFail() {
        Login login = new Login("Kyl_1", "Password1!", "+27838968976");
        String message = login.returnLoginStatus(false, "John", "Doe");
        assertEquals("Username or password incorrect, please try again.", message);
    }
}