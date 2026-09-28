package com.example.greenlog.service;

import com.example.greenlog.model.PlantationDrive;
import com.example.greenlog.repository.PlantationDriveRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantationDriveService {

    private final PlantationDriveRepository repository;

    public PlantationDriveService(
            PlantationDriveRepository repository) {
        this.repository = repository;
    }

    public PlantationDrive createDrive(
            PlantationDrive drive) {

        return repository.save(drive);
    }

    public List<PlantationDrive> getAllDrives() {
        return repository.findAll();
    }

    public PlantationDrive getDriveById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Plantation drive not found"));
    }

    public void deleteDrive(Long id) {
        repository.deleteById(id);
    }
}