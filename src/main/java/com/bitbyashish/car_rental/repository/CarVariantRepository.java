package com.bitbyashish.car_rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bitbyashish.car_rental.entity.CarVariant;

public interface CarVariantRepository extends JpaRepository<CarVariant, Long> {
}