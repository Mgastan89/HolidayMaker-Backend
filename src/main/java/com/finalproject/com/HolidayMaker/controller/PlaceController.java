package com.finalproject.com.HolidayMaker.controller;
import com.finalproject.com.HolidayMaker.dto.PlaceDTO;
import com.finalproject.com.HolidayMaker.model.Place;
import com.finalproject.com.HolidayMaker.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
    @RequestMapping("/api/places")
    public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @GetMapping
    public ResponseEntity<List<PlaceDTO>> getAllPlaces() {
        List<Place> places = placeService.findAllPlaces();
        List<PlaceDTO> placeDTOs = places.stream()
                .map(place -> new PlaceDTO(place.getId(), place.getName(), place.getDescription()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(placeDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaceDTO> getPlaceById(@PathVariable Long id) {
        Optional<Place> optionalPlace = placeService.findPlaceById(id);
        return optionalPlace.map(place -> ResponseEntity.ok(new PlaceDTO(place.getId(), place.getName(), place.getDescription())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PlaceDTO> createPlace(@RequestBody PlaceDTO placeDTO) {
        Place place = new Place(placeDTO.getName(), placeDTO.getDescription());
        Place createdPlace = placeService.savePlace(place);
        return ResponseEntity.ok(new PlaceDTO(createdPlace.getId(), createdPlace.getName(), createdPlace.getDescription()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlaceDTO> updatePlace(@PathVariable Long id, @RequestBody PlaceDTO placeDTO) {
        Place placeDetails = new Place(placeDTO.getName(), placeDTO.getDescription());
        Place updatedPlace = placeService.updatePlace(id, placeDetails);
        if (updatedPlace != null) {
            return ResponseEntity.ok(new PlaceDTO(updatedPlace.getId(), updatedPlace.getName(), updatedPlace.getDescription()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlace(@PathVariable Long id) {
        placeService.deletePlace(id);
        return ResponseEntity.noContent().build();
    }
}

