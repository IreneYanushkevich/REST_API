package com.irinayanushkevich.restapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "events", schema = "rest")
public class Event {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "file_id")
    private File file;

    public Event() {

    }

    public Event(Integer id, User user, File file) {
        this.id = id;
        this.user = user;
        this.file = file;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Event e = (Event) obj;
        if (user == null) {
            if (e.user != null) {
                return false;
            }
        } else if (!user.equals(e.user)) {
            return false;
        }
        if (file == null) {
            if (e.file != null) {
                return false;
            }
        } else if (!file.equals(e.file)) {
            return false;
        }
        return id.equals(e.id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        result = prime * result + ((file == null) ? 0 : file.hashCode());
        result = prime * result + id;
        return result;
    }

    @Override
    public String toString() {
        return "\nEvent id: " + id + " | user: " + user + " | file: " + file;
    }
}
