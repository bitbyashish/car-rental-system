package com.bitbyashish.car_rental.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitbyashish.car_rental.entity.CarVariant;
import com.bitbyashish.car_rental.service.CarVariantService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/car-variants")
@RequiredArgsConstructor
public class CarVariantController {
    private final CarVariantService carVariantService;
    
    @PostMapping("/{companyId}")
    public ResponseEntity<CarVariant> addCarVariant(
            @PathVariable Long companyId, 
            @RequestBody CarVariant carVariant) {
        return ResponseEntity.ok(carVariantService.addCarVariant(companyId, carVariant));
    }
    
    // other endpoints
}
