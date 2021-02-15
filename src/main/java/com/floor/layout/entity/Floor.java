package com.floor.layout.entity;

import javax.persistence.*;

import java.util.List;
import java.util.Set;

import static com.floor.layout.constatns.DBConstant.FLOOR_TABLE;

//@Table(name = FLOOR_TABLE)
@Entity
public class Floor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Coordinate> room;

    public Floor() {
    }

    public Floor(List<Coordinate> room) {
        this.room = room;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Coordinate> getRoom() {
        return room;
    }

    public void setRoom(List<Coordinate> room) {
        this.room = room;
    }
}
