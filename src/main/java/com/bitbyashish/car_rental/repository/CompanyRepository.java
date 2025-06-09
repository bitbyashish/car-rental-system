package com.bitbyashish.car_rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bitbyashish.car_rental.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
