package com.example.fetelix.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "flag_html")
    private String flag;

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getFlag() {
        return this.flag;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
