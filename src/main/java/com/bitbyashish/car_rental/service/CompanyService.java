package com.bitbyashish.car_rental.service;

import org.springframework.stereotype.Service;

import com.bitbyashish.car_rental.entity.CarVariant;
import com.bitbyashish.car_rental.entity.Company;
import com.bitbyashish.car_rental.repository.CarVariantRepository;
import com.bitbyashish.car_rental.repository.CompanyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    private final CarVariantRepository carVariantRepository;

    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    public CarVariant addCarVariant(Long companyId, CarVariant carVariant) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found"));
        carVariant.setCompany(company);
        return carVariantRepository.save(carVariant);
    }
}
