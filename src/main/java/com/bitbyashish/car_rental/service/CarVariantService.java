package com.bitbyashish.car_rental.service;

import org.springframework.stereotype.Service;

import com.bitbyashish.car_rental.entity.CarVariant;
import com.bitbyashish.car_rental.entity.Company;
import com.bitbyashish.car_rental.repository.CarVariantRepository;
import com.bitbyashish.car_rental.repository.CompanyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarVariantService {
    private final CarVariantRepository carVariantRepository;
    private final CompanyRepository companyRepository;

    public CarVariant addCarVariant(Long companyId, CarVariant carVariant) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found"));
        carVariant.setCompany(company);
        return carVariantRepository.save(carVariant);
    }

    // other car variant methods
}
