package com.floor.layout.entity;

import javax.persistence.*;

@Entity
public class Coordinate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @Column
    private Long x;
    @Column
    private Long y;

//    @JsonIgnore
    @ManyToOne
    private Floor floor;

    public Coordinate() {
    }

    public Coordinate(Long x, Long y) {
        this.x = x;
        this.y = y;
    }

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }
}
