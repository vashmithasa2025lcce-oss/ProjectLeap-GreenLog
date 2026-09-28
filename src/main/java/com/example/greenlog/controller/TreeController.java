package com.example.greenlog.controller;

import com.example.greenlog.model.Tree;
import com.example.greenlog.service.TreeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trees")
public class TreeController {

    private final TreeService treeService;

    public TreeController(TreeService treeService) {
        this.treeService = treeService;
    }

    @PostMapping
    public Tree createTree(
            @Valid @RequestBody Tree tree,
            @RequestParam Long driveId,
            @RequestParam Long volunteerId) {

        return treeService.createTree(
                tree,
                driveId,
                volunteerId);
    }

    @GetMapping
    public List<Tree> getAllTrees() {
        return treeService.getAllTrees();
    }

    @GetMapping("/{id}")
    public Tree getTreeById(@PathVariable Long id) {
        return treeService.getTreeById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteTree(@PathVariable Long id) {

        treeService.deleteTree(id);

        return "Tree deleted successfully";
    }

    @GetMapping("/reports/survival-rate/{driveId}")
    public double getSurvivalRate(
            @PathVariable Long driveId) {

        return treeService.getSurvivalRate(driveId);
    }
    @GetMapping("/reports/due-for-checkin")
    public List<Tree> getTreesDueForCheckIn() {

        return treeService.getTreesDueForCheckIn();
    }
}