package com.bitbyashish.car_rental.service;

import org.springframework.stereotype.Service;

import com.bitbyashish.car_rental.entity.Company;
import com.bitbyashish.car_rental.repository.CompanyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    // other company-related methods
}
