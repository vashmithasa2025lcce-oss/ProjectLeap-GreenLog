package com.example.greenlog.repository;

import com.example.greenlog.model.PlantationDrive;
import com.example.greenlog.model.Tree;
import com.example.greenlog.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.time.LocalDate;

public interface TreeRepository
        extends JpaRepository<Tree, Long> {

    List<Tree> findByPlantationDrive(
            PlantationDrive plantationDrive);

    List<Tree> findByNextCheckInDateLessThanEqual(
            LocalDate date);
    long countByVolunteer(Volunteer volunteer);
}

