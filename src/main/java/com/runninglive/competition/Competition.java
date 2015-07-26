package com.runninglive.competition;

import com.runninglive.user.User;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * User: alarive
 */
@Entity
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String name;

    private LocalDateTime dateAndTime;

    private String place;

    @ManyToOne
    private User organizer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "competition", cascade= CascadeType.ALL)
    private Set<Participation> participations = new HashSet<Participation>();

    public Competition() { }

    public Competition(String name, LocalDateTime dateAndTime) {
        this.name = name;
        this.dateAndTime = dateAndTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    public Set<Participation> getParticipations() {
        return participations;
    }

    public void setParticipants(Set<Participation> participations) {
        this.participations = participations;
    }
}
