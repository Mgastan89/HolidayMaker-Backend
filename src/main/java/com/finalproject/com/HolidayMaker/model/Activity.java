package com.finalproject.com.HolidayMaker.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
    @Table(name = "Activities")

    public class Activity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "place_id", nullable = false)
        @JsonManagedReference
        private Place place;

        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        private User user;

        @Column(nullable = false)
        private String name;

        @Column
        private String description;

    public Activity() {
    }

    public Activity( String name, String description, Place place, User user) {
        this.name = name;
        this.description = description;
        this.place = place;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", place=" + place +
                ", user=" + user +
                '}';
    }
}



