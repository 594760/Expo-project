package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.User;
import no.hvl.dat109.expoproject.interfaces.database.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService implements IEventService {
    @Autowired
    private EventRepo er;
    @Override
    public void addEvent(Event event) {

    }

    @Override
    public void UpdateEvent(Event event) {

    }

    @Override
    public void removeEvent(int eventID) {

    }

    @Override
    public boolean isOpen(int eventID) {
        return false;
    }

    @Override
    public Event findEventById(int id) {
       return er.findById(id);
    }

    @Override
    public List<User> getAllUsersInEvent(int eventID) {
        return null;
    }
}
