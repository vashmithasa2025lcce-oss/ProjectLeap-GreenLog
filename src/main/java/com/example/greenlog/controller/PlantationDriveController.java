package com.example.greenlog.controller;

import com.example.greenlog.model.PlantationDrive;
import com.example.greenlog.service.PlantationDriveService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drives")
public class PlantationDriveController {

    private final PlantationDriveService service;

    public PlantationDriveController(
            PlantationDriveService service) {
        this.service = service;
    }

    @PostMapping
    public PlantationDrive createDrive(
            @Valid @RequestBody PlantationDrive drive) {

        return service.createDrive(drive);
    }

    @GetMapping
    public List<PlantationDrive> getAllDrives() {
        return service.getAllDrives();
    }

    @GetMapping("/{id}")
    public PlantationDrive getDriveById(
            @PathVariable Long id) {

        return service.getDriveById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteDrive(@PathVariable Long id) {

        service.deleteDrive(id);

        return "Plantation drive deleted successfully";
    }
}