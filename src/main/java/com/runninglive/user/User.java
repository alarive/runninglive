package com.runninglive.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.runninglive.competition.Competition;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * User: alarive
 */
@Entity
public class User {

    public User() { }

    public User(String username, String password, int height, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.height = height;
        this.roles = roles;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotEmpty
    private String username;

    @JsonIgnore
    @NotEmpty
    private String password;

    private int height;

    @OneToMany(mappedBy = "organizer")
    private Set<Competition> competitions = new HashSet<Competition>();

    @ManyToMany(mappedBy = "participants")
    private Set<Competition> participations = new HashSet<Competition>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id")})
    private Set<Role> roles = new HashSet<Role>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(Set<Competition> competitions) {
        this.competitions = competitions;
    }

    public Set<Competition> getParticipations() {
        return participations;
    }

    public void setParticipations(Set<Competition> participations) {
        this.participations = participations;
    }
}