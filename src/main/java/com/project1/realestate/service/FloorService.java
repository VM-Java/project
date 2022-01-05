package com.project1.realestate.service;

import com.project1.realestate.model.Floor;

import java.util.Set;


public interface FloorService {

    Floor save(Floor floor);

    Floor findByNumber(Integer number);

    Floor findById(Long id);

    Floor update (Floor floor, Long id);

    void delete(Long id);

    Set<Floor> findAll();
}
