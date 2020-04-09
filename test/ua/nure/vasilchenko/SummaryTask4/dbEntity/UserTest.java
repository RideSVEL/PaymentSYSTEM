package ua.nure.vasilchenko.SummaryTask4.dbEntity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.nure.vasilchenko.SummaryTask4.db.entity.User;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User user;

    @BeforeEach
    void init() {
        user = new User();
    }

    @Test
    void checkConstructor() {
        assertNotNull(user);
    }

    @Test
    void getActivityId() {
        user.setActivityId(1);
        assertEquals(1, user.getActivityId());
    }

    @Test
    void getLogin() {
        user.setLogin("ridesvel");
        assertEquals("ridesvel", user.getLogin());
    }

    @Test
    void getPassword() {
        user.setPassword("trulala");
        assertEquals("trulala", user.getPassword());
    }

    @Test
    void getFirstName() {
        user.setFirstName("Igorek");
        assertEquals("Igorek", user.getFirstName());
    }

    @Test
    void getLastName() {
        user.setLastName("igoranskiy");
        assertEquals("igoranskiy", user.getLastName());
    }

    @Test
    void getRoleId() {
        user.setRoleId(1);
        assertEquals(1, user.getRoleId());
    }

}