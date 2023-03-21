package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Stand;
import no.hvl.dat109.expoproject.entities.StandWithVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface StandWithVoteRepo extends JpaRepository<StandWithVote, Stand> {

    @Query("SELECT s FROM StandWithVote s JOIN Event e ON e.id = ?1")
    List<StandWithVote> findAllByEventId(int eventId);

}
