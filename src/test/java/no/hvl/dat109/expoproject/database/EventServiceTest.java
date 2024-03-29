package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.User;
import no.hvl.dat109.expoproject.entities.UserEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @InjectMocks
    private EventService eventService;
    @Mock
    private EventRepo eventRepo;
    @Mock
    private UserEventRepo userEventRepo;
    @Mock
    private UserService us;
    private Event event1, event2, event3, eventNull;
    private User user1, user2;
    private UserEvent userEvent1, userEvent2;
    private LocalDateTime t1;
    private LocalDateTime t2;

    @BeforeEach
    void setup() {
        t1 = LocalDateTime.of(2000, 1, 1, 0, 0, 1);
        t2 = LocalDateTime.of(2000, 1, 2, 0, 0, 1);
        LocalDateTime t3 = LocalDateTime.of(2000, 1, 3, 0, 0, 1);
        LocalDateTime t4 = LocalDateTime.of(2000, 1, 4, 0, 0, 1);

        event1 = new Event(1, "event1", t1, t3, null);
        event2 = new Event(2, "event2", t2, t4, null);
        event3 = new Event(3, "event3", t1, t4, null);
        eventNull = null;

        user1 = new User("user1", new ArrayList<>());
        user2 = new User("user2", new ArrayList<>());

        userEvent1 = new UserEvent(user1, event1);
        userEvent2 = new UserEvent(user2, event1);

        user1.getUserEvents().add(userEvent1);
        user2.getUserEvents().add(userEvent2);
    }

    @Test
    void addEvent() throws Exception {
        when(eventRepo.save(event1)).thenReturn(event1);
        eventService.addEvent(event1);
        when(eventRepo.findById(1)).thenReturn(event1);
        assertEquals(1, eventRepo.findById(1).getId());
    }

    @Test
    void addNullEvent() {
        Exception exception = assertThrows(NullPointerException.class, () -> eventService.addEvent(eventNull));
        String expectedMessage = "The event cannot be null";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /* TODO: Er ikke mulig å teste foreløpig
    @Test
    void addAlreadyExistingEvent() throws Exception {
        when(eventRepo.save(event1)).thenReturn(event1);
        eventService.addEvent(event1);
        when(eventRepo.findById(1)).thenReturn(event1);

        Exception exception = assertThrows(Exception.class, () -> eventService.addEvent(event1));
        String expectedMessage = "Event already exists";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
     */

    @Test
    void updateEvent() throws Exception {
        when(eventRepo.save(event2)).thenReturn(event2);
        eventService.addEvent(event2);
        when(eventRepo.findById(2)).thenReturn(event2);
        assertEquals(event2, eventRepo.findById(2));
    }

    @Test
    void updateNullEvent() {
        Exception exception = assertThrows(NullPointerException.class, () -> eventService.addEvent(eventNull));
        String expectedMessage = "The event cannot be null";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void removeNotExistingEvent() {
        when(eventRepo.deleteById(2)).thenReturn(null);
        assertNull(eventService.removeEvent(2));
    }

    @Test
    void RemoveEvent() {
        when(eventRepo.deleteById(3)).thenReturn(event3);
        assertEquals(event3, eventService.removeEvent(3));
    }

    @Test
    void findEventById() {
        when(eventRepo.findById(1)).thenReturn(event1);
        assertEquals(event1, eventService.findEventById(1));
    }

    @Test
    void isOpenEventOpen() throws Exception {
        when(eventRepo.save(event3)).thenReturn(event3);
        eventService.addEvent(event3);
        when(eventRepo.findById(3)).thenReturn(event3);
        assertTrue(eventService.isOpenSetNow(3, t2));
    }

    @Test
    void isOpenEventClosed() throws Exception {
        when(eventRepo.save(event2)).thenReturn(event2);
        eventService.addEvent(event2);
        when(eventRepo.findById(2)).thenReturn(event2);
        assertFalse(eventService.isOpenSetNow(2, t1));
    }

    @Test
    void getAllUsersInEvent() {
        when(userEventRepo.findAllByEvent(event1)).thenReturn(List.of(userEvent1, userEvent2));
        when(eventRepo.findById(1)).thenReturn(event1);
        List<User> actualUserList = eventService.getAllUsersInEvent(1);
        assertTrue(actualUserList.contains(user1));
        assertTrue(actualUserList.contains(user2));
    }

    @Test
    void testGetEventsByUsername() {
        when(userEventRepo.findAllByUser(user1)).thenReturn(user1.getUserEvents());
        when(us.findUser("user1")).thenReturn(user1);
        assertEquals(user1.getUserEvents().size(), eventService.getEventsForUsername("user1").size());
    }

    @Test
    void findEventsToNullUser() {
        Exception exception = assertThrows(NullPointerException.class, () -> eventService.getEventsForUsername("user5"));
        String message = "The user does not exits";
        assertEquals(message, exception.getMessage());
    }
}