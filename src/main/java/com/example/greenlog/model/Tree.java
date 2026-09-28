package com.example.greenlog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
public class Tree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String species;

    @NotBlank
    private String location;

    private LocalDate datePlanted;

    private LocalDate nextCheckInDate;

    @ManyToOne
    private PlantationDrive plantationDrive;

    @ManyToOne
    private Volunteer volunteer;

    public Tree() {
    }

    public Long getId() {
        return id;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDatePlanted() {
        return datePlanted;
    }

    public void setDatePlanted(LocalDate datePlanted) {
        this.datePlanted = datePlanted;
    }

    public LocalDate getNextCheckInDate() {
        return nextCheckInDate;
    }

    public void setNextCheckInDate(LocalDate nextCheckInDate) {
        this.nextCheckInDate = nextCheckInDate;
    }

    public PlantationDrive getPlantationDrive() {
        return plantationDrive;
    }

    public void setPlantationDrive(PlantationDrive plantationDrive) {
        this.plantationDrive = plantationDrive;
    }

    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }
}