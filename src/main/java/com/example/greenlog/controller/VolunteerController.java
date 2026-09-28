package com.example.greenlog.controller;

import com.example.greenlog.model.Volunteer;
import com.example.greenlog.service.VolunteerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/volunteers")
public class VolunteerController {

    private final VolunteerService volunteerService;

    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @PostMapping
    public Volunteer createVolunteer(
            @Valid @RequestBody Volunteer volunteer) {

        return volunteerService.createVolunteer(volunteer);
    }

    @GetMapping
    public List<Volunteer> getAllVolunteers() {
        return volunteerService.getAllVolunteers();
    }

    @GetMapping("/{id}")
    public Volunteer getVolunteerById(@PathVariable Long id) {
        return volunteerService.getVolunteerById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteVolunteer(@PathVariable Long id) {
        volunteerService.deleteVolunteer(id);
        return "Volunteer deleted successfully";
    }
    @GetMapping("/leaderboard")
    public List<String> getLeaderboard() {
        return volunteerService.getLeaderboard();
    }
}