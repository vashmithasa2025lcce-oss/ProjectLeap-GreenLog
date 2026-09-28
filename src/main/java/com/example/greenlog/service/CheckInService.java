package com.example.greenlog.service;

import com.example.greenlog.model.CheckIn;
import com.example.greenlog.model.Tree;
import com.example.greenlog.repository.CheckInRepository;
import com.example.greenlog.repository.TreeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CheckInService {

    private final CheckInRepository checkInRepository;
    private final TreeRepository treeRepository;

    public CheckInService(CheckInRepository checkInRepository,
                          TreeRepository treeRepository) {
        this.checkInRepository = checkInRepository;
        this.treeRepository = treeRepository;
    }

    public CheckIn addCheckIn(Long treeId, CheckIn checkIn) {

        Tree tree = treeRepository.findById(treeId)
                .orElseThrow(() ->
                        new RuntimeException("Tree not found"));

        // Business rule
        if (checkInRepository.existsByTreeAndStatus(
                tree,
                CheckIn.Status.DEAD)) {

            throw new RuntimeException(
                    "This tree is already marked as DEAD");
        }

        checkIn.setTree(tree);
        checkIn.setCheckInDate(LocalDate.now());

        return checkInRepository.save(checkIn);
    }

    public List<CheckIn> getAllCheckIns() {
        return checkInRepository.findAll();
    }
}
