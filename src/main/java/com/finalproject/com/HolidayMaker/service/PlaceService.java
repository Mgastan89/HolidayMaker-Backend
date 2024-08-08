package com.finalproject.com.HolidayMaker.service;

import com.finalproject.com.HolidayMaker.model.Place;
import com.finalproject.com.HolidayMaker.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

    @Service
    public class PlaceService {

        @Autowired
        private PlaceRepository placeRepository;

        public List<Place> findAllPlaces() {
            return placeRepository.findAll();
        }

        public Optional<Place> findPlaceById(Long id) {
            return placeRepository.findById(id);
        }

        public Place savePlace(Place place) {
            return placeRepository.save(place);
        }

        public Place updatePlace(Long id, Place placeDetails) {
            Optional<Place> optionalPlace = placeRepository.findById(id);
            if (optionalPlace.isPresent()) {
                Place place = optionalPlace.get();
                place.setName(placeDetails.getName());
                place.setDescription(placeDetails.getDescription());
                return placeRepository.save(place);
            }
            return null;
        }

        public void deletePlace(Long id) {
            placeRepository.deleteById(id);
        }
    }


