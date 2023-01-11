package com.irinayanushkevich.restapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "files", schema = "rest")
public class File {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "file_name")
    private String name;
    @Column(name = "file_path")
    private String filePath;

    public File() {
    }

    public File(Integer id, String name, String filePath) {
        this.id = id;
        this.name = name;
        this.filePath = filePath;
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        File f = (File) obj;
        if (name == null) {
            if (f.name != null) {
                return false;
            }
        } else if (!name.equals(f.name)) {
            return false;
        }
        if (filePath == null) {
            if (f.filePath != null) {
                return false;
            }
        } else if (!filePath.equals(f.filePath)) {
            return false;
        }
        return id.equals(f.id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((filePath == null) ? 0 : filePath.hashCode());
        result = prime * result + id;
        return result;
    }

    @Override
    public String toString() {
        return "\nFile id: " + id + " | name: " + name + " | filePath: " + filePath;
    }
}
