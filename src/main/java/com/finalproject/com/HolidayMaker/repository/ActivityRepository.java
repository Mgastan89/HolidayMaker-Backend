package com.finalproject.com.HolidayMaker.repository;
import com.finalproject.com.HolidayMaker.model.Activity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

}
