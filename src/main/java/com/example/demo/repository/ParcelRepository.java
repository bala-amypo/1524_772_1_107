package com.example.demo.repository;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParcelRepository extends JpaRepository<Parcel, Long> {

    Optional<Parcel> findByTrackingNumber(String trackingNumber);

    boolean existsByTrackingNumber(String trackingNumber);
}

