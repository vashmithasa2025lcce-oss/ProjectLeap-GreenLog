package com.example.greenlog.repository;

import com.example.greenlog.model.CheckIn;
import com.example.greenlog.model.Tree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckInRepository
        extends JpaRepository<CheckIn, Long> {

    boolean existsByTreeAndStatus(
            Tree tree,
            CheckIn.Status status);
}