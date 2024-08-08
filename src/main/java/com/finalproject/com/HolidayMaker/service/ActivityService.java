package com.finalproject.com.HolidayMaker.service;
import com.finalproject.com.HolidayMaker.dto.ActivityDTO;
import com.finalproject.com.HolidayMaker.model.Activity;
import com.finalproject.com.HolidayMaker.model.Place;
import com.finalproject.com.HolidayMaker.model.User;
import com.finalproject.com.HolidayMaker.repository.ActivityRepository;
import com.finalproject.com.HolidayMaker.repository.PlaceRepository;
import com.finalproject.com.HolidayMaker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private UserRepository userRepository;

    public List<ActivityDTO> findAllActivities() {
        return activityRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public ActivityDTO findActivityById(Long id) {
        return activityRepository.findById(id).map(this::toDto).orElse(null);
    }

    public ActivityDTO createActivity(ActivityDTO activityDTO) {
        Activity activity = toEntity(activityDTO);
        return toDto(activityRepository.save(activity));
    }

    public ActivityDTO updateActivity(Long id, ActivityDTO activityDTO) {
        return activityRepository.findById(id).map(existingActivity -> {
            existingActivity.setName(activityDTO.getName());
            existingActivity.setDescription(activityDTO.getDescription());

            if (activityDTO.getPlaceId() != null) {
                Optional<Place> place = placeRepository.findById(activityDTO.getPlaceId());
                place.ifPresent(existingActivity::setPlace);
            }

            if (activityDTO.getUserId() != null) {
                Optional<User> user = userRepository.findById(activityDTO.getUserId());
                user.ifPresent(existingActivity::setUser);
            }

            return toDto(activityRepository.save(existingActivity));
        }).orElse(null);
    }

    public boolean deleteActivity(Long id) {
        try {
            activityRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private ActivityDTO toDto(Activity activity) {
        return new ActivityDTO(
                activity.getId(),
                activity.getName(),
                activity.getDescription(),
                activity.getPlace() != null ? activity.getPlace().getId() : null,
                activity.getUser() != null ? activity.getUser().getId() : null
        );
    }

    private Activity toEntity(ActivityDTO activityDTO) {
        Activity activity = new Activity();
        activity.setId(activityDTO.getId());
        activity.setName(activityDTO.getName());
        activity.setDescription(activityDTO.getDescription());

        if (activityDTO.getPlaceId() != null) {
            Optional<Place> place = placeRepository.findById(activityDTO.getPlaceId());
            place.ifPresent(activity::setPlace);
        }

        if (activityDTO.getUserId() != null) {
            Optional<User> user = userRepository.findById(activityDTO.getUserId());
            user.ifPresent(activity::setUser);
        }

        return activity;
    }
}
