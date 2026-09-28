package com.example.greenlog.service;

import com.example.greenlog.model.Volunteer;
import com.example.greenlog.repository.VolunteerRepository;
import org.springframework.stereotype.Service;
import com.example.greenlog.repository.TreeRepository;

import java.util.List;

@Service
public class VolunteerService {

    private final VolunteerRepository volunteerRepository;
    private final TreeRepository treeRepository;

    public VolunteerService(VolunteerRepository volunteerRepository,
                            TreeRepository treeRepository) {
        this.volunteerRepository = volunteerRepository;
        this.treeRepository = treeRepository;
    }

    public Volunteer createVolunteer(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.findAll();
    }

    public Volunteer getVolunteerById(Long id) {
        return volunteerRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Volunteer not found"));
    }

    public void deleteVolunteer(Long id) {
        volunteerRepository.deleteById(id);
    }
    public List<String> getLeaderboard() {

        List<Volunteer> volunteers =
                volunteerRepository.findAll();

        return volunteers.stream()
                .map(volunteer -> {

                    long count =
                            treeRepository.countByVolunteer(volunteer);

                    return volunteer.getName()
                            + " - "
                            + count
                            + " trees";
                })
                .sorted((a, b) -> {

                    int countA = Integer.parseInt(
                            a.replaceAll(".*- (\\d+) trees", "$1"));

                    int countB = Integer.parseInt(
                            b.replaceAll(".*- (\\d+) trees", "$1"));

                    return Integer.compare(countB, countA);
                })
                .toList();
    }
}
