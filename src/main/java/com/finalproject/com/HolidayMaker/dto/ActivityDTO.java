package com.finalproject.com.HolidayMaker.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ActivityDTO {
    private Long id;
    private String name;
    private String description;
    private Long placeId;
    private Long userId;

    public ActivityDTO() {
    }

    public ActivityDTO(Long id, String name, String description, Long placeId, Long userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.placeId = placeId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
