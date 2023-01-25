package com.irinayanushkevich.restapi.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users", schema = "rest")
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_name")
    private String name;
    @OneToMany(mappedBy = "id", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Event> events;

    public User() {
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(String name) {
        this.name = name;
    }

    public User(Integer id, String name, List<Event> events) {
        this.id = id;
        this.name = name;
        this.events = events;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        User u = (User) obj;
        if (name == null) {
            if (u.name != null) {
                return false;
            }
        } else if (!name.equals(u.name)) {
            return false;
        }
        if (events == null) {
            if (u.events != null) {
                return false;
            }
        } else if (!events.equals(u.events)) {
            return false;
        }
        return id.equals(u.id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((events == null) ? 0 : events.hashCode());
        result = prime * result + id;
        return result;
    }

    @Override
    public String toString() {
        return "User id: " + id + ", name: " + name;
    }
}
