package com.finalproject.com.HolidayMaker.controller;
import com.finalproject.com.HolidayMaker.dto.ActivityDTO;
import com.finalproject.com.HolidayMaker.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping
    public ResponseEntity<List<ActivityDTO>> getAllActivities() {
        List<ActivityDTO> activities = activityService.findAllActivities();
        return ResponseEntity.ok(activities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityDTO> getActivityById(@PathVariable Long id) {
        ActivityDTO activityDTO = activityService.findActivityById(id);
        return activityDTO != null ? ResponseEntity.ok(activityDTO) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ActivityDTO> createActivity(@RequestBody ActivityDTO activityDTO) {
        ActivityDTO newActivity = activityService.createActivity(activityDTO);
        return ResponseEntity.ok(newActivity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityDTO> updateActivity(@PathVariable Long id, @RequestBody ActivityDTO activityDTO) {
        ActivityDTO updatedActivity = activityService.updateActivity(id, activityDTO);
        return updatedActivity != null ? ResponseEntity.ok(updatedActivity) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        boolean isDeleted = activityService.deleteActivity(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
