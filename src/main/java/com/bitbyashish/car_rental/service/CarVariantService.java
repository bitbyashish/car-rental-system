package com.bitbyashish.car_rental.service;

import java.util.List;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.bitbyashish.car_rental.entity.CarVariant;
import com.bitbyashish.car_rental.entity.Company;
import com.bitbyashish.car_rental.repository.CarVariantRepository;
import com.bitbyashish.car_rental.repository.CompanyRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CarVariantService {
    private final CarVariantRepository carVariantRepository;
    private final CompanyRepository companyRepository;

    public CarVariant addCarVariant(Long companyId, CarVariant carVariant) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + companyId));
        
        carVariant.setCompany(company);
        return carVariantRepository.save(carVariant);
    }

    public List<CarVariant> getAvailableCars() {
        return carVariantRepository.findByAvailableTrue();
    }

    public CarVariant updateCarAvailability(Long carId, boolean available) {
        CarVariant car = carVariantRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with id: " + carId));
        
        car.setAvailable(available);
        return carVariantRepository.save(car);
    }

    // other car variant methods
}
