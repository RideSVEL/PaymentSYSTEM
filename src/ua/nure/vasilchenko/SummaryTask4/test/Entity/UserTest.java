package ua.nure.vasilchenko.SummaryTask4.test.Entity;

import org.junit.jupiter.api.Test;
import ua.nure.vasilchenko.SummaryTask4.db.entity.Card;
import ua.nure.vasilchenko.SummaryTask4.db.entity.User;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User user;

    @Test
    void checkConstructor() {
        User user = new User();
        assertNotNull(user);
    }

    @Test
    void getActivityId() {
        user = new User();
        user.setActivityId(1);
        assertEquals(1, user.getActivityId());
    }

    @Test
    void getLogin() {
        user = new User();
        user.setLogin("ridesvel");
        assertEquals("ridesvel", user.getLogin());
    }

    @Test
    void getPassword() {
        user = new User();
        user.setPassword("trulala");
        assertEquals("trulala", user.getPassword());
    }

    @Test
    void getFirstName() {
        user = new User();
        user.setFirstName("Igorek");
        assertEquals("Igorek", user.getFirstName());
    }

    @Test
    void getLastName() {
        user = new User();
        user.setLastName("igoranskiy");
        assertEquals("igoranskiy", user.getLastName());
    }

    @Test
    void getRoleId() {
        user = new User();
        user.setRoleId(1);
        assertEquals(1, user.getRoleId());
    }
}