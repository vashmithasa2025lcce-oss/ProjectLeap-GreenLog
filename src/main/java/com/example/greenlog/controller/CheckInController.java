package com.example.greenlog.controller;

import com.example.greenlog.model.CheckIn;
import com.example.greenlog.service.CheckInService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trees")
public class CheckInController {

    private final CheckInService checkInService;

    public CheckInController(CheckInService checkInService) {
        this.checkInService = checkInService;
    }

    @PostMapping("/{treeId}/checkins")
    public CheckIn addCheckIn(
            @PathVariable Long treeId,
            @Valid @RequestBody CheckIn checkIn) {

        return checkInService.addCheckIn(
                treeId,
                checkIn);
    }

    @GetMapping("/checkins")
    public List<CheckIn> getAllCheckIns() {
        return checkInService.getAllCheckIns();
    }
}
