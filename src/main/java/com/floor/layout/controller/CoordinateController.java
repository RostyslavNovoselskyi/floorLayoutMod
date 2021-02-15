package com.floor.layout.controller;

import com.floor.layout.entity.Coordinate;
import com.floor.layout.repository.CoordinateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import static com.floor.layout.constatns.DBConstant.COORDINATE;
import java.util.List;

@RestController
//@RequestMapping(COORDINATE)
public class CoordinateController {
    @Autowired
    private CoordinateRepository coordinateRepository;

    @GetMapping(COORDINATE)
    public List<Coordinate> findCoordinate(){
        return coordinateRepository.findAll();
    }

    @PostMapping(COORDINATE)
    public Coordinate save(@RequestBody Coordinate coordinate){
        return  coordinateRepository.save(coordinate);
    }

}
