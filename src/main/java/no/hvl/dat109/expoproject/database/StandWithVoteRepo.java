package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Stand;
import no.hvl.dat109.expoproject.entities.StandWithVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface StandWithVoteRepo extends JpaRepository<StandWithVote, Stand> {

    /**
     * finds all stands from a given event, and returns a list of containing the stands and their current vote count.
     *
     * @param eventId
     * @return a list of all stands with votes
     */
    @Query("SELECT sv " +
            "FROM StandWithVote sv " +
            "JOIN Stand s ON sv.id = s.id " +
            "JOIN Event e ON s.eventID = e.id " +
            "WHERE e.id = ?1")
    List<StandWithVote> findAllByEventId(int eventId);

}
