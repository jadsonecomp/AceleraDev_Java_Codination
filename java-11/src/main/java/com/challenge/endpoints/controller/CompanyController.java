package com.challenge.endpoints.controller;

import com.challenge.endpoints.controller.advice.ResourceNotFoundException;
import com.challenge.entity.Company;
import com.challenge.service.impl.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/{id}")
    public ResponseEntity<Company> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<Company>(this.companyService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company")), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Company>> findByAccelerationIdOrByUserId(
            @RequestParam(required = false, name = "accelerationId") Long accelerationId,
            @RequestParam(required = false, name = "userId") Long userId) {

        return (accelerationId != null) ? ResponseEntity.ok(this.companyService.findByAccelerationId(accelerationId)) :
                (userId != null) ? ResponseEntity.ok(this.companyService.findByUserId(userId))
                : ResponseEntity.ok(Collections.emptyList());
    }

}
