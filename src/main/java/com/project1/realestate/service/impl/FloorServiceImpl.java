package com.project1.realestate.service.impl;

import com.project1.realestate.exception.DuplicateRecordException;
import com.project1.realestate.exception.ResourceNotFoundException;
import com.project1.realestate.model.Floor;
import com.project1.realestate.repository.FloorRepository;
import com.project1.realestate.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
public class FloorServiceImpl implements FloorService {

    private final FloorRepository floorRepository;

    @Autowired
    public FloorServiceImpl(FloorRepository floorRepository) {
        this.floorRepository = floorRepository;
    }

    @Override
    public Set<Floor> findAll() {
        return new HashSet<>(floorRepository.findAll());
    }

    @Override
    public Floor save(Floor floor) {
        try {
          return floorRepository.save(floor);
        } catch (DataIntegrityViolationException exception) {
            throw new DuplicateRecordException(String.format("Floor with %d does not exists.", floor.getNumber()));
        }
    }

    @Override
    public Floor findByNumber(Integer number) {
        return floorRepository.findByNumber(number)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Floor with %d does not exists.", number)));
    }

    @Override
    public Floor findById(Long id) {
        return floorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Floor with %d does not exists.", id)));
    }

    @Override
    public Floor update(Floor floor, Long id) {
        Floor foundFloor = findById(id);
        Floor updatedFloor = Floor.builder()
                .id(foundFloor.getId())
                .number(floor.getNumber())
                .build();

        return save(updatedFloor);
    }

    @Override
    public void delete(Long id) {
        floorRepository.deleteById(id);
    }

}
