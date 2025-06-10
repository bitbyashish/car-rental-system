package com.bitbyashish.car_rental.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bitbyashish.car_rental.entity.CarVariant;

public interface CarVariantRepository extends JpaRepository<CarVariant, Long> {
    List<CarVariant> findByAvailableTrue();

    List<CarVariant> findByCompanyId(Long companyId);

    Optional<CarVariant> findByIdAndAvailableTrue(Long id);
}