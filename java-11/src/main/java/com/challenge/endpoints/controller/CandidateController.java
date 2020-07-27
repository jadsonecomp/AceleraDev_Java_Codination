package com.challenge.endpoints.controller;

import com.challenge.dto.CandidateDTO;
import com.challenge.mappers.CandidateMapper;
import com.challenge.service.impl.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private CandidateMapper candidateMapper;

    @GetMapping("/{userId}/{accelerationId}/{companyId}")
    public ResponseEntity<CandidateDTO> findById(@PathVariable("userId") Long userId,
                                              @PathVariable("companyId") Long companyId,
                                              @PathVariable("accelerationId") Long accelerationId) {
        return new ResponseEntity<CandidateDTO>(this.candidateMapper.map(this.candidateService.findById(userId, companyId, accelerationId).get()), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CandidateDTO>> findByCompanyId(@RequestParam(required = false, name = "companyId") Long companyId,
                                                              @RequestParam(required = false, name = "accelerationId") Long accelerationId) {
        return companyId != null ? ResponseEntity.ok(this.candidateMapper.map(this.candidateService.findByCompanyId(companyId))) :
                accelerationId != null ? ResponseEntity.ok(this.candidateMapper.map(this.candidateService.findByAccelerationId(accelerationId))) :
                        ResponseEntity.ok(Collections.emptyList());
    }

}
