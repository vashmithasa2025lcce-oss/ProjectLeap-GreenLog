package com.example.greenlog.service;

import com.example.greenlog.model.PlantationDrive;
import com.example.greenlog.model.Tree;
import com.example.greenlog.model.Volunteer;
import com.example.greenlog.repository.PlantationDriveRepository;
import com.example.greenlog.repository.TreeRepository;
import com.example.greenlog.repository.VolunteerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeService {

    private final TreeRepository treeRepository;
    private final PlantationDriveRepository driveRepository;
    private final VolunteerRepository volunteerRepository;

    public TreeService(
            TreeRepository treeRepository,
            PlantationDriveRepository driveRepository,
            VolunteerRepository volunteerRepository) {

        this.treeRepository = treeRepository;
        this.driveRepository = driveRepository;
        this.volunteerRepository = volunteerRepository;
    }

    public Tree createTree(
            Tree tree,
            Long driveId,
            Long volunteerId) {

        PlantationDrive drive =
                driveRepository.findById(driveId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Plantation drive not found"));

        Volunteer volunteer =
                volunteerRepository.findById(volunteerId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Volunteer not found"));

        tree.setPlantationDrive(drive);
        tree.setVolunteer(volunteer);

        return treeRepository.save(tree);
    }

    public List<Tree> getAllTrees() {
        return treeRepository.findAll();
    }

    public Tree getTreeById(Long id) {
        return treeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Tree not found"));
    }

    public void deleteTree(Long id) {
        treeRepository.deleteById(id);
    }
}