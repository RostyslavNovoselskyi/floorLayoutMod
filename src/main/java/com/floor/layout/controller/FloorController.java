package com.floor.layout.controller;

import com.floor.layout.entity.Coordinate;
import com.floor.layout.entity.Floor;
import com.floor.layout.repository.CoordinateRepository;
import com.floor.layout.repository.FloorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static com.floor.layout.constatns.DBConstant.FLOOR;

@RestController
@RequestMapping(FLOOR)
public class FloorController {
    @Autowired
    private FloorRepository floorRepository;
    @Autowired
    CoordinateRepository coordinateRepository;

    private final int MIN_SIZE = 4;
    private final String ILLEGAL = "Illegal, ";
    private final String LEGAL = "Legal room added";
    private final String DIAGONALLY = "as it would result in the wall going diagonally";
    private final String BAD_COORDINATES = "numerous of x coordinates not equal to numerous of y coordinates";
    private final String ILLEGAL_FIRST = "as it only has ";
    private final String ILLEGAL_SECOND = " corners!";
    private final String NOT_CLOCKWISE = " coordinates are not clockwise!";

    @GetMapping
    public List<Floor> findAllFloors(){
        return floorRepository.findAll();
    }

    @PostMapping(consumes = "application/json")
    public String save(@RequestBody Floor floor){
        List<Long> listX = new LinkedList();
        List<Long> listY = new LinkedList();
        String answer = null;
        boolean bool = true;

        long countOfCoordinates = floor.getRoom().stream().count();

        for (int i = 0; i < countOfCoordinates; i++) {
            Coordinate save = coordinateRepository.save(floor.getRoom().get(i));
            if (save.getX() != null && save.getY() != null){
                listX.add(save.getX());
                listY.add(save.getY());
            }
            else if (save.getX() != null) {
                listX.add(save.getX());
            }else if (save.getY() != null) {
                listY.add(save.getY());
            }
        }

        int listSize = listX.size();

        for (int i = 1; i < countOfCoordinates -1; i++) {
            if (listX.size() == listY.size() && listX.size() >= MIN_SIZE){
                if (!listX.get(i).equals(listX.get(i-1)) && listY.get(i).equals(listY.get(i-1))
                    || listX.get(i).equals(listX.get(i-1)) && !listY.get(i).equals(listY.get(i-1))
                    && (listX.get(0).equals(listX.get(listSize-1)) || listY.get(0).equals(listY.get(listSize-1)))
                ){


                    if (listY.get(i) > listY.get(i-1) &&
                            listX.get(i) < listX.get(i+1)
                    ){
                    }
                    else if (listY.get(i) < listY.get(i-1) &&
                            (listX.get(i) < listX.get(i+1) ||
                            listX.get(i) > listX.get(i+1))
                    ){
                    }else if (listX.get(i) > listX.get(i-1) &&
//                            (listY.get(i) < listY.get(i+1) ||
                            listY.get(i) > listY.get(i+1)
                    ){
                    }else if (listX.get(i) < listX.get(i-1) &&
                            listY.get(i) < listY.get(i+1)
//                            listY.get(i) > listY.get(i+1))
                    ){
                    }else {
                        answer = ILLEGAL + NOT_CLOCKWISE;
                        bool = false;
                        break;
                    }

                }
                else {
                    return ILLEGAL + DIAGONALLY;
                }
            }
            else if (listX.size() != listY.size()){
                return ILLEGAL + BAD_COORDINATES;
            }
            else {
                switch (listSize){
                    case 2: answer = ILLEGAL + ILLEGAL_FIRST + listX.size() + ILLEGAL_SECOND;
                        break;
                    case 3: answer = ILLEGAL + ILLEGAL_FIRST + listX.size() + ILLEGAL_SECOND;
                        break;
                    default:
                        break;
                }
            }
        }

        if (bool) {
            floorRepository.save(floor);
            answer = LEGAL;

        }

        return answer;
}

}
