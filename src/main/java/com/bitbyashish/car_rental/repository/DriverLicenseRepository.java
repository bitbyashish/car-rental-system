package com.bitbyashish.car_rental.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bitbyashish.car_rental.entity.DriverLicense;

public interface DriverLicenseRepository extends JpaRepository<DriverLicense, Long> {
    Optional<DriverLicense> findByLicenseNumber(String licenseNumber);

    List<DriverLicense> findByVerified(boolean verified);
}