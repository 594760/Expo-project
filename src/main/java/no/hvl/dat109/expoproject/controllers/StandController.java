package no.hvl.dat109.expoproject.controllers;

import no.hvl.dat109.expoproject.database.StandService;
import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.Stand;
import no.hvl.dat109.expoproject.interfaces.controllers.IStandController;
import no.hvl.dat109.expoproject.interfaces.database.IStandService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stand")
public class StandController implements IStandController {

    private final IStandService ss;

    public StandController(StandService ss) {
        this.ss = ss;
    }

    @Override
    @GetMapping
    public Stand getStand(@RequestParam(defaultValue = "0") int id) {
        if (id < 1) {
            return null;
        }
        return ss.getStand(id);
    }

    @Override
    @GetMapping("/all")
    public List<Stand> getAllStands(@RequestParam(defaultValue = "0") int eventID) {
        if (eventID < 1) {
            return null;
        }
        return ss.getAllStands(eventID);
    }

    @Override
    @PostMapping("/update")
    public Stand postUpdateStand(@RequestBody Stand stand) {
        if (stand == null) {
            throw new IllegalArgumentException("Stand cannot be null");
        }

        ss.addStand(stand);

        return null; // TODO
    }

    @Override
    @PostMapping("/add")
    public Stand postAddStand(@RequestBody Stand stand) {
        if (stand == null) {
            throw new IllegalArgumentException("Stand cannot be null");
        }

        ss.addStand(stand);

        return null; // TODO
    }

    @Override
    @DeleteMapping
    public Boolean removeStand(@RequestBody int standID) {
        if (standID < 1) {
            return false;
        }
        return ss.removeStand(standID) != null;
    }
}