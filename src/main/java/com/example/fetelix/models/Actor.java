package com.example.fetelix.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tbl_actors")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "birthday", columnDefinition = "DATE")
    private Date birthday;
    @Column(name = "image")
    private String image;
    @Column(name = "place_of_birth")
    private String birthLocation;

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public String getImage() {
        return this.image;
    }

    public String getBirthLocation() {
        return this.birthLocation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setBirthLocation(String birthLocation) {
        this.birthLocation = birthLocation;
    }
}
