package com.project1.realestate.controller;

import com.project1.realestate.converter.FloorConverter;
import com.project1.realestate.dto.FloorDto;
import com.project1.realestate.model.Floor;
import com.project1.realestate.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/floors")
public class FloorController {

    private final FloorService floorService;
    private final FloorConverter floorConverter;

    @Autowired
    public FloorController(FloorService floorService,
                           FloorConverter floorConverter) {
        this.floorService = floorService;
        this.floorConverter = floorConverter;
    }

    @GetMapping
    public ResponseEntity<Set<FloorDto>> findAll() {
        Set<FloorDto> floorDtos = new HashSet<>();
        Set<Floor> floors = floorService.findAll();

        for(Floor floor : floors) {
            FloorDto floorDto = floorConverter.toFloorDto(floor);
            floorDtos.add(floorDto);
        }

        return ResponseEntity.ok(floorDtos);
    }
}
