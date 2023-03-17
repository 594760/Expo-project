package no.hvl.dat109.expoproject.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "expo")
public class Stand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String image;
    private String url;

    @ManyToOne
    @JoinColumn(name = "event")
    private Event event;

    @OneToMany(mappedBy = "stand")
    private List<Vote> votes;

    @OneToMany(mappedBy = "stand")
    private List<Exhibitor> exhibitors;

    @OneToOne
    @JoinColumn(name = "responsible")
    private User responsible;

    public Stand(int id, String name, String description, String image, String url, Event event) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.url = url;
        this.event = event;
    }
    public Stand(int id, String name, Event event){
        this.id = id;
        this.name = name;
        this.event = event;
    }

    public Stand() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stand stand = (Stand) o;

        return id == stand.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public List<Exhibitor> getExhibitors() {
        return exhibitors;
    }

    public void setExhibitors(List<Exhibitor> exhibitors) {
        this.exhibitors = exhibitors;
    }

    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User responsible) {
        this.responsible = responsible;
    }
}
